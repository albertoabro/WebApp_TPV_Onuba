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
import tfg.front.service.article.ArticleService;
import tfg.front.service.family.FamilyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/families")
public class FamilyController {
    private List<Family> families = new ArrayList<>();
    private final FamilyService familyService;
    private final ArticleService articleService;

    public FamilyController(FamilyService familyService, ArticleService articleService) {this.familyService = familyService;
        this.articleService = articleService;
    }

    @GetMapping("/families")
    public ModelAndView getFamilies() throws JsonProcessingException {
        families=familyService.getFamilies();

        ModelAndView modelAndView = new ModelAndView("/family/families");
        modelAndView.addObject("families", families);

        return modelAndView;
    }

    @GetMapping("/family")
    public ModelAndView getFamilyById(HttpServletRequest request) throws JsonProcessingException{
        int id = Integer.parseInt(request.getParameter("id"));
        Family searchFamily = familyService.getFamilyById(id);
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
        final Family family = familyService.create();

        return createEdit(family, false);
    }

    @GetMapping("/editFamily")
    public ModelAndView viewEditFamily(HttpServletRequest request) throws JsonProcessingException{
        int id = Integer.parseInt(request.getParameter("id"));
        Family family = familyService.getFamilyById(id);

        return createEdit(family,true);
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
    public ModelAndView createFamily(@Valid Family family, BindingResult result) throws IOException{
        if (result.hasErrors())
            return createEdit(family,false,result.toString());

        if(familyService.createFamily(family))
            families.add(family);

        return getFamilies();
    }

    @PutMapping("/editFamily")
    public ModelAndView editFamily(@Valid Family family, BindingResult result) throws IOException{

        if(result.hasErrors())
            return createEdit(family,true,result.toString());

        if(familyService.updateFamily(family))
        {
            int pos = familyService.searchPosition(families,family.getIdFamily());
            if(pos!=-1)
                families.set(pos, family);
        }

        return getFamilies();
    }
    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam int idFamily) throws JsonProcessingException {
        Family family = familyService.getFamilyById(idFamily);

        List<Article> articles = articleService.getArticlesByFamily(family.getIdFamily());
        if(articles.isEmpty())
            familyService.delete(family);

        return getFamilies();
    }

    private ModelAndView createEdit(Family family, boolean edit) {
        return createEdit(family,edit,null);
    }

    private ModelAndView createEdit(Family family, boolean edit, String message) {
        ModelAndView modelAndView;

        if(edit)
            modelAndView=new ModelAndView("/family/editFamily");
        else
            modelAndView=new ModelAndView("/family/createFamily");

        modelAndView.addObject("family",family);
        modelAndView.addObject("error",message);

        return modelAndView;
    }
}
