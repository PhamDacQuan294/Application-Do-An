
package model;

public class User {
    private String full_name, email, password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    
    public User(String full_name, String email, String password) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    
    
}
