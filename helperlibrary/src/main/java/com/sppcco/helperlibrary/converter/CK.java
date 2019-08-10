package com.sppcco.helperlibrary.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * Created by m_pejam on 05/21/18.
 * Datatype Converter Class
 */

public class CK {

  public static boolean isDoubleNum(String str)
  {
    try
    {
      double d = Double.parseDouble(str);
    }
    catch(NumberFormatException nfe)
    {
      return false;
    }
    return true;
  }

  public static boolean isIntegerNum(String str)
  {
    try
    {
      int i = Integer.parseInt(str);
    }
    catch(NumberFormatException nfe)
    {
      return false;
    }
    return true;
  }
}
