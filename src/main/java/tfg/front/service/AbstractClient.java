package tfg.front.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;

import java.io.IOException;

public abstract class AbstractClient {
    @Value("${api.base-url}")
    protected String baseUrl;

    protected final RestTemplate restTemplate;
    protected final Synchronized aSynchronized;
    protected AbstractClient(RestTemplate restTemplate, Synchronized aSynchronized) throws IOException {
        super();
        this.restTemplate=restTemplate;
        this.aSynchronized = aSynchronized;
    }

}
