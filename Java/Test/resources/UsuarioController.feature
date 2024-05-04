Feature: Desafiar a otro usuario

  Scenario: Desafiar a otro usuario
    Given que inicio la aplicación
    And hay usuarios registrados en la aplicación
    When desafío a un usuario con el siguiente personaje:
      | Usuario  | Personaje |
      | Roberto  | Vampiro   |
    Then debería enviar un desafío al usuario correctamente
