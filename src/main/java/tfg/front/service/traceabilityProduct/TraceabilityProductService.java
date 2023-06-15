package tfg.front.service.traceabilityProduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Product;
import tfg.front.domain.Traceability;
import tfg.front.domain.TraceabilityProduct;

import java.util.List;

public interface TraceabilityProductService {
    List<TraceabilityProduct> getTraceabilityProducts(int id) throws JsonProcessingException;
    List<TraceabilityProduct> getTraceabilityProducts() throws JsonProcessingException;
    boolean create(Traceability traceability, Product product);
}
