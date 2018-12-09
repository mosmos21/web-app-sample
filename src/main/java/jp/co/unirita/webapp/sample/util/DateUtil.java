package jp.co.unirita.webapp.sample.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public class DateUtil {

    public static final Date MIN_DATE = createDate(1970, 1, 1);
    public static final Date MAX_DATE = createDate(9999,12, 31);

    private static Calendar cal = Calendar.getInstance();

    public static Date createDate(int year, int month) {
        return createDate(year, month, 1);
    }

    public static Date createDate(int year, int month, int day) {
        return new Date(year - 1900, month - 1, day);
    }

    public static Optional<Date> parseDate(java.util.Date date) {
        if(date == null) {
            return Optional.ofNullable(null);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return Optional.ofNullable(new Date(cal.getTimeInMillis()));
    }

    public static int parseYear(Optional<Integer> year) {
        return year.orElse(cal.get(Calendar.YEAR));
    }

    public static int parseMonth(Optional<Integer> month) {
        return month.orElse(cal.get(Calendar.MONTH) + 1);
    }

    public static Date add(Date date, int field, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, value);
        return new Date(cal.getTimeInMillis());
    }

    public static String toString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}
