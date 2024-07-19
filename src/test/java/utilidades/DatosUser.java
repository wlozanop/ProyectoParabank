package utilidades;

import com.github.javafaker.Faker;

public class DatosUser {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String ssn;
    private String userName;
    private String password;
    private String confirm;

    public DatosUser() {
        final var faker = new Faker();

        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        address = faker.internet().emailAddress();
        city = faker.address().city();
        state = faker.address().state();
        zipCode = faker.address().zipCode();
        phone = faker.phoneNumber().phoneNumber();
        ssn = faker.app().version();

        userName = faker.name().username();
        password = faker.internet().password();
        confirm = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getSsn() {
        return ssn;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }
}
