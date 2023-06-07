package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.domain.TypeUser;
import tfg.front.domain.User;
import tfg.front.service.typeUser.TypeUserService;
import tfg.front.service.user.UserService;
import tfg.front.service.user.login.LoginRequest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private List<User> employees = new ArrayList<>();
    private List<TypeUser> typesUser = new ArrayList<>();
    private final UserService userService;
    private final TypeUserService typeUserService;

    public UserController(UserService userService, TypeUserService typeUserService){
        this.userService = userService;
        this.typeUserService = typeUserService;
    }

    @PostMapping("/login")
    public void login(HttpSession session, HttpServletResponse response, @RequestParam String userName, @RequestParam String password) throws IOException {
        LoginRequest req = new LoginRequest(userName, password);
        User user = this.userService.login(req);
        if(user.getTypeUser()==1) {
            session.setAttribute("user", user);
            response.sendRedirect("/index");
        }

        else{
            String msg = "Error: Usuario y/o contraseña incorrectos";
            session.setAttribute("error",msg);
            response.sendRedirect("/");
        }
    }

    @GetMapping("/logout")
    public void logOut(HttpSession session, HttpServletResponse response) throws IOException {

        session.invalidate();
        response.sendRedirect("/");
    }
    @GetMapping( "/employees")
    public ModelAndView getUsers() throws JsonProcessingException {
        employees= userService.getUsers();

        ModelAndView modelAndView = new ModelAndView("/employee/employees");
        modelAndView.addObject("users",employees);
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView getUserById(HttpServletRequest request) throws JsonProcessingException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getEmployeeById(id);

        if(typesUser.isEmpty())
            typesUser = typeUserService.getTypesUsers();

        ModelAndView modelAndView;

        if(user!=null){
            modelAndView = new ModelAndView("/employee/employee");
            modelAndView.addObject("user",user);
            modelAndView.addObject("typeUser", typesUser);
        }
        else
            modelAndView=getUsers();

        return modelAndView;
    }

    @GetMapping("/registerEmployee")
    public ModelAndView registerEmployee() throws JsonProcessingException {
        final User user = userService.create();

        return createEdit(user, false);
    }

    @GetMapping("/editEmployee")
    public ModelAndView viewEditEmployee(HttpServletRequest request) throws JsonProcessingException {
        int idUser = Integer.parseInt(request.getParameter("id"));
        User user = userService.getEmployeeById(idUser);

        return createEdit(user,true);
    }

    @GetMapping("/searchEmployee")
    public ModelAndView searchEmployeeByUserName(@RequestParam String userName) throws JsonProcessingException {
        ModelAndView modelAndView;
        List<User> searchUsers = userService.searchEmployeeByUserName(userName);
        modelAndView= new ModelAndView("/employee/employees");

        if (!searchUsers.isEmpty()){
            modelAndView.addObject("users", searchUsers);
        }

        return modelAndView;
    }

    @PostMapping("/addEmployee")
    public ModelAndView createEmployee(@Valid User user, BindingResult result) throws JsonProcessingException {

        if(result.hasErrors())
            return createEdit(user, false,result.toString());

        if(userService.createEmployee(user))
            employees.add(user);

        return getUsers();
    }

    @PutMapping("/editEmployee")
    public ModelAndView editEmployee(@Valid User user, BindingResult result) throws IOException {

        if(result.hasErrors())
            return createEdit(user, true, result.toString());

        if(userService.updateEmployee(user)){
            int pos = userService.searchPosition(employees, user.getIdUser());
            if(pos!=-1)
                employees.set(pos, user);
        }

       return getUsers();
    }

    @DeleteMapping("/delete")
    public ModelAndView delete(@RequestParam int idUser) throws JsonProcessingException {
        User user = userService.getEmployeeById(idUser);

        if (user==null)
            return getUsers();

        userService.delete(user);

        return getUsers();
    }

    private ModelAndView createEdit(final User user, boolean edit) throws JsonProcessingException {
        return createEdit(user,edit,null);
    }

    private ModelAndView createEdit(final User user, boolean edit, final String message) throws JsonProcessingException {
        if(typesUser.isEmpty())
            typesUser = typeUserService.getTypesUsers();

        ModelAndView modelAndView;

        if(edit)
            modelAndView = new ModelAndView("/employee/editEmployee");
        else
            modelAndView = new ModelAndView("/employee/createEmployee");

        modelAndView.addObject("user",user);
        modelAndView.addObject("typesUser",typesUser);
        modelAndView.addObject("error",message);

        return modelAndView;
    }
}
