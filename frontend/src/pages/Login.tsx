import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login as loginService } from '../services/authService';
import { useAuth } from '../contexts/AuthContext';
import { Box, Button, TextField, Typography, Alert, CircularProgress, Paper } from '@mui/material';

const Login: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      const data: any = await loginService(username, password);
      login(data.user, data.token);
      navigate('/dashboard');
    } catch (err: any) {
      setError('Usuário ou senha inválidos');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box display="flex" justifyContent="center" alignItems="center" minHeight="80vh">
      <Paper elevation={3} sx={{ p: 4, width: 350 }}>
        <Typography variant="h5" fontWeight={700} mb={2} align="center">Login</Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Usuário"
            value={username}
            onChange={e => setUsername(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Senha"
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}
          <Box mt={2} display="flex" justifyContent="center">
            <Button type="submit" variant="contained" color="primary" fullWidth disabled={loading}>
              {loading ? <CircularProgress size={24} color="inherit" /> : 'Entrar'}
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
};

export default Login; 