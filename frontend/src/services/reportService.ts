import { api } from './api';

export const getProductReports = async (reportType?: string) => {
  const res = await api.get('/product-reports', { params: reportType ? { reportType } : {} });
  return res.data;
};

export const createProductReport = async (report: any) => {
  const res = await api.post('/product-reports', report);
  return res.data;
};

export const deleteProductReport = async (reportType: string) => {
  await api.delete('/product-reports', { params: { reportType } });
}; 