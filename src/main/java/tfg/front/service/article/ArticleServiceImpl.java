package tfg.front.service.article;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.Article;
import tfg.front.service.AbstractClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ArticleServiceImpl extends AbstractClient implements ArticleService {
    @Autowired
    protected ArticleServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) {
        super(restTemplate, aSynchronized);
    }
    
    private static final String ARTICLES = "/articles/";

    private List<Article> getArticles(ResponseEntity<List> response) throws JsonProcessingException{
        List<Article> articles;
        ObjectMapper mapper = new ObjectMapper(); //Objeto para mapear los json a String
        String json = mapper.writeValueAsString(response.getBody()); //Guarda el valor del Json como String
        Gson gson = new Gson();

        Type typeArticleList = new TypeToken<ArrayList<Article>>(){}.getType(); //Se importa el Type de reflect para generar un tipo de dato entendible por gson
        articles = gson.fromJson(json, typeArticleList); // transforma en el tipo deseado el json que hemos recibido
        return articles;
    }
    @Override
    public List<Article> getArticles() throws JsonProcessingException {
        String uri = baseUrl+"/articles";
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class); //guarda en un objeto de tipo List la lista recibida por la Api en formato Json

        return getArticles(response);
    }

    @Override
    public List<Article> getArticlesByFamily(int idFamily) throws JsonProcessingException {
        String uri = baseUrl+"/articles/family/"+idFamily;
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET,null, List.class);

        return getArticles(response);
    }

    @Override
    public Article getArticleById(int id){
        String uri = baseUrl+ARTICLES+id;
        return restTemplate.getForObject(uri,Article.class);
    }

    @Override
    public Article create() {
        return new Article();
    }

    @Override
    public int searchPosition(List<Article> articles, int id) {
        boolean found = false;
        int counter = 0;
        int pos = -1;

        while (counter<articles.size() && !found)
        {
            if(articles.get(counter).getIdArticle()==id)
                found=true;
            else
                counter++;
        }

        if(found)
            pos = counter;

        return pos;
    }

    @Override
    public List<Article> searchArticleByName(String name) throws JsonProcessingException {
        String searchName = name+"%";
        String uri = baseUrl+"/articles/search/"+searchName;

        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getArticles(response);
    }

    @Override
    public boolean createArticle(Article article) {
        boolean created = false;
        String uri = baseUrl+"/articles";

        try {
            ResponseEntity<Article>response = restTemplate.postForEntity(uri,article, Article.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                created = true;

                String sql = "Insert into article values('" +article.getIdArticle()+ "', '" +article.getNameSales()+ "', '" +article.getPriceSales()+ "', " +
                        "'" +article.getUnits()+ "' ,'" +article.getFamily()+ "' ,'" +article.getNumBatch()+ "','" +article.getStock()+ "')";
                aSynchronized.sqlCommands.add(sql);
            }

        }catch (HttpClientErrorException e){
            return created;
        }

        return created;
    }

    @Override
    public boolean updateArticle(Article article) {
        boolean updated = false;
        String id = String.valueOf(article.getIdArticle());
        String uri = baseUrl+ARTICLES+id;
        HttpEntity<Article>entity = new HttpEntity<>(article);

        try {
            ResponseEntity<Article>response = restTemplate.exchange(uri,HttpMethod.PUT, entity ,Article.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                updated = true;
                String sql = "Update article set nameSales='" +article.getNameSales()+ "', priceSales='" +article.getPriceSales()+ "', units=" +
                        "'" +article.getUnits()+ "', family='" +article.getFamily()+ "', numBatch='" +article.getNumBatch()+ "', stock='" +article.getStock()+ "' where idArticle=" +article.getIdArticle();
                aSynchronized.sqlCommands.add(sql);
            }

        }catch (HttpClientErrorException e){
            return updated;
        }

        return updated;
    }

    @Override
    public void delete(Article article) {
        String id = String.valueOf(article.getIdArticle());
        String uri = baseUrl+ARTICLES+id;

        HttpEntity<Article> entity = new HttpEntity<>(article);
        restTemplate.exchange(uri,HttpMethod.DELETE,entity,Article.class);

        String sql = "Delete from article where idArticle="+id;
        aSynchronized.sqlCommands.add(sql);
    }
}
