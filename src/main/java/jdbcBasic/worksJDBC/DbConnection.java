package jdbcBasic.worksJDBC;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private DbConnection dbConnection;

    public Connection getDbConnection() {
        String connectionString = "jdbc:mysql://localhost:3306/runcenter?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true";
        String dbPassword = "Werbkowice12";
        String dbUser = "nowy";

        Properties prop = new Properties();
        prop.put("password", dbPassword);
        prop.put("user", dbUser);
        prop.put("serverTimezone", "Europe/Warsaw");
        Connection dBConnection = null;
        try {
            dBConnection = DriverManager.getConnection(connectionString, prop);

            return dBConnection;

        } catch (
                SQLException throwables) {
            System.out.println("nie polaczono");
            throwables.printStackTrace();
        }
        return null;

//        String connectionUrl = "jdbc:mysql://localhost:3306/runcenter?serverTimezone=UTC&useSSL=false&useUnicode=true";
//        String user = "nowy";
//        String password = "Werbkowice12";
//
//        Connection conn = null;
//        try {
//            conn = DriverManager.
//                    getConnection(connectionUrl, user, password);
//            return conn;
//        } catch (SQLException e) {
//            System.out.println("Wrong login data");
//            e.printStackTrace();
//        }
//        return null;
    }
}
