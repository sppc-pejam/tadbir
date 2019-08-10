package com.sppcco.customer.ui.acc_vector.account;



import com.sppcco.core.data.local.db.repository.AccountRepository;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.customer.R;
import com.sppcco.core.enums.AccountTree;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


public class AccountPresenter extends BasePresenter implements AccountContract.Presenter {

  private static AccountPresenter INSTANCE;
  private final AccountContract.View mView;


  private List<String> mSortList;

  private AccountPresenter(@NonNull AccountContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  static AccountContract.Presenter getAccountPresenterInstance(
    @NonNull AccountContract.View view) {
    if (INSTANCE == null) {
      INSTANCE = new AccountPresenter(view);
    }
    return INSTANCE;
  }

  @Override
  public void destroy() {
    INSTANCE = null;
  }

  @Override
  public void start() {
    loadSortList();
  }

  @Override
  public void prepareListData() {
    /*if (mView.getRoot() == AccountTree.ACCOUNT) {
      getCountAccountsRelated();
    } else*/
    if (mView.getRoot() == AccountTree.DETAIL_ACC) {
      getCountAccountsRelatedDetailAcc();
    } else if (mView.getRoot() == AccountTree.COST_CENTER) {
      getCountAccountsRelatedCostCenter();
    } else if (mView.getRoot() == AccountTree.PROJECT) {
      getCountAccountsRelatedProject();
    }
  }

  private void getCountAccountsRelated() {
    getCoreDB().accountRepository().getCountAccountsRelated(new AccountRepository.GetCountAccountsRelatedCallback() {
      @Override
      public void onAccountRelated(int count) {
        initData(count);
      }

      @Override
      public void onDataNotAvailable() {
        initData(-1);
      }
    });
  }

  private void getCountAccountsRelatedDetailAcc() {
    getCoreDB().accountRepository().getCountAccountsRelatedDetailAcc(
      mView.getTempACCVector().getDetailAcc().getId(),
      new AccountRepository.GetCountAccountsRelatedDetailAccCallback() {
        @Override
        public void onAccountRelated(int count) {
          initData(count);
        }

        @Override
        public void onDataNotAvailable() {
          initData(-1);
        }
      });
  }

  private void getCountAccountsRelatedCostCenter() {
    getCoreDB().accountRepository().getCountAccountsRelatedCostCenter(
      mView.getTempACCVector().getCostCenter().getCCCode(),
      new AccountRepository.GetCountAccountsRelatedCostCenterCallback() {
        @Override
        public void onAccountRelated(int count) {
          initData(count);
        }

        @Override
        public void onDataNotAvailable() {
          initData(-1);
        }
      });
  }

  private void getCountAccountsRelatedProject() {
    getCoreDB().accountRepository().getCountAccountsRelatedProject(
      mView.getTempACCVector().getProject().getPCode(),
      new AccountRepository.GetCountAccountsRelatedProjectCallback() {
        @Override
        public void onAccountRelated(int count) {
          initData(count);
        }

        @Override
        public void onDataNotAvailable() {
          initData(-1);
        }
      });
  }

  private void initData(int count) {
    if (count > 0) {
      mView.initData();
      mView.initLayout();
    } else
      mView.postValue();
  }

  @Override
  public void setAccount(AccVectorInfo data) {
    getCoreDB().accountRepository().getAccountByFullId(data.getCode(),
      new AccountRepository.GetAccountByFullIdCallback() {
        @Override
        public void onAccountLoaded(Account account) {
          if (mView.getACCVector().getAccount().getId() != 0)
            mView.getTempACCVector().setAccount(account);
          mView.nextStep(data);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void loadSortList() {
    if (getSortList() != null)
      return;

    mSortList = new ArrayList<>();
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_account_code));
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_account_name));
  }


  @Override
  public List<String> getSortList() {
    return mSortList;
  }
}