package tfg.front.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Product;
import tfg.front.domain.TraceabilityProduct;

import java.util.List;

public interface ProductService {

    List<Product> getProducts() throws JsonProcessingException;
    List<Product> getProductsByProvider(int idProvider) throws JsonProcessingException;
    Product getProductById(int id) throws JsonProcessingException;
    Product create();
    int searchPosition(List<Product>products, int id);
    List<Product> searchProductByName(String nameProduct) throws JsonProcessingException;
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean delete(Product product);
    boolean existTraceability(List<TraceabilityProduct> traceabilityProducts, Product product);
}
