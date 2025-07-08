import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register as registerService } from '../services/authService';

const Register: React.FC = () => {
  const [form, setForm] = useState({
    username: '',
    password: '',
    cpf: '',
    birthDate: '',
    address: '',
    gender: '',
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      await registerService(form);
      setSuccess('Registro realizado com sucesso! Faça login.');
      setTimeout(() => navigate('/login'), 1500);
    } catch (err: any) {
      setError('Erro ao registrar. Verifique os dados e tente novamente.');
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: 'auto', padding: 32 }}>
      <h2>Registro de Cliente</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Usuário</label>
          <input name="username" value={form.username} onChange={handleChange} required />
        </div>
        <div>
          <label>Senha</label>
          <input name="password" type="password" value={form.password} onChange={handleChange} required />
        </div>
        <div>
          <label>CPF</label>
          <input name="cpf" value={form.cpf} onChange={handleChange} required />
        </div>
        <div>
          <label>Data de Nascimento</label>
          <input name="birthDate" type="date" value={form.birthDate} onChange={handleChange} required />
        </div>
        <div>
          <label>Endereço</label>
          <input name="address" value={form.address} onChange={handleChange} required />
        </div>
        <div>
          <label>Gênero</label>
          <input name="gender" value={form.gender} onChange={handleChange} />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        {success && <p style={{ color: 'green' }}>{success}</p>}
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Register; 