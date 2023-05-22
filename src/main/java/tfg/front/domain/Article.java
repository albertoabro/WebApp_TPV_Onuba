package tfg.front.domain;

public class Article {

    int idArticle;
    String nameSales;
    double priceSales;
    int units;
    int idFamily;
    int numBatch;
    int stock;

    public Article(int idArticle, String nameSales, double priceSales, int units, int idFamily, int numBatch, int stock) {
        this.idArticle = idArticle;
        this.nameSales = nameSales;
        this.priceSales = priceSales;
        this.units = units;
        this.idFamily = idFamily;
        this.numBatch = numBatch;
        this.stock = stock;
    }

    public Article() {
    }

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

    public int getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily = idFamily;
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
                ", idFamily=" + idFamily +
                ", numBatch=" + numBatch +
                ", stock=" + stock +
                '}';
    }
}
