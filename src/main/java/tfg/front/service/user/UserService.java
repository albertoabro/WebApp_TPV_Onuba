package tfg.front.service.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.User;
import tfg.front.service.user.login.LoginRequest;
import java.util.List;

public interface UserService {

    User login(LoginRequest loginRequest);

    List<User> getUsers() throws JsonProcessingException;

    User create();
    User getEmployeeById(int id);

    int searchPosition(List<User> employees, int id);

    List<User> searchEmployeeByUserName(String userName) throws JsonProcessingException;

    boolean createEmployee(User user);

    boolean updateEmployee(User user);

    void delete(User user);
}
