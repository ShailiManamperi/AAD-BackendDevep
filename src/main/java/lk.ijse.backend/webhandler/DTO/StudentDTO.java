package lk.ijse.backend.webhandler.DTO;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    private String Id;
    private String name;
    private String city;
    private String email;
    private int level;

    public StudentDTO() {
    }

    public StudentDTO(String id,String name, String city, String email, int level) {
        this.Id=id;
        this.name = name;
        this.city = city;
        this.email = email;
        this.level = level;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "name=" + name + '\n' +
                ", city=" + city + '\n' +
                ", email=" + email + '\n' +
                ", level=" + level ;
    }
}
