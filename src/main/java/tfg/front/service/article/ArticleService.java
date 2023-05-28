package tfg.front.service.article;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getArticles() throws JsonProcessingException;
    List<Article> getArticlesByFamily(int idFamily) throws JsonProcessingException;
    Article getArticleById(int id) throws JsonProcessingException;
    Article create();
    int searchPosition(List<Article> articles, int id);
    List<Article> searchArticleByName(String name) throws JsonProcessingException;
    boolean createArticle(Article article);
    boolean updateArticle(Article article);

    void delete(Article article);
}
