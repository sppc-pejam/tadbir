package com.sppcco.core.enums;

import java.io.Serializable;

/**
 * Created by b_nematzadeh on 9/19/2018.
 */
public enum AccountTree implements Serializable {

  START(-1, new String[]{""}),
  ACCOUNT(1, new String[]{"حساب", "تفصیلی شناور", "مرکز هزینه", "پروژه"}),
  DETAIL_ACC(2, new String[]{"تفصیلی شناور", "حساب", "مرکز هزینه", "پروژه"}),
  COST_CENTER(3, new String[]{"مرکز هزینه", "حساب", "تفصیلی شناور", "پروژه"}),
  PROJECT(4, new String[]{"پروژه", "حساب", "تفصیلی شناور", "مرکز هزینه"}),
  END(0, new String[]{""});

  private final int value;
  private final String[] stepName;

  AccountTree(int val, String[] strings) {
    this.value = val;
    stepName = strings;
  }

  public int getValue() {
    return this.value;
  }

  public String[] getNameStep() {
    return stepName;
  }

}
