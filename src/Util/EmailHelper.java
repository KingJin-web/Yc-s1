package Util;

import com.sun.mail.util.MailSSLSocketFactory;


import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

public class EmailHelper {


    /**
     * 漏洞类型：邮箱 SMTP 信息泄露
     *
     * 漏洞等级：高
     *
     * 漏洞地址：https://github.com/KingJin-web/Yc-s1/blob/b08d38b84eef9751e47cefa8454d3da68201bafd/src/Util/EmailHelper.java
     *
     * 漏洞危害：任何人可以通过 SMTP 账号密码收发邮件，进而通过邮箱重置其他平台密码
     *
     * 解决方案：重置 SMTP 密码并检查邮箱是否有敏感信息泄露（请勿只修改代码，历史版本库依旧可见）
     */
    /**
     * @param YanZhengma 验证码
     * @param email 邮箱地
     * @throws MessagingException
     * @throws GeneralSecurityException
     */
    public void email(String email, String YanZhengma) throws MessagingException, GeneralSecurityException {

//        String YanZhengma;
//        int radomInt = new Random().nextInt(999999);
//        YanZhengma = String.valueOf(radomInt);
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host", "smtp.qq.com");

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("发件人邮箱", "邮箱 SMTP 信息");
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com", "发件人邮箱", "邮箱 SMTP 信息");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("发件人邮箱"));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        //邮件标题
        mimeMessage.setSubject("Hello Mail");

        //邮件内容
        mimeMessage.setContent("验证码为：\n" + YanZhengma, "text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();


    }
}
