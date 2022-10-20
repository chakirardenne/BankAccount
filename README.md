# BankAccount
Kata de code Bank account

Implémentation de l'architecture hexagonal en Java avec le framework Spring Boot.

## Architecture de l'application

### Domain
Le package domain contient la logique métier de l'application : 
- Créer un compte
- Débiter un compte
- Créditer un compte
- Consulter l'historique d'un compte

### Adapters
Contient les adapters in et out de persistence et exposition d'une api
- Controller REST
- H2 persistence avec SpringDataJpa

### Application
Contient les ports in et out du domain, les use case du domain et le port de persistence

Commande pour lancer l'application:
- build: ./gradlew build
- lancer: ./gradlew bootRun

### Documentation
Accéder à la documentation de l'api : http://localhost:8080/swagger-ui/index.html#/