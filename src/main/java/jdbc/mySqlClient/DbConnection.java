package jdbc.mySqlClient;

import java.sql.*;

public class DbConnection {
    public static  Connection makeConnectionToDatabase() {
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
