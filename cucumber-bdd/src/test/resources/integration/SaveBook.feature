# language: pt
@SaveBook
Funcionalidade: Cadastrar livro

  Esquema do Cenario: Encontrar livro
    Dado que o livro "<nome>", cujo isbn eh <isbn>, estah com situacao <situacao>
    Entao devera salvar o livro

    Exemplos: 
      | nome   | isbn | situacao |
      | Star   | 111  | 1        |
      | Harry  | 222  | 1        |
