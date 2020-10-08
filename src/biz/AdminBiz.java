package biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Student;
import util.DBHelper;

public class AdminBiz {
	private DBHelper dbh = new DBHelper();

	public boolean login(String aname, String apwd) throws BizException {
		if (aname == null || aname.trim().isEmpty()) {
			throw new BizException("请输入用户名！");
		}

		if (apwd == null || apwd.trim().isEmpty()) {
			throw new BizException("请输入密码！");
		}

		String sql = "select * from admin where aname=? and apwd=?";
		List<Map<String, Object>> list = new DBHelper().query(sql, aname, apwd);
		if (list.size() == 1) {
			return true;
		} else {
			throw new BizException("用户名或密码输入错误!");
		}
	}

	public List<Student> selectByStu(Student student) throws SQLException {
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			String sql = "select * from student where 1 = 1 ";
			List<Object> params = new ArrayList<>();
			if (student.getSname() != null && student.getSname().trim().isEmpty() == false) {
				sql += "and Sname like ? ";
				params.add("%" + student.getSname() + "%");
			}
			if (student.getSno() != 0) {
				sql += "and Sno = ? ";
				params.add(student.getSno());
			}
			if (student.getSclass() != null && student.getSclass().trim().isEmpty() == false) {
				sql += "and Sclass like ? ";
				params.add("%" + student.getSclass() + "%");
			}
			if (student.getCid() != 0) {
				sql += "and Cid = ? ";
				params.add(student.getCid());
			}
			Object[] pArray = params.toArray();
			return dbh.query(sql, Student.class, pArray);
		} finally {
			dbh.close();
		}
	}

	// 注册
	public void insert(int sno, String sname, String ssex, int sage, String sclass, int cid, float smo, String spw,
			String sma, String imgFile) {
		String sql = "insert into student values(null,?,?,?,?,?,?,?,?,?,?,0)";
		dbh.update(sql, sno, sname, ssex, sage, sclass, cid, smo, spw, sma, imgFile);
	}

	// 修改学生信息
	public void update(int sage, String sclass, int cid, String sma, int sno) {
		String sql = "update student set sage=?,sclass=?,cid=?,sma=? where sno=?";
		dbh.update(sql, sage, sclass, cid, sma, sno);
	}

	// 学生证挂失与注销
	public void update(int state, int sno) {
		String sql = "update student set state=? where sno=?";
		dbh.update(sql, state, sno);
	}

	// 学生证补办收取手续费
	public void update(float smo, int state, int sno) throws BizException {
		float smo1 = 0;
		String sql = "select * from student where sno=?";
		List<Student> list = dbh.query(sql, Student.class, sno);
		for (Student student : list) {
			smo1 = student.getSmo();
			if (smo1 >= 10) {
				sql = "update student set smo=smo-10,state=? where sno=?";
				dbh.update(sql, state, sno);
			} else {
				sql = "update student set smo=smo-10,state=? where sno=?";
				dbh.update(sql, state, sno);
				throw new BizException("余额不足,请补办后及时充值补交补办费!");
			}
		}

	}

	public static void main(String[] args) {
		AdminBiz a = new AdminBiz();
//		a.insert(111, "李四", "男", 20, "2", 3, 400.00f, "1234", "lisi@qq.com", "陈栋.JPG");
		a.update(23, "3", 3, "1232@qq.com", 151);
	}
}
