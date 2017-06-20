Feature: Cadastrar Produto
  Cadastrar produtos

  @CadastrarProdutoSucesso
  Scenario: Cadastrar Produto com Sucesso
    Given O nome do produto "carro"
    And a descricao "Carro A"
    And e a data limite "2017-06-06"
    And e o cpf leiloador "327.387.790-18"
    And e o lance minimo "1000"
    When O produto nao existir anteriormente
    Then O sistema deve cadastrar o produto com sucesso
