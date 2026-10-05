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

export async function apiGet(path: string) {
  const response = await fetch(API_BASE_URL + path)
  await checkResponse(response)
  return response.json()
}

export async function apiPost(path: string, body: object) {
  const response = await fetch(API_BASE_URL + path, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
  await checkResponse(response)
  return response.json()
}

export async function apiPut(path: string, body: object) {
  const response = await fetch(API_BASE_URL + path, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
  await checkResponse(response)
}

export async function apiDelete(path: string) {
  const response = await fetch(API_BASE_URL + path, { method: 'DELETE' })
  await checkResponse(response)
}
