package tfg.front;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class Synchronized{
    String dirPc ="C:\\Users\\alber\\Desktop";
    String dirPortatil = "D:\\Alberto\\Desktop";

    protected static final DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/ServerToTpv1").build();
    protected DbxClientV2 client;
    Security security = Security.getSecurityToken();
    public static List<String> sqlCommands = new ArrayList<>();

    public Synchronized() throws IOException {
        client = new DbxClientV2(config, security.token);
    }

    private File createAndWrite() throws IOException {

        File dataBase;
        dataBase = new File(dirPc+"sql.txt");
        dataBase.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataBase));

        for (String sql: sqlCommands) {
            writer.write(sql);
            writer.newLine();
        }
        writer.close();

        return dataBase;
    }

    public boolean syncWithDropBox()  {
        boolean sync = false;

        try {
            File dataBase = createAndWrite();
            InputStream stream = new FileInputStream(dataBase);
            UploadBuilder uploadBuilder = client.files().uploadBuilder("/Upload/" + dataBase.getName());
            uploadBuilder.withClientModified(new Date(dataBase.lastModified()));
            uploadBuilder.withMode(WriteMode.ADD);
            uploadBuilder.withAutorename(true);

            uploadBuilder.uploadAndFinish(stream);

            stream.close();
            sqlCommands.clear();
            dataBase.delete();

            sync = true;
        } catch (DbxException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return sync;
    }

    public void syncDropboxWithServer() throws FileNotFoundException {

        try {
            String path = "/download/";

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
        } catch (DbxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
