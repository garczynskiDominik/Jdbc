package jdbcBasic.worksJDBC.daoImpl;

import jdbcBasic.worksJDBC.dao.MemberDao;
import jdbcBasic.worksJDBC.dao.RunDao;
import jdbcBasic.worksJDBC.database.jdbc.utils.JdbcUtils;
import jdbcBasic.worksJDBC.entity.Member;
import jdbcBasic.worksJDBC.entity.Run;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    @Override
    public void save(Member member) throws SQLException {

        String query = "INSERT INTO MEMBERS (ID,NAME,START_NUMBER,RUN_ID) VALUES (?,?,?,?)";
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);

        statement.setInt(1, member.getId());
        statement.setString(2, member.getName());
        statement.setInt(3, member.getStartNumber());
        statement.setInt(4, member.getRunId());

        statement.execute();
        statement.close();
    }

    @Override
    public List<Member> findAll() throws SQLException {
        String queryToDataBase = "SELECT* FROM MEMBERS";
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(queryToDataBase);

        ResultSet rs = statement.executeQuery();
        List<Member> members = new ArrayList<>();
        Member member = null;
        while (rs.next()) {
            members.add(member = new Member(rs.getInt("id"), rs.getString("name"), rs.getInt("START_NUMBER"), rs.getInt("run_id")));
        }
        return members;
    }

    @Override
    public Member findById(int id) throws SQLException {

        String query = "SELECT* FROM MEMBERS WHERE id=" + id;
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        Member member = null;
        if (rs.next()) {
            member = new Member();
            member.setId(rs.getInt("id"));
            member.setName(rs.getString("name"));
            member.setStartNumber(rs.getInt("start_number"));
            member.setRunId(rs.getInt("run_id"));
        }
        return member;
    }

    @Override
    public void update(Member member) throws SQLException {
        try {
            String query = "UPDATE MEMBERS SET NAME= ?, START_NUMBER= ?, RUN_ID = ? WHERE ID= ?";
            PreparedStatement statement = JdbcUtils
                    .getInstance()
                    .getConnection()
                    .prepareStatement(query);

            statement.setString(1, member.getName());
            statement.setInt(2, member.getStartNumber());
            statement.setInt(3, member.getRunId());
            statement.setInt(4, member.getId());
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try {
            String query = "DELETE FROM MEMBERS  WHERE id=" + id;
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
    public List<Member> findByNameFragment(String fragment) throws SQLException {
        String query = "SELECT * FROM MEMBERS WHERE name LIKE ?";
        System.out.println(query);
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);

        statement.setString(1, "%" + fragment + "%");
        ResultSet rs = statement.executeQuery();
        List<Member> members = new ArrayList<>();
        while (rs.next()) {
            members.add(new Member(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("start_number"),
                    rs.getInt("run_id")));
        }
        statement.close();
        return members;


    }

    @Override
    public List<Member> findByMembersLimitRange(int min, int max) throws SQLException {

        String query = "SELECT * FROM MEMBERS WHERE START_NUMBER BETWEEN ? AND ?";

        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement(query);

        statement.setInt(1, min);
        statement.setInt(2, max);

        ResultSet rs = statement.executeQuery();

        List<Member> members = new ArrayList<>();
        Member member = null;
        while (rs.next()) {
            members.add(new Member(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("start_number"),
                    rs.getInt("run_id")));
        }
        return members;

    }
}

