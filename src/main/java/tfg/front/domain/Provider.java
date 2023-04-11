package tfg.front.domain;

public class Provider {
    int idProvider;
    String nameProvider;
    String address;
    String phone;
    String products;

    public Provider(int idProvider, String nameProvider, String address, String phone, String products) {
        this.idProvider = idProvider;
        this.nameProvider = nameProvider;
        this.address = address;
        this.phone = phone;
        this.products = products;
    }

    public Provider() {
    }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", nameProvider='" + nameProvider + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", products='" + products + '\'' +
                '}';
    }
}
