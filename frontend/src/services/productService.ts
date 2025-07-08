import { api } from './api';

export const getProducts = async (name?: string) => {
  const res = await api.get('/products', { params: name ? { name } : {} });
  return res.data;
};

export const createProduct = async (product: any) => {
  const res = await api.post('/products', product);
  return res.data;
};

export const updateProduct = async (name: string, product: any) => {
  const res = await api.put('/products', product, { params: { name } });
  return res.data;
};

export const deleteProduct = async (name: string) => {
  await api.delete('/products', { params: { name } });
}; 