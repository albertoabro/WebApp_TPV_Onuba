package tfg.front.service.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public List<User> getUsers() throws JsonProcessingException {
        String uri = baseUrl+"/users";

        ResponseEntity<List> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        List<User>users = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        log.info("String: "+json );
        Gson gson = new Gson();
        Type typeUserList = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(json,typeUserList);
        for(User u : users)
            log.info("Usuario: "+ u.toString());
        return users;
    }

    @Override
    public User searchEmployeeById(List<User> employees,int id) {
        boolean found = false;
        int counter=0;
        User searchEmployee;

        while (counter<employees.size() && !found){
            if(employees.get(counter).getIdUser()==id)
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
