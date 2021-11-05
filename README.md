# BlueBank

Com a construção de uma nova plataforma, o banco fictício BlueBank precisava de
uma API para gerenciar as transações. Este repositório contém todos os códigos para o funcionamento da ferramenta.

O sistema permite o cadastro de novos clientes, incluindo dados pessoais e
dados para contato. O cliente pode ser atrelado a uma conta bancária, com um valor
inicial de saldo. Além disso, é possível fazer transferências de um cliente para outro e esse
histórico de transações entre as contas são registrados.

## Diagrama de classes
![bluebank-diagrama](https://user-images.githubusercontent.com/38514137/140539023-0e1006f4-9799-4714-9814-a0c22d6750ae.png)

## Endpoints

| Url | Método | Descrição | Request Stream | Response Stream | Status Code Retornado |
| --- | ------ | ----------- | -------------- | --------------- | -------------------- |
| /clientes | GET | Todos os clientes no sistema | n/a | Lista de **Clientes** | 200/404 |
| /clientes/{id} | GET | Retorna um **Cliente** específico por id | n/a | **Cliente** | 200/404 |
| /clientes | POST | Cria uma entidade **Cliente** no sistema | **Cliente** sem id especificado | **Cliente** | 201/404 |
| /clientes/{id} | PUT | Modifica um **Cliente** | **Cliente** | n/a | 200/404 |
| /clientes/{id} | DELETE | Deleta um **Cliente** | n/a | n/a | 200/404 |
