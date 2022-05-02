package bot;

import java.util.ArrayList;
//создаем класс для списка пользователей
public class UserList {
    ArrayList<String> list = new ArrayList<>();

    public void addList(String text) {
        list.add(text);
    }

    public ArrayList<String> getList() {
        return list;
    }
}
