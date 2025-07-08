import React, { useEffect, useState } from 'react';
import { getProductReports, createProductReport, deleteProductReport } from '../services/reportService';

const Reports: React.FC = () => {
  const [reports, setReports] = useState<any[]>([]);
  const [form, setForm] = useState({ reportType: '', periodStart: '', periodEnd: '', generatedAt: '', user: null });
  const [search, setSearch] = useState('');

  const fetchReports = async () => {
    const data = await getProductReports(search);
    setReports(data as any[]);
  };

  useEffect(() => { fetchReports(); }, [search]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createProductReport(form);
    setForm({ reportType: '', periodStart: '', periodEnd: '', generatedAt: '', user: null });
    fetchReports();
  };

  const handleDelete = async (reportType: string) => {
    await deleteProductReport(reportType);
    fetchReports();
  };

  return (
    <div style={{ maxWidth: 800, margin: 'auto', padding: 32 }}>
      <h2>Relatórios de Produto</h2>
      <input placeholder="Buscar por tipo" value={search} onChange={e => setSearch(e.target.value)} />
      <form onSubmit={handleSubmit} style={{ margin: '16px 0' }}>
        <input name="reportType" placeholder="Tipo de relatório" value={form.reportType} onChange={handleChange} required />
        <input name="periodStart" type="date" placeholder="Início" value={form.periodStart} onChange={handleChange} />
        <input name="periodEnd" type="date" placeholder="Fim" value={form.periodEnd} onChange={handleChange} />
        <button type="submit">Gerar Relatório</button>
      </form>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>Tipo</th>
            <th>Início</th>
            <th>Fim</th>
            <th>Gerado em</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((r) => (
            <tr key={r.idReport}>
              <td>{r.reportType}</td>
              <td>{r.periodStart}</td>
              <td>{r.periodEnd}</td>
              <td>{r.generatedAt}</td>
              <td><button onClick={() => handleDelete(r.reportType)}>Remover</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Reports; 