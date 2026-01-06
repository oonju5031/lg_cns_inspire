package lgcns.domain.user;

public class UserRequestDTO {

    private String email, password, name;

    
    public UserRequestDTO() {

    }

    public UserRequestDTO(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

}
