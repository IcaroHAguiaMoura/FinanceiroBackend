# FinanceiroBackend

**FinanceiroBackend** é um sistema de gestão financeira pessoal desenvolvido em **Java com Spring Boot**.  
Ele disponibiliza uma **API RESTful** para gerenciar contas, lançamentos financeiros, usuários, centros de custo e bancos.  
Utiliza banco de dados em memória **H2** para testes e desenvolvimento rápido.

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

