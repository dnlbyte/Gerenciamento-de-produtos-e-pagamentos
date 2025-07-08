import React, { useEffect, useState } from 'react';
import { getCart, addItemToCart, updateItemQuantity, removeItemFromCart, finalizeCart } from '../services/cartService';
import { useAuth } from '../contexts/AuthContext';

const Cart: React.FC = () => {
  const { user } = useAuth();
  const [cart, setCart] = useState<any>(null);
  const [productName, setProductName] = useState('');
  const [quantity, setQuantity] = useState(1);
  const [message, setMessage] = useState('');

  const fetchCart = async () => {
    if (user) {
      const data = await getCart(user.username);
      setCart(data);
    }
  };

  useEffect(() => { fetchCart(); }, [user]);

  const handleAdd = async () => {
    if (!productName) return;
    await addItemToCart(user.username, productName, quantity);
    setProductName('');
    setQuantity(1);
    fetchCart();
  };

  const handleUpdate = async (prodName: string, qty: number) => {
    await updateItemQuantity(user.username, prodName, qty);
    fetchCart();
  };

  const handleRemove = async (prodName: string) => {
    await removeItemFromCart(user.username, prodName);
    fetchCart();
  };

  const handleFinalize = async () => {
    await finalizeCart(user.username);
    setMessage('Compra finalizada!');
    fetchCart();
  };

  return (
    <div style={{ maxWidth: 800, margin: 'auto', padding: 32 }}>
      <h2>Carrinho</h2>
      <div>
        <input placeholder="Nome do produto" value={productName} onChange={e => setProductName(e.target.value)} />
        <input type="number" min={1} value={quantity} onChange={e => setQuantity(Number(e.target.value))} />
        <button onClick={handleAdd}>Adicionar</button>
      </div>
      {cart && cart.items && cart.items.length > 0 ? (
        <table style={{ width: '100%', marginTop: 16 }}>
          <thead>
            <tr>
              <th>Produto</th>
              <th>Quantidade</th>
              <th>Preço Unitário</th>
              <th>Total</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {cart.items.map((item: any) => (
              <tr key={item.product.name}>
                <td>{item.product.name}</td>
                <td>
                  <input type="number" min={1} value={item.quantity} onChange={e => handleUpdate(item.product.name, Number(e.target.value))} />
                </td>
                <td>{item.unitPrice}</td>
                <td>{item.unitPrice * item.quantity}</td>
                <td><button onClick={() => handleRemove(item.product.name)}>Remover</button></td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Carrinho vazio.</p>
      )}
      {cart && cart.items && cart.items.length > 0 && (
        <div style={{ marginTop: 16 }}>
          <button onClick={handleFinalize}>Finalizar Compra</button>
        </div>
      )}
      {message && <p style={{ color: 'green' }}>{message}</p>}
    </div>
  );
};

export default Cart; 