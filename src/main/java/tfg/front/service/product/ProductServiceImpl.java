package tfg.front.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.Product;
import tfg.front.domain.TraceabilityProduct;
import tfg.front.service.AbstractClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl extends AbstractClient implements ProductService {
    @Autowired
    protected ProductServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) throws IOException {
        super(restTemplate, aSynchronized);
    }

    private List<Product> getProducts(ResponseEntity<List>response) throws JsonProcessingException{
        List<Product> products;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();

        Type typeProductList = new TypeToken<ArrayList<Product>>(){}.getType();
        products = gson.fromJson(json,typeProductList);

        return products;
    }
    @Override
    public List<Product> getProducts() throws JsonProcessingException {
        String uri = baseUrl+"/products";
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getProducts(response);
    }

    @Override
    public List<Product> getProductsByProvider(int idProvider) throws JsonProcessingException {
        String uri = baseUrl+"/products/provider/"+idProvider;
        ResponseEntity<List> response = restTemplate.exchange(uri,HttpMethod.GET,null, List.class);

        return getProducts(response);
    }

    @Override
    public Product getProductById(int id){

        String uri = baseUrl+"/products/"+id;
        Product product = restTemplate.getForObject(uri, Product.class);

        return product;
    }

    @Override
    public Product create() {
       return new Product();
    }

    @Override
    public int searchPosition(List<Product> products, int id) {
        boolean found = false;
        int counter = 0, pos=-1;

        while (counter<products.size() && !found)
        {
            if(products.get(counter).getIdProduct() == id)
                found=true;
            else
                counter++;
        }

        if(found)
            pos = counter;

        return pos;
    }

    @Override
    public List<Product> searchProductByName(String nameProduct) throws JsonProcessingException {
        String searchName = nameProduct+"%";
        String uri = baseUrl+"/products/search/"+searchName;

        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getProducts(response);
    }

    @Override
    public boolean createProduct(Product product) {
        boolean created = false;
        String uri = baseUrl+"/products";

        try {
            ResponseEntity<Product> response = restTemplate.postForEntity(uri, product, Product.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                created = true;
                String sql = "Insert into product values ('" +product.getIdProduct()+ "', '" +product.getNameProduct()+ "', '" +product.getIdProvider()+ "', " +
                        "'" +product.getCategory()+ "', '" +product.getPrice()+ "', '" +product.getStock()+ "')";
                aSynchronized.sqlCommands.add(sql);
            }

        }catch (HttpClientErrorException e){
            return created;
        }
        return created;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean updated=false;
        String id = String.valueOf(product.getIdProduct());
        String uri = baseUrl+"/products/"+id;
        HttpEntity<Product> entity = new HttpEntity<>(product);
        try {
            ResponseEntity<Product> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, Product.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                updated = true;
                String sql = "Update product set nameProduct='" +product.getNameProduct()+ "', provider='" +product.getIdProvider()+ "', category=" +
                        "'" +product.getCategory()+ "', price='" +product.getPrice()+ "', stock='" +product.getStock()+ "' where idProduct=" +product.getIdProduct();
                aSynchronized.sqlCommands.add(sql);
            }

        }catch (HttpClientErrorException e){
            return updated;
        }
        return updated;
    }

    @Override
    public boolean delete(Product product) {
        String id = String.valueOf(product.getIdProduct());
        String uri = baseUrl+"/products/"+id;
        boolean deleled = false;

        HttpEntity<Product> entity = new HttpEntity<>(product);
        if(!restTemplate.exchange(uri,HttpMethod.DELETE,entity, Product.class).getStatusCode().is4xxClientError()){
            deleled =true;
            String sql = "Delete from product where idProduct="+id;
            aSynchronized.sqlCommands.add(sql);
        }

        return deleled;
    }

    @Override
    public boolean existTraceability(List<TraceabilityProduct> traceabilityProducts, Product product) {
        boolean existTraceability = false;
        int i = 0;

        while(i<traceabilityProducts.size() && !existTraceability) {
            if (traceabilityProducts.get(i).getIdProduct() == product.getIdProduct())
                existTraceability = true;
            else
                i++;
        }

        return existTraceability;
    }
}
