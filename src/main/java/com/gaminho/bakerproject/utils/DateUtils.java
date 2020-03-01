package com.gaminho.bakerproject.utils;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate fromString(final String strDate) {
        try {
            return StringUtils.isNotBlank(strDate) ? LocalDate.from(formatter.parse(strDate)) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
