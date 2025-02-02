@EjecutarExamen
Feature: Ejecucion del examen de API de mascotas - store

  @CrearOrden
  Scenario Outline: Creacion de una ORDEN
    Given El usuario tiene acceso al API de STORE "https://petstore.swagger.io/v2"
    When Creo un nuevo pedido con la siguiente informacion: <id>, petId <petId>, quantity <quantity>, status "<status>"
    Then La respuesta debe tener un status code <statusCode>
    And Valida body del response: id <id>, petId <petId>, quantity <quantity>, status "<status>"
    Examples:
      | id | petId | quantity  | status   | statusCode |
      | 56 | 2     | 2         | placed   | 200        |
      | 76 | 3     | 5         | approved | 200        |

  @ConsultarOrden
  Scenario Outline: Consulta de una ORDEN
    Given El usuario tiene acceso al API de STORE "https://petstore.swagger.io/v2"
    When Consulto un pedido por orderId <id>
    Then La respuesta debe tener un status code <statusCode>
    And Valida body del response: id <id>, petId <petId>, quantity <quantity>, status "<status>"

    Examples:
      | id | petId | quantity  | status   | statusCode |
      | 56 | 2     | 2         | placed   | 200        |
      | 76 | 3     | 5         | approved | 200        |
