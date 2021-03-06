# Projeto Biblioteca

## Descrição do Projeto

É um projeto desenvolvido para a disciplina Engenharia de Software IV (ES4A4) de um sistema bibliotecário baseado no seguinte Diagrama de Classes:

<br>
<img src="/Diagrama de Classes - Sistema Biblioteca.png">
<br>

- Em termos técnicos, é uma API que consiste em, basicamente, realizar operações CRUD, realizar login e enviar email.
- Em termos de negócio, é possível cadastrar items e usuários, realizar empréstimos, reservas e devoluções de item.

<br>

## Configurações de Ambiente

1. Java 8+
2. IDE para desenvolvimento Java/Spring (recomendável STS, mas pode ser IntelliJ ou Eclipse)
3. Lombok: Plugin a ser instalado na IDE para Java para realizar métodos construtor, getters e setters
4. Insomnia ou Postman
5. PostgreSQL
6. DBeaver (opcional, interface gráfica para PostgreSQL)

<br>

### OBS 1: Lombok
Caso o lombok não esteja configurado, os métodos getters, setters e construtores vão gerar erros.
<br>
[Clique aqui para abrir um site de referência para instalar o lombok no STS.](https://dicasdejava.com.br/como-configurar-o-lombok-no-eclipse/)

<br>

### OBS 2: Email Remetente
Para configurar um email para enviar emails no sistema no gmail, [precisa ativar o acesso a apps menos seguros](https://myaccount.google.com/lesssecureapps).
<br><br>
As informações de remetente (email e senha) devem estar no arquivo application.yml na pasta src/main/resources, nos campos username e password respectivamente.
<br><br>
Um aviso para os desenvolvedores, sempre evitar de deixar as senhas de email expostas.

<br>

### OBS 3: Banco de Dados
As configurações de banco de dados (url, driver, username password) também estão no arquivo application.yml na pasta src/main/resources.

<br>

## Tecnologias Utilizadas

1. Spring Boot
2. Spring Data
3. JWT
4. Nodemailer



