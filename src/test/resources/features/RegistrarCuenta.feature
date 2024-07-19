Feature: Navegar a la pagina parabank

  Background:Abrir la vista principal de la pagina
    Given El usuario ingresa a la Pagina Principal

  Scenario: Crear una cuenta nueva de usuario para luego loguearse
    When El usuario da click en el boton Register de la pagina
    And Luego llena el formulario de nuevo registro, finalmente damos click en el boton Registrar
    Then Validamos el mensaje de existo de la cuenta nueva que se creo
    And Luego volvemos a la ventana principal para probar el login nuevo
    Then Validamos el ingreso a la cuenta.