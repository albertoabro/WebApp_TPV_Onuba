package tfg.front;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadBuilder;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.users.FullAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class Synchronized{
    String dirPc ="C:\\Users\\alber\\Desktop\\Estudios\\Universidad\\TFG\\Front\\src\\main\\java\\tfg\\front\\";
    String dirPortatil = "D:\\Alberto\\Estudios\\Universidad\\Cuarto\\TFG\\Front\\";

    protected static final DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/ServerToTpv1").build();
    protected DbxClientV2 client;
    Security security = Security.getSecurityToken();
    public List<String> sqlCommands = new ArrayList<>();

    public Synchronized() throws IOException {
        client = new DbxClientV2(config, security.token);
    }

    public List<String> getSqlCommands() {
        return sqlCommands;
    }

    public void setSqlCommands(List<String> sqlCommands) {
        this.sqlCommands = sqlCommands;
    }

    private File createAndWrite() throws IOException {

        File dataBase;
        dataBase = new File(dirPortatil+"sql.txt");
        dataBase.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataBase));

        for (String sql: sqlCommands) {
            writer.write(sql);
            writer.newLine();
        }
        writer.close();

        return dataBase;
    }

    public boolean SyncWithDropBox()  {
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

    public boolean syncDropboxWithServer() throws FileNotFoundException {
        boolean sync;
        try {
            String path = "/download/sales.txt";
            OutputStream stream = new FileOutputStream(dirPortatil+"sales.txt");
            DownloadBuilder builder = client.files().downloadBuilder(path);
            builder.download(stream);
            client.files().deleteV2(path);
            stream.close();
            sync = true;
        } catch (DbxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sync;
    }

    public List<String> readFile(File file){
        return null;
    }
}
