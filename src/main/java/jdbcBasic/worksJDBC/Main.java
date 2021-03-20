package jdbcBasic.worksJDBC;

import jdbcBasic.mySqlClient.DbConnection;
import jdbcBasic.worksJDBC.database.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //insertTotableRuns(3, "Tomek", 10);
        //deleteRecordFromTableRunsByInde(connection, 3);
        //updateRecordsInRuns(3, "Tomek");
       // showAllFromRuns("*");

    }

    public static void insertTotableRuns(int id, String nazwa, int limit) {
        Connection connection = JdbcUtils.getInstance().getConnection();
        String query = "INSERT INTO RUNS (ID,NAME,MEMBERS_LIMIT) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nazwa);
            preparedStatement.setInt(3, limit);

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteRecordFromTableRunsByIndex(int id) {
        Connection connection = JdbcUtils.getInstance().getConnection();
        String queryToDataBase = "DELETE FROM RUNS  WHERE id=" + id;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(queryToDataBase);
            System.out.println("Delete Complete!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateRecordsInRuns(int id, String name) {
        Connection connection = JdbcUtils.getInstance().getConnection();
        String query = "UPDATE RUNS SET NAME= ? WHERE ID= ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void showAllFromRuns(String withColumn) {


        Connection connection = JdbcUtils.getInstance().getConnection();
        String query = "SELECT *FROM RUNS";


//        Statement st = connection.createStatement();
//        ResultSet rs = st.executeUpdate(query);
//        while (rs.next()){
//            System.out.println("id=%d, name %s, members limit=%d\n");
//            rs.getInt("id"),
//            rs.getString("name"),
//            rs.getInt("members_limit");
//        }

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            int valeuOfcolumn = ps.getMetaData().getColumnCount();



            while (rs.next()) {
                if (withColumn == "*") {
                    for (int i = 1; i <= valeuOfcolumn; i++) {
                        String nameColumn2 = rs.getString(i);
                        System.out.print(nameColumn2 + " ");
                    }
                    System.out.println();
                } else {
                    String nameColumn2 = rs.getString(rs.findColumn(withColumn));
                    System.out.println(nameColumn2 + " ");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


/**
 * ZADANIE 3
 * Utworzyć nową paczkę pl.sdacademy.entity, a następnie utworzyć w niej klasę: Run
 * wraz z odpowiadającymi im polom (o odpowiednich typach) z tabeli bazy danych.
 * <p>
 * <p>
 * ZADANIE 4
 * Utworzyć nową paczkę pl.sdacademy.database.dao i dodać w niej interfejs RunDao.
 * Zdefiniować w nim metody: save, update, findAll, findById, deleteById.
 * <p>
 * <p>
 * ZADANIE 5
 * Utworzyć nową paczkę pl.sdacademy.database.jdbc.daoimpl w której umieścić
 * klasę RunDaoImpl implementującą wcześniej zdefiniowane interfejs. Następnie
 * zaimplementować wszystkie zdefiniowane metody.
 * ZADANIE 6
 * Utworzyć nowy test jednostkowy sprawdzający działanie wszystkich metod klasy
 * RunDaoImpl.
 * ZADANIE 7
 * Dodać do RunDao metodę pozwalająca na wyszukiwanie biegu bo fragmencie
 * nazwy. Utworzyć do niej test oraz implementację.
 * ZADANIE 8
 * Dodać do RunDao metodę pozwalającą na wyszukiwanie biegów w zakresie limitu
 * uczestników (limitMax, limitMin). Utworzyć do niej test oraz implementację.
 * ZADANIE 9
 * Powtórzyć wszystko od zadania nr 3 dla tabeli MEMBERS, tworząc nową encję o
 * nazwie Member
 * ZADANIE 10
 * Dodać do klasy Runs pole zawierające listę obiektów typu Member. Zmodyfikować
 * metodę pobierającą dane dla tabeli RUNS tak by wypełnić listę wszystkimi
 * uczestnikami przypisanymi do danego biegu.
 * ZADANIE 11
 * Utworzyć nową tabelę o nazwie RUN_RESULTS związaną z tabelą MEMBERS.
 * Nowa tabela zawiera wyniki jakie uzyskał uczestnik biegu. Minimalny zestaw pól
 * które powinna zawierać nowa tabela to: id, members_id, run_time_result o
 * odpowiednich typach. Utworzyć dla nich odpowiednie klasy encyjne do
 * przechowywania danych oraz klasy DAO.
 */
