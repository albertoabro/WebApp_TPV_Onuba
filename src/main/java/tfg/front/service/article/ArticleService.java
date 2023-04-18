package tfg.front.service.article;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getArticles() throws JsonProcessingException;
    Article searchProviderById(List<Article> articles, int id);
    int searchPosition(List<Article> articles, int id);
    List<Article> searchArticleByName(String name) throws JsonProcessingException;
    boolean createArticle(Article article);
    boolean updateArticle(Article article);
}
