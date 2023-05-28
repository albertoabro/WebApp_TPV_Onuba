package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Product;
import tfg.front.domain.Provider;
import tfg.front.domain.TraceabilityProduct;
import tfg.front.service.product.ProductService;
import tfg.front.service.provider.ProviderService;
import tfg.front.service.traceabilityProduct.TraceabilityProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private List<Provider> providers = new ArrayList<>();
    private final ProductService productService;
    private final ProviderService providerService;
    private final TraceabilityProductService traceabilityProductService;

    public ProductController(ProductService productService, ProviderService providerService, TraceabilityProductService traceabilityProductService){
        this.productService=productService;
        this.providerService=providerService;
        this.traceabilityProductService = traceabilityProductService;
    }

    @GetMapping("/products")
    public ModelAndView getProducts() throws JsonProcessingException{
        products = productService.getProducts();

        ModelAndView modelAndView = new ModelAndView("/product/products");
        modelAndView.addObject("products",products);

        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView getProductById(HttpServletRequest request) throws JsonProcessingException{
        int id = Integer.parseInt(request.getParameter("id"));
        Product searchProduct = productService.getProductById(id);
        providers = providerService.getProviders();

        ModelAndView modelAndView;

        if(searchProduct!=null)
        {
            modelAndView = new ModelAndView("/product/product");
            modelAndView.addObject("products", searchProduct);
            modelAndView.addObject("providers", providers);
        }

        else
            modelAndView=getProducts();

        return modelAndView;
    }

    @GetMapping("/registerProduct")
    public ModelAndView registerProduct() throws JsonProcessingException {
        Product product = productService.create();

        return createEdit(product,false);
    }



    @GetMapping("/editProduct")
    public ModelAndView viewEditProduct(HttpServletRequest request) throws JsonProcessingException{
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);

        return createEdit(product,true);
    }

    @GetMapping("/searchProduct")
    public ModelAndView searchProductByName(@RequestParam String nameProduct) throws JsonProcessingException{
        List<Product> searchProducts = productService.searchProductByName(nameProduct);
        ModelAndView modelAndView = new ModelAndView("/product/products");

        if(!searchProducts.isEmpty())
            modelAndView.addObject("products", searchProducts);

        return modelAndView;
    }

    @GetMapping("/productsTraceability")
    public ModelAndView productsTraceability(@RequestParam List<Integer> idsProducts) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("/product/product");
        List<Product> productList = new ArrayList<>();
        providers = providerService.getProviders();

        for(Integer id: idsProducts)
            productList.add(productService.getProductById(id));

        modelAndView.addObject("products", productList);
        modelAndView.addObject("providers", providers);

        return modelAndView;
    }

    @GetMapping("productsByProvider")
    public ModelAndView productsByProvider(HttpServletRequest request) throws JsonProcessingException {
        int provider = Integer.parseInt(request.getParameter("id"));
        List<Product> productList = productService.getProductsByProvider(provider);
        ModelAndView modelAndView = new ModelAndView("/product/products");

        modelAndView.addObject("products",productList);

        return modelAndView;
    }

    @PostMapping("/addProduct")
    public ModelAndView createProduct(@Valid Product product, BindingResult result) throws IOException {

        if(result.hasErrors())
            return createEdit(product,false, result.toString());

        if(productService.createProduct(product))
            products.add(product);

       return getProducts();
    }

    @PutMapping("/editProduct")
    public ModelAndView updateProduct(@Valid Product product, BindingResult result) throws IOException{

        if(result.hasErrors())
            return createEdit(product,true,result.toString());

        if(productService.updateProduct(product)){
            int pos = productService.searchPosition(products, product.getIdProduct());
            if(pos!=-1)
                products.set(pos, product);
        }

        return getProducts();
    }

    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam int idProduct) throws JsonProcessingException {
        Product product = productService.getProductById(idProduct);
        List<TraceabilityProduct> traceabilityProducts = traceabilityProductService.getTraceabilityProducts();
        boolean existTraceability = productService.existTraceability(traceabilityProducts, product);

        if(product==null)
            return getProducts();

        if(!existTraceability)
            productService.delete(product);

        return getProducts();
    }

    private ModelAndView createEdit(Product product, boolean edit) throws JsonProcessingException {
        return createEdit(product,edit,null);
    }
    private ModelAndView createEdit(Product product, boolean edit, String message) throws JsonProcessingException {
        ModelAndView modelAndView;
        providers = providerService.getProviders();

        if(edit)
            modelAndView=new ModelAndView("/product/editProduct");
        else
            modelAndView=new ModelAndView("/product/createProduct");

        modelAndView.addObject("product",product);
        modelAndView.addObject("providers", providers);
        modelAndView.addObject("error", message);

        return modelAndView;
    }
}
