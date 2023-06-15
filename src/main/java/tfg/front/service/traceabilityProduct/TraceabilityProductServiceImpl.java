package tfg.front.service.traceabilityProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.Product;
import tfg.front.domain.Traceability;
import tfg.front.domain.TraceabilityProduct;
import tfg.front.service.AbstractClient;
import tfg.front.service.product.ProductService;
import tfg.front.service.traceability.TraceabilityService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TraceabilityProductServiceImpl extends AbstractClient implements TraceabilityProductService{

    private final TraceabilityService traceabilityService;
    private final ProductService productService;
    private static final String TRACEABILITY = "/traceabilityProducts";

    @Autowired
    protected TraceabilityProductServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized, ProductService productService, TraceabilityService traceabilityService){
        super(restTemplate, aSynchronized);
        this.productService = productService;
        this.traceabilityService = traceabilityService;
    }

    private List<TraceabilityProduct>getTraceabilityProducts(ResponseEntity<List> response)throws JsonProcessingException{
        List<TraceabilityProduct> list;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();

        Type typeList = new TypeToken<ArrayList<TraceabilityProduct>>(){}.getType();
        list = gson.fromJson(json,typeList);

        return list;
    }
    @Override
    public List<TraceabilityProduct> getTraceabilityProducts(int id) throws JsonProcessingException {
        String uri = baseUrl+"/traceabilityProduct/"+id;
        ResponseEntity<List>response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getTraceabilityProducts(response);
    }

    @Override
    public List<TraceabilityProduct> getTraceabilityProducts() throws JsonProcessingException {
        String uri = baseUrl+ TRACEABILITY;
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getTraceabilityProducts(response);
    }

    @Override
    public boolean create(Traceability traceability, Product product) {
        boolean created = false;
        String uri = baseUrl+ TRACEABILITY;

        try {

            productService.updateProduct(product);

            Traceability traceabilityFromServer = traceabilityService.getTraceabilityByNumberBatch(traceability.getNumberBatch());
            if(traceabilityFromServer==null)
                traceabilityFromServer = traceabilityService.createTraceability(traceability);

            TraceabilityProduct traceabilityProduct = new TraceabilityProduct();
            traceabilityProduct.setIdTraceability(traceabilityFromServer.getIdTraceability());
            traceabilityProduct.setIdProduct(product.getIdProduct());

            ResponseEntity<TraceabilityProduct> response = restTemplate.postForEntity(uri,traceabilityProduct, TraceabilityProduct.class);
            if(response.getStatusCode().is2xxSuccessful()){
                created=true;
                String sql = "Insert into traceabilityproducts values('" +traceabilityProduct.getIdAuto()+ "', " +
                        "'" +traceabilityProduct.getIdTraceability()+ "', '" +traceabilityProduct.getIdProduct()+ "')";
                
                aSynchronized.sqlCommands.add(sql);
            }
        }catch (HttpClientErrorException e){
            return created;
        }
        return created;
    }
}
