package beain;

import java.util.Objects;

public class Student {
    private String Sid;
    private int Sno; //学号
    private String Sname;
    private String Ssex;
    private int Sage;
    private String Sclass;
    private String Sco;
    private int Smo;
    private String Spw;
    private String Sma;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public int getSno() {
        return Sno;
    }

    public void setSno(int sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public int getSage() {
        return Sage;
    }

    public void setSage(int sage) {
        Sage = sage;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSco() {
        return Sco;
    }

    public void setSco(String sco) {
        Sco = sco;
    }

    public int getSmo() {
        return Smo;
    }

    public void setSmo(int smo) {
        Smo = smo;
    }

    public String getSpw() {
        return Spw;
    }

    public void setSpw(String spw) {
        Spw = spw;
    }

    public String getSma() {
        return Sma;
    }

    public void setSma(String sma) {
        Sma = sma;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Sid='" + Sid + '\'' +
                ", Sno=" + Sno +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sage=" + Sage +
                ", Sclass='" + Sclass + '\'' +
                ", Sco='" + Sco + '\'' +
                ", Smo=" + Smo +
                ", Spw='" + Spw + '\'' +
                ", Sma='" + Sma + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Sno == student.Sno &&
                Sage == student.Sage &&
                Smo == student.Smo &&
                Objects.equals(Sid, student.Sid) &&
                Objects.equals(Sname, student.Sname) &&
                Objects.equals(Ssex, student.Ssex) &&
                Objects.equals(Sclass, student.Sclass) &&
                Objects.equals(Sco, student.Sco) &&
                Objects.equals(Spw, student.Spw) &&
                Objects.equals(Sma, student.Sma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Sid, Sno, Sname, Ssex, Sage, Sclass, Sco, Smo, Spw, Sma);
    }
}
