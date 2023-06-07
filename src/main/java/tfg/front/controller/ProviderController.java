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
@RequestMapping("/providers")
public class ProviderController {
    private List<Provider> providers = new ArrayList<>();
    private final ProviderService providerService;
    private final ProductService productService;
    private final TraceabilityProductService traceabilityProductService;

    public ProviderController(ProviderService providerService, ProductService productService, TraceabilityProductService traceabilityProductService) {
        this.providerService = providerService;
        this.productService = productService;
        this.traceabilityProductService = traceabilityProductService;
    }

    @GetMapping("/providers")
    public ModelAndView getProviders() throws JsonProcessingException{
        providers=providerService.getProviders();
        ModelAndView modelAndView = new ModelAndView("/provider/providers");
        modelAndView.addObject("providers", providers);

        return modelAndView;
    }

    @GetMapping("/provider")
    public ModelAndView getProviderById(HttpServletRequest request) throws JsonProcessingException{
        int id= Integer.parseInt(request.getParameter("id"));
        Provider searchProvider = providerService.getProviderById(id);
        ModelAndView modelAndView;

        if(searchProvider!=null){
            modelAndView = new ModelAndView("/provider/provider");
            modelAndView.addObject("provider",searchProvider);
        }
        else
            modelAndView=getProviders();

        return modelAndView;
    }

    @GetMapping("/registerProvider")
    public ModelAndView registerProvider(){
        Provider provider = providerService.create();

        return createEdit(provider,false);
    }


    @GetMapping("/editProvider")
    public ModelAndView viewEditProvider(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Provider provider = providerService.getProviderById(id);

        return createEdit(provider, true);
    }

    @GetMapping("/searchProvider")
    public ModelAndView searchProviderByName(@RequestParam String nameProvider) throws JsonProcessingException{
        List<Provider> searchProviders = providerService.searchProviderByName(nameProvider);
        ModelAndView modelAndView = new ModelAndView("/provider/providers");

        if(!searchProviders.isEmpty())
            modelAndView.addObject("providers",searchProviders);

        return modelAndView;
    }

    @PostMapping("addProvider")
    public ModelAndView createProvider(@Valid Provider provider, BindingResult result) throws IOException{
        if(result.hasErrors())
            return createEdit(provider,false,result.toString());

        if(providerService.createProvider(provider))
            providers.add(provider);

        return getProviders();
    }

    @PutMapping("/editProvider")
    public ModelAndView editProvider(@Valid Provider provider, BindingResult result) throws IOException{
        if(result.hasErrors())
            return createEdit(provider,true,result.toString());

        if(providerService.updateProvider(provider))
        {
            int pos = providerService.searchPosition(providers, provider.getIdProvider());
            if(pos!=-1)
                providers.set(pos,provider);
        }

        return getProviders();
    }

    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam int idProvider) throws JsonProcessingException {
        Provider provider = providerService.getProviderById(idProvider);

        if(provider==null)
            return getProviders();

        List<Product> productList = productService.getProductsByProvider(provider.getIdProvider());
        List<TraceabilityProduct>traceabilityProducts = traceabilityProductService.getTraceabilityProducts();
        int numProducts = productList.size();
        int i=0;
        boolean existSomeProductTraceability = false;

        if(!productList.isEmpty()) {
            while (i<numProducts && !existSomeProductTraceability)
            {
                Product product = productList.get(i);
                if(productService.existTraceability(traceabilityProducts,product))
                    existSomeProductTraceability=true;

                else
                    i++;
            }

            if(!existSomeProductTraceability)
                providerService.delete(provider);
        }

        else
            providerService.delete(provider);


        return getProviders();
    }

    private ModelAndView createEdit(Provider provider, boolean edit) {
        return createEdit(provider,edit,null);
    }

    private ModelAndView createEdit(Provider provider, boolean edit, String message) {
        ModelAndView modelAndView;

        if(edit)
            modelAndView=new ModelAndView("/provider/editProvider");
        else
            modelAndView=new ModelAndView("/provider/createProvider");

        modelAndView.addObject("provider",provider);
        modelAndView.addObject("error",message);

        return modelAndView;
    }

}
