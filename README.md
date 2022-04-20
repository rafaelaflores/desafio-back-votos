# DESAFIO TÉCNICO SICREDI #

Nessa aplicação foi desenvolvido um sistema para Assembleias de Votação. No cooperativismo, muitas decisões são tomadas em assembleias, onde cada associado possui um voto por pauta em questão. A partir disso, essa solução permite gerenciar essas sessões de votação.


## 🚀 Funcionalidades

- Cadastrar uma pauta, buscar uma pauta por id e listar pautas cadastradas.
- Cadastrar associados e listar associados.
- Abrir uma sessão de votação em uma pauta. É possível ou não informar um valor de duração, se não informar a sessão fica aberta por 1 minuto.
- O usuário deve fornecer o id da pauta e do associado para registrar um voto em uma sessão de votação. O voto deve ser igual a "true" ou "false".
- Para registrar um voto os associados devem estar cadastrados com um CPF válido e aptos à votação (ABLE TO VOTE).
- O resultado da votação é retornado através da pauta que o usuário consultar.

## 🔧 Ferramentas
O desenvolvimento da API foi feito utilizando a versão 11 do Java, Maven versão 4.0.0, Spring Boot e banco de dados MySQL versão 8.

Para a criação de uma aplicação que execute na nuvem foi usado o Docker.

Afim de manter o código menos verboso, foi utilizado o framework Lombok.

Foi utilizada a api externa https://user-info.herokuapp.com/users/{cpf} para verificação do CPF dos associados, por meio do RestTemplate do Java.

O versionamento da api foi feito através do path, inserindo o número da versão que foi desenvolvida no início da URL, por exemplo: http://localhost:8080/api/v1/pautas

Além disso, para a documentação da API foi utilizado o Swagger. Disponível em: http://localhost:8080/api/swagger-ui.html

## ⚙️ Utilização

Utilizando docker para subir os containers:
```
$ docker-compose up
```

Executando o projeto por meio do Maven:
```
$ ./mvnw install
$ ./mvnw spring-boot:run
```
## 👩‍🚀 Endpoints
###Pautas

Criar pauta
```
POST http://localhost:8080/api/v1/pautas/
{
    "titulo": "exemplo",
    "descricao" : "descricao exemplo"
}
```
Listar pautas
```
GET http://localhost:8080/api/v1/pautas/
```
Retornar pauta por id
```
GET http://localhost:8080/api/v1/pauta/{idPauta}
```
Mostrar o resultado de uma sessão de votação a partir da pauta
```
GET http://localhost:8080/api/v1/pauta/{idPauta}/resultados
```
###Associados
Cadastrar associado
```
POST http://localhost:8080/api/v1/associados/
{
    "nome": "exemplo",
    "cpf" : "00000000000"
}
```
Listar associados
```
GET http://localhost:8080/api/v1/associados/
```

###Sessões

Abrir sessão de votação
```
POST http://localhost:8080/api/v1/pautas/{idPauta}/sessoes
{
    "duracao": "30"
}
```

###Votos
Votar em uma pauta
```
POST http://localhost:8080/api/v1/votos/
{
    "voto": true,
    "pauta_id" : "1",
    "associado_id" : "1"
}
```

###Descrição do desafio
https://github.com/rh-southsystem/desafio-back-votos
