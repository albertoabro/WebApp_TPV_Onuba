package tfg.front.domain;

import lombok.Data;

@Data
public class User {

    int idUser;
    String userName;
    String password;
    String address;
    String phone;
    int typeUser;

    public User(int idUser, String userName, String password, String address, String phone, int typeUser) {
        this.idUser= idUser;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.typeUser = typeUser;
    }
    public User() {
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + idUser +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", typeUser=" + typeUser +
                '}';
    }
}
