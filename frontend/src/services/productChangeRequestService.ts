import { api } from './api';

export const getProductChangeRequests = async (status?: string, employee?: string) => {
  const res = await api.get('/product-change-requests', { params: { status, employee } });
  return res.data;
};

export const createProductChangeRequest = async (request: any) => {
  const res = await api.post('/product-change-requests', request);
  return res.data;
};

export const approveProductChangeRequest = async (idRequest: number, adminUsername: string) => {
  const res = await api.post('/product-change-requests/approve', null, { params: { idRequest, adminUsername } });
  return res.data;
};

export const rejectProductChangeRequest = async (idRequest: number, adminUsername: string) => {
  const res = await api.post('/product-change-requests/reject', null, { params: { idRequest, adminUsername } });
  return res.data;
};

export const deleteProductChangeRequestsByStatus = async (status: string) => {
  await api.delete('/product-change-requests', { params: { status } });
}; 