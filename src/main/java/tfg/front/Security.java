package tfg.front;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Security{

    static Security security;
    String token;
    private static final String path = new File("token.dat").getPath();


    public Security() throws IOException {
        super();
        token = getToken();
    }


    private List<String> readTokenFile() throws IOException {
        String path = "C:\\Users\\alber\\Desktop\\dataBase\\token.dat";
        FileInputStream inputStream = new FileInputStream(path);
        DataInputStream stream = new DataInputStream(inputStream);
        List<String> tokens = new ArrayList<>();
        while (stream.available()>0)
            tokens.add(stream.readUTF().replaceFirst("\n",""));

        return tokens;
    }

    private String getToken() throws IOException {

        List <String> tokens = readTokenFile();
        String command  = "curl https://api.dropbox.com/oauth2/token -d grant_type=refresh_token -d refresh_token="+ tokens.get(3) +" -u "+ tokens.get(1) +":"+ tokens.get(2) +"";
        log.info(command);
        Process process = Runtime.getRuntime().exec(command);

        InputStream stream = process.getInputStream();
        String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));

        int posComma = result.indexOf(",");
        return result.substring(18,posComma-1);
    }

    public static Security getSecurityToken() throws IOException {
        if(security == null)
            security = new Security();
        return security;
    }
}
