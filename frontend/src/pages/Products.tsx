import React, { useEffect, useState } from 'react';
import { getProducts, createProduct, updateProduct, deleteProduct } from '../services/productService';
import { useAuth } from '../contexts/AuthContext';

const Products: React.FC = () => {
  const [products, setProducts] = useState<any[]>([]);
  const [form, setForm] = useState({ name: '', description: '', stock: 0, unitPrice: 0, tags: '' });
  const [editing, setEditing] = useState<string | null>(null);
  const [search, setSearch] = useState('');
  const { user } = useAuth();

  const fetchProducts = async () => {
    const data = await getProducts(search);
    setProducts(data as any[]);
  };

  useEffect(() => { fetchProducts(); }, [search]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (editing) {
      await updateProduct(editing, form);
      setEditing(null);
    } else {
      await createProduct(form);
    }
    setForm({ name: '', description: '', stock: 0, unitPrice: 0, tags: '' });
    fetchProducts();
  };

  const handleEdit = (product: any) => {
    setForm(product);
    setEditing(product.name);
  };

  const handleDelete = async (name: string) => {
    await deleteProduct(name);
    fetchProducts();
  };

  return (
    <div style={{ maxWidth: 800, margin: 'auto', padding: 32 }}>
      <h2>Produtos</h2>
      <input placeholder="Buscar por nome" value={search} onChange={e => setSearch(e.target.value)} />
      <form onSubmit={handleSubmit} style={{ margin: '16px 0' }}>
        <input name="name" placeholder="Nome" value={form.name} onChange={handleChange} required />
        <input name="description" placeholder="Descrição" value={form.description} onChange={handleChange} />
        <input name="stock" type="number" placeholder="Estoque" value={form.stock} onChange={handleChange} />
        <input name="unitPrice" type="number" placeholder="Preço" value={form.unitPrice} onChange={handleChange} />
        <input name="tags" placeholder="Tags" value={form.tags} onChange={handleChange} />
        <button type="submit">{editing ? 'Salvar' : 'Cadastrar'}</button>
        {editing && <button type="button" onClick={() => { setEditing(null); setForm({ name: '', description: '', stock: 0, unitPrice: 0, tags: '' }); }}>Cancelar</button>}
      </form>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Estoque</th>
            <th>Preço</th>
            <th>Tags</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {products.map((p) => (
            <tr key={p.name}>
              <td>{p.name}</td>
              <td>{p.description}</td>
              <td>{p.stock}</td>
              <td>{p.unitPrice}</td>
              <td>{p.tags}</td>
              <td>
                <button onClick={() => handleEdit(p)}>Editar</button>
                <button onClick={() => handleDelete(p.name)}>Remover</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Products; 