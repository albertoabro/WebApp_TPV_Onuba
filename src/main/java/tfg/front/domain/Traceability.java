package tfg.front.domain;

import java.util.Date;

public class Traceability {
    int idTraceability;
    int idArticle;
    int numberBatch;
    Date expirationDate;

    public Traceability(int idTraceability, int idArticle, int numberBatch, Date expirationDate) {
        this.idTraceability = idTraceability;
        this.idArticle = idArticle;
        this.numberBatch = numberBatch;
        this.expirationDate = expirationDate;
    }

    public Traceability() {
    }

    public int getIdTraceability() {
        return idTraceability;
    }

    public void setIdTraceability(int idTraceability) {
        this.idTraceability = idTraceability;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getNumberBatch() {
        return numberBatch;
    }

    public void setNumberBatch(int numberBatch) {
        this.numberBatch = numberBatch;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Traceability{" +
                "idTraceability=" + idTraceability +
                ", idArticle=" + idArticle +
                ", numberBatch=" + numberBatch +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
