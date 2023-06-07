package tfg.front.service.traceability;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.Traceability;
import tfg.front.domain.TraceabilityToServer;
import tfg.front.service.AbstractClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TraceabilityServiceImpl extends AbstractClient implements TraceabilityService{
    @Autowired
    protected TraceabilityServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) {
        super(restTemplate, aSynchronized);
    }

    private final static String urlTraceabilities = "/traceabilities";

    private List<Traceability> getTraceabilities(ResponseEntity<List> response) throws JsonProcessingException{
        List<Traceability> traceabilities;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();

        Type typeTraceabilityList = new TypeToken<ArrayList<Traceability>>(){}.getType();
        traceabilities = gson.fromJson(json,typeTraceabilityList);

        return traceabilities;
    }
    @Override
    public List<Traceability> getTraceabilities() throws JsonProcessingException {
        String uri = baseUrl+urlTraceabilities;
        ResponseEntity<List> response = restTemplate.exchange(uri, HttpMethod.GET, null, List.class);
        return getTraceabilities(response);
    }

    @Override
    public Traceability getTraceabilityById(int id) {
        String uri = baseUrl+"/traceabilities/"+id;
        return restTemplate.getForObject(uri, Traceability.class);
    }

    @Override
    public Traceability getTraceabilityByNumberBatch(int numberBatch) {
        String uri = baseUrl+"/traceabilities/search/"+numberBatch;
        return restTemplate.getForObject(uri,Traceability.class);
    }

    @Override
    public Traceability create() {
        return new Traceability();
    }

    @Override
    public int searchPosition(List<Traceability> traceabilities, int id) {
        boolean found = false;
        int counter = 0;
        int pos = -1;

        while (counter<traceabilities.size() && !found)
        {
            if (traceabilities.get(counter).getIdTraceability()==id)
                found=true;
            else counter++;
        }

        if (found)
            pos = counter;

        return pos;
    }

    @Override
    public Traceability createTraceability(TraceabilityToServer traceability) {

        String uri = baseUrl+urlTraceabilities;
        Traceability traceabilityFromServer;
        try{
            ResponseEntity<Traceability> response = restTemplate.postForEntity(uri, traceability, Traceability.class);
            if(response.getBody()!=null) {
                traceabilityFromServer = response.getBody();
                String sql = "Insert into traceability values('" +traceability.getIdTraceability()+ "', '" +traceability.getArticle()+ "', " +
                        "'" +traceability.getNumberBatch()+ "' ,'" +traceability.getExpirationDate()+ "' )";
                aSynchronized.sqlCommands.add(sql);
            }
            else
                traceabilityFromServer=null;
        }catch (HttpClientErrorException e){
            return null;
        }

        return traceabilityFromServer;
    }
}
