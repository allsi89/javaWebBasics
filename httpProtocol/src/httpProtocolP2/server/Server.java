package httpProtocolP2.server;

import java.util.ArrayList;
import java.util.List;

import static httpProtocolP2.constants.MessageConstants.INVALID_PATH_MESSAGE;

public class Server {
    private List<String> paths;

    public Server() {
        this.paths = new ArrayList<>();
    }

    public void setPaths(List<String> paths) {
        if (paths == null) {
            throw new IllegalArgumentException(INVALID_PATH_MESSAGE);
        }
        this.paths = paths;
    }

    public boolean isPathValid(String path){
        return this.paths.contains(path);
    }
}
