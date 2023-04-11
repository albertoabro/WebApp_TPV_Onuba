package tfg.front.service.traceability;

import org.springframework.web.client.RestTemplate;
import tfg.front.service.AbstractClient;

public class TraceabilityServiceImpl extends AbstractClient implements TraceabilityService{
    protected TraceabilityServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }
}
