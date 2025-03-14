# Padão do projeto

## Padrão vertical slice com Mediator


```📦 com.seuprojeto
 ┣ 📂 application
 ┃ ┣ 📂 common
 ┃ ┃ ┣ 📂 behaviors         # Comportamentos transversais (logging, validation)
 ┃ ┃ ┣ 📂 exceptions        # Exceções personalizadas
 ┃ ┃ ┣ 📂 interfaces        # Interfaces comuns (ex: IUnitOfWork, ICurrentUser)
 ┃ ┃ ┗ 📂 security          # Segurança e autenticação
 ┃ ┗ 📂 mediators           # Implementação do padrão Mediator
 ┃   ┣ 📜 CommandHandler.java
 ┃   ┣ 📜 QueryHandler.java
 ┃   ┗ 📜 Mediator.java
 ┣ 📂 domain
 ┃ ┣ 📂 entities            # Entidades de negócio
 ┃ ┣ 📂 enums               # Enums usados no sistema
 ┃ ┗ 📂 events              # Eventos de domínio
 ┣ 📂 infrastructure
 ┃ ┣ 📂 persistence         # Configuração de repositórios e banco de dados
 ┃ ┣ 📂 external            # Integração com APIs externas
 ┃ ┗ 📂 configuration       # Configurações gerais (CORS, Swagger, etc.)
 ┣ 📂 features
 ┃ ┣ 📂 users               # Slice de funcionalidade "Users"
 ┃ ┃ ┣ 📜 UserController.java
 ┃ ┃ ┣ 📜 UserQuery.java
 ┃ ┃ ┣ 📜 UserQueryHandler.java
 ┃ ┃ ┣ 📜 CreateUserCommand.java
 ┃ ┃ ┣ 📜 CreateUserHandler.java
 ┃ ┃ ┣ 📜 UserRepository.java
 ┃ ┃ ┣ 📜 UserMapper.java
 ┃ ┃ ┗ 📂 DTOs

