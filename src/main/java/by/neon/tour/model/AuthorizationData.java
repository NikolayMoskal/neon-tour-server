package by.neon.tour.model;

/**
 * @author Nikolay Moskal
 */
public final class AuthorizationData {
    private String firstName;
    private String lastName;
    private long birthDate;
    private String email;
    private String username;
    private String password;

    /**
     * Builds a new object of AuthorizationData
     *
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param email
     * @param username
     * @param password
     */
    public AuthorizationData(String firstName, String lastName, long birthDate, String email, String username,
                             String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Builds a new object of AuthorizationData
     */
    public AuthorizationData() {
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the birthdate
     */
    public long getBirthDate() {
        return birthDate;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
