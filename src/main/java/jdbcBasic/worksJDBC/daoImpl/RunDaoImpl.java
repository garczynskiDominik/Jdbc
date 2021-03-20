package jdbcBasic.worksJDBC.daoImpl;

import jdbcBasic.worksJDBC.dao.RunDao;
import jdbcBasic.worksJDBC.database.jdbc.utils.JdbcUtils;
import jdbcBasic.worksJDBC.entity.Run;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImpl implements RunDao {

    @Override
    public void save(Run run) throws SQLException {

        Connection connection = JdbcUtils.getInstance().getConnection();
        String query = "INSERT INTO RUNS (ID,NAME,MEMBERS_LIMIT) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, run.getId());
        preparedStatement.setString(2, run.getName());
        preparedStatement.setInt(3, run.getMembersLimit());

        preparedStatement.execute();
        preparedStatement.close();
    }


    @Override
    public List<Run> findAll() throws SQLException {
        Connection connection = JdbcUtils.getInstance()
                .getConnection();
        String queryToDataBase = "SELECT* FROM RUNS";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(queryToDataBase);
        List<Run> runs = new ArrayList<>();
        Run run = null;
        while (rs.next()) {
            runs.add(run = new Run(rs.getInt("id"), rs.getString("name"), rs.getInt("members_limit")));
        }
        return runs;
    }

    @Override
    public Run findById(int id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        String queryToDataBase = "SELECT* FROM RUNS WHERE id=" + id;
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(queryToDataBase);

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
        Connection connection = JdbcUtils.getInstance().getConnection();
        String query = "UPDATE RUNS SET NAME= ?, MEMBERS_LIMIT= ? WHERE ID= ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, run.getName());
            preparedStatement.setInt(2, run.getMembersLimit());
            preparedStatement.setInt(3, run.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        String queryToDataBase = "DELETE FROM RUNS  WHERE id=" + id;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(queryToDataBase);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<Run> findByNameFragment(String fragment) throws SQLException {

        Connection connection = JdbcUtils.getInstance()
                .getConnection();
        String query = "SELECT * FROM runs WHERE name LIKE CONCAT('%',?,'%')";
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, fragment);
        ResultSet rs = preparedStatement.executeQuery();
        List<Run> runs = new ArrayList<>();
        while (rs.next()) {
            runs.add(new Run(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("members_limit")));
        }
        preparedStatement.close();
        return runs;


    }

    @Override
    public List<Run> findByMembersLimitRange(int min, int max) throws SQLException {
        Connection connection = JdbcUtils.getInstance()
                .getConnection();
        String query = "SELECT * FROM RUNS WHERE MEMBERS_LIMIT BETWEEN " + min + " AND " + max + ";";
        System.out.println(query);

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(query);

        List<Run> runs = new ArrayList<>();
        Run run = null;
        while (rs.next()) {
            runs.add(run = new Run(rs.getInt("id"), rs.getString("name"), rs.getInt("members_limit")));
        }
        return runs;

    }
}
