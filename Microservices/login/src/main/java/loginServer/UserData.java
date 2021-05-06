package loginServer;
import java.io.Serializable;
import java.text.MessageFormat;

public class UserData implements Serializable {

    private String name;
    private String password;
    private String registrationDate;
    private String email;

    public UserData() {

    }

    public UserData(final String name, final String password, final String registrationDate, final String email) {
        this.name = name;
        this.password = password;
        this.registrationDate = registrationDate;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return MessageFormat
                .format("UserData: {0} - {1} - {2} - {3}", name, password, registrationDate, email);
    }

}
