package tfg.front.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts() throws JsonProcessingException;
    Product searchProductById(List<Product> products, int id);
    int searchPosition(List<Product>products, int id);
    List<Product> searchProductByName(String nameProduct) throws JsonProcessingException;

    boolean createProduct(Product product);
    boolean updateProduct(Product product);
}
