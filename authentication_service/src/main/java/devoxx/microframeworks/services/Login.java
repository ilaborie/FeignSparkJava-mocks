package devoxx.microframeworks.services;

public class Login {
    private String email;
    private String password; // FIXME use char[]

    @Override
    public String toString() {
        return String.format("Login{email='%s', password='xxx'}", email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
