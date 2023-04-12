package tfg.front.service.family;


import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Family;

import java.util.List;

public interface FamilyService {
    List<Family> getFamilies() throws JsonProcessingException;
    Family searchFamily(List<Family> families, int id);
    int searchPosition(List<Family> families, int id);
    List<Family> searchFamilyByName(String nameFamily) throws JsonProcessingException;
    boolean createFamily(Family family);
    boolean updateFamily(Family family);
}
