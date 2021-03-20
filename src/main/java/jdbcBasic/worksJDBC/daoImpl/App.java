package jdbcBasic.worksJDBC.daoImpl;

import jdbcBasic.worksJDBC.entity.Run;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        RunDaoImpl runDao = new RunDaoImpl();
//        runDao.save(new Run(1,"elo",50));
//        runDao.save(new Run(2,"elo",100));


        List<Run> list = runDao.findByNameFragment("%m%");

        list.forEach(System.out::println);
    }
}
