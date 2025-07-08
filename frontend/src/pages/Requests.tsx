import React, { useEffect, useState } from 'react';
import {
  getProductChangeRequests,
  createProductChangeRequest,
  approveProductChangeRequest,
  rejectProductChangeRequest,
  deleteProductChangeRequestsByStatus
} from '../services/productChangeRequestService';
import { useAuth } from '../contexts/AuthContext';

const Requests: React.FC = () => {
  const { user } = useAuth();
  const [requests, setRequests] = useState<any[]>([]);
  const [form, setForm] = useState({ requestType: '', productData: '', status: 'PENDENTE', product: null });
  const [searchStatus, setSearchStatus] = useState('');
  const [searchEmployee, setSearchEmployee] = useState('');

  const fetchRequests = async () => {
    const data = await getProductChangeRequests(searchStatus, searchEmployee);
    setRequests(data as any[]);
  };

  useEffect(() => { fetchRequests(); }, [searchStatus, searchEmployee]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createProductChangeRequest({ ...form, employee: user });
    setForm({ requestType: '', productData: '', status: 'PENDENTE', product: null });
    fetchRequests();
  };

  const handleApprove = async (idRequest: number) => {
    await approveProductChangeRequest(idRequest, user.username);
    fetchRequests();
  };

  const handleReject = async (idRequest: number) => {
    await rejectProductChangeRequest(idRequest, user.username);
    fetchRequests();
  };

  const handleDeleteByStatus = async () => {
    await deleteProductChangeRequestsByStatus(searchStatus);
    fetchRequests();
  };

  return (
    <div style={{ maxWidth: 900, margin: 'auto', padding: 32 }}>
      <h2>Solicitações de Alteração de Produto</h2>
      <div style={{ marginBottom: 16 }}>
        <input placeholder="Buscar por status" value={searchStatus} onChange={e => setSearchStatus(e.target.value)} />
        <input placeholder="Buscar por funcionário" value={searchEmployee} onChange={e => setSearchEmployee(e.target.value)} />
        <button onClick={handleDeleteByStatus}>Remover por status</button>
      </div>
      <form onSubmit={handleSubmit} style={{ margin: '16px 0' }}>
        <input name="requestType" placeholder="Tipo de requisição" value={form.requestType} onChange={handleChange} required />
        <input name="productData" placeholder="Dados do produto (JSON)" value={form.productData} onChange={handleChange} required />
        <button type="submit">Solicitar alteração</button>
      </form>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Status</th>
            <th>Funcionário</th>
            <th>Admin</th>
            <th>Produto</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {requests.map((r) => (
            <tr key={r.idRequest}>
              <td>{r.idRequest}</td>
              <td>{r.requestType}</td>
              <td>{r.status}</td>
              <td>{r.employee?.username}</td>
              <td>{r.admin?.username}</td>
              <td>{r.product?.name}</td>
              <td>
                {r.status === 'PENDENTE' && user?.role === 'ADMIN' && (
                  <>
                    <button onClick={() => handleApprove(r.idRequest)}>Aprovar</button>
                    <button onClick={() => handleReject(r.idRequest)}>Rejeitar</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Requests; 