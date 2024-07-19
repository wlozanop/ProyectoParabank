package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilidades.DriverManager;
import utilidades.FileManager;
import utilidades.Logs;

public class Hooks {
    private static final DriverManager driverManager = new DriverManager();

    @BeforeAll
    public static void masterSetup() {
        Logs.info("Before All");
    }

    @AfterAll
    public static void masterTearDown() {
        Logs.info("After All");
    }

    @Before
    public static void before(Scenario scenario) {
        Logs.info("Before: %s", scenario.getName());
        driverManager.buildDriver();
    }

    @After
    public static void after(Scenario scenario) {
        Logs.info("After: %s Status: %s", scenario.getName(), scenario.getStatus());
        switch (scenario.getStatus()) {
            case FAILED, SKIPPED -> {
                FileManager.attachPageSource(scenario);
                FileManager.attachScreenshot(scenario);
                FileManager.getScreenshot(scenario.getName());
                FileManager.getPageSource(scenario.getName());
            }
            case PASSED -> {
                FileManager.getScreenshot(scenario.getName());
                FileManager.getPageSource(scenario.getName());
            }
        }
        driverManager.killDriver();
    }
}
