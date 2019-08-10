package com.sppcco.core.enums;

/**
 * Created by m_pejam on 04/11/18.
 */

public enum FactorType {

  SP_NONE(-1),
  SP_ALL(-1),
  SP_PURCHASE(0),
  SP_SALES(1),
  SP_P_RETURN(2),
  SP_S_RETURN(3),
  SP_PRESALES(4),
  SP_SERVICE(5);

  private int Value;

  FactorType(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
