package com.sppcco.core.enums;

public enum PreFactorRightPos {

  VIEW(0),
  APPEND_ARTICLE(1),
  MODIFY_ARTICLE(2),
  DELETE_ARTICLE(3),
  APPEND(4),
  MODIFY(5),
  DELETE(6),
  COMMIT(7),
  MOVE_ON_PRE_FACTOR(8),
  PRINT(9),
  CHANGE_SALES_PRICE(10);

  private int Value;

  PreFactorRightPos(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
