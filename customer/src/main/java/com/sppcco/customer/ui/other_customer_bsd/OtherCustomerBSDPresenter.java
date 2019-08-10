package com.sppcco.customer.ui.other_customer_bsd;
 

import com.sppcco.core.data.local.db.repository.AccountRepository;
import com.sppcco.core.data.local.db.repository.CustomerAccRepository;
import com.sppcco.core.data.local.db.repository.DetailAccRepository;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.CustomerAcc;
import com.sppcco.core.data.sub_model.OtherCustomer;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class OtherCustomerBSDPresenter extends BasePresenter implements OtherCustomerBSDContract.Presenter {

  private final OtherCustomerBSDContract.View mView;

  private final CompositeDisposable disposables = new CompositeDisposable();

  private OtherCustomerBSDPresenter(@NonNull OtherCustomerBSDContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  static OtherCustomerBSDContract.Presenter getPresenterInstance(@NonNull OtherCustomerBSDContract.View view) {
    return new OtherCustomerBSDPresenter(view);
  }

  @Override
  public void start() {

    if (mView.getMode() != Mode.EDIT) {
      Completable.fromAction(() -> mView.setDependentOnAccount(
        getCoreDB().accountDao().getIsDependentOnAccount(mView.getTempOtherCustomer().getAccId()) > 0))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
    } else {
      setACC(mView::updateACCView);
    }

  }


  @Override
  public void loadACC() {

    // حساب و تفصیلی شناور موجود باشد
    if (isFullACC())
      is_CC_Prj_DependentOnAccount();

      // بررسی خالی بودن بردار حساب خریدار/فروشنده(ALT+A)
    else if (isEmptyCustomerACC())
      mView.callACCVectorActivity(AccountTree.DETAIL_ACC);

      // اگر مشتری انتخاب شده فاقد حساب (در بردار حساب ALT+A) بود
    else if (mView.getTempOtherCustomer().getAccId().matches("0"))
      getDetailAccById();

      // تفصیلی شناور، مرکز هزینه و یا پروژه ای به این حساب وابسته است
    else if (!mView.getTempOtherCustomer().getAccId().matches("0"))
      isDependentOnAccount();

      // حساب کامل
    else
      setACC(mView::updateACCView);
  }

  @Override
  public void setACC(DoneResponseListener listener) {
    Completable.fromAction(() -> {
      Account account = getCoreDB().accountDao().getAccountByFullId(mView.getTempOtherCustomer().getAccId());
      mView.updateACC(account == null ? new Account() : account);

      DetailAcc detailAcc = getCoreDB().detailAccDao().getDetailAccById(mView.getTempOtherCustomer().getFAccId());
      mView.updateACC(detailAcc == null ? new DetailAcc() : detailAcc);

      CostCenter costCenter = getCoreDB().costCenterDao().getCostCenterByCode(mView.getTempOtherCustomer().getCCId());
      mView.updateACC(costCenter == null ? new CostCenter() : costCenter);

      Project project = getCoreDB().projectDao().getProjectByPCode(mView.getTempOtherCustomer().getPrjId());
      mView.updateACC(project == null ? new Project() : project);

      if (detailAcc != null)
        mView.setAccCode(getCoreDB().detailAccDao().getDetailAccCodeById(detailAcc.getId()));
      else
        mView.setAccCode(null);
    })
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnComplete(listener::onDone)
      .subscribe();
  }

  private void getDetailAccById() {
    getCoreDB().detailAccRepository().getDetailAcc(mView.getTempOtherCustomer().getFAccId(),
      new DetailAccRepository.GetDetailAccCallback() {
        @Override
        public void onDetailAccLoaded(DetailAcc detailAcc) {
          mView.getTempACCVector().setDetailAcc(detailAcc);
          getListAccountByDetailId();
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void getListAccountByDetailId() {
    getCoreDB().accountRepository().getAccountsByDetId(mView.getTempOtherCustomer().getFAccId(),
      new AccountRepository.GetAccountsByDetIdCallback() {
        @Override
        public void onAccountLoaded(List<Account> accounts) {
          if (accounts.size() > 0) {
            mView.callACCVectorActivity(AccountTree.DETAIL_ACC);
          } else {
            setACCCustomer();
          }
        }

        @Override
        public void onDataNotAvailable() {

        }
      }
    );
  }

  private void is_CC_Prj_DependentOnAccount() {
    getCoreDB().accountRepository().is_CC_Prj_DependentOnAccount(mView.getTempOtherCustomer().getAccId(),
      new AccountRepository.GetIsDependentOnAccountCallback() {
        @Override
        public void onIsDependent(int count) {
          setACC(() -> {
            mView.updateACCView();
            if (count != 0) {
              mView.callACCVectorActivity(AccountTree.ACCOUNT);
            }
          });

        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void isDependentOnAccount() {
    getCoreDB().accountRepository().getIsDependentOnAccount(
      mView.getTempOtherCustomer().getAccId(),
      new AccountRepository.GetIsDependentOnAccountCallback() {
        @Override
        public void onIsDependent(int count) {
          setACC(() -> {
            if (count != 0) {
              mView.callACCVectorActivity(AccountTree.ACCOUNT);
            } else {
              mView.updateACCView();
            }
          });
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  // بررسی خالی بودن بردار حساب خریدار/فروشنده(ALT+A)
  private boolean isEmptyCustomerACC() {
    OtherCustomer otherCustomer = mView.getTempOtherCustomer();
    return (otherCustomer.getAccId() == null || otherCustomer.getAccId().matches("0")) && otherCustomer.getFAccId() == 0
      && otherCustomer.getCCId() == 0 && otherCustomer.getPrjId() == 0;
  }

  // حساب و تفصیلی شناور موجود باشد
  private boolean isFullACC() {
    OtherCustomer otherCustomer = mView.getTempOtherCustomer();
    return otherCustomer.getAccId() != null && !otherCustomer.getAccId().matches("0")
      && otherCustomer.getFAccId() != 0 && otherCustomer.getCCId() == 0 && otherCustomer.getPrjId() == 0;
  }

  private void setACCCustomer() {
    String fullId = mView.getTempOtherCustomer().getAccId();
    int detailId = mView.getTempOtherCustomer().getFAccId();
    int ccCode = mView.getTempOtherCustomer().getCCId();
    int prjCode = mView.getTempOtherCustomer().getPrjId();

    getCoreDB().customerAccRepository().getCustomerAcc(fullId, detailId, ccCode, prjCode,
      new CustomerAccRepository.GetCustomerAccCallback() {
        @Override
        public void onCustomerAcc(CustomerAcc customerAcc) {
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  @Override
  public void destroy() {
    disposables.dispose();
    disposables.clear();
  }

}
