import React from 'react';
import { AuthProvider, useAuth } from './contexts/AuthContext';
import AppRouter from './routes/AppRouter';
import Header from './components/Header';
import { ThemeProvider, createTheme, CssBaseline } from '@mui/material';

const theme = createTheme({
  palette: {
    mode: 'light',
    primary: { main: '#1976d2' },
    secondary: { main: '#9c27b0' },
    background: { default: '#f4f6fa' },
  },
  shape: { borderRadius: 8 },
  typography: {
    fontFamily: 'Roboto, Arial, sans-serif',
    h2: { fontWeight: 700 },
  },
});

const AppContent: React.FC = () => {
  const { token } = useAuth();
  return (
    <>
      {token && <Header />}
      <AppRouter />
    </>
  );
};

const App: React.FC = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AuthProvider>
        <AppContent />
      </AuthProvider>
    </ThemeProvider>
  );
};

export default App;
