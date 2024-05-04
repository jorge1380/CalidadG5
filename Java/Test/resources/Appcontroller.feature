Feature: Registro de usuarios

  Scenario: Registrar un nuevo usuario
    Given que inicio la aplicación
    When registro un nuevo usuario con los siguientes datos:
      | Nombre    | Nickname     | Contraseña  |
      | Antonio   | AntonioNick  | AntonioPass |
    Then el usuario debería estar registrado correctamente

  Scenario: Iniciar sesión con usuario no existente
    Given que inicio la aplicación
    When intento iniciar sesión con un usuario no existente con los siguientes datos:
      | Usuario  | Contraseña |
      | Mario    | 1234       |
    Then debería recibir un mensaje de que el usuario no existe

  Scenario: Guardar y cargar datos de usuarios
    Given que inicio la aplicación
    When guardo los datos de los usuarios
    Then debería poder cargar los mismos datos y que sean iguales
