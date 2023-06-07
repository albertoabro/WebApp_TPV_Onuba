package tfg.front;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import lombok.extern.slf4j.Slf4j;
import tfg.front.error.RestTemplateError;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class Synchronized{
    String dirPc ="C:\\Users\\alber\\Desktop";
    String path = "/download/";

    protected static final DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/ServerToTpv1").build();
    protected DbxClientV2 client;
    Security security = Security.getSecurityToken();
    public List<String> sqlCommands = new ArrayList<>();

    public Synchronized() throws IOException {
        client = new DbxClientV2(config, security.token);
    }

    private File createAndWrite() throws IOException {

        File dataBase;
        dataBase = new File("sql.txt");
        if(dataBase.createNewFile()) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataBase))) {

                for (String sql : sqlCommands) {
                    writer.write(sql);
                    writer.newLine();
                }
            }
        }

        return dataBase;
    }

    public void syncWithDropBox()  {

        try {
            File dataBase = createAndWrite();
            try(InputStream stream = new FileInputStream(dataBase)){

                UploadBuilder uploadBuilder = client.files().uploadBuilder("/Upload/" + dataBase.getName());
                uploadBuilder.withClientModified(new Date(dataBase.lastModified()));
                uploadBuilder.withMode(WriteMode.ADD);
                uploadBuilder.withAutorename(true);

                uploadBuilder.uploadAndFinish(stream);

                stream.close();
                sqlCommands.clear();
                dataBase.deleteOnExit();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void syncDropboxWithServer(){

        try {

            ListFolderResult files = client.files().listFolderBuilder(path).withRecursive(true).start();
            int i = 1;

                for(Metadata metadata : files.getEntries())
                {
                    if(!metadata.getName().equals("download"))
                    {
                        path = metadata.getPathDisplay();
                        DownloadBuilder builder = client.files().downloadBuilder(path);
                        OutputStream stream = new FileOutputStream(dirPc+"sales"+i+".txt");
                        builder.download(stream);
                        client.files().deleteV2(path);
                        stream.close();
                        i++;
                    }
                }
        } catch (Exception e) {
            throw new RestTemplateError(e.getMessage());
        }
    }

}
