package tfg.front.service.article;

import org.springframework.web.client.RestTemplate;
import tfg.front.service.AbstractClient;

public class ArticleServiceImpl extends AbstractClient implements ArticleService {
    protected ArticleServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }
}
