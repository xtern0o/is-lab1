const backendHost = import.meta.env.VITE_BACKEND_HOST

if (!backendHost) {
  throw new Error('не задан VITE_BACKEND_HOST в .env')
}

export const API_BASE_URL = `${backendHost.replace(/\/$/, '')}/api`

export async function apiGet(path: string) {
  const response = await fetch(API_BASE_URL + path)
  if (!response.ok) throw new Error(`HTTP ${response.status}`)
  return response.json()
}

export async function apiPost(path: string, body: object) {
  const response = await fetch(API_BASE_URL + path, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
  if (!response.ok) throw new Error(`HTTP ${response.status}`)
}

export async function apiPut(path: string, body: object) {
  const response = await fetch(API_BASE_URL + path, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
  if (!response.ok) throw new Error(`HTTP ${response.status}`)
}

export async function apiDelete(path: string) {
  const response = await fetch(API_BASE_URL + path, { method: 'DELETE' })
  if (!response.ok) {
    const { detail } = await response.json()
    throw new Error(detail || `HTTP ${response.status}`)
  }
}
