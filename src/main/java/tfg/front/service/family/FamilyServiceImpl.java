package tfg.front.service.family;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.domain.Family;
import tfg.front.service.AbstractClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FamilyServiceImpl extends AbstractClient implements FamilyService {
    @Autowired
    protected FamilyServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    private List<Family> getFamilies(ResponseEntity<List> response) throws JsonProcessingException{
        List<Family> families;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();

        Type typeFamilyList = new TypeToken<ArrayList<Family>>(){}.getType();
        families = gson.fromJson(json,typeFamilyList);

        return families;
    }
    @Override
    public List<Family> getFamilies() throws JsonProcessingException {
        String uri = baseUrl+"/families";
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET,null, List.class);

        return getFamilies(response);
    }

    @Override
    public Family searchFamily(List<Family> families, int id) {
        boolean found = false;
        int counter = 0;
        Family searchFamily;

        while(counter<families.size() && !found)
        {
            if(families.get(counter).getIdFamily()==id)
                found=true;
            else
                counter++;
        }

        if(found)
            searchFamily = families.get(counter);
        else
            searchFamily=null;

        return searchFamily;
    }

    @Override
    public int searchPosition(List<Family> families, int id) {
        boolean found = false;
        int counter = 0, pos=-1;


        while(counter<families.size() && !found)
        {
            if(families.get(counter).getIdFamily()==id)
                found=true;
            else
                counter++;
        }

        if(found)
            pos = counter;

        return pos;
    }

    @Override
    public List<Family> searchFamilyByName(String nameFamily) throws JsonProcessingException {
        String searchName = nameFamily+"%";
        String uri = baseUrl+"/families/search/"+searchName;

        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getFamilies(response);
    }

    @Override
    public boolean createFamily(Family family) {
        boolean created = false;
        String uri = baseUrl+"/families";

        try {
            ResponseEntity<Family> response = restTemplate.postForEntity(uri,family, Family.class);
            if(response.getStatusCode().is2xxSuccessful())
                created=true;

        }catch (HttpClientErrorException e)
        {
            log.error("Error: "+e.toString());
        }

        return created;
    }

    @Override
    public boolean updateFamily(Family family) {
        boolean updated = false;
        String id = String.valueOf(family.getIdFamily());
        String uri = baseUrl+"families"+id;
        HttpEntity<Family>entity = new HttpEntity<>(family);

        try {
            ResponseEntity<Family> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, Family.class);
            if(response.getStatusCode().is2xxSuccessful())
                updated=true;

        }catch (HttpClientErrorException e)
        {
            log.error("Error: "+e.toString());
        }
        return updated;
    }
}
