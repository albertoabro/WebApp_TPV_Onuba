package tfg.front.service.typeUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.TypeUser;
import tfg.front.service.AbstractClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TypeUserServiceImpl extends AbstractClient implements TypeUserService{
    @Autowired
    protected TypeUserServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) throws IOException {super(restTemplate, aSynchronized);}

    @Override
    public List<TypeUser> getTypesUsers() throws JsonProcessingException {
        String uri = baseUrl+"/typesUser";

        ResponseEntity<List> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());

        Gson gson = new Gson();
        Type typeTypeUserList = new TypeToken<ArrayList<TypeUser>>(){}.getType();

        List<TypeUser>typeUsers = gson.fromJson(json,typeTypeUserList);

        return typeUsers;
    }
}
