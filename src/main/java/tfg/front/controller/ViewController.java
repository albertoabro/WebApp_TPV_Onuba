package tfg.front.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String login(){
        return "login";
    }
    @GetMapping("/index")
    public String loginSuccess(){return "index";}
    @GetMapping("/article")
    public String viewArticle(){return "article";}
    @GetMapping("/production")
    public String viewProduction(){return "production";}
    @GetMapping("/family")
    public String viewFamily(){return "family";}

}
