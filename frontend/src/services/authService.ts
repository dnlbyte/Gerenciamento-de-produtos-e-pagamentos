import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Ajuste conforme necessário

export const login = async (username: string, password: string) => {
  const response = await axios.post(`${API_URL}/auth/login`, { username, password });
  return response.data;
};

export const register = async (data: any) => {
  const response = await axios.post(`${API_URL}/auth/register`, data);
  return response.data;
}; 