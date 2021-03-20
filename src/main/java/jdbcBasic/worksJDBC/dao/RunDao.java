package jdbcBasic.worksJDBC.dao;

import jdbcBasic.worksJDBC.entity.Run;

import java.util.List;

public interface RunDao {
    void save(Run run); //C

    List<Run> findAll(); //R

    Run findById(int id); //R

    void update(Run run); //U

    void deleteBy(int id); //D
}
