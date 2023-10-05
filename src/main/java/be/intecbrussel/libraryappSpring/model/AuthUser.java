package be.intecbrussel.libraryappSpring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AuthUser {

    @Id
    private String email;
    private String password;
    private boolean isAdmin;

    public AuthUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected AuthUser(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
