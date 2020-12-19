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
        return matcher.matches();
    }

    /**
     * 判断字符串是不是纯数字
     * @param str 字符串
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher((CharSequence) str);
        return matcher.matches();
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     * @param str 待截取字符串
     * @param strStart 字符串
     * @param strEnd 字符串
     * @return str中指定字符 strStart、strEnd之间的字符串
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        return str.substring(strStartIndex, strEndIndex).substring(strStart.length());
    }
    public static void main(String[] args) {
        JudgeHelper jdh = new JudgeHelper();
        System.out.println(jdh.isEmail("1@1.com"));
    }
}
