package com.sppcco.core.data.model;

import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.Mode;

import java.io.Serializable;

public class BranchInfo implements Serializable {

  private Customer mCustomer;
  private ACCVector mACCVector;
  private AccountTree mRoot;
  private BranchStatus mBranchStatus;
  private Mode mMode;
  private Direction mDirection;

  public BranchInfo() {
    super();
  }

  public BranchInfo(Customer customer, ACCVector bundle, AccountTree accountRoot, BranchStatus branchStatus, Mode mode, Direction direction) {

    mCustomer = customer;
    mACCVector = bundle;
    mRoot = accountRoot;
    mBranchStatus = branchStatus;
    mMode = mode;
    mDirection = direction;
  }

  public Customer getCustomer() {
    return mCustomer;
  }

  public void setCustomer(Customer customer) {
    this.mCustomer = customer;
  }

  public ACCVector getAccVector() {
    return mACCVector;
  }

  public void setAccVector(ACCVector accVector) {
    mACCVector = accVector;
  }

  public AccountTree getRoot() {
    return mRoot;
  }

  public void setRoot(AccountTree accountRoot) {
    mRoot = accountRoot;
  }

  public BranchStatus getBranchStatus() {
    return mBranchStatus;
  }

  public void setBranchStatus(BranchStatus branchStatus) {
    this.mBranchStatus = branchStatus;
  }

  public Mode getMode() {
    return mMode;
  }

  public void setMode(Mode mode) {
    this.mMode = mode;
  }

  public Direction getDirection() {
    return mDirection;
  }

  public void setDirection(Direction direction) {
    this.mDirection = direction;
  }
}
