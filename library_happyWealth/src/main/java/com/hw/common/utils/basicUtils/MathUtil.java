package com.hw.common.utils.basicUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MathUtil {

    // 保留两位小数
    public static String DoubleFormat(Double val){
        try {
            return String .format("%.2f", val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * 经纬度测距
     *
     * @param jingdu1
     * @param weidu1
     * @param jingdu2
     * @param weidu2
     * @return
     */
    public static double getDistance(double jingdu1, double weidu1, double jingdu2, double weidu2) {
        if (jingdu1 == 4.9E-324 || weidu1 == 4.9E-324 || jingdu2 == 4.9E-324 || weidu2 == 4.9E-324) {
            return 0;
        }

        double a, b, R;
        R = 6378137; // 地球半径
        weidu1 = weidu1 * Math.PI / 180.0;
        weidu2 = weidu2 * Math.PI / 180.0;
        a = weidu1 - weidu2;
        b = (jingdu1 - jingdu2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(weidu1) * Math.cos(weidu2) * sb2 * sb2));
        return d;
    }

    // 两点坐标距离
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2) + Math.abs(y1 - y2) * Math.abs(y1 - y2));
    }

    public static double pointTotoDegrees(double x, double y) {
        return Math.toDegrees(Math.atan2(x, y));
    }

    public static boolean checkInRound(float sx, float sy, float r, float x, float y) {
        return Math.sqrt((sx - x) * (sx - x) + (sy - y) * (sy - y)) < r;
    }

    // 字符串转float
    public static float stringToFloat(String str) {
        float num = 0;
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        try {
            Number parsedNumber = nf.parse(str);
            num = parsedNumber.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static int stringToInt(String str) {
        int num = 0;
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        try {
            Number parsedNumber = nf.parse(str);
            num = parsedNumber.intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }
}