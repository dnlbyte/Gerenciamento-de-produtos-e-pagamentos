import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

const Header: React.FC = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header style={{ background: '#222', color: '#fff', padding: 16, marginBottom: 32 }}>
      <nav style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        <div>
          <Link to="/dashboard" style={{ color: '#fff', marginRight: 16 }}>Dashboard</Link>
          <Link to="/products" style={{ color: '#fff', marginRight: 16 }}>Produtos</Link>
          <Link to="/cart" style={{ color: '#fff', marginRight: 16 }}>Carrinho</Link>
          <Link to="/reports" style={{ color: '#fff', marginRight: 16 }}>Relatórios</Link>
          <Link to="/requests" style={{ color: '#fff', marginRight: 16 }}>Solicitações</Link>
        </div>
        <div>
          {user && <span style={{ marginRight: 16 }}>Usuário: <b>{user.username}</b></span>}
          {user && <button onClick={handleLogout} style={{ background: '#fff', color: '#222', border: 'none', padding: '6px 12px', borderRadius: 4 }}>Sair</button>}
        </div>
      </nav>
    </header>
  );
};

export default Header; 