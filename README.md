# ORDER API 📦

## Descrição ⚡

Este foi um desafio realizado para a construção de uma API em Java Spring Boot, que é responsável por receber produtos de um serviço externo A, 
realizar o cálculo do preço total do pedido e enviar para um serviço externo B (Utilizando WebClient). Para o Banco de Dados foi utilizado o MongoDB e para sistema de cache, 
com o intuito de evitar o envio de múltiplas requisições desnecessárias ao banco, além de melhorar a perfomance e a agilidade, foi utilizado o Redis. 

**OBS**: A API está funcionando em http://localhost:8080

## Tecnologias Utilizadas 💿
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![WebClient](https://img.shields.io/badge/WebClient-4CAF50?style=for-the-badge&logo=spring&logoColor=white)

## Como Rodar o Projeto Localmente 🚀
- Clone este repositório:
   ```bash
   git clone ...
  ```
- Inicie o MongoDB
- Inicie o Redis-Server
- Inicie a Aplicação

## Endpoints para Testar
- **GET All**: localhost:8080/orders
- **POST**: localhost:8080/orders
- **DELETE All** localhost:8080/orders
- **GET ou DELETE (Para um Pedido Específico)**: localhost:8080/orders/{id}

## Próximos Passos 🔜
- Utilização do Docker e Docker Compose para facilitar e garantir a Orquestração do Ambiente
- Melhorar certos pontos da arquitetura
