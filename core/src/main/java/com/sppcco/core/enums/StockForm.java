package com.sppcco.core.enums;


public enum StockForm {

  INF_NONE(-1),
  INF_STOCK(0),
  INF_UNIT(1),
  INF_MERCHANDISE(2);

  private int Value;

  StockForm(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
