package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NewLoginPage;
import utilidades.CommonFlows;

public class NewLoginStepDefinition {
    private final CommonFlows commonFlows = new CommonFlows();
    private final NewLoginPage newLoginPage = new NewLoginPage();

    @Given("El usuario ingresa a la Pagina Principal")
    public void navegarPaginaPrincipal() {
        commonFlows.goToLoginPage();
    }

    @When("El usuario da click en el boton Register de la pagina")
    public void ingresarOpcionRegistrar() {
        newLoginPage.clickRegister();
        newLoginPage.verifyPage();
    }

    @And("Luego llena el formulario de nuevo registro, finalmente damos click en el boton Registrar")
    public void completarFormulario() {
        newLoginPage.ingresarDatosFormulario();
    }

    @Then("Validamos el mensaje de existo de la cuenta nueva que se creo")
    public void validarMensajeExito() {
        newLoginPage.cargaTitulo();
        newLoginPage.verifyMessage();
    }

    @And("Luego volvemos a la ventana principal para probar el login nuevo")
    public void probarAccesoCuentaNueva() {
        newLoginPage.iniciarSesion();
    }

    @Then("Validamos el ingreso a la cuenta.")
    public void verificamosAccesoALogin() {
        newLoginPage.verificarAccesoCuenta();
    }
}
