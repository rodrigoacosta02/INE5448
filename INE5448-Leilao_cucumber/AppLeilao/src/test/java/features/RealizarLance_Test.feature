Feature: Realizar Lance
  Realizar lance

  @RealizarLanceComSucesso
  Scenario Outline: Realizar Lance com Sucesso
    Given O nome produto <nomeProduto>
    And e o cpf do comprador <cpfComprador>
    And e o valor do lance <valorLance>
    When o lance efetuado com <sucesso>
    Then o lance efetuado pelo usuario <existir>

Examples:

|nomeProduto	|cpfComprador 	 |valorLance	|sucesso			|existir	|
|"casa"       |"327.387.790-18"|"1000"      |"false"      |"false"	|
|"casa"       |"327.387.790-18"|"1500"      |"true"      	|"true"   |
|"casa"       |"327.387.790-18"|"1800"      |"true"      	|"true"   |

                   

