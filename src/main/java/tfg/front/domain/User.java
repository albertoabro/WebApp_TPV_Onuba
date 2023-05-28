package tfg.front.domain;


import jakarta.validation.constraints.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idUser;
    @NotBlank(message = "{Blank.User.username}")
    @Size(max = 20, message = ("Size.User.username"))
    String userName;
    @Pattern(regexp = "^\\d{0,20}$", message ="{Pattern.User.password}")
    String password;
    @Size(max = 200, message = "{Size.User.Address}")
    String address;
    @Pattern(regexp = "^(\\+\\d{1,2}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", message = "{Pattern.User.phone}")
    String phone;
    @NotNull(message = "{Null.User.TypeUser}")
    int typeUser;
    @Pattern(regexp = "^\\d{0,4}$", message ="{Size.User.passTPV}")
    String passwordTPV;

    public User() {}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    public String getPasswordTPV() {
        return passwordTPV;
    }

    public void setPasswordTPV(String passwordTPV) {
        this.passwordTPV = passwordTPV;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", typeUser=" + typeUser +
                ", passwordTPV='" + passwordTPV + '\'' +
                '}';
    }
}
