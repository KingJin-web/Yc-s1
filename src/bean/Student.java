package bean;


import java.io.FileInputStream;
import java.io.InputStream;

public class Student {
    private int sid;  //学生id
    private int sno; //学号
    private String sname;
    private String ssex;
    private int sage;
    private String sclass;
    private int cid;
    private float smo;
    private String spw;
    private String sma;
    private String imgfile;
    private Integer state;
	private InputStream photo;

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sno=" + sno +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sage=" + sage +
                ", sclass='" + sclass + '\'' +
                ", cid=" + cid +
                ", smo=" + smo +
                ", spw='" + spw + '\'' +
                ", sma='" + sma + '\'' +
                ", imgfile='" + imgfile + '\'' +
                '}';
    }


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
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


	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}


	public float getSmo() {
		return smo;
	}


	public void setSmo(float smo) {
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


	public String getImgfile() {
		return imgfile;
	}


	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}

}
