# language: pt
@FindBook
Funcionalidade: Encontrar livro

  Contexto: Cria todas as contas e associa ao banco
    Dado que foram cadastrados os seguintes livros
      | titulo       | isbn |
      | Star Wars    | 111  |
      | Harry Potter | 222  |
      
  Cenario: Cria todas as contas e associa ao banco
    Dado que foi buscado o livro "Harry Potter"
    E estah com status 2
    Quando for encontrado
    Entao deverah mudar o status para 3
    
    