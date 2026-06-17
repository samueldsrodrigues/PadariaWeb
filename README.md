# PadariaWeb

Sistema web para gerenciamento de padarias desenvolvido em Java, permitindo o controle de produtos, clientes, funcionários, vendas e encomendas. A aplicação foi construída utilizando Java EE e segue o padrão MVC para organização das camadas do sistema.

## Demonstração

### Gestão de Produtos

#### Listagem de Produtos

![Listagem de Produtos](docs/produtos-listagem.png)

#### Pesquisa de Produtos

![Pesquisa de Produtos](docs/produtos-pesquisa.png)

#### Cadastro de Produtos

![Cadastro de Produtos](docs/produtos-formulario.png)

---

### Gestão de Funcionários

![Funcionários](docs/funcionarios.png)

---

### Gestão de Vendas

#### Inclusão de Venda

![Inclusão de Venda](docs/vendas-inclusao.png)

#### Pesquisa e Listagem de Vendas

![Listagem de Vendas](docs/vendas-listagem.png)

---

### Modelo do Banco de Dados

![DER](docs/der_padariaweb.png)

---

## Funcionalidades

- Cadastro de produtos
- Cadastro de clientes
- Cadastro de funcionários
- Controle de vendas
- Controle de encomendas
- Pesquisa e filtros
- Paginação de resultados
- Cancelamento de vendas
- Controle de estoque
- Autenticação e controle de acesso

---

## Tecnologias utilizadas

- Java EE
- JSF
- PrimeFaces
- JPA / Hibernate
- PostgreSQL
- Maven
- Apache Tomcat
- Lombok
- OmniFaces
- Spring Security
- HTML5
- CSS3
- JavaScript
- Git
- GitHub
- Linux Ubuntu

---

## Arquitetura

O sistema segue o padrão MVC (Model-View-Controller):

### Model
- Entidades JPA
- DAO (Data Access Object)
- Services

### View
- JSF (Facelets)
- PrimeFaces
- HTML
- CSS
- JavaScript

### Controller
- Managed Beans responsáveis pelo fluxo da aplicação

---

## Banco de Dados

- PostgreSQL
- Modelagem relacional normalizada
- Relacionamentos entre clientes, funcionários, produtos, vendas e encomendas
- Persistência utilizando JPA/Hibernate

---

## Principais entidades

- Produto
- Cliente
- Funcionário
- Cargo
- Turno
- Venda
- Encomenda
- Forma de Pagamento
- Valor do Produto
- Contrato de Funcionário

---

## Como executar

1. Clone o repositório:

```bash
git clone https://github.com/samueldsrodrigues/PadariaWeb.git
```

2. Importe o projeto em uma IDE Java EE (Eclipse EE recomendado).

3. Configure o banco de dados PostgreSQL.

4. Execute os scripts SQL de criação das tabelas.

5. Ajuste as credenciais de conexão com o banco.

6. Execute o projeto no Apache Tomcat.

7. Acesse no navegador:

```text
http://localhost:8080/PadariaWeb
```

---

## Conceitos aplicados

- Programação Orientada a Objetos (POO)
- Arquitetura MVC
- DAO Pattern
- Paginação
- Filtros de pesquisa
- Relacionamentos JPA
- Segurança com Spring Security
- Persistência com Hibernate
- Desenvolvimento Web com JSF e PrimeFaces

---

## Autor

Samuel Rodrigues

- GitHub: https://github.com/samueldsrodrigues
- LinkedIn: https://www.linkedin.com/in/samueldsrodrigues/
