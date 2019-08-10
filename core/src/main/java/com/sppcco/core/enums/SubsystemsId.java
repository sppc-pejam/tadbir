package com.sppcco.core.enums;


public enum SubsystemsId {

  FISCAL_SYS(1),
  CHECK_SYS(2),
  CASH_SYS(3),
  INVENTORY_SYS(4),
  SALESPURCHASE_SYS(5);

  private int Value;

  SubsystemsId(int nVal) {
    Value = nVal;
  }

  public int getValue() {
    return Value;
  }
}
