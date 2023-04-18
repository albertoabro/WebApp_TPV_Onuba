package tfg.front.service.traceability;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Traceability;

import java.util.List;

public interface TraceabilityService {
    List<Traceability> getTraceabilities()throws JsonProcessingException;
    Traceability searchTraceabilityById(List<Traceability> traceabilities, int id);
    int searchPosition(List<Traceability> traceabilities, int id);

    boolean createTraceability(Traceability traceability);
}
