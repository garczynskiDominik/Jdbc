package jdbc.mySqlClient;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class OperationInMySql {
    private int counter = 31;

    public static void showInformationFromDataBase(DbConnection dbConnection, String nameOfTable, String withColumn) {
        String queryToDatabase = "SELECT * FROM " + nameOfTable;
        try {
            PreparedStatement ps = dbConnection.makeConnectionToDatabase().prepareStatement(queryToDatabase);
            ResultSet rs = ps.executeQuery();
            int valeuOfcolumn = ps.getMetaData().getColumnCount();

            while (rs.next()) {
                if (withColumn == "*") {
                    for (int i = 1; i < valeuOfcolumn; i++) {
                        String nameColumn2 = rs.getString(i);
                        System.out.print(nameColumn2 + " ");
                    }
                    System.out.println();
                } else {
                    String nameColumn2 = rs.getString(rs.findColumn(withColumn));
                    System.out.print(nameColumn2 + " ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertToTableTowar(DbConnection dbConnection, int id, String nazwa, BigDecimal cena, int ilosc, int id_pozycja, String nameOfTable) {
        String queryToDatabase = "INSERT INTO " + nameOfTable + " (id,nazwa,cena,ilosc,id_pozycja) VALUES (" + id + "," + "\"" + nazwa + "\"" + "," + cena + "," + ilosc + "," + id_pozycja + ");";
        try {
            Statement statement = dbConnection.makeConnectionToDatabase().createStatement();
            statement.executeUpdate(queryToDatabase);
            System.out.println("Insert Completed!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecordFromTable(DbConnection dbConnection, int id, String nameOfTable) {
        String queryToDataBase = "DELETE FROM " + nameOfTable + " WHERE id=" + id;
        Statement statement = null;
        try {
            statement = dbConnection.makeConnectionToDatabase().createStatement();
            statement.executeUpdate(queryToDataBase);
            System.out.println("Delete Complete!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void randomValueForInsertToTowar(DbConnection dbConnection, String nameOfTable) {
        OperationInMySql operationInMySql = new OperationInMySql();
        Random random = new Random();
        int randomValueofPrice = random.nextInt(999 - 1) + 1;
        int randomValueOfIdFaktura = random.nextInt(100 - 1) + 1;
        int randomValueofQuantity = random.nextInt(50 - 1) + 1;
        StringBuilder nameRandom = new StringBuilder();
        for (int i = 0; i < random.nextInt(8 - 1) + 1; i++) {
            char o = (char) (random.nextInt(122 - 97) + 97);
            nameRandom.append(o);
        }
        String randomName = nameRandom.toString();
        insertToTableTowar(dbConnection, counter, randomName, BigDecimal.valueOf(randomValueofPrice), randomValueofQuantity, randomValueOfIdFaktura, nameOfTable);
        counter++;
    }

}
