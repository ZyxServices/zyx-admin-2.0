package com.zyx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by HL on 2016/11/9.
 */
public class GetTimeUtil {

    public static Long getDateTime(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(time != null && !Objects.equals(time, "") ? time : "0").getTime();
        } catch (ParseException e) {
            try {
                SimpleDateFormat formatters = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return formatters.parse(time != null && !Objects.equals(time, "") ? time : "0").getTime();
            } catch (ParseException e1) {
                e1.printStackTrace();
                return 0L;

            }
        }
    }

}
