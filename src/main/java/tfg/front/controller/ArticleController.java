package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Article;
import tfg.front.domain.Family;
import tfg.front.service.article.ArticleService;
import tfg.front.service.family.FamilyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticleController {
    int id, idFamily, unit, numBatch, stock;
    String nameArticle;
    double price;
    private List<Article> articles  = new ArrayList<>();
    private List<Family> families = new ArrayList<>();
    private final ArticleService articleService;
    private final FamilyService familyService;

    public ArticleController(ArticleService articleService, FamilyService familyService) {
        this.articleService = articleService;
        this.familyService = familyService;
    }

    @GetMapping("/articles")
    public ModelAndView getArticles() throws JsonProcessingException{
        articles=articleService.getArticles();

        ModelAndView modelAndView = new ModelAndView("/article/articles");
        modelAndView.addObject("articles", articles);

        return modelAndView;
    }

    @GetMapping("/article")
    public ModelAndView getArticleById(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Article searchArticle = articleService.searchProviderById(articles,id);
        families = familyService.getFamilies();

        ModelAndView modelAndView;

        if(searchArticle!=null){
            modelAndView = new ModelAndView("/article/article");
            modelAndView.addObject("article", searchArticle);
            modelAndView.addObject("families", families);
        }

        else
            modelAndView = new ModelAndView();

        return modelAndView;
    }

    @GetMapping("/registerArticle")
    public ModelAndView registerArticle() throws JsonProcessingException{
        families = familyService.getFamilies();
        ModelAndView modelAndView = new ModelAndView("/article/createArticle");
        modelAndView.addObject("families",families);
        return modelAndView;
    }

    @GetMapping("/editArticle")
    public ModelAndView viewEditArticle(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Article searchArticle = articleService.searchProviderById(articles, id);
        families = familyService.getFamilies();
        ModelAndView modelAndView;

        if(searchArticle!=null){
            modelAndView = new ModelAndView("/article/editArticle");
            modelAndView.addObject("article",searchArticle);
            modelAndView.addObject("families",families);
        }

        else modelAndView = getArticles();

        return modelAndView;
    }

    @GetMapping("/searchArticle")
    public ModelAndView searchArticleByName(@RequestParam String nameArticle) throws JsonProcessingException{
        List<Article> searchArticle = articleService.searchArticleByName(nameArticle);
        ModelAndView modelAndView = new ModelAndView("/article/articles");

        if(!searchArticle.isEmpty())
            modelAndView.addObject("articles",searchArticle);

        return modelAndView;
    }

    @PostMapping("/createArticle")
    public void createArticle(HttpServletResponse response, @RequestParam String nameArticle, @RequestParam double price, @RequestParam int idFamily, @RequestParam int numBatch, @RequestParam int stock) throws IOException{
        if(articles.isEmpty())
            this.id = 1;
        else
            this.id = articles.get(articles.size()-1).getIdArticle()+1;

        this.nameArticle = nameArticle;
        this.price = price;
        this.unit = 0;
        this.idFamily = idFamily;
        this.numBatch = numBatch;
        this.stock = stock;

        Article article = new Article(id, nameArticle, price, unit, idFamily, numBatch, stock);
        if(articleService.createArticle(article))
            articles.add(article);

        response.sendRedirect("/articles/articles");
    }

    @PutMapping("/editArticle")
    public void updateArticle(HttpServletResponse response, @RequestParam int id, @RequestParam String nameArticle, @RequestParam double price, @RequestParam int unit, @RequestParam int idFamily, @RequestParam int numBatch, @RequestParam int stock) throws IOException{
        this.id = id;
        this.nameArticle = nameArticle;
        this.price = price;
        this.unit = unit;
        this.idFamily = idFamily;
        this.numBatch = numBatch;
        this.stock = stock;

        Article article = new Article(id, nameArticle, price, unit, idFamily, numBatch,stock);
        if(articleService.updateArticle(article)){
            int pos = articleService.searchPosition(articles, id);
            if(pos!=-1)
                articles.set(pos, article);
        }

        response.sendRedirect("/articles/articles");
    }
}
