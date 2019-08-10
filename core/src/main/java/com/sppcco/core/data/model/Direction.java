package com.sppcco.core.data.model;

/**
 * Created by b_nematzadeh on 11/9/2018.
 */
public enum Direction {

  PREVIOUS(0),
  NEXT(1);

  private final int direction;

  Direction(int direction) {
    this.direction = direction;
  }

  public int getValue() {
    return direction;
  }

}
