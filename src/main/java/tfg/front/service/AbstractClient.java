package tfg.front.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;

public abstract class AbstractClient {
    @Value("${api.base-url}")
    protected String baseUrl;

    protected final RestTemplate restTemplate;
    protected final Synchronized aSynchronized;
    protected AbstractClient(RestTemplate restTemplate, Synchronized aSynchronized){
        super();
        this.restTemplate=restTemplate;
        this.aSynchronized = aSynchronized;
    }

}
