package tfg.front.service.typeUser;

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
import org.springframework.web.client.RestTemplate;
import tfg.front.domain.TypeUser;
import tfg.front.service.AbstractClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TypeUserServiceImpl extends AbstractClient implements TypeUserService{
    @Autowired
    protected TypeUserServiceImpl(RestTemplate restTemplate) {super(restTemplate);}

    @Override
    public List<TypeUser> getTypesUsers() throws JsonProcessingException {
        String uri = baseUrl+"/typesUser";

        ResponseEntity<List> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        List<TypeUser> typeUsers = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());

        Gson gson = new Gson();
        Type typeTypeUserList = new TypeToken<ArrayList<TypeUser>>(){}.getType();

        typeUsers = gson.fromJson(json,typeTypeUserList);

        return typeUsers;
    }

    @Override
    public TypeUser searchTypeUserById(List<TypeUser> typeUsers, int id) {
        boolean found = false;
        int counter=0;
        TypeUser typeUser;

        while (counter<typeUsers.size() && !found)
        {
            if(typeUsers.get(counter).getId() == id)
                found=true;
            else
                counter++;
        }

        if(found)
            typeUser = typeUsers.get(counter);
        else
            typeUser=null;

        return typeUser;
    }

    @Override
    public boolean createTypeUser(TypeUser typeUser) {
        return false;
    }

    @Override
    public boolean updateTypeUser(TypeUser typeUser) {
        return false;
    }
}
