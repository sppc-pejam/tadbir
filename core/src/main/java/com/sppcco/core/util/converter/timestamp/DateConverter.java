package com.sppcco.core.util.converter.timestamp;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Created by m_pejam on 27/10/18.
 */

public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
