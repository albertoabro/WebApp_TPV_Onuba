package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.*;
import tfg.front.service.article.ArticleService;
import tfg.front.service.product.ProductService;
import tfg.front.service.traceability.TraceabilityService;
import tfg.front.service.traceabilityProduct.TraceabilityProductService;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/traceabilities")
@Transactional
public class TraceabilityController {
    private List<Traceability> traceabilities = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();
    private final TraceabilityService traceabilityService;
    private final ProductService productService;
    private final ArticleService articleService;
    private final TraceabilityProductService traceabilityProductService;

    public TraceabilityController(TraceabilityService traceabilityService, ProductService productService, ArticleService articleService, TraceabilityProductService traceabilityProductService) {
        this.traceabilityService = traceabilityService;
        this.productService = productService;
        this.articleService = articleService;
        this.traceabilityProductService = traceabilityProductService;
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
        ModelAndView modelAndView = new ModelAndView("/production/traceability");
        int idTraceability = Integer.parseInt(request.getParameter("id"));
        Traceability traceability = traceabilityService.getTraceabilityById(idTraceability);
        List<TraceabilityProduct> traceabilityProducts = traceabilityProductService.getTraceabilityProducts(traceability.getIdTraceability());
        List<Integer> idsProducts = new ArrayList<>();

        for(TraceabilityProduct product : traceabilityProducts)
            idsProducts.add(product.getIdProduct());

        Article article = articleService.getArticleById(traceability.getArticle());

        modelAndView.addObject("traceability", traceability);
        modelAndView.addObject("article", article);
        modelAndView.addObject("idsProducts",idsProducts);

        return modelAndView;
    }

    @GetMapping("/searchTraceability")
    public ModelAndView search(@RequestParam int numberBatch) throws JsonProcessingException {
        Traceability traceability = traceabilityService.getTraceabilityByNumberBatch(numberBatch);
        ModelAndView modelAndView = new ModelAndView("/production/traceability");

        if(traceability !=null){
            List<TraceabilityProduct> traceabilityProducts = traceabilityProductService.getTraceabilityProducts(traceability.getIdTraceability());
            List<Integer> idsProducts = new ArrayList<>();

            for(TraceabilityProduct product : traceabilityProducts)
                idsProducts.add(product.getIdProduct());

            Article article = articleService.getArticleById(traceability.getArticle());

            modelAndView.addObject("traceability", traceability);
            modelAndView.addObject("article", article);
            modelAndView.addObject("idsProducts",idsProducts);

            return modelAndView;
        }

        else
            return getTraceabilities();
    }

    @GetMapping("/registerTraceability")
    public ModelAndView registerTraceability() throws JsonProcessingException{
        Traceability traceability = traceabilityService.create();
        return createEdit(traceability);
    }

    @PostMapping("/createTraceability")
    public ModelAndView createTraceability(@Valid Traceability traceability, BindingResult result) throws JsonProcessingException {

        if(result.hasErrors())
            return createEdit(traceability,result.toString());

        ModelAndView modelAndView = new ModelAndView("/production/products");
        products = productService.getProducts();

        modelAndView.addObject("traceability", traceability);
        modelAndView.addObject("products",products);

        return modelAndView;
    }
    
    @PostMapping("/selectNumProducts")
    public ModelAndView selectNumProducts(@Valid Traceability traceability, BindingResult result, @RequestParam List<Integer> idsProducts) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("/production/selectNumProducts");

        if(result.hasErrors())
            return createEdit(traceability,result.toString());
        
        List<Product> productList = new ArrayList<>();
        
        for(Integer idProduct:idsProducts){
            productList.add(productService.getProductById(idProduct));
        }

        modelAndView.addObject("traceability",traceability);
        modelAndView.addObject("products", productList);
        
        return modelAndView;
    }

    @PostMapping("/addTraceability")
    public ModelAndView addTraceability(@Valid Traceability traceability, BindingResult result, @RequestParam List<Integer> idsProducts, @RequestParam List<Integer> stocks) throws JsonProcessingException {

        int error = 0;

        if(result.hasErrors())
            return createEdit(traceability,result.toString());

        List<Product> productList = new ArrayList<>();

        for(int idProduct: idsProducts)
            productList.add(productService.getProductById(idProduct));


        for(int i =0; i<stocks.size();i++)
            productList.get(i).setStock(productList.get(i).getStock()-stocks.get(i));

        if (error==0 && !productList.isEmpty()) {

            TraceabilityToServer traceabilityToServer = new TraceabilityToServer();
            Date date = Date.valueOf(traceability.getExpirationDate());
            traceabilityToServer.setIdTraceability(traceability.getIdTraceability());
            traceabilityToServer.setArticle(traceability.getArticle());
            traceabilityToServer.setNumberBatch(traceability.getNumberBatch());
            traceabilityToServer.setExpirationDate(date);


            List<TraceabilityProduct> traceabilityProducts =  traceabilityProductService.getTraceabilityProducts();


            for(Product product:productList)
                traceabilityProductService.create(traceabilityToServer,product);

        }
        return getTraceabilities();
    }

    private ModelAndView createEdit(Traceability traceability) throws JsonProcessingException {
        return createEdit(traceability,null);
    }

    private ModelAndView createEdit(Traceability traceability, String message) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("/production/createTraceability");
        articles = articleService.getArticles();

        if(traceabilities.isEmpty())
            traceability.setNumberBatch(1);
        else
            traceability.setNumberBatch(traceabilities.get(traceabilities.size()-1).getNumberBatch()+1);

        modelAndView.addObject("traceability",traceability);
        modelAndView.addObject("articles",articles);
        modelAndView.addObject("error", message);

        return modelAndView;
    }
}
