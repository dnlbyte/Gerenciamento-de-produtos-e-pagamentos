import { api } from './api';

export const getCart = async (username: string) => {
  const res = await api.get('/cart', { params: { username } });
  return res.data;
};

export const addItemToCart = async (username: string, productName: string, quantity: number) => {
  const res = await api.post('/cart/add', null, { params: { username, productName, quantity } });
  return res.data;
};

export const updateItemQuantity = async (username: string, productName: string, quantity: number) => {
  const res = await api.put('/cart/update', null, { params: { username, productName, quantity } });
  return res.data;
};

export const removeItemFromCart = async (username: string, productName: string) => {
  const res = await api.delete('/cart/remove', { params: { username, productName } });
  return res.data;
};

export const finalizeCart = async (username: string) => {
  const res = await api.post('/cart/finalize', null, { params: { username } });
  return res.data;
}; 