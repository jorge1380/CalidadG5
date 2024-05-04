Feature: Desafíos en la aplicación

  Scenario: Iniciar un desafío entre dos usuarios
    Given que inicio la aplicación
    When inicio un desafío entre los siguientes usuarios:
      | Nombre   | Edad | Puntos de Sangre | Coste de Habilidad | Habilidad     | 
      | Paco     | 500  | 10               | 3                  | Transformación| 
      | Pepe     | 100  | 10               | 3                  | Transformación| 
    Then debería obtener el resultado esperado del desafío

  Scenario: Guardar y cargar desafíos
    Given que inicio la aplicación
    When guardo un desafío y lo cargo nuevamente
    Then debería obtener los mismos desafíos guardados
