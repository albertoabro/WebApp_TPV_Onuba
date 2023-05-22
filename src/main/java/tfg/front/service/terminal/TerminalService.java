package tfg.front.service.terminal;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.Synchronized;
import tfg.front.domain.Terminal;

import java.util.List;

public interface TerminalService {
    List<Terminal> getTerminals() throws JsonProcessingException;
    Synchronized getObject();
}
