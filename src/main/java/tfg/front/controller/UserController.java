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

    String userName, password, address, phone;
    int id, idTypeUser;
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
        employees= userService.getUsers();

        ModelAndView modelAndView = new ModelAndView("/employee/employees");
        modelAndView.addObject("users",employees);
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView getUserById(HttpServletRequest request) throws JsonProcessingException {
        id = Integer.parseInt(request.getParameter("id"));
        User searchEmployee = userService.searchEmployeeById(employees, id);
        if(typesUser.isEmpty())
            typesUser = typeUserService.getTypesUsers();

        ModelAndView modelAndView;

        if(searchEmployee!=null){
            modelAndView = new ModelAndView("/employee/employee");
            modelAndView.addObject("user",searchEmployee);
            modelAndView.addObject("typeUser", typesUser);
        }
        else
            modelAndView=getUsers();

        return modelAndView;
    }

    @GetMapping("/registerEmployee")
    public ModelAndView registerEmployee() throws JsonProcessingException {
        if(typesUser.isEmpty())
            typesUser = typeUserService.getTypesUsers();

        ModelAndView modelAndView = new ModelAndView("/employee/createEmployee");
        modelAndView.addObject("typesUser",typesUser);

        return modelAndView;
    }

    @PostMapping("/addEmployee")
    public void createEmployee(HttpSession session, HttpServletResponse response, @RequestParam String userName, @RequestParam String password, @RequestParam int typeUser, @RequestParam String address, @RequestParam String phone) throws IOException {
        if(employees.isEmpty())
            this.id =1;
        else
            this.id = employees.get(employees.size()-1).getIdUser()+1;

        this.userName=userName;
        this.password=password;
        this.idTypeUser=typeUser;
        this.address=address;
        this.phone=phone;
        String msg;
        User user = new User(id,userName,password,address,phone,typeUser);
        if(userService.createEmployee(user)){
            employees.add(user);
            msg = "Empleado creado con éxito";
        }

        else
        {
            msg = "Error al crear el empleado";
        }

        session.setAttribute("msg",msg);
        response.sendRedirect("/users/employees");
    }

    @GetMapping("/editEmployee")
    public ModelAndView viewEditEmployee(HttpServletRequest request) throws JsonProcessingException {
        id = Integer.parseInt(request.getParameter("id"));
        User searchEmployee = userService.searchEmployeeById(employees, id);
        if(typesUser.isEmpty())
            typesUser = typeUserService.getTypesUsers();

        ModelAndView modelAndView;

        if(searchEmployee!=null){
            modelAndView = new ModelAndView("/employee/editEmployee");
            modelAndView.addObject("user",searchEmployee);
            modelAndView.addObject("typesUser",typesUser);
        }
        else
            modelAndView=getUsers();

        return modelAndView;
    }
    @PutMapping("/editEmployee")
    public void editEmployee(HttpServletResponse response, @RequestParam int id, @RequestParam String userName, @RequestParam String password, @RequestParam int typeUser, @RequestParam String address, @RequestParam String phone) throws IOException {
        this.id = id;
        this.userName=userName;
        this.password = password;
        this.idTypeUser=typeUser;
        this.address=address;
        this.phone=phone;

        User user = new User(id,userName,password,address,phone,typeUser);
        if(userService.updateEmployee(user)){
            int pos = userService.searchPosition(employees, id);
            if(pos!=-1)
                employees.set(pos, user);
        }

        response.sendRedirect("/users/employees");
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
}
