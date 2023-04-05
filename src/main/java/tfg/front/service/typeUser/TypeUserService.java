package tfg.front.service.typeUser;

import tfg.front.domain.TypeUser;

import java.util.List;

public interface TypeUserService {
    List<TypeUser> getTypesUsers();
    TypeUser searchTypeUserById(List<TypeUser> typeUsers, int id);

    boolean createTypeUser(TypeUser typeUser);
    boolean updateTypeUser(TypeUser typeUser);
}
