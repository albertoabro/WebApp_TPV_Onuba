package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Article;
import tfg.front.domain.Family;
import tfg.front.domain.Traceability;
import tfg.front.service.article.ArticleService;
import tfg.front.service.family.FamilyService;
import tfg.front.service.traceability.TraceabilityService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private List<Article> articles  = new ArrayList<>();
    private List<Family> families = new ArrayList<>();
    private final ArticleService articleService;
    private final FamilyService familyService;
    private final TraceabilityService traceabilityService;

    public ArticleController(ArticleService articleService, FamilyService familyService, TraceabilityService traceabilityService) {
        this.articleService = articleService;
        this.familyService = familyService;
        this.traceabilityService = traceabilityService;
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
        int id = Integer.parseInt(request.getParameter("id"));
        Article searchArticle = articleService.getArticleById(id);
        families = familyService.getFamilies();

        ModelAndView modelAndView;

        if(searchArticle!=null){
            modelAndView = new ModelAndView("/article/article");
            modelAndView.addObject("article", searchArticle);
            modelAndView.addObject("families", families);
        }

        else
            modelAndView = getArticles();

        return modelAndView;
    }

    @GetMapping("/registerArticle")
    public ModelAndView registerArticle() throws JsonProcessingException{
        Article article = articleService.create();

        return createEdit(article,false);
    }

    @GetMapping("/editArticle")
    public ModelAndView viewEditArticle(HttpServletRequest request) throws JsonProcessingException{
        int id = Integer.parseInt(request.getParameter("id"));
        Article article = articleService.getArticleById(id);

        return createEdit(article,true);
    }

    @GetMapping("/searchArticle")
    public ModelAndView searchArticleByName(@RequestParam String nameArticle) throws JsonProcessingException{

        List<Article> searchArticle = articleService.searchArticleByName(nameArticle);
        ModelAndView modelAndView = new ModelAndView("/article/articles");

        if(!searchArticle.isEmpty())
            modelAndView.addObject("articles",searchArticle);

        return modelAndView;
    }

    @GetMapping("/articlesByFamily")
    public ModelAndView articlesByFamily(HttpServletRequest request) throws JsonProcessingException {
        int idFamily = Integer.parseInt(request.getParameter("id"));
        List<Article> articleList = articleService.getArticlesByFamily(idFamily);
        ModelAndView modelAndView = new ModelAndView("/article/articles");
        modelAndView.addObject("articles", articleList);

        return modelAndView;
    }

    @PostMapping("/addArticle")
    public ModelAndView createArticle(@Valid Article article, BindingResult result) throws IOException{

        if(result.hasErrors())
            return createEdit(article,false,result.toString());

        if(articleService.createArticle(article))
            articles.add(article);

        return getArticles();
    }

    @PutMapping("/editArticle")
    public ModelAndView updateArticle(@Valid Article article, BindingResult result) throws IOException{

        if(result.hasErrors())
            return createEdit(article,true,result.toString());

        if(articleService.updateArticle(article)){
            int pos = articleService.searchPosition(articles, article.getIdArticle());
            if(pos!=-1)
                articles.set(pos, article);
        }

        return getArticles();
    }

    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam int idArticle) throws JsonProcessingException {
        Article article = articleService.getArticleById(idArticle);
        String msg = "Error: El artículo no existe";

        if(article==null)
            return getArticles();

        List<Traceability> traceabilities = traceabilityService.getTraceabilities();
        boolean existTraceability = false;
        int i=0;


        while(i<traceabilities.size() && !existTraceability) {
            if (traceabilities.get(i).getArticle() == article.getIdArticle())
                existTraceability = true;
            else
                i++;
        }

        if(!existTraceability)
            articleService.delete(article);

        return getArticles();
    }

    private ModelAndView createEdit(Article article, boolean edit) throws JsonProcessingException {
        return createEdit(article,edit, null);
    }
    private ModelAndView createEdit(Article article, boolean edit, String message) throws JsonProcessingException {
        families = familyService.getFamilies();

        ModelAndView modelAndView;

        if(edit)
            modelAndView=new ModelAndView("/article/editArticle");
        else {
           
            modelAndView = new ModelAndView("/article/createArticle");
        }
        modelAndView.addObject("families",families);
        modelAndView.addObject("article",article);

        return modelAndView;
    }

}
