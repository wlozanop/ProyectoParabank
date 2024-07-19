package utilidades;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots";
    private final static String pageStructurePath = "src/test/resources/pageStructure";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Taking screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error al tomar screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando Page Source");
        final var path = String.format("%s/page-structure-%s.html", pageStructurePath, fileName);

        try {
            final var file = new File(path);
            Logs.debug("Creating file parents");
            if (file.getParentFile().mkdirs()) {
                final var fileWrite = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                fileWrite.write(Jsoup.parse(pageSource).toString());
                fileWrite.close();
            } else {
                Logs.debug("no existe el padre");
            }
        } catch (IOException ioException) {
            Logs.error("Failed getting page source: %s", ioException.getLocalizedMessage());
        }
    }

    //Obtener el Screenshot
    public static void attachScreenshot(Scenario scenario) {
        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).
                getScreenshotAs(OutputType.BYTES);

        scenario.attach(
                screenshotFile,
                "imagen/png",
                String.format("screenshot-%s", scenario.getName())
        );
    }

    //Obtener el el HTML de la pagina
    public static void attachPageSource(Scenario scenario) {
        final var pageSource = new WebDriverProvider().get().getPageSource();
        final var parsePageSource = Jsoup.parse(pageSource).toString();

        scenario.attach(
                parsePageSource,
                "text/plain",
                String.format("pageSource-%s", scenario.getName())
        );
    }

    public static void deleteProviousEvidencen() {
        try {
            Logs.debug("Borrando la evidencia Anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));
        } catch (IOException ioException) {
            Logs.error("Error al tomar Evidencia: %s", ioException.getLocalizedMessage());
        }
    }
}
