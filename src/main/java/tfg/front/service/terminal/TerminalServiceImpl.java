package tfg.front.service.terminal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tfg.front.Synchronized;
import tfg.front.domain.Terminal;
import tfg.front.service.AbstractClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class TerminalServiceImpl extends AbstractClient implements TerminalService {
    @Autowired
    public TerminalServiceImpl(RestTemplate restTemplate, Synchronized aSynchronized) throws IOException {super(restTemplate, aSynchronized);}

    private List<Terminal> getTerminals(ResponseEntity<List> response) throws JsonProcessingException{
        List<Terminal> terminals;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        Gson gson = new Gson();

        Type typeTerminalList = new TypeToken<ArrayList<Terminal>>(){}.getType();
        terminals = gson.fromJson(json, typeTerminalList);

        return terminals;
    }
    @Override
    public List<Terminal> getTerminals() throws JsonProcessingException {
        String uri = baseUrl+"/terminals";

        ResponseEntity response = restTemplate.exchange(uri, HttpMethod.GET,null, List.class);

        return getTerminals(response);
    }

    @Override
    public Synchronized getObject() {
        return aSynchronized;
    }
}
