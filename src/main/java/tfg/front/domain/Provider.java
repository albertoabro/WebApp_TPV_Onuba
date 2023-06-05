package tfg.front.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idProvider;
    @NotBlank(message = "{Blank.Provider.name}")
    @Size(max = 50,message = "{Size.Provider.name}")
    String nameProvider;
    @NotBlank
    @Size(max = 200,message = "{Size.Provider.Address}")
    String address;
    @Pattern(regexp = "^(\\+\\d{1,2}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", message ="{Pattern.Provider.phone}")
    String phone;
    @Size(max = 50,message = "{Size.Provider.description}")
    String productDescription;

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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", nameProvider='" + nameProvider + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
