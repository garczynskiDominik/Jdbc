package jdbcBasic.worksJDBC.dao;

import jdbcBasic.worksJDBC.entity.Member;
import jdbcBasic.worksJDBC.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {
    void save(Member member) throws SQLException; //C

    List<Member> findAll() throws SQLException; //R

    Member findById(int id) throws SQLException; //R

    void update(Member member) throws SQLException; //U

    void deleteById(int id) throws SQLException; //D

    List<Member> findByNameFragment(String fragment) throws SQLException;

    List<Member> findByMembersLimitRange(int min, int max) throws SQLException;
}
