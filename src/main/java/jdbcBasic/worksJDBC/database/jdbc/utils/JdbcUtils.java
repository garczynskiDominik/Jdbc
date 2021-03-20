package jdbcBasic.worksJDBC.database.jdbc.utils;

import jdbcBasic.worksJDBC.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static final JdbcUtils instance = new JdbcUtils();
    private DbConnection dbConnection;


    private JdbcUtils() {

    }

    public static JdbcUtils getInstance() {
        return instance;
    }

    public Connection getConnection() {
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
    }


}
