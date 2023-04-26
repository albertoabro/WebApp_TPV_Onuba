package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Product;
import tfg.front.domain.Provider;
import tfg.front.service.product.ProductService;
import tfg.front.service.provider.ProviderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    String nameProduct, category;
    int id, idProvider;
    double price;

    private List<Product> products = new ArrayList<>();
    private List<Provider> providers = new ArrayList<>();
    private final ProductService productService;
    private final ProviderService providerService;

    public ProductController(ProductService productService, ProviderService providerService){
        this.productService=productService;
        this.providerService=providerService;
    }

    @GetMapping("/products")
    public ModelAndView getProducts() throws JsonProcessingException{
        if(products.isEmpty())
            products = productService.getProducts();

        ModelAndView modelAndView = new ModelAndView("/product/products");
        modelAndView.addObject("products",products);

        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView getProductById(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Product searchProduct = productService.searchProductById(products, id);
        providers = providerService.getProviders();

        ModelAndView modelAndView;

        if(searchProduct!=null)
        {
            modelAndView = new ModelAndView("/product/product");
            modelAndView.addObject("product", searchProduct);
            modelAndView.addObject("providers", providers);
        }

        else
            modelAndView = getProducts();

        return modelAndView;
    }

    @GetMapping("/registerProduct")
    public ModelAndView registerProduct() throws JsonProcessingException {
        providers = providerService.getProviders();
        log.info("Register: "+providers.toString());
        ModelAndView modelAndView = new ModelAndView("/product/createProduct");
        modelAndView.addObject("providers",providers);

        return modelAndView;
    }

    @GetMapping("/editProduct")
    public ModelAndView viewEditProduct(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Product searchProduct = productService.searchProductById(products, id);
        providers = providerService.getProviders();
        ModelAndView modelAndView;

        if(searchProduct!=null){
            modelAndView = new ModelAndView("/product/editProduct");
            modelAndView.addObject("product", searchProduct);
            modelAndView.addObject("providers", providers);
        }

        else
            modelAndView = getProducts();

        return modelAndView;
    }

    @GetMapping("/searchProduct")
    public ModelAndView searchProductByName(@RequestParam String nameProduct) throws JsonProcessingException{
        List<Product> searchProducts = productService.searchProductByName(nameProduct);
        ModelAndView modelAndView = new ModelAndView("/product/products");

        if(!searchProducts.isEmpty())
            modelAndView.addObject("products", searchProducts);

        return modelAndView;
    }

    @PostMapping("/addProduct")
    public void createProduct(HttpServletResponse response, @RequestParam String nameProduct, @RequestParam int idProvider, @RequestParam String category, @RequestParam double price) throws IOException {
        if(products.isEmpty())
            this.id=1;
        else
            this.id=products.get(products.size()-1).getIdProduct()+1;

        this.nameProduct=nameProduct;
        this.idProvider=idProvider;
        this.category=category;
        this.price = price;

        Product product = new Product(id,nameProduct, idProvider,category,price);
        log.info("Producto: "+product);
        if(productService.createProduct(product))
            products.add(product);

        response.sendRedirect("/products/products");
    }

    @PutMapping("/editProduct")
    public void updateProduct(HttpServletResponse response, @RequestParam int id, @RequestParam String nameProduct, @RequestParam int idProvider, @RequestParam String category, @RequestParam double price) throws IOException{
        this.id=id;
        this.nameProduct=nameProduct;
        this.idProvider=idProvider;
        this.category=category;
        this.price = price;

        Product product = new Product(id,nameProduct, idProvider,category,price);
        if(productService.updateProduct(product)){
            int pos = productService.searchPosition(products, id);
            if(pos!=-1)
                products.set(pos, product);
        }

        response.sendRedirect("/products/products");
    }
}
