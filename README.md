# :moneybag: BlueBank :moneybag:

Com a construção de uma nova plataforma, o banco fictício BlueBank precisava de
uma API para gerenciar as transações. Este repositório contém todos os códigos para o funcionamento da ferramenta.

O sistema permite o cadastro de novos clientes, incluindo dados pessoais e
dados para contato. O cliente pode ser atrelado a uma conta bancária, com um valor
inicial de saldo. Além disso, é possível fazer transferências de um cliente para outro e esse
histórico de transações entre as contas são registrados.

## :file_folder: Diagrama de classes
![bluebank](https://user-images.githubusercontent.com/38514137/140621664-96f517ad-e2c5-46f0-b974-b11a9d93a9f8.png)

## :twisted_rightwards_arrows: Endpoints

Caminho base: `http://localhost:8080/v1/`

### Operações do Cliente
| Url | Método | Descrição | Request Stream | Response Stream | Status Code Retornado |
| --- | ------ | ----------- | -------------- | --------------- | -------------------- |
| /clientes | GET | Retorna todos os clientes no sistema | n/a | Lista de **Clientes** | 200/404 |
| /clientes/{id} | GET | Retorna um **Cliente** específico por id | n/a | **Cliente** | 200/404 |
| /clientes | POST | Cria uma entidade **Cliente** no sistema | **Cliente** sem id especificado | **Cliente** | 201/404 |
| /clientes/{id} | PUT | Modifica um **Cliente** | **Cliente** | n/a | 200/404 |
| /clientes/{id} | DELETE | Deleta um **Cliente** inativando seu registro | n/a | n/a | 200/404 |

### Operações da Conta
| Url | Método | Descrição | Request Stream | Response Stream | Status Code Retornado |
| --- | ------ | ----------- | -------------- | --------------- | -------------------- |
| /contas | GET | Retorna todas as contas **ativas** no sistema | n/a | Lista de **Contas** | 200/404 |
| /contas/{id} | GET | Retorna uma **Conta** específica por id | n/a | **Conta** | 200/404 |
| /contas | POST | Cria uma entidade **Conta** no sistema | **Conta** sem id especificado | **Conta** | 201/404 |
| /contas/{id} | DELETE | Deleta uma **Conta** seu registro | n/a | n/a | 200/404 |
| /contas/deposit/{id} | PUT | Incrementa o saldo de uma **Conta** no sistema | n/a | **Conta** | 200/404 |
| /contas/withdraw/{id} | PUT | Decrementa o saldo de uma **Conta** no sistema | n/a | **Conta** | 200/404 |

### Transferência
| Url | Método | Descrição | Request Stream | Response Stream | Status Code Retornado |
| --- | ------ | ----------- | -------------- | --------------- | -------------------- |
| /transacao | GET | Retorna todas as **Transações ativas** no sistema | n/a | Lista de **Transações** | 200/404 |
| /transacao/{id} | GET | Retorna uma lista de **Transações** específicas por **id do Cliente** | n/a | Lista de **Transações** | 200/404 |
| /transacao/{id} | POST | Cria uma **Transação** entre **Contas** | **Transação** sem id especificado | **Transação** | 200/404 |

