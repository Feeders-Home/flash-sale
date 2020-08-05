package com.feeder.flashsale.utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * date utilities
 */
public interface DateUtils {

    String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * return the unix time of the <code>LocalDateTime</code>
     * @param localDateTime
     * @return unix time
     */
    static int unixTime(LocalDateTime localDateTime) {
        return (int)(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000);
    }

    /**
     * format the date with default format "yyyy-MM-dd"
     * @param date
     * @return
     */
    static String formatLocalDate(LocalDate date) {
        return formatLocalDate(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * format date with the format
     * @param date
     * @param format
     * @return
     */
    static String formatLocalDate(LocalDate date, String format) {
        return DateTimeFormatter.ofPattern(format).format(date);
    }

    /**
     * format the datetime with default format "yyyy-MM-dd HH:mm:ss"
     * @param dateTime
     * @return
     */
    static String formatLocalDateTime(LocalDateTime dateTime) {
        return formatLocalDateTime(dateTime, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * format dateTime with the format
     * @param dateTime
     * @param format
     * @return
     */
    static String formatLocalDateTime(LocalDateTime dateTime, String format) {
        return DateTimeFormatter.ofPattern(format).format(dateTime);
    }

    static LocalDateTime parseLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }

    static LocalDate lastDayOfWeek(LocalDate startFrom, DayOfWeek dayOfWeek) {
        int dist = startFrom.getDayOfWeek().getValue() - dayOfWeek.getValue();
        if (dist == 0) {
            dist = DayOfWeek.values().length;
        } else if (dist < 0) {
            dist += DayOfWeek.values().length;
        }
        return startFrom.minusDays(dist);
    }

    /**
     * returns the local date time from the unix time with the millisecond
     * @param unixTimeMillis unix time with millisecond
     * @return
     */
    static LocalDateTime fromUnixTimeMillis(long unixTimeMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimeMillis), ZoneId.systemDefault());
    }

    /**
     * date time utils at beijing zone
     */
    interface BeijingDateTime {
        ZoneOffset ZONE_OFFSET = ZoneOffset.of("+8");

        static LocalDate nowDate() {
            return LocalDate.now(ZONE_OFFSET);
        }

        static LocalDateTime nowDateTime() {
            return LocalDateTime.now(ZONE_OFFSET);
        }

        static LocalDateTime fromUnixTime(int unixTime) {
            return LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(unixTime * 1000L),
                    BeijingDateTime.ZONE_OFFSET
            );
        }
    }
}
