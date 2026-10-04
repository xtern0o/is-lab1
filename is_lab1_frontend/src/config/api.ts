const backendHost = import.meta.env.VITE_BACKEND_HOST

if (!backendHost) {
  throw new Error('Не задан VITE_BACKEND_HOST в .env')
}

export const API_BASE_URL = `${backendHost.replace(/\/$/, '')}/api`
