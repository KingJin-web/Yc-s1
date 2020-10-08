package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断帮助类
 */
public class JudgeHelper {
    /**
     * 判断Email合法性
     *
     * @param email 邮箱
     * @return 是便ture不是便false
     */
    public boolean isEmail(String email) {
        email  = email.trim();//去末尾的空格
        System.out.println(email);
        if (email.isEmpty())
            return false;
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是不是纯数字
     * @param str 字符串
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher((CharSequence) str);
        boolean result = matcher.matches();
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        JudgeHelper jdh = new JudgeHelper();
        System.out.println(jdh.isEmail("1@1.com"));
    }
}
