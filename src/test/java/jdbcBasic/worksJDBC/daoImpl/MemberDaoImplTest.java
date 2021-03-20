package jdbcBasic.worksJDBC.daoImpl;

import jdbcBasic.worksJDBC.database.jdbc.utils.JdbcUtils;
import jdbcBasic.worksJDBC.entity.Member;
import jdbcBasic.worksJDBC.entity.Run;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberDaoImplTest {
    MemberDaoImpl memberDao = new MemberDaoImpl();

    @BeforeEach
    void clearTable() throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();
        statement.executeUpdate("DELETE FROM MEMBERS");
        statement.close();
    }

    @Test
    void save() {
        Member member = new Member(1, "Dominik", 1, 4);
        try {
            memberDao.save(member);

            Member saved = memberDao.findById(member.getId());

            assertNotNull(saved);
            assertEquals(member.getId(), saved.getId());
            assertEquals(member.getName(), saved.getName());
            assertEquals(member.getStartNumber(), saved.getStartNumber());
        } catch (SQLException throwables) {
            fail(throwables);
            throwables.printStackTrace();
        }
    }

    @Test
    void findAll() {
        try {

            Member member1 = new Member(100, "Tomek", 2, 4);
            Member member2 = new Member(105, "Roman", 3, 10);

            memberDao.save(member1);
            memberDao.save(member2);

            List<Member> memberList = memberDao.findAll();
            Member testMember1 = null;
            for (Member member : memberList) {
                if (member.getId() == member1.getId()) {
                    testMember1 = member1;
                }
            }

            assertNotNull(memberList);
            assertEquals(2, memberList.size());
            assertEquals(member1.getId(), testMember1.getId());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void update() {
        Member member = new Member(1, "Tomek", 1, 100);
        try {
            memberDao.save(member);
            member.setStartNumber(2);
            member.setName("Krzysiek");
            memberDao.update(member);

            Member update = memberDao.findById(member.getId());
            assertNotNull(update);
            assertEquals(member.getStartNumber(), update.getStartNumber());
            assertEquals(member.getName(), update.getName());

        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void deleteById() {
        Run run = new Run(1, "Testowy", 100);
        Member member = new Member(1, "Tomek", 1, 100);
        try {
            memberDao.save(member);
            memberDao.deleteById(member.getId());
            Member deleted = memberDao.findById(member.getId());

            assertNull(deleted);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void findByNameFragment() {
        try {

            Member member1 = new Member(1, "Tomek", 1, 100);
            Member member2 = new Member(2, "Krzysiek", 5, 200);
            Member member3 = new Member(3, "Roman", 10, 300);
            memberDao.save(member1);
            memberDao.save(member2);
            memberDao.save(member3);

            List<Member> result = memberDao.findByNameFragment("om");
            List<Member> result1 = memberDao.findByNameFragment("Marek");
            List<Member> result2 = memberDao.findByNameFragment("dsdsds");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(member1.getId(), result.get(0).getId());
            assertEquals(member1.getName(), result.get(0).getName());
            assertEquals(member1.getStartNumber(), result.get(0).getStartNumber());
            assertEquals(0, result1.size());
            assertEquals(0, result2.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void findByMembersLimitRange() {
        try {
            Member member1 = new Member(1, "Tomek", 1, 100);
            Member member2 = new Member(2, "Krzysiek", 5, 200);
            Member member3 = new Member(3, "Roman", 10, 300);
            memberDao.save(member1);
            memberDao.save(member2);
            memberDao.save(member3);

            List<Member> result = memberDao.findByMembersLimitRange(0,6);
            List<Member> result1 = memberDao.findByMembersLimitRange(20,30);
            List<Member> result2 = memberDao.findByMembersLimitRange(4,6);


            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(member1.getId(), result.get(0).getId());
            assertEquals(member1.getName(), result.get(0).getName());
            assertEquals(member1.getStartNumber(), result.get(0).getStartNumber());
            assertEquals(0, result1.size());
            assertEquals(1, result2.size());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}