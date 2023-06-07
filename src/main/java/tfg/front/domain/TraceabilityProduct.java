package tfg.front.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TraceabilityProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idAuto;
    int idTraceability;
    int idProduct;

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public int getIdTraceability() {
        return idTraceability;
    }

    public void setIdTraceability(int idTraceability) {
        this.idTraceability = idTraceability;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "TraceabilityProduct{" +
                "idAuto=" + idAuto +
                ", idTraceability=" + idTraceability +
                ", idProduct=" + idProduct +
                '}';
    }
}
