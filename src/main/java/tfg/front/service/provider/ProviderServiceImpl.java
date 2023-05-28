package tfg.front.service.provider;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.Provider;
import tfg.front.service.AbstractClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProviderServiceImpl extends AbstractClient implements ProviderService{
    @Autowired
    public ProviderServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) throws IOException {super(restTemplate, aSynchronized);}
    private List<Provider> getProviders(ResponseEntity<List> response) throws JsonProcessingException{
        List<Provider> providers;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();
        Type typeProviderList = new TypeToken<ArrayList<Provider>>(){}.getType();
        providers = gson.fromJson(json,typeProviderList);

        return providers;
    }
    @Override
    public List<Provider> getProviders() throws JsonProcessingException {
        String uri = baseUrl+"/providers";

        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);
        return getProviders(response);
    }

    @Override
    public Provider getProviderById(int id) {
        String uri = baseUrl+"/providers/"+id;
        Provider provider = restTemplate.getForObject(uri, Provider.class);

        return provider;
    }

    @Override
    public Provider create(){
        return new Provider();
    }
    @Override
    public int searchPosition(List<Provider> providers, int id){
        boolean found = false;
        int counter = 0, pos =-1;
        Provider searchProvider;

        while (counter<providers.size() && !found)
        {
            if(providers.get(counter).getIdProvider()==id)
                found=true;
            else
                counter++;
        }

        if(found)
            pos = counter;

        return pos;
    }
    @Override
    public List<Provider> searchProviderByName(String name) throws JsonProcessingException {
        String searchName = name+"%";
        String uri = baseUrl+"/providers/search/"+searchName;

        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);

        return getProviders(response);
    }
    @Override
    public boolean createProvider(Provider provider){
        boolean created = false;
        String uri = baseUrl+"/providers";

        try {
            ResponseEntity<Provider> response = restTemplate.postForEntity(uri, provider, Provider.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                created = true;

                String sql = "Insert into providers values(\'"+provider.getIdProvider()+"\', \'"+provider.getNameProvider()+"\', \'"+provider.getAddress()+"\', " +
                        "\'"+provider.getPhone()+"\' ,\'"+provider.getProductDescription()+"\' )";
                aSynchronized.sqlCommands.add(sql);
            }

        }catch (HttpClientErrorException e){
        	return created;
        }

        return created;
    }
    @Override
    public boolean updateProvider(Provider provider){
        boolean updated = false;
        String id = String.valueOf(provider.getIdProvider());
        String uri = baseUrl+"/providers/"+id;
        HttpEntity<Provider> entity = new HttpEntity<>(provider);
        try {
            ResponseEntity<Provider> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, Provider.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                updated = true;

                String sql = "Update providers set nameProvider=\'"+provider.getNameProvider()+"\', address=\'"+provider.getAddress()+"\', phone" +
                        "\'"+provider.getPhone()+"\', productDescription=\'"+provider.getProductDescription()+"\'where idArticle="+provider.getIdProvider();
                aSynchronized.sqlCommands.add(sql);
            }

        }catch (HttpClientErrorException e){
            return updated;
        }
        return updated;
    }

    @Override
    public void delete(Provider provider) {
        String id = String.valueOf(provider.getIdProvider());
        String uri = baseUrl+"/providers/"+id;

        HttpEntity<Provider> entity = new HttpEntity<>(provider);
        restTemplate.exchange(uri,HttpMethod.DELETE,entity,Provider.class);
        String sql = "Delete from providers where idProvider="+id;
        aSynchronized.sqlCommands.add(sql);
    }
}
