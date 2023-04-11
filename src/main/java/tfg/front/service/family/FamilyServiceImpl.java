package tfg.front.service.family;

import org.springframework.web.client.RestTemplate;
import tfg.front.service.AbstractClient;

public class FamilyServiceImpl extends AbstractClient implements FamilyService {
    protected FamilyServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }
}
