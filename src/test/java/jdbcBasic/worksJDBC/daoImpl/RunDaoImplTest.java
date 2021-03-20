package jdbcBasic.worksJDBC.daoImpl;

import jdbcBasic.worksJDBC.database.jdbc.utils.JdbcUtils;
import jdbcBasic.worksJDBC.entity.Run;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RunDaoImplTest {
    private RunDaoImpl runDao = new RunDaoImpl();

    @BeforeEach
    void clearTable() throws SQLException {
        Connection connection = JdbcUtils
                .getInstance()
                .getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate("Delete From runs");
        st.close();
    }


    @Test
    void save() {
        Run run = new Run(1, "Testowy bieg", 99);
        try {
            runDao.save(run);

            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());
        } catch (SQLException throwables) {
            fail(throwables);
            throwables.printStackTrace();
        }
    }

    @Test
    void findAll() {
        try {
            Run run1 = new Run(100, "Testowy", 99);
            Run run2 = new Run(105, "Testowy", 20);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> runList = runDao.findAll();
            Run testRun1 = null;
            for (Run run : runList) {
                if (run.getId() == run1.getId()) {
                    testRun1 = run1;
                }
            }


            assertNotNull(runList);
            assertEquals(2, runList.size());
            assertEquals(run1.getId(), testRun1.getId());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void update() {

        Run run = new Run(1, "Testowy", 50);
        try {
            runDao.save(run);
            run.setMembersLimit(20);
            run.setName("Inna nazwa");
            runDao.update(run);

            Run update = runDao.findById(run.getId());
            assertNotNull(update);
            assertEquals(run.getMembersLimit(), update.getMembersLimit());
            assertEquals(run.getName(), update.getName());

        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void deleteById() {
        Run run = new Run(1, "Testowy", 100);
        try {
            runDao.save(run);
            runDao.deleteById(run.getId());
            Run deleted = runDao.findById(run.getId());

            assertNull(deleted);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    void findByNameFragment() {

        try {
            Run run1 = new Run(1, "Tomek", 50);
            Run run2 = new Run(2, "Marek", 40);
            Run run3 = new Run(3, "Roman", 30);
            runDao.save(run1);
            runDao.save(run2);
            runDao.save(run3);

            List<Run> result = runDao.findByNameFragment("om");
            List<Run> result1 = runDao.findByNameFragment("Marek");
            List<Run> result2 = runDao.findByNameFragment("dsdsds");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(run1.getId(), result.get(0).getId());
            assertEquals(run1.getName(), result.get(0).getName());
            assertEquals(run1.getMembersLimit(), result.get(0).getMembersLimit());
            assertEquals(1, result1.size());
            assertEquals(0, result2.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void findByMembersLimitRange() {
        try {
            Run run1 = new Run(1, "Tomek", 50);
            Run run2 = new Run(2, "Marek", 100);
            Run run3 = new Run(3, "Roman", 150);
            runDao.save(run1);
            runDao.save(run2);
            runDao.save(run3);

            List<Run> result = runDao.findByMembersLimitRange(70, 155);
            List<Run> result1 = runDao.findByMembersLimitRange(60, 64);
            List<Run> result2 = runDao.findByMembersLimitRange(80, 120);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(0, result1.size());
            assertEquals(1, result2.size());
            assertEquals(run2.getId(), result2.get(0).getId());
            assertEquals(run2.getName(), result2.get(0).getName());
            assertEquals(run2.getMembersLimit(), result2.get(0).getMembersLimit());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}