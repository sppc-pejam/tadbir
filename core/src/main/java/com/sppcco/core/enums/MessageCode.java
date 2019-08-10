package com.sppcco.core.enums;

public enum MessageCode {

  EA_EMPTY_CUSTOMER(0),
  E_EMPTY_ARTICLE(1),
  WA_FIRST_SYNC(2),
  E_INPUT_NAME(3),
  E_INPUT_POSTAL_CODE(4),
  E_INPUT_NATIONAL_CODE(5),
  E_USER_NOT_ALLOW(6),
  E_USER_NOT_ALLOW_APPEND(7),
  E_USER_NOT_ALLOW_MODIFY(8),
  E_USER_NOT_ALLOW_DELETE(9),
  E_USER_NOT_ALLOW_NOT_BUYER(10),
  E_REPEATED_CUSTOMER_NAME(11),
  E_REPEATED_CUSTOMER_SUBSCRIPTION_NO(12),
  S_SENT(13),
  E_NOT_SENT(14),
  W_NO_ITEM_NEED_SYNC(15),
  E_USER_NOT_ACCESS(16);

  private final int mValue;

  MessageCode(int value) {
    mValue = value;
  }

  public int getValue() {
    return mValue;
  }
}
