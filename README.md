<<<<<<< HEAD
# Gerenciamento-de-produtos-e-pagamentos
Projeto Spring Boot para gerenciamento de produtos e seus pagamentos
=======
# Sistema de Gerenciamento de Produtos e Pagamentos

![Capa do Projeto](img.png)

O **Sistema de Gerenciamento de Produtos e Pagamentos** é uma solução completa para gestão de produtos, pedidos, pagamentos, clientes e funcionários. O foco do desenvolvimento está tanto no backend (Spring Boot) quanto no frontend (React + Material-UI), proporcionando uma experiência moderna, responsiva e amigável.

Possui uma interface Web para facilitar o uso e a visualização das suas funcionalidades.

🚧⚠️ Sistema em fase de desenvolvimento ⚠️🚧

---

## 🗃️ Modelagem

![DER](DER-GERENCIAMENTO_DE_PRODUTOS.jpg)

**Diagrama de Modelagem de Entidades:**

![Modelagem de Entidades](modelagem-entidades.png)

---

## ⚡ Funcionalidades
- **Cadastro e gerenciamento de clientes:** Inclui informações como nome, CPF, data de nascimento, endereço e gênero.
- **Cadastro e gerenciamento de funcionários/admins:** Permite o controle de usuários com diferentes permissões e papéis.
- **Cadastro e gerenciamento de produtos:** Nome, descrição, preço, estoque, tags e solicitações de alteração.
- **Carrinho de compras:** Clientes podem adicionar, editar e remover itens, e finalizar a compra de todos os itens de uma vez.
- **Pagamentos:** Integração de métodos de pagamento, status e histórico.
- **Relatórios:** Geração de relatórios gerais e por produto, com visualização detalhada.
- **Solicitações de alteração de produto:** Funcionários podem solicitar alterações, que são aprovadas/rejeitadas por admins.
- **Sistema de autenticação e autorização:** Controle de acesso por tipo de usuário (admin, funcionário, cliente).

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Spring Boot**: Framework para criar aplicativos Java
- **Java 21**: Linguagem de programação
- **Spring Security**: Framework de segurança
- **Spring Data JPA**: ORM para persistência
- **JUnit**: Testes unitários
- **Mockito**: Simulação para testes

### Frontend
- **React**: Biblioteca JavaScript para interfaces de usuário
- **TypeScript**: Superconjunto tipado de JavaScript
- **Material-UI (MUI)**: Componentes visuais modernos e responsivos
- **Axios**: Requisições HTTP
- **Jest/Testing Library**: Testes de componentes

---

## 📌 Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

### Backend
- Java 17 ou superior
- Apache Maven 3.1+

### Frontend
- Node.js (recomendado versão 18+)

---

## 🚀 Iniciar Projeto
Para o Frontend funcionar corretamente, é preciso que o Backend esteja sendo executado.

### Instalação
```bash
# Clone este repositório
$ git clone https://github.com/SeuUsuario/Gerenciamento-de-produtos-e-pagamentos.git
```

### Iniciando Servidor (Backend)
```bash
# Acesse a pasta do servidor
$ cd Gerenciamento-de-produtos-e-pagamentos/salesmanagement

# Instale as dependências
$ mvn clean install

# Execute a aplicação
$ mvn spring-boot:run
# O servidor iniciará na porta:8080
# Caminho da API: http://localhost:8080/
```

### Iniciando o Cliente web (Frontend)
```bash
# Acesse a pasta do frontend
$ cd Gerenciamento-de-produtos-e-pagamentos/frontend

# Instale as dependências
$ npm install

# Execute a aplicação
$ npm start
# O cliente iniciará na porta:3000
# Acesse: http://localhost:3000
```

---

## ⚙️ Executando os Testes
### Backend
```bash
# Acesse o diretório do backend
$ cd Gerenciamento-de-produtos-e-pagamentos/salesmanagement

# Executa todos os testes
$ mvn test

# Executa todos os testes de uma classe
$ mvn -Dtest=NomeDaClasseTest test

# Executa um único teste
$ mvn -Dtest=NomeDaClasseTest#nomeDoMetodo test
```

### Frontend
```bash
# Acesse o diretório do frontend
$ cd Gerenciamento-de-produtos-e-pagamentos/frontend

# Executa todos os testes
$ npm test
```

---

## 📝 Licença
Este projeto está sob a licença MIT - Veja o arquivo LICENSE.md para detalhes.

---

> **Espaços reservados para imagens:**
> - `img.png` (capa do projeto)
> - `DER-GERENCIAMENTO_DE_PRODUTOS.jpg` (modelagem)
> - `modelagem-entidades.png` (diagrama de entidades) 
>>>>>>> develop
