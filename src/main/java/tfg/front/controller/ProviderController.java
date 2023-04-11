package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Provider;
import tfg.front.service.provider.ProviderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/providers")
public class ProviderController {
    String nameProvider, address, phone, products;
    int id;
    private List<Provider> providers = new ArrayList<>();
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/providers")
    public ModelAndView getProviders() throws JsonProcessingException{
        if(providers.isEmpty())
            providers=providerService.getProviders();
        ModelAndView modelAndView = new ModelAndView("/provider/providers");
        modelAndView.addObject("providers", providers);
        return modelAndView;
    }

    @GetMapping("/provider")
    public ModelAndView getProviderById(HttpServletRequest request) throws JsonProcessingException{
        id= Integer.parseInt(request.getParameter("id"));
        Provider searchProvider = providerService.searchProviderById(providers, id);
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
        ModelAndView modelAndView = new ModelAndView("/provider/createProvider");

        return modelAndView;
    }

    @GetMapping("/editProvider")
    public ModelAndView viewEditProvider(HttpServletRequest request) throws JsonProcessingException {
        id = Integer.parseInt(request.getParameter("id"));
        Provider searchProvider = providerService.searchProviderById(providers,id);
        ModelAndView modelAndView;

        if(searchProvider!=null){
            modelAndView = new ModelAndView("/provider/editProvider");
            modelAndView.addObject("provider",searchProvider);
        }
        else
            modelAndView = getProviders();

        return modelAndView;
    }

    @GetMapping("/searchProvider")
    public ModelAndView searchProviderByName(@RequestParam String nameProvider) throws JsonProcessingException{
        List<Provider> searchProviders = providerService.searchProviderByName(nameProvider);
        ModelAndView modelAndView = new ModelAndView("/provider/providers");

        if(!searchProviders.isEmpty())
            modelAndView.addObject("providers",providers);

        return modelAndView;
    }

    @PostMapping("addProvider")
    public void createProvider(HttpServletResponse response, @RequestParam String nameProvider, @RequestParam String address, @RequestParam String phone, @RequestParam String products) throws IOException{
        if(providers.isEmpty())
            this.id = 1;
        else
            this.id = providers.get(providers.size()-1).getIdProvider()+1;

        this.nameProvider=nameProvider;
        this.address = address;
        this.phone = phone;
        this. products = products;

        Provider provider = new Provider(id,nameProvider,address,phone,products);


        if(providerService.createProvider(provider))
            providers.add(provider);

        response.sendRedirect("/providers/providers");
    }

    @PutMapping("/editProvider")
    public void editProvider(HttpServletResponse response, @RequestParam int idProvider, @RequestParam String nameProvider, @RequestParam String address, @RequestParam String phone, @RequestParam String products) throws IOException{
        this.id = idProvider;
        this.nameProvider=nameProvider;
        this.address = address;
        this.phone = phone;
        this.products = products;

        Provider provider = new Provider(id,nameProvider,address,phone,products);

        if(providerService.updateProvider(provider))
        {
            int pos = providerService.searchPosition(providers, id);
            providers.set(pos,provider);
        }

        response.sendRedirect("/providers/providers");
    }
}
