package tfg.front.service.provider;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Provider;
public interface ProviderService {
    List<Provider> getProviders() throws JsonProcessingException;
    Provider getProviderById(int id);
    Provider create();
    int searchPosition(List<Provider> providers, int id);
    List<Provider> searchProviderByName(String name) throws JsonProcessingException;
    boolean createProvider(Provider provider);
    boolean updateProvider(Provider provider);
    void delete(Provider provider);
}
