package lv.javaguru.java2.web.mvc.fileServlet;

import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.database.jdbc.FileDAOImpl;
import lv.javaguru.java2.domain.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by Sergey on 13.11.14.
 */
public class FileModel {

    private static final FileDAO fileDAO = new FileDAOImpl();
    //private static final TodoItemDAO todoItemDAO = new TodoItemDAOImpl();

    private List<File> files = fileDAO.getAll();
    //private List<TodoItem> todoItems = todoItemDAO.getAll();
    private List<Long> itemList = obtainItemList();

    public List<Long> obtainItemList() {
        List<Long> itemList = new ArrayList<Long>();
        int fileAmount = getFileAmount();

        for (int i = 1; i < fileAmount; i++) {
            long x = files.get(i).getTodoItemID();
            if (!itemList.contains(x)) {
                itemList.add(x);
            }
        }
        Collections.sort(itemList);
        return itemList;
    }


    public File getFile(int i) {
        return files.get(i);
    }

 /*   public TodoItem getTodoItem(int i) {
        return todoItems.get(i);
    }*/

    public int getFileAmount() {
        return files.size();
    }

    public int getItemListAmount() {
        return itemList.size();
    }

    public List<Long> getItemList() {
        return itemList;
    }


}

