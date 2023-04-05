package tfg.front.domain;

public class TypeUser {
    int id;
    String denomination;
    int rol;

    public TypeUser(int id, String denomination, int rol) {
        this.id = id;
        this.denomination = denomination;
        this.rol = rol;
    }

    public TypeUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
