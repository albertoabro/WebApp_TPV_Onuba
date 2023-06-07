package tfg.front.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idTypeUser;
    String denomination;
    int rol;

    public TypeUser(int idTypeUser, String denomination, int rol) {
        this.idTypeUser = idTypeUser;
        this.denomination = denomination;
        this.rol = rol;
    }

    public int getId() {
        return idTypeUser;
    }

    public void setId(int idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "TypeUser{" +
                "idTypeUser=" + idTypeUser +
                ", denomination='" + denomination + '\'' +
                ", rol=" + rol +
                '}';
    }
}
