package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.front.domain.TypeUser;
import tfg.front.service.typeUser.TypeUserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/typeUsers")
public class TypeUserController {
    String denomination;
    int id, rol;
    private List<TypeUser> typesUser;
    private final TypeUserService typeUserService;

    public TypeUserController(TypeUserService typeUserService) {this.typeUserService = typeUserService;}


    public List<String> getDenomination() throws JsonProcessingException {
        typesUser = typeUserService.getTypesUsers();
        List<String> denominations = new ArrayList<>();
        for (TypeUser typeUser:typesUser) {
            denominations.add(typeUser.getDenomination());
        }
        return denominations;
    }
}
