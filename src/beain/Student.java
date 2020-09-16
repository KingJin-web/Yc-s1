package beain;

import java.util.Objects;

public class Student {
    private String sid;
    private int sno; //学号
    private String sname;
    private String ssex;
    private int sage;
    private String sclass;
    private String sco;
    private int smo;
    private String spw;
    private String sma;

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sno=" + sno +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sage=" + sage +
                ", sclass='" + sclass + '\'' +
                ", sco='" + sco + '\'' +
                ", smo=" + smo +
                ", spw='" + spw + '\'' +
                ", sma='" + sma + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sno == student.sno &&
                sage == student.sage &&
                smo == student.smo &&
                Objects.equals(sid, student.sid) &&
                Objects.equals(sname, student.sname) &&
                Objects.equals(ssex, student.ssex) &&
                Objects.equals(sclass, student.sclass) &&
                Objects.equals(sco, student.sco) &&
                Objects.equals(spw, student.spw) &&
                Objects.equals(sma, student.sma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, sno, sname, ssex, sage, sclass, sco, smo, spw, sma);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSco() {
        return sco;
    }

    public void setSco(String sco) {
        this.sco = sco;
    }

    public int getSmo() {
        return smo;
    }

    public void setSmo(int smo) {
        this.smo = smo;
    }

    public String getSpw() {
        return spw;
    }

    public void setSpw(String spw) {
        this.spw = spw;
    }

    public String getSma() {
        return sma;
    }

    public void setSma(String sma) {
        this.sma = sma;
    }
}
