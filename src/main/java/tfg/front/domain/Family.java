package tfg.front.domain;

public class Family {

    int idFamily;
    String nameFamily;

    public Family(int idFamily, String nameFamily) {
        this.idFamily = idFamily;
        this.nameFamily = nameFamily;
    }

    public Family() {
    }

    public int getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily = idFamily;
    }

    public String getNameFamily() {
        return nameFamily;
    }

    public void setNameFamily(String nameFamily) {
        this.nameFamily = nameFamily;
    }

    @Override
    public String
    toString() {
        return "Family{" +
                "idFamily=" + idFamily +
                ", nameFamily='" + nameFamily + '\'' +
                '}';
    }
}
