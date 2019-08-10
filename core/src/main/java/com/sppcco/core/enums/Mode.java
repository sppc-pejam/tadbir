package com.sppcco.core.enums;

/**
 * Created by m_pejam on 01/31/18.
 *
 */

public enum Mode {

  NEW(0),
  EDIT(1),
  DELETE(2),
  REVIEW(-1);

  private final int value;

  Mode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
