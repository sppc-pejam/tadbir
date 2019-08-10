package com.sppcco.core.enums;

public enum CustomerRightPos {


  VIEW(0),
  APPEND(1),
  MODIFY(2),
  DELETE(3),
  SEARCH(4),
  FILTER(5),
  PRINT(6),
  NOT_BUYER(7),
  NOT_SELLER(8);

  private int Value;

  CustomerRightPos(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
