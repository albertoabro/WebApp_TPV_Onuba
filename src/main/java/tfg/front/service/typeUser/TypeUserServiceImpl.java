package tfg.front.service.typeUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tfg.front.domain.TypeUser;
import tfg.front.service.AbstractClient;

import java.util.List;

@Service
public class TypeUserServiceImpl extends AbstractClient implements TypeUserService{
    @Autowired
    protected TypeUserServiceImpl(RestTemplate restTemplate) {super(restTemplate);}

    @Override
    public List<TypeUser> getTypesUsers() {
        String uri = baseUrl+"/typesUser";
        ResponseEntity<List<TypeUser>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<TypeUser>>() {});

        List<TypeUser> typeUsers = response.getBody();
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
