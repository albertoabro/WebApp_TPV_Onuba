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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.domain.Product;
import tfg.front.service.AbstractClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl extends AbstractClient implements ProductService {
    @Autowired
    protected ProductServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
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
    public Product searchProductById(List<Product> products, int id) {
        boolean found = false;
        int counter = 0;
        Product searchProduct;

        while (counter<products.size() && !found)
        {
            if(products.get(counter).getIdProduct() == id)
                found=true;
            else
                counter++;
        }

        if(found)
            searchProduct = products.get(counter);
        else
            searchProduct=null;

        return searchProduct;
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
            if(response.getStatusCode().is2xxSuccessful())
                created=true;

        }catch (HttpClientErrorException e){
            log.error("Error: "+ e.toString());
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
            if(response.getStatusCode().is2xxSuccessful())
                updated=true;

        }catch (HttpClientErrorException e){
            log.error("Error: "+e.toString());
        }
        return updated;
    }
}
