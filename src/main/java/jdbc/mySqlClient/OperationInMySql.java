package jdbc.mySqlClient;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class OperationInMySql {
    private static int counter = 31;

    public static void showInformationFromDataBase(String nameOfTable, String withColumn) {
        String queryToDatabase = "SELECT * FROM " + nameOfTable;
        try {
            Connection dbConnection = DbConnection.makeConnectionToDatabase();
            PreparedStatement ps = dbConnection.prepareStatement(queryToDatabase);
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
                    System.out.println(nameColumn2 + " ");
                }
            }
        } catch (SQLException e) {
            System.out.println("ddd");
            e.printStackTrace();
        }
    }

    public static void insertToTableTowar(int id, String nazwa, BigDecimal cena, int ilosc, int id_pozycja, String nameOfTable) {
        String queryToDatabase = "INSERT INTO " + nameOfTable + " (id,nazwa,cena,ilosc,id_pozycja) VALUES (" + id + "," + "\"" + nazwa + "\"" + "," + cena + "," + ilosc + "," + id_pozycja + ");";
        try {
            Connection dbConnection = DbConnection.makeConnectionToDatabase();
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(queryToDatabase);
            System.out.println("Insert Completed!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecordFromTable(int id, String nameOfTable) {
        String queryToDataBase = "DELETE FROM " + nameOfTable + " WHERE id=" + id;
        Statement statement = null;
        try {
            Connection dbConnection = DbConnection.makeConnectionToDatabase();
            statement = dbConnection.createStatement();
            statement.executeUpdate(queryToDataBase);
            System.out.println("Delete Complete!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void randomValueForInsertToTowar(String nameOfTable) {
        OperationInMySql operationInMySql = new OperationInMySql();
        Random random = new Random();
        int randomValueofPrice = random.nextInt(999 - 1) + 1;
        int randomValueOfIdFaktura = random.nextInt(100 - 1) + 1;
        int randomValueofQuantity = random.nextInt(50 - 1) + 1;
        int valueOfWordLength = random.nextInt(8 - 1) + 1;

        StringBuilder nameRandom = new StringBuilder();

        for (int i = 0; i < valueOfWordLength; i++) {
            int valueLettersFromAsci = random.nextInt(122 - 97) + 97;
            char o = (char) valueLettersFromAsci;
            nameRandom.append(o);
        }
        String randomName = nameRandom.toString();
        insertToTableTowar(counter, randomName, BigDecimal.valueOf(randomValueofPrice), randomValueofQuantity, randomValueOfIdFaktura, nameOfTable);
        counter++;
    }

}
