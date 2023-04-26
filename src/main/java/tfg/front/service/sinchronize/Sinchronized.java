package tfg.front.service.sinchronize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sinchronized {

    List<String> sqlCommands = new ArrayList<>();

    public Sinchronized(List<String> sqlCommands) {
        this.sqlCommands = sqlCommands;
    }

    public Sinchronized() {
    }

    public List<String> getSqlCommands() {
        return sqlCommands;
    }

    public void setSqlCommands(List<String> sqlCommands) {
        this.sqlCommands = sqlCommands;
    }

    public boolean createFile(List<String> sqlCommands){
        boolean created = false;
        return created;
    }

    public List<String> readFile(File file){
        return null;
    }
}
