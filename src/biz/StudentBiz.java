package biz;

import util.EmailHelper;
import util.DBHelper;
import bean.Student;
import util.IOHelper;
import util.ImageUtil;

import javax.mail.MessagingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StudentBiz {
    private  int radomInt = new Random().nextInt(999999);
    private final DBHelper dbh = new DBHelper();

    // 验证账号密码合法性然后登陆
    public boolean login(String sname, String spw) throws BizException {
        String sql = "select * from student where sname=? and spw=?";
        List<Map<String, Object>> list = dbh.query(sql, sname, spw);
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
        String sql = "select * from student where 1 = 1 and sname=?";
        List<Student> list = dbh.query(sql, Student.class, sname);
        System.out.println(list);
        return list;
    }

    /**
     * 显示学生信息
     *
     * @return 学生信息list
     */
    public List<Map<String, Object>> selectByName(String sname) {
        //String sql = "select sno,sname,ssex,sage,sclass,smo,sma,imgFile ,c.Cna from student s, college c where 1 = 1 and sname= ? and c.Cid=s.Cid";
        String sql = "select * from select_student where sname = ?";
        List<Map<String, Object>> list = dbh.query(sql, sname);

        String Path = "D:\\stuImg\\";
        sql = "select photo,imgFile from student where sname = ?";
        dbh.readDB2Image(sql,Path,sname);
        return list;
    }


    /*
     * 判断是否在这个用户里面
     *
     * @param Sno
     * @return
     * @throws BizException
     */
    public boolean select(String Sno, int i) throws BizException {
        String sql = "select * from student where Sname=? ";
        List<Student> list = new DBHelper().query(sql, Student.class, Sno);
        if (list.size() == 1) {
            return true;
        } else {
            throw new BizException("您不是此用户表用户请重新选择 ");
        }
    }


    /**
     * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
     * 修改密码
     *
     * @param sno
     * @param newPw1
     * @param newPw2
     * @param YanZhengma
     * @return
     * @throws BizException
     */
    public boolean changePw(String sno, String newPw1, String newPw2, int YanZhengma) throws BizException {
        if (newPw1 == null || newPw1.trim().isEmpty()) {
            throw new BizException("密码为空 ! ");
        }
        if (newPw2 == null || newPw2.trim().isEmpty()) {
            throw new BizException("密码为空 ! ");
        }
        if (!newPw1.equals(newPw2)) {
            throw new BizException("两次密码不一致请重新输入 ! ");
        }
        if (YanZhengma != 0) {
            if (radomInt != YanZhengma) {
                throw new BizException("验证码不一致请重新获取 ! ");
            }
        }

        String sql = "update student set spw = ? where sname = ?";
        dbh.update(sql, newPw1, sno);
        return true;
    }


    /**
     * 发送验证码
     *
     * @param name
     * @return 验证码
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    public int YanZhengma(String name) {
        radomInt = new Random().nextInt(999999);
        EmailHelper eh = new EmailHelper();
        String sql = "select * from student where Sname=? ";
        List<Student> list = new DBHelper().query(sql, Student.class, name);
        String email = null;
        for (Student stu : list) {
            email = stu.getSma();
        }
        System.out.println("邮箱 " + email + "验证码 " + radomInt);

        try {
            eh.email(email, String.valueOf(radomInt),name);
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return radomInt;
    }

    public static void main(String[] args) throws BizException, GeneralSecurityException, MessagingException {
        StudentBiz studentBiz = new StudentBiz();

        //studentBiz.RetFile("钟浣文");
        System.out.println(studentBiz.selectByName("陈栋"));
    }

    /**
     * // 学生证挂失与注销
     * @param state
     * @param sno
     */
    public void update(int state , int sno) {
        String sql = "update student set state=? where sno=?";
        dbh.update(sql, state, sno);
    }
    /**
     * 返回图片名
     *
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

    public void updateImg(String url, String name, String fileName) {

        String path = url;
        FileInputStream in = null;
        try {
            in = ImageUtil.readImage(path);
            String sql = "UPDATE student set imgFile = ?, photo = ? where sname = ?";
            dbh.update(sql,fileName, in, name);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOHelper.close(in);
        }

    }

}
