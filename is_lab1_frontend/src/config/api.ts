import { useAuthStore, type AuthTokens } from '@/stores/auth'

const backendHost = import.meta.env.VITE_BACKEND_HOST

if (!backendHost) {
  throw new Error('не задан VITE_BACKEND_HOST в .env')
}

export const API_BASE_URL = `${backendHost.replace(/\/$/, '')}/api`

async function checkResponse(response: Response) {
  if (response.ok) return

  const error = await response.json().catch(() => ({}))
  throw new Error(error.detail || error.message || `HTTP ${response.status}`)
}

async function request(path: string, options: RequestInit = {}) {
  const auth = useAuthStore()
  const headers = new Headers(options.headers)

  if (auth.accessToken && !path.startsWith('/auth/')) {
    headers.set('Authorization', `Bearer ${auth.accessToken}`)
  }

  let response = await fetch(API_BASE_URL + path, { ...options, headers })

  if (response.status === 401 && !path.startsWith('/auth/')) {
    if (auth.refreshToken) {
      const refreshResponse = await fetch(API_BASE_URL + '/auth/refresh', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ refreshToken: auth.refreshToken }),
      })

      if (refreshResponse.ok) {
        const tokens = (await refreshResponse.json()) as AuthTokens
        auth.setTokens(tokens.accessToken, tokens.refreshToken)
        headers.set('Authorization', `Bearer ${tokens.accessToken}`)
        response = await fetch(API_BASE_URL + path, { ...options, headers })
      } else {
        auth.logout()
        window.location.replace('/auth')
      }
    } else {
      auth.logout()
      window.location.replace('/auth')
    }
  }

  await checkResponse(response)
  return response
}

export async function apiGet(path: string) {
  return (await request(path)).json()
}

export async function apiPost(path: string, body: object) {
  const response = await request(path, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
  return response.json()
}

export async function apiPut(path: string, body: object) {
  await request(path, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
}

export async function apiDelete(path: string) {
  await request(path, { method: 'DELETE' })
}
