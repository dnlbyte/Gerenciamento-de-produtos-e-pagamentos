import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

const Dashboard: React.FC = () => {
  const { user } = useAuth();

  return (
    <div style={{ maxWidth: 800, margin: 'auto', padding: 32 }}>
      <h2>Bem-vindo ao Sistema de Gerenciamento</h2>
      {user ? (
        <>
          <p>Olá, <b>{user.username}</b>!</p>
          <p>Tipo de usuário: <b>{user.role || 'Desconhecido'}</b></p>
          <div style={{ marginTop: 32 }}>
            <h3>Funcionalidades disponíveis:</h3>
            <ul>
              <li><Link to="/products">Gerenciar Produtos</Link> - Listar, cadastrar, editar e remover produtos</li>
              <li><Link to="/cart">Carrinho de Compras</Link> - Adicionar itens, editar quantidades e finalizar compra</li>
              <li><Link to="/reports">Relatórios</Link> - Gerar e visualizar relatórios de produtos</li>
              {user.role === 'ADMIN' && (
                <li><Link to="/requests">Solicitações de Alteração</Link> - Aprovar ou rejeitar solicitações de funcionários</li>
              )}
              {user.role === 'FUNCIONARIO' && (
                <li><Link to="/requests">Minhas Solicitações</Link> - Criar e acompanhar solicitações de alteração</li>
              )}
            </ul>
          </div>
        </>
      ) : (
        <p>Você não está autenticado.</p>
      )}
    </div>
  );
};

export default Dashboard; 