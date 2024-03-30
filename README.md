
# Microsserviço APIRestfull
> Implementação de uma API para gestão de restaurantes usando a arquitetura Rest com os conceitos Restfull

## Tecnologias
* Spring Boot
* Spring MVC
* Spring Data JPA
* Mysql
* Postman

## Práticas adotadas
* API RESTful
* Consultas com Spring Data JPA
* Injeção de Dependência
* Tratamento de respostas de erro
  
## Como Começar

1) Clone o repositório: git clone https://github.com/seuusuario/seurepositorio.git
2) Navegue até o diretório do projeto: cd seurepositorio
3) Execute o comando para instalar as dependências: packagemanager install


# Principais Endpoints
A API poderá ser acessada em localhost:8080 e também pelo Postman.

## Criar um novo restaurante
Este endpoint permite criar um novo restaurante.

Método: Requisição HTTP POST (http://localhost:8085/restaurantes)

Endpoint: /restaurantes

    $ @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<EntityModel<Restaurant>> create(@RequestBody Restaurant obj) {
	  	EntityModel<Restaurant> restaurantEntityModel = restService.create(obj);
		
	  	return ResponseEntity.created(null).body(restaurantEntityModel);		
	}

**Retorno:** JSON contendo o restaurante criado
```json
[
    {
        "id": 1,
        "name": "Restaurante Paulistano",
        "url": "www.restaurantepaulistano.com.br",
        "menu": "cardapio XPTO",
        "telephone": "2323-2323",
        "priceRange": "R$10,00...",
        "address": {
            "postalCode": "123456-050",
            "streetAddress": "Rua ABC, 123",
            "addressLocality": "São Paulo",
            "addressRegion": "SP",
            "addressCountry": "Brasil"
        },
        "_links": {
            "self": {
                "href": "http://localhost:8085/restaurantes/1"
            }
        }
    }
]
```

## Listar todos os restaurantes
Este endpoint retorna uma lista de todos os restaurantes cadastrados.

Método: Requisição HTTP GET (http://localhost:8085/restaurantes)

Endpoint: /restaurantes

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    	public List<Restaurant> findAll(){
    		return restService.findAll();
    	}

Retorno: JSON contendo a lista de restaurantes

```json
[
    {
        "id":1,
        "name": "Restaurante Paulistano",
        "url": "www.restaurantepaulistano.com.br",
        "menu": "cardapio XPTO",
        "telephone": "2323-2323",
        "priceRange": "R$10,00...",
        "address": {
            "postalCode": "123456-050",
            "streetAddress": "Rua ABC, 123",
            "addressLocality": "São Paulo",
            "addressRegion": "SP",
            "addressCountry": "Brasil"
        },
        "_links": {
            "self": {
                "href": "http://localhost:8085/restaurantes/1"
            }
        }
    }
 {
        "id": 2,
        "name": "Restaurante Italiano",
        "url": "www.restauranteitaliano.com.br",
        "menu": "cardapio XPTO",
        "telephone": "2323-2323",
        "priceRange": "R$10,00...",
        "address": {
            "postalCode": "123456-050",
            "streetAddress": "Rua ABC, 123",
            "addressLocality": "Roma",
            "addressRegion": "IT",
            "addressCountry": "Italia"
        },
        "_links": {
            "self": {
                "href": "http://localhost:8085/restaurantes/1"
            }
        }
    }
    ........
]
```



## Buscar restaurante por ID
Este endpoint retorna um restaurante específico com base no ID fornecido.

Método: GET  
Endpoint: /restaurantes/{id}
Parâmetros: {id} - ID do restaurante a ser buscado
 
  

    @GetMapping(value = "/{id}")
  	public ResponseEntity<EntityModel<Restaurant>> findById(@PathVariable(value="id") Long id) {
  		EntityModel<Restaurant> restaurantEntityModel = restService.findById(id);
  		return ResponseEntity.ok().body(restaurantEntityModel);
  	}


Retorno: JSON contendo os detalhes do restaurante

http://localhost:8085/restaurantes/9

```json
[
    {
        "id": 9,
        "name": "Restaurante ABC",
        "url": "www.restauranteabc.com.br",
        "menu": "cardapio XPTO",
        "telephone": "2323-2323",
        "priceRange": "R$10,00...",
        "address": {
            "postalCode": "123456-050",
            "streetAddress": "Rua ABC, 123",
            "addressLocality": "São Paulo",
            "addressRegion": "SP",
            "addressCountry": "Brasil"
        },
        "_links": {
            "self": {
                "href": "http://localhost:8085/restaurantes/9"
            }
        }
    }
]
```


### Atualizar um restaurante existente

Este endpoint permite atualizar os detalhes de um restaurante existente com base no ID fornecido.

Método: PUT
Endpoint: /restaurantes/{id}
Parâmetros: {id} - ID do restaurante a ser atualizado

    @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<EntityModel<Restaurant>> update(@PathVariable(value="id") Long id, @RequestBody Restaurant obj){
    		EntityModel<Restaurant> restaurantEntityModel = restService.update(id, obj);
    		
    		return ResponseEntity.ok(restaurantEntityModel);
    	}

Retorno: JSON contendo os detalhes atualizados do restaurante

```json
[
     "id": 12,
        "name": "Restaurante Frances",
        "url": "www.restaurantebrasileiro.com.br",
        "menu": "cardapio XPTO",
        "telephone": "2323-2323",
        "priceRange": "R$10,00...",
        "address": {
            "postalCode": "123456-050",
            "streetAddress": "Rua ABC, 123",
            "addressLocality": "São Paulo",
            "addressRegion": "SP",
            "addressCountry": "Brasil"
        },
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8085/restaurantes/12"
            }
        ]
    },
]
```

### Excluir um restaurante

Este endpoint permite excluir um restaurante existente com base no ID fornecido.

Método: DELETE
Endpoint: /restaurantes/{id}
Parâmetros: {id} - ID do restaurante a ser excluído

    @DeleteMapping(value="/{id}")
    	public ResponseEntity<?> delete(@PathVariable Long id) {
    		restService.delete(id);
    		return ResponseEntity.noContent().build();
    	}

Retorno: Código de status HTTP 204 (No Content)

     Código de status HTTP 204 (No Content)



## Buscar restaurantes por cidade

Este endpoint retorna uma lista de restaurantes com base na cidade fornecida.

* Método: GET
* Endpoint: /restaurantes?cidade={cidade}
* Parâmetros: {cidade} - Nome da cidade para filtrar os restaurantes

       @GetMapping(params = "cidade", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Restaurant>> findRestaurantsByCity(@RequestParam(value = "cidade") String cidade) {
            List<Restaurant> restaurants = restService.findRestaurantsByAddressLocality(cidade);
            return ResponseEntity.ok(restaurants);
        }
  
* Retorno: JSON contendo a lista de restaurantes na cidade especificada

  http://localhost:8085/restaurantes?cidade=São%20Paulo

```json
[
     "id": 12,
        "name": "Restaurante Frances",
        "url": "www.restaurantebrasileiro.com.br",
        "menu": "cardapio XPTO",
        "telephone": "2323-2323",
        "priceRange": "R$10,00...",
        "address": {
            "postalCode": "123456-050",
            "streetAddress": "Rua ABC, 123",
            "addressLocality": "São Paulo",
            "addressRegion": "SP",
            "addressCountry": "Brasil"
        },
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8085/restaurantes/12"
            }
        ]
    },
]
```


## Licensing

One really important part: Give your project a proper license. Here you should
state what the license is and how to find the text version of the license.
Something like:

"The code in this project is licensed under MIT license."
