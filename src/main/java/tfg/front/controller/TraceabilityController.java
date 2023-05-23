package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Article;
import tfg.front.domain.Product;
import tfg.front.domain.Traceability;
import tfg.front.service.article.ArticleService;
import tfg.front.service.product.ProductService;
import tfg.front.service.traceability.TraceabilityService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/traceabilities")
public class TraceabilityController {
    int id, idArticle, idProduct, numberBatch;
    Date expirationDate;
    private List<Traceability> traceabilities = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();
    private final TraceabilityService traceabilityService;
    private final ProductService productService;
    private final ArticleService articleService;

    public TraceabilityController(TraceabilityService traceabilityService, ProductService productService, ArticleService articleService) {
        this.traceabilityService = traceabilityService;
        this.productService = productService;
        this.articleService = articleService;
    }

    @GetMapping("/traceabilities")
    public ModelAndView getTraceabilities() throws JsonProcessingException {
        traceabilities=traceabilityService.getTraceabilities();

        ModelAndView modelAndView = new ModelAndView("/production/traceabilities");
        modelAndView.addObject("traceabilities",traceabilities);

        return modelAndView;
    }

    @GetMapping("/traceability")
    public ModelAndView getTraceabilityById(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Traceability searchTraceability = traceabilityService.searchTraceabilityById(traceabilities, id);
        return null;
    }

    @GetMapping("/registerTraceability")
    public ModelAndView registerTraceability() throws JsonProcessingException{
        articles = articleService.getArticles();
        products = productService.getProducts();

        ModelAndView modelAndView = new ModelAndView("/production/createTraceability");
        modelAndView.addObject("articles",articles);
        modelAndView.addObject("products",products);

        return modelAndView;
    }

    @PostMapping("/createTraceability")
    public void createTraceability(HttpServletResponse response, @RequestParam List<Integer> idProducts, @RequestParam int idArticle, @RequestParam Date expirationDate, @RequestParam int numberBatch ){
        if(traceabilities.isEmpty())
            this.id = 1;

        else
            this.id = traceabilities.get(traceabilities.size()-1).getIdTraceability()+1;

        int numIds = idProducts.size();
        this.idArticle = idArticle;
        this.expirationDate = expirationDate;
        this.numberBatch = numberBatch;

        Traceability traceability = new Traceability(id,idArticle, numberBatch, expirationDate);
        if(traceabilityService.createTraceability(traceability))
            traceabilities.add(traceability);
    }
}
