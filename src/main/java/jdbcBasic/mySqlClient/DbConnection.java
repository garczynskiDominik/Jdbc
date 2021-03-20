package jdbcBasic.mySqlClient;

import java.sql.*;

public class DbConnection {
    public static  Connection makeConnectionToDatabase() {


//        String connectionString = "jdbc:mysql://localhost:3306/runcenter";
//        String dbPassword= "Werbkowice12";
//        String dbUser = "root";
//
//        Properties prop = new Properties();
//        prop.put("password", dbPassword);
//        prop.put ("user" ,dbUser);
//        prop.put("serverTimezone", "Europe/Warsaw");
//
//        try {
//            Connection connection = DriverManager.getConnection(connectionString,prop);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


        String connectionUrl = "jdbc:mysql://localhost:3306/sklep?serverTimezone=UTC&useSSL=false&useUnicode=true";
        String user = "nowy";
        String password = "Werbkowice12";

        Connection conn = null;
        try {
            conn = DriverManager.
                    getConnection(connectionUrl, user, password);
            return conn;
        } catch (SQLException e) {
            System.out.println("Wrong login data");
            e.printStackTrace();
        }
        return null;
    }
}
