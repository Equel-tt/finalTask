package by.allahverdiev.finaltask.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Utilities for conversion between the old and new JDK date types
 * (between {@code java.util.Date} and {@code java.time.*}).
 * All methods are null-safe.
 */
public class DateConversion {
    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or {@code java.sql.Date}. Null-safe.
     */
    public LocalDate toLocalDate(java.util.Date date) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
