package bean;
import java.util.Objects;

public class Teacher {

    private String tid;
    private String tno;
    private String tname;
    private String tpw;
    private Integer tlimi;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpw() {
        return tpw;
    }

    public void setTpw(String tpw) {
        this.tpw = tpw;
    }

    public Integer getTlimi() {
        return tlimi;
    }

    public void setTlimi(Integer tlimi) {
        this.tlimi = tlimi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getTid(), teacher.getTid()) &&
                Objects.equals(getTno(), teacher.getTno()) &&
                Objects.equals(getTname(), teacher.getTname()) &&
                Objects.equals(getTpw(), teacher.getTpw()) &&
                Objects.equals(getTlimi(), teacher.getTlimi());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTid(), getTno(), getTname(), getTpw(), getTlimi());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", tpw='" + tpw + '\'' +
                ", tlimi='" + tlimi + '\'' +
                '}';
    }
}
