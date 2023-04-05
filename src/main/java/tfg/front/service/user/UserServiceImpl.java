package tfg.front.service.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.domain.User;
import tfg.front.service.AbstractClient;
import tfg.front.service.user.login.LoginRequest;

import java.util.List;
@Slf4j
@Service
public class UserServiceImpl extends AbstractClient implements UserService{
    @Autowired
    public UserServiceImpl(RestTemplate restTemplate){
        super(restTemplate);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        String uri = baseUrl+"/users/login";

        try {
            ResponseEntity<User> response = restTemplate.postForEntity(uri, loginRequest, User.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch (HttpClientErrorException e) {
            User user = null;
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        String uri = baseUrl+"/users";

        ResponseEntity<List<User>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User>users = response.getBody();
        return users;
    }

    @Override
    public User searchEmployeeById(List<User> employees,int id) {
        boolean found = false;
        int counter=0;
        User searchEmployee;

        while (counter<employees.size() && !found){
            if(employees.get(counter).getId()==id)
                found=true;

            else
                counter++;
        }
        if(found)
            searchEmployee=employees.get(counter);
        else
            searchEmployee=null;

        return searchEmployee;
    }

    @Override
    public boolean createEmployee(User user) {
        boolean created = false;
        String uri = baseUrl+"/users";
        try {
            ResponseEntity<User> response = restTemplate.postForEntity(uri, user, User.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                created = true;
            }
        }
        catch (HttpClientErrorException e) {

        }
        return created;
    }
}
