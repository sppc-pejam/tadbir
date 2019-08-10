package com.sppcco.core.util.converter.timestamp;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Created by m_pejam on 27/10/18.
 */

public class TimestampConverter {
  @SuppressLint("SimpleDateFormat")
  private static DateFormat df = new SimpleDateFormat(Constants.TIME_STAMP_FORMAT);

  @TypeConverter
  public static Date fromTimestamp(String value) {
    if (value != null) {
      try {
        return df.parse(value);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return null;
    } else {
      return null;
    }
  }

  @TypeConverter
  public static String dateToTimestamp(Date value) {

    return value == null ? null : df.format(value);
  }
}


