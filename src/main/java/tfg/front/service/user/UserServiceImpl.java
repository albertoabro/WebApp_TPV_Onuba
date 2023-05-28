package tfg.front.service.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.User;
import tfg.front.service.AbstractClient;
import tfg.front.service.user.login.LoginRequest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
public class UserServiceImpl extends AbstractClient implements UserService{
    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) throws IOException {
        super(restTemplate, aSynchronized);
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
            return null;
        }
        return null;
    }

    @Override
    public List<User> getUsers() throws JsonProcessingException {
        String uri = baseUrl+"/users";

        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getUsers(response);
    }

    @Override
    public User create() {
        User user = new User();
        return user;
    }

    private List<User> getUsers(ResponseEntity<List> response) throws JsonProcessingException {
        List<User>users;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();
        Type typeUserList = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(json,typeUserList);

        return users;
    }
    @Override
    public User getEmployeeById(int id) {
        Assert.notNull(id,"El Id no puede ser nulo");
        String uri = baseUrl+"/users/"+id;

        User user = restTemplate.getForObject(uri, User.class);

        return user;
    }

    @Override
    public int searchPosition(List<User> employees,int id) {
        boolean found = false;
        int counter=0, pos=-1;

        while (counter<employees.size() && !found){
            if(employees.get(counter).getIdUser()==id)
                found=true;

            else
                counter++;
        }
        if(found)
            pos = counter;

        return pos;
    }

    @Override
    public List<User> searchEmployeeByUserName(String userName) throws JsonProcessingException {
        String searchName = userName+"%";
        String uri = baseUrl+"/users/search/"+searchName;
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getUsers(response);

    }

    @Override
    public boolean createEmployee(User user) {
        boolean created = false;
        String uri = baseUrl+"/users";

        try {
            ResponseEntity<User> response = restTemplate.postForEntity(uri, user, User.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                created = true;
                
                String sql = "Insert into users values(\'"+user.getIdUser()+"\', \'"+user.getUserName()+"\', \'"+user.getPassword()+"\', " +
                        "\'"+user.getAddress()+"\' ,\'"+user.getPhone()+"\' ,\'"+user.getTypeUser()+"\', \'"+user.getPasswordTPV()+"\')";

                aSynchronized.sqlCommands.add(sql);
            }
        }
        catch (HttpClientErrorException e) {
            return created;
        }
        return created;
    }

    @Override
    public boolean updateEmployee(User user) {
        boolean updated = false;
        String id = String.valueOf(user.getIdUser());
        String uri = baseUrl+"/users/"+id;
        HttpEntity<User> entity = new HttpEntity<>(user);
        try {
            ResponseEntity<User> response = restTemplate.exchange(uri,HttpMethod.PUT,entity,User.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                updated = true;

                String sql = "Update users set userName= \'"+user.getUserName()+"\', password= \'"+user.getPassword()+"\', address=" +
                        "\'"+user.getAddress()+"\', phone= \'"+user.getPhone()+"\', typeUser=\'"+user.getTypeUser()+"\', passwordTPV=\'"+user.getPasswordTPV()+"\' where idUser = "+user.getIdUser();
                aSynchronized.sqlCommands.add(sql);
            }
        }catch (HttpClientErrorException e){
            return updated;
        }

        return updated;
    }

    @Override
    public void delete(User user) {
        String id = String.valueOf(user.getIdUser());
        String uri = baseUrl+"/users/"+id;

        HttpEntity<User> entity = new HttpEntity<>(user);
        restTemplate.exchange(uri, HttpMethod.DELETE,entity,User.class);

        String sql = "Delete from users where idUser="+id;
        aSynchronized.sqlCommands.add(sql);
    }
}
