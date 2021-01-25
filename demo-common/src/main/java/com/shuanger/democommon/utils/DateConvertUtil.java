package com.shuanger.democommon.utils;

import java.time.*;
import java.util.Date;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-25 10:16
 * @description:
 */
public class DateConvertUtil {

    // Date to LocalDate
    public static LocalDate toLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate toLocalDate1(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate toLocalDate2(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    // Date to LocalDateTime
    public static LocalDateTime toLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp( dateToConvert.getTime()).toLocalDateTime();
    }

    // Date to LocalDateTime
    public static LocalDateTime toLocalDateTime1(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime2(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    //LocalDate to Date
    public static Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static Date toDate1(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    // LocalDateTime to Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    public static Date localDateTimeToDate1(LocalDateTime localDateTime) {
        return java.util.Date
                .from(localDateTime.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return localDate.atTime(LocalTime.MIN);
    }


    public static void main(String[] args) {
        Date date = new Date();
        LocalDate localDate = LocalDate.now();

        LocalDateTime dateTime = LocalDateTime.now();
    }
}
