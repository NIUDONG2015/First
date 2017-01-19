package musictheory.xinweitech.cn.musictheory.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jnn on 2016/test2/25.
 */
public class StringUtil {

    //提现类型转换为数字
    public static String getWithdrawType(String type) {
        String typeInt = "test2";
        if (TextUtils.isEmpty(type)) {
            return typeInt;
        }
        switch (type) {
            case "银行卡":
                typeInt = "1";
                break;
            case "微信":
                typeInt = "2";
                break;
            case "支付宝":
                typeInt = "test2";
                break;
        }
        return typeInt;
    }

    /**
     * int数组转换为集合
     *
     * @param arr 要转换的数组
     * @return 转换后的集合
     */
    public static List<Integer> integerArrCovertList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * String数组转换为集合
     *
     * @param arr 要转换的数组
     * @return 转换后的集合
     */
    public static List<String> stringArrCovertList(String[] arr) {
        if (arr.length == 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * 字符串以符号分割为数组
     *
     * @param str       要分割的字符串
     * @param separator 分隔符
     * @return 字符数组
     */
    public static String[] stringSliptArr(String str, String separator) {
        String[] arr = new String[]{};
        if (TextUtils.isEmpty(str))
            return arr;
        arr = str.split(separator);
        return arr;
    }

    /**
     * 设置文本部分颜色
     *
     * @param content
     * @param colorString
     * @return
     */

    public static SpannableStringBuilder getSpannableStyleString(String content, String colorString) {
        int start = content.indexOf(colorString);
        int end = start + colorString.length();
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new ForegroundColorSpan(Color.rgb(254, 106, 0)), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

    public static SpannableStringBuilder getSpannableStyleString(String content, String colorString, int r, int g, int b) {
        int start = content.indexOf(colorString);
        int end = start + colorString.length();
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new ForegroundColorSpan(Color.rgb(r, g, b)), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

    public static String cutStartAndEndChar(String str, String separator) {
        if (TextUtils.isEmpty(str))
            return "";
        if (TextUtils.isEmpty(separator))
            return str;
        return str.endsWith(separator) ? str.substring(0, str.length() - 1) : str;
    }

    /**
     * 将大于1000m的数据转换成km为单位
     *
     * @param str
     * @return
     */
    public static String getDistance(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0m";
        }
        double d = Double.parseDouble(str);
        if ((d / 1000) < 1)
            return String.valueOf(d) + "m";
        return String.format("%.2f", d / 1000.0) + "km";
    }

    /**
     * 在单词之间添加符号
     */
    public static String stringAddChar(List<String> list) {
        String s = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        s = sb.substring(0, sb.length() - 1);
        return s;
    }

    /**
     * 读取assets下的文件
     *
     * @param filename
     * @return 字符串
     */
    public static String readAssetFile(Context mContext, String filename) {
        try {
            InputStreamReader is = new InputStreamReader(mContext.getResources().getAssets().open(filename));
            BufferedReader bufReader = new BufferedReader(is);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将long型时间转化为 日期格式
     *
     * @param time
     * @return
     */
    public static String longToDate(long time) {
        String date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }

    public static String longToDateTime(long time) {
        String date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }

    public static long dateToLong(String in) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(in);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.getTimeInMillis();
        }

    }

    //判断密码不能为中文
    public static void CheckChinesePassword(Context context, String password) {
        for (int i = 0; i < password.length(); i++) {
            Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]*$");
            Matcher m = p.matcher(String.valueOf(password.charAt(i)));
            if (m.matches()) {
                Toast.makeText(context, "密码不能为中文", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }


    //判断是否全是数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    //两字符串是否相同
    public static boolean isSame(String first, String second) {
        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(second))
            return false;
        return first.equals(second);

    }

    //判断名字是否含有特殊字符
    public static boolean checkName(String name) {
        String regEx = "[`~!@#$%^&* ()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        return !m.find();
    }


    //验证手机号
    public static boolean isMobileNum(String mobiles) {
        String txtregex = "[1][3578]\\d{9}";
        return mobiles.matches(txtregex);
    }

    //验证邮箱
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }
}
