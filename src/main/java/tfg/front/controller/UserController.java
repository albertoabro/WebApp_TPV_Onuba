package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
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

    String userName, password, address, phone, denomination;
    int id, idTypeUser;
    private List<User> employees = new ArrayList<>();
    private List<TypeUser> typesUsers = new ArrayList<>();
    private final UserService userService;
    private final TypeUserService typeUserService;

    public UserController(UserService userService, TypeUserService typeUserService){
        this.userService = userService;
        this.typeUserService = typeUserService;
    }

    @PostMapping("/login")
    public void login(HttpSession session, HttpServletResponse response, @RequestParam String userName, @RequestParam String password) throws IOException {
        this.userName = userName;
        this.password = password;
        LoginRequest req = new LoginRequest(userName, password);
        User user = this.userService.login(req);
        if(user!=null) {
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
        List<User> users = userService.getUsers();
        employees=users;
        ModelAndView modelAndView = new ModelAndView("/employee/employees");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView getUserById(HttpServletRequest request) throws JsonProcessingException {
        id = Integer.parseInt(request.getParameter("id"));
        User searchEmployee = userService.searchEmployeeById(employees, id);
        ModelAndView modelAndView;

        if(searchEmployee!=null){
            modelAndView = new ModelAndView("/employee/employee");
            modelAndView.addObject(searchEmployee);
        }
        else
            modelAndView=getUsers();

        return modelAndView;
    }

    @GetMapping("/registerEmployee")
    public ModelAndView registerEmployee() throws JsonProcessingException {
        List<TypeUser> typesUser = typeUserService.getTypesUsers();
        for(TypeUser u :typesUser)
            log.info("Tipos: "+u.toString());
        ModelAndView modelAndView = new ModelAndView("/employee/createEmployee");
        modelAndView.addObject("typesUser",typesUser);

        return modelAndView;
    }

    @PostMapping("/addEmployee")
    public void createEmployee(HttpSession session, HttpServletResponse response, @RequestParam String userName, @RequestParam String password, @RequestParam int typeUser, @RequestParam String address, @RequestParam String phone) throws IOException {
        this.id = employees.get(employees.size()-1).getIdUser()+1;
        log.info("Num empleados: "+employees.size());
        log.info("last Employee: "+employees.get(employees.size()-1).toString());
        log.info("Id: "+id);
        this.userName=userName;
        this.password=password;
        this.idTypeUser=typeUser;
        this.address=address;
        this.phone=phone;
        String msg="";
        User user = new User(id,userName,password,address,phone,typeUser);
        log.info("Usuario: "+user.toString());
        /*if(userService.createEmployee(user)){
            employees.add(user);
            msg = "Empleado creado con éxito";
        }

        else
        {
            msg = "Error al crear el empleado";
        }*/

        session.setAttribute("msg",msg);
        response.sendRedirect("/users/employees");
    }
}
