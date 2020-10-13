package biz;

import bean.Student;
import util.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherBiz {
	public void TeacherDelete(String stuName) throws BizException {
		if (stuName == null || stuName.trim().isEmpty()) {
			throw new BizException("请输入完整的学生姓名！");
		}
		DBHelper dbh = new DBHelper();
		String sql = "delete from student where Sname = ?";
		dbh.update(sql, stuName);

	}

	public List<Student> selectByStu (Student student) throws SQLException{
    	DBHelper dbh = null;
    	try {
	    	dbh = new DBHelper();
			String sql = "select * from student where 1 = 1 and state <> 2 ";

	    	List<Object> params = new ArrayList<>();
	    	if(student.getSname() != null && !student.getSname().trim().isEmpty()) {
	    		sql += "and Sname like ? ";
	    		params.add("%" + student.getSname() + "%");
	    	}
	    	if(student.getSno() != 0 ) {
	    		sql += "and Sno = ? ";
	    		params.add( student.getSno());
	    	}
	    	if(student.getSclass() != null && !student.getSclass().trim().isEmpty()) {
	    		sql += "and Sclass like ? ";
	    		params.add("%" + student.getSclass() + "%");
	    	}
	    	if(student.getCid() != 0) {
	    		sql += "and Cid = ? ";
	    		params.add( student.getCid());
	    	}
	    	Object[] pArray = params.toArray();
			return dbh.query(sql,Student.class,pArray);
    	}finally {
			assert dbh != null;
			dbh.close();
    	}
    }

	public void selectByName(String stuName) throws BizException {
		if (stuName == null || stuName.trim().isEmpty()) {
			throw new BizException("请输入完整的学生姓名！");
		}

		DBHelper dbh = new DBHelper(false);

		String sql = "select * from student where Sname = ?";
		dbh.query(sql, stuName);
		List<Map<String, Object>> list = dbh.query(sql, stuName);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	public boolean login(String tname, String tpw) throws BizException {
		String sql = "select * from teacher where tname = ? and tpw = ?";
		List<Map<String, Object>> list = new DBHelper().query(sql, tname, tpw);
		if (list.size() == 1) {
			return true;
		} else {
			throw new BizException("用户名或密码输入错误!");
		}
	}

	/**
	 * 判断是否在这个用户里面
	 * 
	 * @param Sno
	 * @return
	 * @throws BizException
	 */
	public boolean select(String Sno) throws BizException {
		String sql = "select * from teacher where tname = ?";
		List<Map<String, Object>> list = new DBHelper().query(sql, Sno);
		System.out.println(list);
		if (list.size() == 1) {
			return true;
		} else {
			throw new BizException("您不是此用户表用户请重新选择 ");
		}
	}
}
