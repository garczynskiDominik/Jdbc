package jdbcBasic.worksJDBC.entity;

public class Member {

    private int id;
    private String name;
    private int startNumber;
    private int runId;

    public Member(int id, String name, int startNumber, int runId) {
        this.id = id;
        this.name = name;
        this.startNumber = startNumber;
        this.runId = runId;
    }
    public Member() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startNumber=" + startNumber +
                ", runId=" + runId +
                '}';
    }
}
