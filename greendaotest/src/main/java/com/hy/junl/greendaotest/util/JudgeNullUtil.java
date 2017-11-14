package com.hy.junl.greendaotest.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by yuanjunliang on 2017/4/25.
 * description：判断单个、多个String及List是否为空
 */

public class JudgeNullUtil {

    /**
     *  详细正则表达式说明请参考JDK API 1.6 : java.util.regex.Pattern
     *  1. \s 空白字符：[ \t\n\x0B\f\r]
     *  2. \p{Blank} 空格或制表符：[ \t] （仅 US-ASCII）
     *  3. \p{Space} 空白字符：[ \t\n\x0B\f\r] （仅 US-ASCII）
     */
    private static final String REGEX_ALL = "[　\\s\\p{Space}\\p{Blank}]*";

    private static final String REGEX_TRIM = "^[　\\s\\p{Space}\\p{Blank}]*|[　\\s\\p{Space}\\p{Blank}]*$";

    /**
     * 只判断单个String.trim()是否为空
     * 若非空则返回true,否则返回false
     * @param str
     * @return boolean
     */
    public static boolean iStrTrim(String str) {
        if (!"".equals(str) && null != str) {
            if (!"".equals(str.trim()) && null != str.trim()) return true;
        }
        return false;
    }

    /**
     * 只判断单个String是否为空(无论有没全角或半角的空格)
     * 若非空则返回true,否则返回false
     * @param str
     * @return boolean
     */
    public static boolean iStr(String str) {
        if (!"".equals(str) && null != str) return true;
        return false;
    }

    /**
     * 只判断单个String删除所有空格后是否为空
     * @param str
     * @return boolean
     */
    public static boolean iRegStr(String str){
        String tmps = removeAllSpace(str);
        if(iStr(tmps)){
            tmps = null;
            return true;
        }
        return false;
    }

    /**
     * 只判断单个String删除所有开头和结尾的空格后是否为空
     * @param str
     * @return boolean
     */
    public static boolean iRegStrTrim(String str){
        String tmps = removeTrimSpace(str);
        if(iStr(tmps)){
            tmps = null;
            return true;
        }
        return false;
    }


    /**
     * 只判断多个String是否为空(无论有没全角或半角的空格)
     * 若非空则返回true,否则返回false
     * @param str
     * @return boolean
     */
    public static boolean iAryStr(String...str) {
        if(null == str) return false;
        for (String s : str) {
            if(!iStr(s)) return false;
        }
        return true;
    }

    /**
     * 只判断多个String.trim()是否为空
     * 若非空则返回true,否则返回false
     * @param str
     * @return boolean
     */
    public static boolean iAryStrTrim(String...str){
        if(null == str) return false;
        for (String s : str) {
            if(!iStrTrim(s)) return false;
        }
        return true;
    }

    /**
     * 判断多个String删除所有空格后是否为空
     * @param str
     * @return boolean
     */
    public static boolean iAryRegStr(String...str){
        if(null == str) return false;
        for(String s : str){
            if(!iRegStr(s)) return false;
        }
        return true;
    }

    /**
     * 判断多个String删除所有开头和结尾的空格后是否为空
     * @param str
     * @return boolean
     */
    public static boolean iAryRegStrTrim(String...str){
        if(null == str) return false;
        for(String s : str){
            if(!iRegStrTrim(s)) return false;
        }
        return true;
    }

    /**
     * 判断List是否为空,非空返回true,空则返回false
     *
     * @param list
     * @return boolean
     */
    public static boolean iList(List<?> list) {
        if (null != list) {
            if ((list.size() > 0) && !list.isEmpty()) return true;
        }
        return false;
    }

    /**
     * 删除String的所有空格(包括全角和半角),
     * 回车符(\r),制表符(\t \x0B),换行符(\n),换页符(\f)
     * @param str
     * @return String
     */
    public static String removeAllSpace(String str) {
        if(iStr(str)){
            // 全角空格的UniCode是12288,要先将所有的全角空格进行转化,然后替换所有空格
            String tempStr = str.replace((char) 12288, ' ');
            Pattern p = Pattern.compile(REGEX_ALL);
            Matcher m = p.matcher(tempStr);
            String s = m.replaceAll("");
            tempStr = null;
            return s;
        }
        return null;
    }

    /**
     * 删除String的开头和结尾空格(包括全角和半角),
     * 回车符(\r),制表符(\t \x0B),换行符(\n),换页符(\f)
     * @param str
     * @return String
     */
    public static String removeTrimSpace(String str){
        if(iStr(str)){
            return str.replaceAll(REGEX_TRIM, "");
        }
        return null;
    }

}