package tfg.front.service.user;


import org.springframework.http.ResponseEntity;
import tfg.front.domain.User;
import tfg.front.service.user.login.LoginRequest;
import tfg.front.service.user.login.LoginResponse;

import java.util.List;

public interface UserService {

    User login(LoginRequest loginRequest);

    List<User> getUsers();

    User searchEmployeeById(List<User> employees, int id);

    boolean createEmployee(User user);
}
