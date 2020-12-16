package bean;

import java.util.Date;

public class PEmail {

	private int mid;
	private int sno;
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

	@Override
	public String toString() {
		return "PEmail{" +
				"mid=" + mid +
				", sno=" + sno +
				", sname='" + sname + '\'' +
				", smessage='" + smessage + '\'' +
				", ppreply='" + ppreply + '\'' +
				", mtime=" + mtime +
				", ptime=" + ptime +
				'}';
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

	public String getSmessage() {
		return smessage;
	}

	public void setSmessage(String smessage) {
		this.smessage = smessage;
	}

	public String getPpreply() {
		return ppreply;
	}

	public void setPpreply(String ppreply) {
		this.ppreply = ppreply;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}
}
