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
        tableNames.add(new Table("USERS", "UserID", 1002));
        tableNames.add(new Table("FILES", "FileID", 10));
        tableNames.add(new Table("FILEEXTENSIONS", "ExtensionID", 10));
        return tableNames;
    }

    public void cleanDatabase() throws DBException {
        for (Table table : getTableNames()) {
            Connection connection = getConnection();
            try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + table.tableName + " where " + table.primaryKey + " >= " + table.initialAutoIncrement);
                preparedStatement.executeUpdate();

                preparedStatement = connection
                        .prepareStatement("ALTER TABLE " + table.tableName + " AUTO_INCREMENT = " + table.initialAutoIncrement);
                preparedStatement.executeUpdate();

            } catch (Throwable e) {
                System.out.println("Exception while execute cleanDatabase() for table " + table.tableName);
                e.printStackTrace();
                throw new DBException(e);
            } finally {
                closeConnection(connection);
            }
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
