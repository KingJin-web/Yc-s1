package biz;

import Util.sql.DBHelper;
import beain.Student;

import java.util.List;
import java.util.Map;

public class StudentBiz {
    // 更改密码
    public void modifyPassword(String newPwd) {

    }

    // 根据选择的商品消费，从余额中扣除对应金额
    public void consume() {

    }

    // 显示充值、消费记录
    public String[][] checkHistory() {

        return null;
    }

    // 验证账号密码合法性然后登陆
    public boolean login(String Sno, String Spw) throws BizException {
        if(Sno==null ||Sno.trim().isEmpty() ) {
            throw new BizException("请输入用户名 ! ");
        }

        if(Spw==null || Spw.trim().isEmpty() ) {
            throw new BizException("请输入密码! ");
        }

        // 需要基本的判断
        String sql = "select * from student where Sno=? and Spw=?";
        List<Map<String, Object>> list= new DBHelper().query(sql, Sno,Spw);

        if(list.size() == 1) {
            return true;
        } else {
            throw new BizException("用户名或密码错误 ! ");
        }

    }

    public static void main(String[] args) throws BizException {
        StudentBiz studentBiz = new StudentBiz();
        studentBiz.select("1001","123");
    }
    // 显示学生信息
    public void select(String Sno, String Spw) {
        String sql = "select * from student where Sno=? and Spw=?";
        List<Student> list= new DBHelper().query(sql,Student.class,Sno,Spw);
        System.out.println(list);
    }
}
