package tfg.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FrontApplication {
    @Value("$accessToken.tpv1")
    static String token;


    public static void main(String[] args) {
        log.info("Valor de token: " + token);
        System.out.println("Valor de token: "+ token);
        SpringApplication.run(FrontApplication.class, args);
    }

}
