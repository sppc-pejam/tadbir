package com.sppcco.core.enums;

public enum FormId {

  SP_CUSTOMER(2),
  SP_PURCHASE(3),
  SP_SALES(4),
  SP_P_RETURN(5),
  SP_S_RETURN(6),
  SP_PRESALES(11),
  SPF_SALESORDER(90);

  private int Value;

  FormId(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
