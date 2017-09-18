package domain;

/**
 * Created by antonia on 2017/09/18.
 */
public class Admin {
    private String name;
    private String password;

    public Admin(String name, String password){
        this.name = name;
        this.password = password;
    }
    // getters and setters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

