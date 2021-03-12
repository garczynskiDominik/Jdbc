package jdbc.mySqlClient;

import java.sql.*;
import java.util.Optional;


public class DbConnection {
    public Connection makeConnectionToDatabase() {
        String connectionUrl = "jdbc:mysql://localhost:3306/sklep?serverTimezone=UTC&useSSL=false&useUnicode=true";
        String user = "nowy";
        String password = "Werbkowice12";

        Connection conn = null;
        Optional<Connection> connectionOptional = Optional.of(conn);
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
