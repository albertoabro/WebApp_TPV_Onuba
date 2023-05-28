package tfg.front.service.traceabilityProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Product;
import tfg.front.domain.Traceability;
import tfg.front.domain.TraceabilityProduct;
import tfg.front.domain.TraceabilityToServer;

import java.util.List;

public interface TraceabilityProductService {
    List<TraceabilityProduct> getTraceabilityProducts(int id) throws JsonProcessingException;
    List<TraceabilityProduct> getTraceabilityProducts() throws JsonProcessingException;
    boolean create(TraceabilityToServer traceability, Product product);
}
