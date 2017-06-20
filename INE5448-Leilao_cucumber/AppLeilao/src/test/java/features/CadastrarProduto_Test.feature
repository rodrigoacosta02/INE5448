Feature: Cadastrar Produto
  Cadastrar produtos

  @CadastrarProdutoSucesso
  Scenario Outline: Cadastrar Produto com Sucesso
    Given O nome do produto <nome>
    And a descricao <descricao>
    And e a data limite <dataLimite>
    And e o cpf leiloador <cpfLeiloador>
    And e o lance minimo <lanceMinimo>
    When o produto cadastrado com <sucesso>
    Then o produto deve <existir>

Examples:

|nome 		|descricao          	|dataLimite		|cpfLeiloador  		|lanceMinimo	| sucesso 	| existir 	|
|"carro"	|"Carro A"						|"2017-06-06"	|"327.387.790-18"	|"1000.0"  		|  "true"		|"true"			|
|"casa"		|"Casa A"							|"2017-06-06"	|"327.387.790-18"	|"1500.0"     |  "true"		|"true"			|
|"carro B"|"Carro B"						|"2017-06-06"	|"327.387.790-18"	|"1822.0"     |  "false"	|"true"			|
|"fruta"	|"Fruta A sem usuario"|"2017-06-06"	|"113.533.184-78"	|"1822.0"     |  "false" 	|"false"		|
                   



#
#
  #@CadastrarProdutoSucesso
  #Scenario: Cadastrar Produto com Sucesso
    #Given O nome do produto "carro"
    #And a descricao "Carro A"
    #And e a data limite "2017-06-06"
    #And e o cpf leiloador "327.387.790-18"
    #And e o lance minimo "1000"
    #When O produto nao existir anteriormente
    #Then O sistema deve cadastrar o produto com sucesso