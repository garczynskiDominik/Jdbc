package jdbcBasic.worksJDBC.daoImpl;

import jdbcBasic.worksJDBC.dao.RunDao;
import jdbcBasic.worksJDBC.database.jdbc.utils.JdbcUtils;
import jdbcBasic.worksJDBC.entity.Run;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImpl implements RunDao {

    @Override
    public void save(Run run) throws SQLException {

        String query = "INSERT INTO RUNS (ID,NAME,MEMBERS_LIMIT) VALUES (?,?,?)";
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);

        statement.setInt(1, run.getId());
        statement.setString(2, run.getName());
        statement.setInt(3, run.getMembersLimit());

        statement.execute();
        statement.close();
    }

    @Override
    public List<Run> findAll() throws SQLException {
        String queryToDataBase = "SELECT* FROM RUNS";
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(queryToDataBase);

        ResultSet rs = statement.executeQuery();
        List<Run> runs = new ArrayList<>();
        Run run = null;
        while (rs.next()) {
            runs.add(run = new Run(rs.getInt("id"), rs.getString("name"), rs.getInt("members_limit")));
        }
        return runs;
    }

    @Override
    public Run findById(int id) throws SQLException {

        String query = "SELECT* FROM RUNS WHERE id=" + id;
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        Run run = null;
        if (rs.next()) {
            run = new Run();
            run.setId(rs.getInt("id"));
            run.setName(rs.getString("name"));
            run.setMembersLimit(rs.getInt("members_limit"));
        }
        return run;
    }

    @Override
    public void update(Run run) throws SQLException {
        try {
            String query = "UPDATE RUNS SET NAME= ?, MEMBERS_LIMIT= ? WHERE ID= ?";
            PreparedStatement statement = JdbcUtils
                    .getInstance()
                    .getConnection()
                    .prepareStatement(query);

            statement.setString(1, run.getName());
            statement.setInt(2, run.getMembersLimit());
            statement.setInt(3, run.getId());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try {
            String query = "DELETE FROM RUNS  WHERE id=" + id;
            PreparedStatement statement = JdbcUtils
                    .getInstance()
                    .getConnection()
                    .prepareStatement(query);

            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<Run> findByNameFragment(String fragment) throws SQLException {
        String query = "SELECT * FROM runs WHERE name LIKE ?";
        System.out.println(query);
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);

        statement.setString(1, "%" + fragment + "%");
        ResultSet rs = statement.executeQuery();
        List<Run> runs = new ArrayList<>();
        while (rs.next()) {
            runs.add(new Run(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("members_limit")));
        }
        statement.close();
        return runs;


    }

    @Override
    public List<Run> findByMembersLimitRange(int min, int max) throws SQLException {

        String query = "SELECT * FROM RUNS WHERE MEMBERS_LIMIT BETWEEN ? AND ?";

        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);

        statement.setInt(1, min);
        statement.setInt(2, max);

        ResultSet rs = statement.executeQuery();

        List<Run> runs = new ArrayList<>();
        Run run = null;
        while (rs.next()) {
            runs.add(run = new Run(rs.getInt("id"), rs.getString("name"), rs.getInt("members_limit")));
        }
        return runs;

    }
}
