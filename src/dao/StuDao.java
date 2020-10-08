package dao;

import java.util.List;


import bean.Message;
import bean.PEmail;
import util.DBHelper;

public class StuDao {
	public String showMessage() {
		DBHelper dbh = new DBHelper();
		String sql = "select sname,mdate,message from messageboard order by Mdate desc limit 3;";
		List<Message> list = dbh.query(sql, Message.class);
		String ms = "学生留言：";
		for (Message mss : list) {
			ms = ms + String.valueOf(mss);
		}
//		String ms = String.valueOf(list);
		System.out.println(ms);
		return ms;
	}

	public String showEmail() {
		DBHelper dbh = new DBHelper();
		String sql = "select * from pmail where pPreply is null order by mTime desc limit 1";
		List<PEmail> list = dbh.query(sql, PEmail.class);
		String email = "学生信箱:";
		for (PEmail em : list) {
			email = email + String.valueOf(em);
		}
		System.out.println(email);
		return email;
	}

	public static void main(String[] args) {
		StuDao s = new StuDao();
		s.showMessage();
	}
}
