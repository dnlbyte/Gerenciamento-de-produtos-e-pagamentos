import { api } from './api';

export const getPayments = async (method?: string) => {
  const res = await api.get('/payments', { params: method ? { method } : {} });
  return res.data;
};

export const createPayment = async (payment: any) => {
  const res = await api.post('/payments', payment);
  return res.data;
};

export const updatePayment = async (method: string, payment: any) => {
  const res = await api.put('/payments', payment, { params: { method } });
  return res.data;
};

export const deletePayment = async (method: string) => {
  await api.delete('/payments', { params: { method } });
}; 