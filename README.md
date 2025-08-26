# FinanceiroBackend

**FinanceiroBackend** é um sistema de gestão financeira pessoal desenvolvido em **Java com Spring Boot**.  
Ele disponibiliza uma **API RESTful** para gerenciar contas, lançamentos financeiros, usuários, centros de custo e bancos.  
Utiliza banco de dados em memória **H2** para testes e desenvolvimento rápido.

---

## DIagrama de Classes
<img width="700" height="500" alt="imagem_readme" src="https://github.com/user-attachments/assets/df2366bc-fb04-41ac-a3ca-091c65739209" />

---

##  Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **H2 Database **
- **Maven**

---

##  Funcionalidades

-  **Usuários**: criação, consulta, atualização e exclusão  
-  **Contas**: gerenciamento completo com operações CRUD  
-  **Lançamentos Financeiros**: registro, consulta, atualização, exclusão e pagamento de lançamentos  
-  **Centros de Custo**: categorização de despesas e receitas  
-  **Bancos**: cadastro e gerenciamento de instituições financeiras  

---

##   Endpoints (Exemplos)

| Recurso                 | Método | Rota                      | Ação                       |
| ----------------------- | ------ | ------------------------- | -------------------------- |
| Usuários                | POST   | `/usuarios`               | Criar usuário              |
| Usuários                | GET    | `/usuarios`               | Listar usuários            |
| Usuários                | GET    | `/usuarios/{id}`          | Buscar usuário por ID      |
| Usuários                | PUT    | `/usuarios/{id}`          | Atualizar usuário          |
| Usuários                | DELETE | `/usuarios/{id}`          | Excluir usuário            |
| Contas                  | CRUD   | `/contas`                 | Gerenciar contas           |
| Lançamentos Financeiros | CRUD   | `/lancamentos`            | Gerenciar lançamentos      |
| Pagamento de Lançamento | POST   | `/lancamentos/{id}/pagar` | Pagar lançamento           |
| Centros de Custo        | CRUD   | `/centros-custo`          | Gerenciar centros de custo |
| Bancos                  | CRUD   | `/bancos`                 | Gerenciar bancos           |


## Método pagarLancamento

Este método foi criado para dar baixa no lançamento. Por isso, quando baixado, ocorre a verificação para saber o tipo de lançamento (Debito ou Credito)

## Endpoint para Lançamento
GET http://localhost:8080/lancamentos 

<img width="460" height="314" alt="image" src="https://github.com/user-attachments/assets/3cb27a31-9259-4db6-aab3-b1d36b5c0e8b" />


## Endpoint para pagar Lançamento
PUT  http://localhost:8080/lancamentos/pagar/{id}

<img width="426" height="276" alt="image" src="https://github.com/user-attachments/assets/a8c2021e-e62b-47ba-855e-c31523250b7d" />


## Melhorias Futuras

- Banco de dados persistente (PostgreSQL/MySQL)

- Autenticação e autorização (Spring Security, JWT)

- Tratamento de exceções


