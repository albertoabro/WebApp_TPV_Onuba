package tfg.front.service.traceability;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Traceability;
import tfg.front.domain.TraceabilityToServer;

import java.util.List;

public interface TraceabilityService {
    List<Traceability> getTraceabilities()throws JsonProcessingException;
    Traceability create();
    Traceability getTraceabilityByNumberBatch(int numberBatch);
    int searchPosition(List<Traceability> traceabilities, int id);
    Traceability createTraceability(TraceabilityToServer traceability);
    Traceability getTraceabilityById(int id);
}
