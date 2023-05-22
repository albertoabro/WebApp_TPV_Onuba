package tfg.front;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.stream.Collectors;

@Slf4j
public class Security {

    static Security security;
    String token;
    public Security() throws IOException {
        super();
        token = getToken();
    }

    private String getToken() throws IOException {

        String command  = "curl https://api.dropbox.com/oauth2/token -d grant_type=refresh_token -d refresh_token=Ivu0Qby8XJYAAAAAAAAAAXDyGjXzn4p2ErD1_IvLGn_O0TjHPomZjtb9D8nHp3y_ -u 2uvws8yubbya2zl:0ehhkv4p3uzrm1x";

        Process process = Runtime.getRuntime().exec(command);

        InputStream stream = process.getInputStream();
        String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));

        int posComma = result.indexOf(",");
        String token = result.substring(18,posComma-1);

        return token;

    }

    public static Security getSecurityToken() throws IOException {
        if(security == null)
            security = new Security();
        return security;
    }
}