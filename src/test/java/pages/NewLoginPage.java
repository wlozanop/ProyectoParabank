package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilidades.BasePage;
import utilidades.DatosUser;
import utilidades.Logs;

public class NewLoginPage extends BasePage {
    private final By Register = By.xpath("//a[text()='Register']");
    private final By campoFirstName = By.id("customer.firstName");
    private final By campoLastName = By.id("customer.lastName");
    private final By campoAddress = By.id("customer.address.street");
    private final By campoCity = By.id("customer.address.city");
    private final By campoState = By.id("customer.address.state");
    private final By campoZipCode = By.id("customer.address.zipCode");
    private final By campoPhone = By.id("customer.phoneNumber");
    private final By campoSsn = By.id("customer.ssn");
    private final By usuario = By.id("customer.username");
    private final By password = By.id("customer.password");
    private final By confirmar = By.id("repeatedPassword");
    private final By mensaje = By.cssSelector("div[id='rightPanel'] > p");
    private final String contenidoMensaje = "Your account was created successfully. You are now logged in.";
    private final By titulo = By.xpath("//h1[contains(text(),'Welcome')]");
    private final By botonRegistrar = By.cssSelector("input[value='Register']");
    private final By campoUserName = By.cssSelector("input[name='username']");
    private final By campoPassword = By.cssSelector("input[name='password']");
    private final By botonLogin = By.cssSelector("input[value='Log In']");
    private final By botonLogout = By.cssSelector("a[href='logout.htm']");
    private final By iniciarSesion = By.id("showOverview");
    private final DatosUser datosUser = new DatosUser();
    String guardarUsuario = "";
    String guardarpassword = "";

    @Override
    public void waitPageToLoad() {
        waitPage(Register, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando los campos del formulario");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(campoFirstName).isDisplayed(),
                        "Fallo en el campo firstName"),
                () -> Assertions.assertTrue(find(campoLastName).isDisplayed(),
                        "Fallo en el campo LastName"),
                () -> Assertions.assertTrue(find(campoAddress).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(campoCity).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(campoState).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(campoZipCode).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(campoPhone).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(campoSsn).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(usuario).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(password).isDisplayed(),
                        "Fallo en el campo address"),
                () -> Assertions.assertTrue(find(confirmar).isDisplayed(),
                        "Fallo en el campo address")
        );
    }

    public void clickRegister() {
        Logs.info("Vamos a dar click en la opcion Registrer para ingresar a crear una cuenta");
        find(Register).click();
    }

    public void ingresarDatosFormulario() {
        Logs.info("Completamos datos del formulario");
        find(campoFirstName).sendKeys(datosUser.getFirstName());
        find(campoLastName).sendKeys(datosUser.getLastName());
        find(campoAddress).sendKeys(datosUser.getAddress());
        find(campoCity).sendKeys(datosUser.getCity());
        find(campoState).sendKeys(datosUser.getState());
        find(campoZipCode).sendKeys(datosUser.getZipCode());
        find(campoPhone).sendKeys(datosUser.getPhone());
        find(campoSsn).sendKeys(datosUser.getSsn());

        Logs.debug("Datos de acceso: nuevo usuario %s%n clave: %s%n confirmarClave: %s",
                datosUser.getUserName(),
                datosUser.getPassword(),
                datosUser.getConfirm()
        );
        guardarUsuario = datosUser.getUserName();
        guardarpassword = datosUser.getPassword();
        find(usuario).sendKeys(datosUser.getUserName());
        find(password).sendKeys(datosUser.getPassword());
        find(confirmar).sendKeys(datosUser.getConfirm());

    }

    public void darClickBotonRegistrar() {
        Logs.info("Vamos a dar click en registrar");
        find(botonRegistrar).click();
    }

    public void cargaTitulo() {
        Logs.info("Esperar que cargue el titulo despues de un registro Exitoso");
        waitPage(titulo, this.getClass().getSimpleName());
    }

    public void verifyMessage() {
        Logs.info("Verificamos el mensaje de exito de la cuenta nueva");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(mensaje).isDisplayed(),
                        "fallo en el mensaje"),
                () -> Assertions.assertEquals(find(mensaje).getText(),
                        contenidoMensaje,
                        "fallo en el texto del mensaje")
        );
    }

    public void iniciarSesion() {
        Logs.info("Vamos a iniciar sesión con la cuenta nueva");
        find(botonLogout).click();
        find(campoUserName).sendKeys(guardarUsuario);
        find(campoPassword).sendKeys(guardarpassword);
        find(botonLogin).click();
    }

    public void verificarAccesoCuenta() {
        Logs.info("Verificamos que iniciamos sesion con la cuenta nueva");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(iniciarSesion).isDisplayed(),
                        "fallo en iniciar sesion visible"),
                () -> Assertions.assertTrue(find(iniciarSesion).isEnabled(),
                        "fallo en iniciar habilitado")
        );
    }
}
