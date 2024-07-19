package utilidades;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver() {
        if (runServer) {
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    private void buildLocalDriver() {
        final var headlessMode = System.getProperty("headless") != null;
        var browserProperty = System.getProperty("browser");

        if (browserProperty == null) { // Si no lo paso por linea de comandos
            browserProperty = "CHROME";
        }

        final var browser = Browser.valueOf(browserProperty.toUpperCase());

        Logs.debug("Inicializando Driver");
        final var driver = switch (browser) {
            case CHROME -> {
                final var chromeOptions = new ChromeOptions();
                if (headlessMode) {
                    chromeOptions.addArguments("--headless=new");
                }
                yield new ChromeDriver(chromeOptions);
            }
        };

        Logs.debug("Maximizando la pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        new WebDriverProvider().set(driver);
    }

    public void killDriver() {
        Logs.debug("Matando Driver");
        new WebDriverProvider().get().quit();
    }

    private void buildRemoteDriver() {
    }

    private enum Browser {
        CHROME,
    }

}
