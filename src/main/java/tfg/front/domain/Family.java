package tfg.front.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idFamily;
    @NotBlank(message = "{Blank.Family.name}")
    @Size(max = 50, message = "{Size.Family.name}")
    String nameFamily;

    public Family() {}

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
