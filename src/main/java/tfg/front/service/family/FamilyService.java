package tfg.front.service.family;


import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.Family;

import java.util.List;

public interface FamilyService {
    List<Family> getFamilies() throws JsonProcessingException;
    Family searchFamily(List<Family> families, int id);
    Family getFamilyById(int idFamily);
    Family create();
    int searchPosition(List<Family> families, int id);
    List<Family> searchFamilyByName(String nameFamily) throws JsonProcessingException;
    boolean createFamily(Family family);
    boolean updateFamily(Family family);
    void delete(Family family);
}
