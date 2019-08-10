package com.sppcco.core.data.model;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.enums.AccountTree;

import java.io.Serializable;

import androidx.annotation.IdRes;

/**
 * Created by b_nematzadeh on 10/31/2018.
 */
public class BranchStatus implements Serializable {

  private AccountTree mPreviousBranchName;
  private AccountTree mCurrentBranchName;
  private AccountTree mNextBranchName;

  @IdRes
  private int mPreviousBranchIdRes = 0;

  @IdRes
  private int mNextBranchIdRes = 0;

  private StateProgressBar.StateNumber mPreviousBranchPosition = null;
  private StateProgressBar.StateNumber mCurrentBranchPosition = null;
  private StateProgressBar.StateNumber mNextBranchPosition = null;


  public BranchStatus() {
    super();
  }

  public BranchStatus(AccountTree previousBranch,
               AccountTree currentBranchName,
               AccountTree nextBranch,
               int previousIdRes,
               int nextIdRes,
               StateProgressBar.StateNumber previousPosition,
               StateProgressBar.StateNumber currentPosition,
               StateProgressBar.StateNumber nextPosition) {

    mPreviousBranchName = previousBranch;
    mCurrentBranchName = currentBranchName;
    mNextBranchName = nextBranch;
    mPreviousBranchIdRes = previousIdRes;
    mNextBranchIdRes = nextIdRes;
    mPreviousBranchPosition = previousPosition;
    mCurrentBranchPosition = currentPosition;
    mNextBranchPosition = nextPosition;
  }

  public AccountTree getPreviousBranchName() {
    return mPreviousBranchName;
  }

  public void setPreviousBranchName(AccountTree previousBranch) {
    this.mPreviousBranchName = previousBranch;
  }

  public AccountTree getCurrentBranchName() {
    return mCurrentBranchName;
  }

  public void setCurrentBranchName(AccountTree currentBranchName) {
    this.mCurrentBranchName = currentBranchName;
  }

  public AccountTree getNextBranchName() {
    return mNextBranchName;
  }

  public void setNextBranchName(AccountTree nextBranch) {
    this.mNextBranchName = nextBranch;
  }

  public StateProgressBar.StateNumber getCurrentBranchPosition() {
    return mCurrentBranchPosition;
  }

  public void setCurrentBranchPosition(StateProgressBar.StateNumber currentPosition) {
    this.mCurrentBranchPosition = currentPosition;
  }

  public StateProgressBar.StateNumber getNextBranchPosition() {
    return mNextBranchPosition;
  }

  public void setNextBranchPosition(StateProgressBar.StateNumber nextPosition) {
    this.mNextBranchPosition = nextPosition;
  }

  public StateProgressBar.StateNumber getPreviousBranchPosition() {
    return mPreviousBranchPosition;
  }

  public void setPreviousBranchPosition(StateProgressBar.StateNumber previousPosition) {
    this.mPreviousBranchPosition = previousPosition;
  }

  public int getPreviousBranchIdRes() {
    return mPreviousBranchIdRes;
  }

  public void setPreviousBranchIdRes(int previousIdRes) {
    this.mPreviousBranchIdRes = previousIdRes;
  }

  public int getNextBranchIdRes() {
    return mNextBranchIdRes;
  }

  public void setNextBranchIdRes(int nextIdRes) {
    this.mNextBranchIdRes = nextIdRes;
  }
}
