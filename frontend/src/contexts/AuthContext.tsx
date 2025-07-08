import React, { createContext, useState, useContext, ReactNode } from 'react';

interface AuthContextType {
  user: any;
  token: string | null;
  login: (user: any, token: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  // MOCK: Usuário autenticado para testes
  const [user, setUser] = useState<any>({ username: 'admin', role: 'ADMIN' });
  const [token, setToken] = useState<string | null>('fake-token');

  const login = (userData: any, jwt: string) => {
    setUser(userData);
    setToken(jwt);
    localStorage.setItem('token', jwt);
  };

  const logout = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem('token');
  };

  return (
    <AuthContext.Provider value={{ user, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth deve ser usado dentro de AuthProvider');
  return context;
}; 