package bot;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class UserNameMap {
    HashMap<String, String> userNames = new HashMap<>();
    ArrayList<String> idList = new ArrayList<>(userNames.keySet());

    public void addUserNames(String id, String name) {
        userNames.put(id, name);
    }

    public HashMap<String, String> getUserNames() {
        return userNames;
    }

    public String getChatId(int i) {
        return idList.get(i);
    }

}