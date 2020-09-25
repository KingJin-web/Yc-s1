package biz;

import Util.sql.DBHelper;
import bean.Student;
import bean.Teacher;

import java.util.List;
import java.util.Map;

public class TeacherBiz {
    public void TeacherDelete(String stuName) throws BizException{
        if (stuName == null || stuName.trim().isEmpty() ){
            throw new BizException("请输入完整的学生姓名！");
        }
        DBHelper dbh = new DBHelper();
        String sql = "delete from student where Sname = ?";
        dbh.update(sql,stuName);

    }
    public void selectByName(String stuName) throws BizException{
        if (stuName == null || stuName.trim().isEmpty() ){
            throw new BizException("请输入完整的学生姓名！");
        }

        DBHelper dbh = new DBHelper(false);

        String sql = "select * from student where Sname = ?";
        dbh.query(sql,stuName);
        List<Map<String, Object>> list = dbh.query(sql,stuName);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }
    public boolean login(String tno,String tpw) throws BizException {
        if (tno == null || tno.trim().isEmpty() ){
            throw new BizException("请输入用户名！");
        }

        if (tpw == null || tpw.trim().isEmpty() ){
            throw new BizException("请输入密码！");
        }

        String sql = "select * from teacher where tno = ? and tpw = ?";
        List<Teacher> list = new DBHelper().query(sql,Teacher.class,tno,tpw);
        if (list.size() == 1){
            return true;
        }else {
            throw new BizException("用户名或密码输入错误!");
        }
    }

    public void TeacherInsert(Student student) {
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?)";
        new DBHelper().update(sql,

                student.getSno(),
                student.getSname(),
                student.getSsex(),
                student.getSage(),
                student.getSclass(),
                student.getCid(),
                student.getSmo(),
                student.getSpw(),
                student.getSma());
    }

    public void TeacherUpdate(Student student) {
        String sql = "update student set Sclass = ? ,Cid = ?,Smo = ? ,Sma = ? where Sname = ?";
        new DBHelper().update(sql,
                student.getSclass(),
                student.getCid(),
                student.getSmo(),
                student.getSma(),
                student.getSname());
    }
}
