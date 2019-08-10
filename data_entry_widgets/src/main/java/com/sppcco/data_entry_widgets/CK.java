package com.sppcco.data_entry_widgets;

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
