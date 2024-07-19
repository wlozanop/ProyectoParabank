package utilidades;

import org.openqa.selenium.WebDriver;
import pages.NewLoginPage;

public class CommonFlows {
    private WebDriver getDriver() {
        return new WebDriverProvider().get();
    }

    public void goToBodyPage() {
        getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
        new NewLoginPage().waitPageToLoad();
    }

    public void goToLoginPage() {
        goToBodyPage();
    }

}
