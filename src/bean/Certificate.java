package bean;

import java.util.Date;

public class Certificate {

	private Integer id;
	private String zname;
	private String sname;
	private Date time;
	private Date sptime;
	private String zsimg;
	private Integer zt;


	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getSptime() {
		return sptime;
	}
	public void setSptime(Date sptime) {
		this.sptime = sptime;
	}
	public String getZsimg() {
		return zsimg;
	}
	public void setZsimg(String zsimg) {
		this.zsimg = zsimg;
	}

}
