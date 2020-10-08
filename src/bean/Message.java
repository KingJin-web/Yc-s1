package bean;

import java.util.Date;

public class Message {
	private int mid;
	private String sname;
	private Date mdate;
	private String message;

	public Message() {

	}

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

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "学生姓名: " + sname + "\n留言内容: " + message + "\n留言时间: " + mdate + "\n";
	}

}
