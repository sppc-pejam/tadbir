package com.sppcco.core.enums;

/**
 * Created by m_pejam on 04/11/18.
 *
 */

public enum DocType {

  SALESORDER(0),
  SPFACTOR(1);

  private int Value;

  DocType(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
