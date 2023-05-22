package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.Family;
import tfg.front.service.family.FamilyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/families")
public class FamilyController {
    String nameFamily;
    int id;

    private List<Family> families = new ArrayList<>();
    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {this.familyService = familyService;}

    @GetMapping("/families")
    public ModelAndView getFamilies() throws JsonProcessingException {
        families=familyService.getFamilies();

        ModelAndView modelAndView = new ModelAndView("/family/families");
        modelAndView.addObject("families", families);

        return modelAndView;
    }

    @GetMapping("/family")
    public ModelAndView getFamilyById(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Family searchFamily = familyService.searchFamily(families, id);
        ModelAndView modelAndView;

        if(searchFamily!=null)
        {
            modelAndView = new ModelAndView("/family/family");
            modelAndView.addObject("family", searchFamily);
        }

        else modelAndView = getFamilies();

        return modelAndView;
    }

    @GetMapping("/registerFamily")
    public ModelAndView registerFamily(){
        ModelAndView modelAndView = new ModelAndView("/family/createFamily");
        return modelAndView;
    }

    @GetMapping("/editFamily")
    public ModelAndView viewEditFamily(HttpServletRequest request) throws JsonProcessingException{
        id = Integer.parseInt(request.getParameter("id"));
        Family family = familyService.searchFamily(families,id);
        ModelAndView modelAndView;

        if(!families.isEmpty())
        {
            modelAndView = new ModelAndView("/family/editFamily");
            modelAndView.addObject("family",family);
        }

        else
            modelAndView = getFamilies();

        return modelAndView;
    }

    @GetMapping("/searchFamily")
    public ModelAndView searchFamilyByName(@RequestParam String nameFamily) throws JsonProcessingException{
        List<Family> searchFamilies = familyService.searchFamilyByName(nameFamily);
        ModelAndView modelAndView = new ModelAndView("/family/families");

        if(!searchFamilies.isEmpty())
            modelAndView.addObject("families",searchFamilies);

        return modelAndView;
    }

    @PostMapping("/addFamily")
    public void createFamily(HttpServletResponse response, @RequestParam String nameFamily) throws IOException{
        if(families.isEmpty())
            this.id=1;
        else
            this.id=families.get(families.size()-1).getIdFamily()+1;
        this.nameFamily = nameFamily;

        log.info("ID: "+id);
        Family family = new Family(id, nameFamily);

        if(familyService.createFamily(family))
            families.add(family);

        response.sendRedirect("/families/families");
    }

    @PutMapping("/editfamily")
    public void editFamily(HttpServletResponse response, @RequestParam int id, @RequestParam String nameFamily) throws IOException{
        this.id=id;
        this.nameFamily=nameFamily;

        Family family = new Family(id, nameFamily);

        if(familyService.updateFamily(family))
        {
            int pos = familyService.searchPosition(families,id);
            if(pos!=-1)
                families.set(pos, family);
        }

        response.sendRedirect("/families/families");
    }
}
