package bean;

import java.util.Date;

public class PEmail {

	private int mid;
	private String sname;
	private String smessage;
	private String ppreply;
	private Date mtime;
	private Date ptime;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSmessage() {
		return smessage;
	}
	public void setSmessage(String smessage) {
		this.smessage = smessage;
	}
	public String getpPreply() {
		return ppreply;
	}
	public void setpPreply(String pPreply) {
		this.ppreply = pPreply;
	}
	public Date getmtime() {
		return mtime;
	}
	public void setmtime(Date mtime) {
		this.mtime = mtime;
	}
	public Date getptime() {
		return ptime;
	}
	public void setptime(Date ptime) {
		this.ptime = ptime;
	}
	
	
}
