package jdbcBasic.worksJDBC.database.jdbc.utils;

import jdbcBasic.worksJDBC.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static  JdbcUtils instance;
    private Connection dBConnection;

    public static JdbcUtils getInstance() {
        if (instance==null){
            instance= new JdbcUtils();
        }
        return instance;
    }

    private JdbcUtils() {
        try {
            String connectionString = "jdbc:mysql://localhost:3306/runcenter";
            String dbPassword = "Werbkowice12";
            String dbUser = "nowy";

            Properties prop = new Properties();
            prop.put("password", dbPassword);
            prop.put("user", dbUser);
            prop.put("serverTimezone", "Europe/Warsaw");
            dBConnection = DriverManager.getConnection(connectionString, prop);

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return  dBConnection;
    }
}
