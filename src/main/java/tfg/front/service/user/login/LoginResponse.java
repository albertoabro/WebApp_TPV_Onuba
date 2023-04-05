package tfg.front.service.user.login;

import lombok.Data;
import tfg.front.domain.User;

@Data
public class LoginResponse {

    private String status;
    private User user;
}
