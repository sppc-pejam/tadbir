package com.sppcco.customer.ui.acc_vector;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.model.BranchStatus;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.customer.R;

import androidx.annotation.IdRes;

/**
 * Created by b_nematzadeh on 9/19/2018.
 */
public class ScrollingAccountTree {

  // get PREVIOUS_INDEX, NEXT_INDEX & mCurrentPosition from origin START & current currentBranch
  public static BranchStatus scrolling(AccountTree ROOT, AccountTree currentBranch) {

    AccountTree mPreviousBranchName = null;
    AccountTree mNextBranchName = null;

    @IdRes int mPreviousBranchIdRes = 0;
    @IdRes int mNextBranchIdRes = 0;

    StateProgressBar.StateNumber mPreviousPosition = null;
    StateProgressBar.StateNumber mCurrentPosition = null;
    StateProgressBar.StateNumber mNextPosition = null;

    switch (ROOT) {

      // ACCOUNT -> DETAIL_ACC -> COST_CENTER -> PROJECT
      case ACCOUNT:
        switch (currentBranch) {
          case ACCOUNT:
            mPreviousBranchName = AccountTree.START;
            mNextBranchName = AccountTree.DETAIL_ACC;
            //mPreviousBranchIdRes = 0;
            mNextBranchIdRes = R.id.action_accountFragment_to_detailAccFragment;
            //mPreviousPosition = null;
            mCurrentPosition = StateProgressBar.StateNumber.ONE;
            mNextPosition = StateProgressBar.StateNumber.TWO;
            break;

          case DETAIL_ACC:
            mPreviousBranchName = AccountTree.ACCOUNT;
            mNextBranchName = AccountTree.COST_CENTER;
            mPreviousBranchIdRes = R.id.action_detailAccFragment_to_accountFragment;
            mNextBranchIdRes = R.id.action_detailAccFragment_to_costCenterFragment;
            mPreviousPosition = StateProgressBar.StateNumber.ONE;
            mCurrentPosition = StateProgressBar.StateNumber.TWO;
            mNextPosition = StateProgressBar.StateNumber.THREE;
            break;

          case COST_CENTER:
            mPreviousBranchName = AccountTree.DETAIL_ACC;
            mNextBranchName = AccountTree.PROJECT;
            mPreviousBranchIdRes = R.id.action_costCenterFragment_to_detailAccFragment;
            mNextBranchIdRes = R.id.action_costCenterFragment_to_projectFragment;
            mPreviousPosition = StateProgressBar.StateNumber.TWO;
            mCurrentPosition = StateProgressBar.StateNumber.THREE;
            mNextPosition = StateProgressBar.StateNumber.FOUR;
            break;

          case PROJECT:
            mPreviousBranchName = AccountTree.COST_CENTER;
            mNextBranchName = AccountTree.END;
            mPreviousBranchIdRes = R.id.action_projectFragment_to_costCenterFragment;
            //mNextBranchIdRes = 0;
            mPreviousPosition = StateProgressBar.StateNumber.THREE;
            mCurrentPosition = StateProgressBar.StateNumber.FOUR;
            //mNextPosition = null;
            break;
        }
        break;

      // DETAIL_ACC -> ACCOUNT -> COST_CENTER -> PROJECT
      case DETAIL_ACC:
        switch (currentBranch) {
          case ACCOUNT:
            mPreviousBranchName = AccountTree.DETAIL_ACC;
            mNextBranchName = AccountTree.COST_CENTER;
            mPreviousBranchIdRes = R.id.action_accountFragment_to_detailAccFragment;
            mNextBranchIdRes = R.id.action_accountFragment_to_costCenterFragment;
            mPreviousPosition = StateProgressBar.StateNumber.ONE;
            mCurrentPosition = StateProgressBar.StateNumber.TWO;
            mNextPosition = StateProgressBar.StateNumber.THREE;
            break;

          case DETAIL_ACC:
            mPreviousBranchName = AccountTree.START;
            mNextBranchName = AccountTree.ACCOUNT;
            //mPreviousBranchIdRes = 0;
            mNextBranchIdRes = R.id.action_detailAccFragment_to_accountFragment;
            //mPreviousPosition = null;
            mCurrentPosition = StateProgressBar.StateNumber.ONE;
            mNextPosition = StateProgressBar.StateNumber.TWO;
            break;

          case COST_CENTER:
            mPreviousBranchName = AccountTree.ACCOUNT;
            mNextBranchName = AccountTree.PROJECT;
            mPreviousBranchIdRes = R.id.action_costCenterFragment_to_accountFragment;
            mNextBranchIdRes = R.id.action_costCenterFragment_to_projectFragment;
            mPreviousPosition = StateProgressBar.StateNumber.TWO;
            mCurrentPosition = StateProgressBar.StateNumber.THREE;
            mNextPosition = StateProgressBar.StateNumber.FOUR;
            break;

          case PROJECT:
            mPreviousBranchName = AccountTree.COST_CENTER;
            mNextBranchName = AccountTree.END;
            mPreviousBranchIdRes = R.id.action_projectFragment_to_costCenterFragment;
            //mNextBranchIdRes = 0;
            mPreviousPosition = StateProgressBar.StateNumber.THREE;
            mCurrentPosition = StateProgressBar.StateNumber.FOUR;
            //mNextPosition = null;
            break;
        }
        break;

      // COST_CENTER -> ACCOUNT -> DETAIL_ACC -> PROJECT
      case COST_CENTER:
        switch (currentBranch) {
          case ACCOUNT:
            mPreviousBranchName = AccountTree.COST_CENTER;
            mNextBranchName = AccountTree.DETAIL_ACC;
            mPreviousBranchIdRes = R.id.action_accountFragment_to_costCenterFragment;
            mNextBranchIdRes = R.id.action_accountFragment_to_detailAccFragment;
            mPreviousPosition = StateProgressBar.StateNumber.ONE;
            mCurrentPosition = StateProgressBar.StateNumber.TWO;
            mNextPosition = StateProgressBar.StateNumber.THREE;
            break;

          case DETAIL_ACC:
            mPreviousBranchName = AccountTree.ACCOUNT;
            mNextBranchName = AccountTree.PROJECT;
            mPreviousBranchIdRes = R.id.action_detailAccFragment_to_accountFragment;
            mNextBranchIdRes = R.id.action_detailAccFragment_to_projectFragment;
            mPreviousPosition = StateProgressBar.StateNumber.TWO;
            mCurrentPosition = StateProgressBar.StateNumber.THREE;
            mNextPosition = StateProgressBar.StateNumber.FOUR;
            break;

          case COST_CENTER:
            mPreviousBranchName = AccountTree.START;
            mNextBranchName = AccountTree.ACCOUNT;
            //mPreviousBranchIdRes = 0;
            mNextBranchIdRes = R.id.action_costCenterFragment_to_accountFragment;
            //mPreviousPosition = null;
            mCurrentPosition = StateProgressBar.StateNumber.ONE;
            mNextPosition = StateProgressBar.StateNumber.TWO;
            break;

          case PROJECT:
            mPreviousBranchName = AccountTree.DETAIL_ACC;
            mNextBranchName = AccountTree.END;
            mPreviousBranchIdRes = R.id.action_projectFragment_to_detailAccFragment;
            //mNextBranchIdRes = 0;
            mPreviousPosition = StateProgressBar.StateNumber.THREE;
            mCurrentPosition = StateProgressBar.StateNumber.FOUR;
            //mNextPosition = null;
            break;
        }
        break;

      // PROJECT -> ACCOUNT -> DETAIL_ACC -> COST_CENTER
      case PROJECT:
        switch (currentBranch) {
          case ACCOUNT:
            mPreviousBranchName = AccountTree.PROJECT;
            mNextBranchName = AccountTree.DETAIL_ACC;
            mPreviousBranchIdRes = R.id.action_accountFragment_to_projectFragment;
            mNextBranchIdRes = R.id.action_accountFragment_to_detailAccFragment;
            mPreviousPosition = StateProgressBar.StateNumber.ONE;
            mCurrentPosition = StateProgressBar.StateNumber.TWO;
            mNextPosition = StateProgressBar.StateNumber.THREE;
            break;

          case DETAIL_ACC:
            mPreviousBranchName = AccountTree.ACCOUNT;
            mNextBranchName = AccountTree.COST_CENTER;
            mPreviousBranchIdRes = R.id.action_detailAccFragment_to_accountFragment;
            mNextBranchIdRes = R.id.action_detailAccFragment_to_costCenterFragment;
            mPreviousPosition = StateProgressBar.StateNumber.TWO;
            mCurrentPosition = StateProgressBar.StateNumber.THREE;
            mNextPosition = StateProgressBar.StateNumber.FOUR;
            break;

          case COST_CENTER:
            mPreviousBranchName = AccountTree.DETAIL_ACC;
            mNextBranchName = AccountTree.END;
            mPreviousBranchIdRes = R.id.action_costCenterFragment_to_detailAccFragment;
            //mNextBranchIdRes = 0;
            mPreviousPosition = StateProgressBar.StateNumber.THREE;
            mCurrentPosition = StateProgressBar.StateNumber.FOUR;
            //mNextPosition = null;
            break;

          case PROJECT:
            mPreviousBranchName = AccountTree.START;
            mNextBranchName = AccountTree.ACCOUNT;
            //mPreviousBranchIdRes = 0;
            mNextBranchIdRes = R.id.action_projectFragment_to_accountFragment;
            //mPreviousPosition = null;
            mCurrentPosition = StateProgressBar.StateNumber.ONE;
            mNextPosition = StateProgressBar.StateNumber.TWO;
            break;
        }
        break;
    }
    return new BranchStatus(mPreviousBranchName, currentBranch, mNextBranchName, mPreviousBranchIdRes, mNextBranchIdRes, mPreviousPosition, mCurrentPosition, mNextPosition);
  }


  public static BranchStatus onChangeBranch(AccountTree ROOT,
                                            StateProgressBar.StateNumber sourceNumber,
                                            StateProgressBar.StateNumber destinationNumber) {

    AccountTree mPreviousBranch = null;
    @IdRes int mPreviousIdRes = 0;

    switch (ROOT) {

      // ACCOUNT -> DETAIL_ACC -> COST_CENTER -> PROJECT
      case ACCOUNT:
        switch (sourceNumber) {
          case TWO:
            mPreviousBranch = AccountTree.ACCOUNT;
            mPreviousIdRes = R.id.action_detailAccFragment_to_accountFragment;
            break;

          case THREE:
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_costCenterFragment_to_accountFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.DETAIL_ACC;
                mPreviousIdRes = R.id.action_costCenterFragment_to_detailAccFragment;
                break;
            }
            break;

          case FOUR:
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_projectFragment_to_accountFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.DETAIL_ACC;
                mPreviousIdRes = R.id.action_projectFragment_to_detailAccFragment;
                break;
              case THREE:
                mPreviousBranch = AccountTree.COST_CENTER;
                mPreviousIdRes = R.id.action_projectFragment_to_costCenterFragment;
                break;
            }
            break;
        }
        break;

      // DETAIL_ACC -> ACCOUNT -> COST_CENTER -> PROJECT
      case DETAIL_ACC:
        switch (sourceNumber) {
          case TWO:
            mPreviousBranch = AccountTree.DETAIL_ACC;
            mPreviousIdRes = R.id.action_accountFragment_to_detailAccFragment;
            break;

          case THREE:
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.DETAIL_ACC;
                mPreviousIdRes = R.id.action_costCenterFragment_to_detailAccFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_costCenterFragment_to_accountFragment;
                break;
            }
            break;

          case FOUR:
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.DETAIL_ACC;
                mPreviousIdRes = R.id.action_projectFragment_to_detailAccFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_projectFragment_to_accountFragment;
                break;
              case THREE:
                mPreviousBranch = AccountTree.COST_CENTER;
                mPreviousIdRes = R.id.action_projectFragment_to_costCenterFragment;
                break;
            }
            break;
        }
        break;

      // COST_CENTER -> ACCOUNT -> DETAIL_ACC -> PROJECT
      case COST_CENTER:
        switch (sourceNumber) {
          case TWO:
            mPreviousBranch = AccountTree.COST_CENTER;
            mPreviousIdRes = R.id.action_accountFragment_to_costCenterFragment;
            break;

          case THREE:
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.COST_CENTER;
                mPreviousIdRes = R.id.action_detailAccFragment_to_costCenterFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_detailAccFragment_to_accountFragment;
                break;
            }
            break;

          case FOUR:
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.COST_CENTER;
                mPreviousIdRes = R.id.action_projectFragment_to_costCenterFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_projectFragment_to_accountFragment;
                break;
              case THREE:
                mPreviousBranch = AccountTree.DETAIL_ACC;
                mPreviousIdRes = R.id.action_projectFragment_to_detailAccFragment;
                break;
            }
            break;
        }
        break;

      // PROJECT -> ACCOUNT -> DETAIL_ACC -> COST_CENTER
      case PROJECT:
        switch (sourceNumber) {
          case TWO: // ACCOUNT
            mPreviousBranch = AccountTree.PROJECT;
            mPreviousIdRes = R.id.action_accountFragment_to_projectFragment;
            break;

          case THREE: // DETAIL_ACC
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.PROJECT;
                mPreviousIdRes = R.id.action_detailAccFragment_to_projectFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_detailAccFragment_to_accountFragment;
                break;
            }
            break;

          case FOUR: // COST_CENTER
            switch (destinationNumber) {
              case ONE:
                mPreviousBranch = AccountTree.PROJECT;
                mPreviousIdRes = R.id.action_costCenterFragment_to_projectFragment;
                break;
              case TWO:
                mPreviousBranch = AccountTree.ACCOUNT;
                mPreviousIdRes = R.id.action_costCenterFragment_to_accountFragment;
                break;
              case THREE:
                mPreviousBranch = AccountTree.DETAIL_ACC;
                mPreviousIdRes = R.id.action_costCenterFragment_to_detailAccFragment;
                break;
            }
            break;
        }
        break;
    }
    return new BranchStatus(mPreviousBranch, null, null, mPreviousIdRes, 0, null, null, null);
  }

  public static AccountTree getBranchFromRootByPosition(AccountTree ROOT, StateProgressBar.StateNumber stateNumber) {

    AccountTree mBranch = null;

    switch (ROOT) {

      case ACCOUNT:
        switch (stateNumber) {
          case ONE:
            mBranch = AccountTree.ACCOUNT;
            break;

          case TWO:
            mBranch = AccountTree.DETAIL_ACC;
            break;

          case THREE:
            mBranch = AccountTree.COST_CENTER;
            break;

          case FOUR:
            mBranch = AccountTree.PROJECT;
            break;
        }
        break;

      case DETAIL_ACC:
        switch (stateNumber) {
          case ONE:
            mBranch = AccountTree.DETAIL_ACC;
            break;

          case TWO:
            mBranch = AccountTree.ACCOUNT;
            break;

          case THREE:
            mBranch = AccountTree.COST_CENTER;
            break;

          case FOUR:
            mBranch = AccountTree.PROJECT;
            break;
        }
        break;

      case COST_CENTER:
        switch (stateNumber) {
          case ONE:
            mBranch = AccountTree.COST_CENTER;
            break;

          case TWO:
            mBranch = AccountTree.ACCOUNT;
            break;

          case THREE:
            mBranch = AccountTree.DETAIL_ACC;
            break;

          case FOUR:
            mBranch = AccountTree.PROJECT;
            break;
        }
        break;

      case PROJECT:
        switch (stateNumber) {
          case ONE:
            mBranch = AccountTree.PROJECT;
            break;

          case TWO:
            mBranch = AccountTree.ACCOUNT;
            break;

          case THREE:
            mBranch = AccountTree.DETAIL_ACC;
            break;

          case FOUR:
            mBranch = AccountTree.COST_CENTER;
            break;
        }
        break;
    }
    return mBranch;
  }

  public static StateProgressBar.StateNumber[] stepDetection(StateProgressBar.StateNumber currentStep) {

    // 0 => previous
    // 1 => next
    StateProgressBar.StateNumber[] steps = new StateProgressBar.StateNumber[2];

    switch (currentStep) {
      case ONE:
        steps[0] = null;
        steps[1] = StateProgressBar.StateNumber.TWO;
        break;
      case TWO:
        steps[0] = StateProgressBar.StateNumber.ONE;
        steps[1] = StateProgressBar.StateNumber.THREE;
        break;
      case THREE:
        steps[0] = StateProgressBar.StateNumber.TWO;
        steps[1] = StateProgressBar.StateNumber.FOUR;
        break;
      case FOUR:
        steps[0] = StateProgressBar.StateNumber.THREE;
        steps[1] = StateProgressBar.StateNumber.FIVE;
        break;
    }

    return steps;
  }
}
