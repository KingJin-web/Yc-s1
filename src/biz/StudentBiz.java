package biz;

import Util.EmailHelper;
import Util.sql.DBHelper;
import bean.Student;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StudentBiz {
    private static int radomInt = 0;

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
        if (Sno == null || Sno.trim().isEmpty()) {
            throw new BizException("请输入用户名 ! ");
        }

        if (Spw == null || Spw.trim().isEmpty()) {
            throw new BizException("请输入密码! ");
        }

        // 需要基本的判断
        String sql = "select * from student where Sname=? and Spw=?";
        List<Map<String, Object>> list = new DBHelper().query(sql, Sno, Spw);

        if (list.size() == 1) {
            return true;
        } else {
            throw new BizException("用户名或密码错误 ! ");
        }

    }


    /**
     * 显示学生信息
     *
     * @return 学生信息list
     */
    public List<Student> select(String sname) {
        String sql = "select * from student where 1 = 1 and Sname=?";
        List<Student> list = new DBHelper().query(sql, Student.class, sname);
        System.out.println(list);
//        Student email = list.getSma();
        return list;
    }

    /**
     * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值 修改密码
     *
     * @param newPw1
     * @param newPw2
     * @return
     */
    public boolean changePw(String sno, String newPw1, String newPw2, int YanZhengma)
            throws BizException{

        if (newPw1 == null || newPw1.trim().isEmpty()) {
            throw new BizException("密码为空 ! ");
        }
        if (newPw2 == null || newPw2.trim().isEmpty()) {
            throw new BizException("密码为空 ! ");
        }
        if (!newPw1.equals(newPw2)) {
            throw new BizException("两次密码不一致请重新输入 ! ");
        }

        Scanner input = new Scanner(System.in);// 创建一个键盘扫描类对象

        System.out.println(radomInt);
        if (radomInt != YanZhengma) {
            throw new BizException("验证码不一致请重新获取 ! ");
        }
        // UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
        String sql = "update student set spw = ? where sname = ?";
        new DBHelper().update(sql, newPw1, sno);
        return true;
    }

    /**
     * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
     * 修改密码
     *
     * @param newPw1
     * @param newPw2
     * @return 修改结果
     */
    public int changePw(String name, String newPw1, String newPw2) throws BizException, GeneralSecurityException, MessagingException {
        StudentBiz studentBiz = new StudentBiz();

        if (newPw1 == null || newPw1.trim().isEmpty()) {
            throw new BizException("密码为空 ! ");
        }
        if (newPw2 == null || newPw2.trim().isEmpty()) {
            throw new BizException("密码为空 ! ");
        }
        if (!newPw1.equals(newPw2)) {
            throw new BizException("两次密码不一致请重新输入 ! ");
        }
        radomInt = studentBiz.YanZhengma(name);
        Scanner input = new Scanner(System.in);//创建一个键盘扫描类对象
        System.out.print("请您输入内容:");
        int YanZhengma = input.nextInt(); //输入整型
        System.out.println(radomInt);
        if (radomInt != YanZhengma) {
            throw new BizException("验证码不一致请重新获取 ! ");
        }
        //UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
        String sql = "update student set spw = ? where Sname = ?";
        new DBHelper().update(sql, newPw1, name);
        return 0;
    }

    /**
     * 发送验证码
     *
     * @param name
     * @return 验证码
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    public int YanZhengma(String name) throws GeneralSecurityException, MessagingException {
        radomInt = new Random().nextInt(999999);
        EmailHelper eh = new EmailHelper();
        String sql = "select * from student where Sname=? ";
        List<Student> list = new DBHelper().query(sql, Student.class, name);
        String email = null;
        for (Student stu : list) {
            email = stu.getSma();
        }
        System.out.println(email + " " + radomInt);

        eh.email(email, String.valueOf(radomInt));

        return radomInt;
    }

    public static void main(String[] args) throws BizException, GeneralSecurityException, MessagingException {
        StudentBiz studentBiz = new StudentBiz();
//        List<Student> list = studentBiz.select("蔡徐坤");
//        String email = null;
//        for (Student stu : list) {
//            email = stu.getSma();
//        }
//        System.out.println(email);
        studentBiz.RetFile("钟浣文");
    }

    /**
     * 返回图片名
     * @param name 学生姓名
     * @return 图片名
     */
    public String RetFile(String name) {
        String File = null;
        StudentBiz studentBiz = new StudentBiz();
        List<Student> list = studentBiz.select(name);
        for (Student stu : list) {
            File = stu.getImgfile();
        }
        System.out.println(File);
        return File;
    }

    //UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
    public void updaeImg(String fileName,String name){
        String sql = "update student set imgFile = ? where Sname = ?";
        DBHelper dbh = new DBHelper();
        dbh.update(sql,fileName,name);
    }

    String[] college = new String[]{"外国语学院", "建工学院", "数能学院", "机械学院", "材化学院", "电信学院", "经管学院", "计信学院"};


}
