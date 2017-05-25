package edu.hm.authentification.model;

/**
 * Container for the credentials of a user, e.g. name and password.
 */
public class Credentials {

    private String name;

    private String password;

    public Credentials() {
        this("", "");
    }

    public Credentials(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Credentials that = (Credentials) o;

        if (!name.equals(that.name)) {
            return false;
        }

        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
