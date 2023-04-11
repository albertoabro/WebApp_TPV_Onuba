package tfg.front.service.typeUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import tfg.front.domain.TypeUser;

import java.util.List;

public interface TypeUserService {
    List<TypeUser> getTypesUsers() throws JsonProcessingException;
    TypeUser searchTypeUserById(List<TypeUser> typeUsers, int id);

    boolean createTypeUser(TypeUser typeUser);
    boolean updateTypeUser(TypeUser typeUser);
}
