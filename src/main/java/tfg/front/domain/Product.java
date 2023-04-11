package tfg.front.domain;

public class Product {

    int idProduct;
    String nameProduct;
    int idProvider;
    String category;
    double price;

    public Product(int idProduct, String nameProduct, int idProvider, String category, double price) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.idProvider = idProvider;
        this.category = category;
        this.price = price;
    }

    public Product() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", idProvider=" + idProvider +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
