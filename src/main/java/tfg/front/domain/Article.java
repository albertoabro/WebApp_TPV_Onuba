package tfg.front.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idArticle;

    @NotBlank(message = "{Blank.Article.name}")
    String nameSales;
    @NotNull(message = "{Null.Article.price}")
    double priceSales;
    int units;
    @NotNull(message = "{Null.Article.family}")
    int family;
    @NotNull(message = "{Null.Article.numberBatch}")
    int numBatch;
    @NotNull(message = "{Null.Article.stock}")
    int stock;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNameSales() {
        return nameSales;
    }

    public void setNameSales(String nameSales) {
        this.nameSales = nameSales;
    }

    public double getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(double priceSales) {
        this.priceSales = priceSales;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public int getNumBatch() {
        return numBatch;
    }

    public void setNumBatch(int numBatch) {
        this.numBatch = numBatch;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", nameSales='" + nameSales + '\'' +
                ", priceSales=" + priceSales +
                ", units=" + units +
                ", family=" + family +
                ", numBatch=" + numBatch +
                ", stock=" + stock +
                '}';
    }
}
