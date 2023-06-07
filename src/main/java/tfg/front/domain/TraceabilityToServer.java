package tfg.front.domain;


import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TraceabilityToServer {

    @Id
    int idTraceability;
    @NotNull(message = "{Null.Traceability.article}")
    int article;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull(message = "{Null.Traceability.numberBatch}")
    int numberBatch;
    @NotNull(message = "{Null.Traceability.date}")
    Date expirationDate;

    public int getIdTraceability() {
        return idTraceability;
    }

    public void setIdTraceability(int idTraceability) {
        this.idTraceability = idTraceability;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
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
                ", article=" + article +
                ", numberBatch=" + numberBatch +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
