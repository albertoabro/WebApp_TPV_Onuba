package tfg.front.domain;

import lombok.Data;

@Data
public class User {

    int id;
    String userName;
    String password;
    String address;
    String phone;
    int typeUser;

    public User(int id, String userName, String password, String address, String phone, int typeUser) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.typeUser = typeUser;
    }
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", typeUser=" + typeUser +
                '}';
    }
}
