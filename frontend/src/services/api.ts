import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Ajuste conforme necessário

export const api = axios.create({
  baseURL: API_URL,
});

// Interceptor para adicionar token JWT se existir
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers = config.headers || {};
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}); 