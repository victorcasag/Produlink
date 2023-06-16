package BigHouse.Produlink.LibraryProdulink.Login;

public class ModelLogin {
    public Long id;
    public String name;
    public String login;
    public String password;
    public String role;

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
