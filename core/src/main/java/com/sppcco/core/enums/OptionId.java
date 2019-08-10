package com.sppcco.core.enums;


public enum OptionId {

  OPT_CHECK_REPEATED_MERCH(122),
  OPT_MODIFY_PRICE_DISCOUNT(341),
  OPT_APP_APPROVE_REQ_PERIODIC_TIME(947),
  OPT_APP_APPROVE_REQ_ALERT_TIMEOUT(948);

  private int Value;

  OptionId(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
