package com.wedul.wedul_timeline.core.util;

import com.wedul.wedul_timeline.core.config.error.BadRequestException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-07-14
 **/
public class DateUtil {

    private DateUtil() {}
    private static final DateFormat unixTimeSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat dateTimeSimpleFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 유닉스 타임스탬프 얻깅
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static long unixTimeStamp(String date) throws ParseException {
        if (!checkInputDate(date)) throw new BadRequestException();
        return unixTimeSimpleFormat.parse(date + " 00:00:00").getTime();
    }

    /**
     * 입력된 날짜의 시작 데이터
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static long startUnixTimeStamp(String date) throws ParseException {
        if (!checkInputDate(date)) throw new BadRequestException();
        return unixTimeSimpleFormat.parse(date + " 00:00:00").getTime();
    }

    /**
     * 입력된 날짜의 시작 데이터
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static long endUnixTimeStamp(String date) throws ParseException {
        if (!checkInputDate(date)) throw new BadRequestException();
        return unixTimeSimpleFormat.parse(date + " 23:59:59").getTime();
    }

    /**
     * date 가져오기 ('yyyy-mm-dd')
     *
     * @return
     * @throws ParseException
     */
    public static String todayDate() throws ParseException {
        return dateTimeSimpleFormat.format(new Date());
    }

    private static boolean checkInputDate(String input) {
        return input.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}");
    }

}
