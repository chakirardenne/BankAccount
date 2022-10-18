# BankAccount
Kata de code Bank account

Implémentation de l'architecture hexagonal en Java avec le framework Spring Boot.

## Architecture de l'application

### Domain
Le package domain contient la logique métier de l'application : 
- Compte banquaire
- Débiter un compte
- Créditer un compte 

### Adapters
Contient les adapters in et out de persistence et exposition d'une api
- Controller REST
- H2 persistence avec SpringDataJpa

### Application
Contient les ports in et out du domain, les use case du domain et le port de persistence

