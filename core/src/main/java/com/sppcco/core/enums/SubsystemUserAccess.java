package com.sppcco.core.enums;


public enum SubsystemUserAccess {

  WINDOWS_APP(1),
  WEB_APP(2),
  MOBILE_APP(3);

  private int subsystem;

  SubsystemUserAccess(int nVal) {
    subsystem = nVal;
  }

  public int getValue() {
    return subsystem;
  }
}
