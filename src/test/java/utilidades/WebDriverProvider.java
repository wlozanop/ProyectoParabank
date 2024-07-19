package utilidades;

import org.openqa.selenium.WebDriver;

//METODO QUE SIRVE PARA INVOCAR EL DRIVER DEL NAVEGADOR DE CUALQUIER CLASE
public class WebDriverProvider {
    private static final ThreadLocal<WebDriver> threadlocal = new ThreadLocal<>();

    public void set(WebDriver driver) {
        threadlocal.set(driver);
    }

    public WebDriver get() {
        return threadlocal.get();
    }
}
