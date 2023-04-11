package tfg.front.service.product;

import org.springframework.web.client.RestTemplate;
import tfg.front.service.AbstractClient;

public class ProductServiceImpl extends AbstractClient implements ProductService {
    protected ProductServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }
}
