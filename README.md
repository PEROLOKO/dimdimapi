# DimDim API
 API de movimentações bancárias para a DimDim

## Endpoints
 - Cliente
   - [Cadastrar Cliente](#cadastrar-cliente)
   - [Listar Clientes](#listar-clientes)
   - [Detalhar Cliente](#detalhar-cliente)
   - [Editar Cliente](#editar-cliente)
   - [Deletar Cliente](#deletar-cliente)
 - Movimentação
   - [Fazer Movimentação](#fazer-movimentação)
   - [Listar todas Movimentações](#listar-todas-movimentações)
   - [Listar Movimentações pelo Cliente que fez](#listar-movimentações-pelo-cliente-que-fez)
   - [Listar Movimentações pelo Cliente que recebeu](#listar-movimentações-pelo-cliente-que-recebeu)

## Cliente
### Cadastrar Cliente

`POST` /dimdim/api/cliente

*Campos de requisição*

| campo | tipo   | obrigatório | descrição        |
| ----- | ------ | :---------: | ---------------- |
| nome  | String |     sim     | Nome do Cliente  |
| cpf   | String |     sim     | CPF do Cliente   |
| saldo | Double |     sim     | Saldo do Cliente |

*Exemplo de requisição*
```
{
  "nome": "Arthur Edson Joaquim Ramos",
  "cpf": "861.067.339-10",
  "saldo": 200.0
}
```

*Resposta*

| código | descrição                            |
| ------ | ------------------------------------ |
| 201    | o cliente foi cadastrado com sucesso |
| 400    | dados inválidos                      |

### Listar Clientes

`GET` /dimdim/api/cliente

```
[
	{
		"id": 1,
		"nome": "Antonio Pietro Mateus Lopes",
		"cpf": "490.098.832-48",
		"saldo": 100.0,
		"movimentacoesFez": [],
		"movimentacoesRecebe": []
	},
	{
		"id": 2,
		"nome": "Stefany Marina Lopes",
		"cpf": "242.139.909-23",
		"saldo": 150.0,
		"movimentacoesFez": [],
		"movimentacoesRecebe": []
	},
	{
		"id": 3,
		"nome": "Roberto Osvaldo Barros",
		"cpf": "328.916.505-14",
		"saldo": 200.0,
		"movimentacoesFez": [],
		"movimentacoesRecebe": []
	},
	{
		"id": 4,
		"nome": "Enrico Geraldo Manoel da Mota",
		"cpf": "317.488.066-10",
		"saldo": 60.5,
		"movimentacoesFez": [],
		"movimentacoesRecebe": []
	},
	{
		"id": 5,
		"nome": "Arthur Edson Joaquim Ramos",
		"cpf": "861.067.339-10",
		"saldo": 200.0,
		"movimentacoesFez": [],
		"movimentacoesRecebe": []
	}
]
```

*Resposta*

| código | descrição                             |
| ------ | ------------------------------------- |
| 200    | os dados foram retornados com sucesso |

### Detalhar Cliente

`GET` /dimdim/api/cliente/{id}

```
{
	"id": 5,
	"nome": "Arthur Edson Joaquim Ramos",
	"cpf": "861.067.339-10",
	"saldo": 200.0,
	"movimentacoesFez": [],
	"movimentacoesRecebe": []
}
```

### Editar Cliente

`PUT` /dimdim/api/cliente/{id}

*Campos de requisição*

| campo | tipo   | obrigatório | descrição        |
| ----- | ------ | :---------: | ---------------- |
| nome  | String |     sim     | Nome do Cliente  |
| cpf   | String |     sim     | CPF do Cliente   |
| saldo | Double |     sim     | Saldo do Cliente |

*Exemplo de requisição*
```
{
  "nome": "Arthur Edson da Silva",
  "cpf": "071.320.068-51",
  "saldo": 1000.0
}
```

*Resposta*

| código | descrição                            |
| ------ | ------------------------------------ |
| 201    | o cliente foi atualizado com sucesso |
| 400    | dados inválidos                      |

### Deletar Cliente

`DELETE` /dimdim/api/cliente/{id}

*Resposta*

| código | descrição                                     |
| ------ | --------------------------------------------- |
| 200    | o cliente foi removido com sucesso            |
| 404    | não foi possível achar um cliente com esse id |

## Movimentação
### Fazer Movimentação

`POST` /dimdim/api/movimentacao/{idFaz}/{idRecebe}/{valor}

*Resposta*

| código | descrição                                     |
| ------ | --------------------------------------------- |
| 200    | movimentação feita com sucesso                |
| 403    | saldo do cliente insuficiente                 |
| 404    | não foi possível achar um cliente com esse id |

### Listar todas Movimentações

`GET` /dimdim/api/movimentacao

```
[
	{
		"id": 1,
		"valor": 50.0
	},
	{
		"id": 2,
		"valor": 20.0
	},
	{
		"id": 3,
		"valor": 100.0
	},
	{
		"id": 4,
		"valor": 5.0
	}
]
```

*Resposta*

| código | descrição                             |
| ------ | ------------------------------------- |
| 200    | os dados foram retornados com sucesso |

### Listar Movimentações pelo Cliente que fez

`GET` /dimdim/api/movimentacao/fez/{idFez}

```
[
	{
		"id": 1,
		"valor": 50.0
	},
	{
		"id": 2,
		"valor": 20.0
	}
]
```

*Resposta*

| código | descrição                             |
| ------ | ------------------------------------- |
| 200    | os dados foram retornados com sucesso |

### Listar Movimentações pelo Cliente que recebeu

`GET` /dimdim/api/movimentacao/recebeu/{idRecebeu}

```
[
	{
		"id": 1,
		"valor": 50.0
	},
	{
		"id": 3,
		"valor": 100.0
	}
]
```

*Resposta*

| código | descrição                             |
| ------ | ------------------------------------- |
| 200    | os dados foram retornados com sucesso |
