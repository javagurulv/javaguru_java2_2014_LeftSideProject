package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor on 01/07/2014.
 */
public class DatabaseCleaner extends DAOImpl {

    private List<Table> getTableNames() {
        List<Table> tableNames = new ArrayList<Table>();
        tableNames.add(new Table("Users", "UserID", 1002));
        tableNames.add(new Table("Files", "FileID", 10));
        tableNames.add(new Table("fileExtensions", "ExtensionID", 10));
        tableNames.add(new Table("todoItemsToUsers", "ItemID", 10));
        tableNames.add(new Table("todoItemsToUsers", "UserID", 10));
        tableNames.add(new Table("todoItemsToGroups", "ItemID", 10));
        tableNames.add(new Table("todoItemsToGroups", "GroupID", 10));
        tableNames.add(new Table("todoItems", "ItemID", 10));
        tableNames.add(new Table("todoStates", "StateID", 10));
        tableNames.add(new Table("todoGroups", "GroupID", 10));
        tableNames.add(new Table("todoItemComments", "CommentID", 10));
        return tableNames;
    }

    public void cleanDatabase() throws DBException {
        Connection connection = null;
        Table processingTable = null;
        try {
            connection = getConnection();
            for (Table table : getTableNames()) {
                processingTable = table;
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + table.tableName + " where " + table.primaryKey + " >= " + table.initialAutoIncrement);
                preparedStatement.executeUpdate();

                preparedStatement = connection
                        .prepareStatement("ALTER TABLE " + table.tableName + " AUTO_INCREMENT = " + table.initialAutoIncrement);
                preparedStatement.executeUpdate();
            }
        } catch (Throwable e) {
            System.out.println("DatabaseCleaner: Exception while execute cleanDatabase()" +
                    (null == processingTable ? "": " for table " + processingTable.tableName ));
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private class Table {
        public String tableName;
        public String primaryKey;
        public int initialAutoIncrement;

        private Table(String tableName, String primaryKey, int initialAutoIncrement) {
            this.tableName = tableName;
            this.primaryKey = primaryKey;
            this.initialAutoIncrement = initialAutoIncrement;
        }
    }
}
