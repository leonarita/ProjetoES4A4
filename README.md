# Projeto Biblioteca

## Descrição do Projeto

É um projeto desenvolvido para a disciplina Engenharia de Software IV (ES4A4) de um sistema bibliotecário baseado no seguinte Diagrama de Classes (com algumas adaptações):

<br>
<img src="/.documents/Diagrama de Classes - Sistema Biblioteca.png">
<br>

O projeto é composto por uma API REST e uma API GraphQL.

<br>

- Em termos técnicos, é uma API que consiste em, basicamente, operações CRUD, realizar login e enviar email.
- Em termos de negócio, é possível cadastrar items e usuários, realizar empréstimos, reservas e devoluções de item.
- Ao que se refere à Engenharia de Software, inclui Engenharia de Requisitos, Metodologias Ágeis, Arquitetura, Design Patterns e Testes Automatizados.

<br>

## Configurações de Ambiente

1. Java 8+
2. IDE para desenvolvimento Java/Spring (recomendável STS, mas pode ser IntelliJ ou Eclipse)
3. Lombok: Plugin a ser instalado na IDE para Java para realizar métodos construtor, getters e setters
4. Insomnia ou Postman
5. PostgreSQL
6. DBeaver (opcional, interface gráfica para PostgreSQL)
7. Docker

<br>

### OBS 1: Lombok
Caso o lombok não esteja configurado, os métodos getters, setters e construtores vão gerar erros.
<br>
[Clique aqui para abrir um site de referência para instalar o lombok no STS.](https://dicasdejava.com.br/como-configurar-o-lombok-no-eclipse/)

<br>

### OBS 2: Email Remetente
Para configurar um email para enviar emails no sistema no gmail, [precisa ativar o acesso a apps menos seguros](https://myaccount.google.com/lesssecureapps).
<br>
As informações de remetente (username e password) devem estar no arquivo application.yml na pasta src/main/resources.
<br>
Um aviso para os desenvolvedores é sempre evitar de deixar as senhas de email expostas.

<br>

### OBS 3: Banco de Dados
As configurações de banco de dados (url, driver, username password) também estão no arquivo application.yml na pasta src/main/resources.

<br>

### OBS 4: Redis
Para ligar o redis no docker, use o comando: `docker run -it --name redis -p 6379:6379 redi:5.0.3`

<br>

## Tecnologias Utilizadas

1. Spring Boot
2. Spring GraphQL
3. Spring Data
4. Spring Security
5. Spring Cache
6. JPA e Hibernate
7. JWT
8. Javamailer
9. Flyway
10. jUnit 5
11. Cucumber (que, por ser baseado no jUnit 4, tem um projeto só pra ele)
12. Redis
13. Feign

<br>

## Testes Automatizados

Encontram-se na pasta src/test/java:
- Testes Unitários: Feitos pelo jUnit
- Testes de Integração: Feitos pelo jUnit

<br>

Foram aplicados as seguintes técnicas de testes:
- Test Driven Design (TDD)
- Behavior Driven Development (BDD)

<br>

## ELK Stack (Elasticsearch, Logstash e Kibana)
ELK Stack é um conjunto de ferramentas de coleta e análise de logs. <br>
O Elasticsearch armazena os dados coletados pelo Logstash. O Kibana apresenta dashobards com esses dados. <br>
Essa stack pode ser tanto usado no site oficial (https://www.elastic.co/pt/downloads/) quanto no docker.

<br>

Caso instale na máquina local e esteja utilizando windows, siga os seguintes passos em 3 terminais (CMD):

```
1) LIGAR ELASTICSEARCH
  - cd C:\Libs\kibana\bin
  - kibana.bat

2) LIGAR LOGSTASH
  - Colocar o arquivo syslog.conf (localizado em /.documents) no diretório /conf do logstash
  - cd C:\Libs\logstash
  - .\bin\logstash.bat -f .\config\syslog.conf

3) LIGAR KIBANA
  - Descomentar a linha "elasticsearch.hosts: ["http://localhost:9200"]" em /config/kibana.yml
  - cd C:\Libs\kibana\bin
  - kibana.bat
```

<br>

## Aplicações Complementares

O [Projeto de DW2A4](https://github.com/leonarita/ProjetoDW2A4/tree/master/EXAMPLE01), que utiliza Angular, é uma aplicação front-end que utiliza as rotas do controller LivroController para exemplicar conceitos do Angular. Por esse motivo, as rotas desse controller não demandam autenticação.



