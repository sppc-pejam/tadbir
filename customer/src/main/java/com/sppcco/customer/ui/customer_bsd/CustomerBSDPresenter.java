package com.sppcco.customer.ui.customer_bsd;

import com.sppcco.core.data.local.db.repository.AccountRepository;
import com.sppcco.core.data.local.db.repository.CustomerAccRepository;
import com.sppcco.core.data.local.db.repository.DetailAccRepository;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.sub_model.CustomerAcc;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerBSDPresenter extends BasePresenter implements CustomerBSDContract.Presenter {

  private final CustomerBSDContract.View mView;

  private final CompositeDisposable disposables = new CompositeDisposable();

  public CustomerBSDPresenter(@NonNull CustomerBSDContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  public static CustomerBSDContract.Presenter getPresenterInstance(@NonNull CustomerBSDContract.View view) {
    return new CustomerBSDPresenter(view);
  }

  @Override
  public void start() {
    if (mView.getMode() == Mode.EDIT) {
      mView.clearACC();
      mView.updateCustomerView();
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
    else if (mView.getTempCustomer().getAccId() == null || mView.getTempCustomer().getAccId().matches("0"))
      getDetailAccById();

      // تفصیلی شناور، مرکز هزینه و یا پروژه ای به این حساب وابسته است
    else if (mView.getTempCustomer().getAccId() != null || !mView.getTempCustomer().getAccId().matches("0"))
      isDependentOnAccount();

      // حساب کامل
    else
      setACC(mView::updateACCView);
  }

  @Override
  public void setACC(DoneResponseListener listener) {
    Completable.fromAction(() -> {
      Account account = getCoreDB().accountDao().getAccountByFullId(mView.getTempCustomer().getAccId());
      mView.updateACC(account == null ? new Account() : account);

      DetailAcc detailAcc = getCoreDB().detailAccDao().getDetailAccById(mView.getTempCustomer().getFAccId());
      mView.updateACC(detailAcc == null ? new DetailAcc() : detailAcc);

      CostCenter costCenter = getCoreDB().costCenterDao().getCostCenterByCode(mView.getTempCustomer().getCCId());
      mView.updateACC(costCenter == null ? new CostCenter() : costCenter);

      Project project = getCoreDB().projectDao().getProjectByPCode(mView.getTempCustomer().getPrjId());
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

  @Override
  public void getAccVectorBalance(Customer customer) {
    disposables.add(getCoreNet().customerRemoteControlRepository().getAccVectorBalance(customer, CalenderManager.getCurrentDate(),
      new CustomerRemoteRepository.LoadStringArrayCallback() {
        @Override
        public void onResponse(String... response) {
          mView.updateAccountBalance(response[1]);
          mView.enableRefresh(true);
        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.enableRefresh(true);
        }
      })
    );
  }

  @Override
  public void disposeRequest() {
    disposables.clear();
  }

  private void getDetailAccById() {
    getCoreDB().detailAccRepository().getDetailAcc(mView.getTempCustomer().getFAccId(),
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
    getCoreDB().accountRepository().getAccountsByDetId(mView.getTempCustomer().getFAccId(),
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
    getCoreDB().accountRepository().is_CC_Prj_DependentOnAccount(
      mView.getTempCustomer().getAccId(),
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
      mView.getTempCustomer().getAccId(),
      new AccountRepository.GetIsDependentOnAccountCallback() {
        @Override
        public void onIsDependent(int count) {
          setACC(() -> {
            mView.updateACCView();
            if (count != 0) {
              mView.callACCVectorActivity(AccountTree.ACCOUNT);
            }
          });

          /*if (count != 0) {
            mView.callACCVectorActivity(AccountTree.ACCOUNT);
          } else {
            setACC(() -> {

            });
          }*/
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  // بررسی خالی بودن بردار حساب خریدار/فروشنده(ALT+A)
  private boolean isEmptyCustomerACC() {
    Customer customer = mView.getTempCustomer();
    return (customer.getAccId() == null || customer.getAccId().matches("0")) && customer.getFAccId() == 0
      /*&& customer.getCCId() == 0 && customer.getPrjId() == 0*/;
  }

  // حساب و تفصیلی شناور موجود باشد
  private boolean isFullACC() {
    Customer customer = mView.getTempCustomer();
    return !customer.getAccId().matches("0") && customer.getFAccId() != 0
      /*&& customer.getCCId() == 0 && customer.getPrjId() == 0*/;
  }

  private void setACCCustomer() {
    String fullId = mView.getTempCustomer().getAccId();
    int detailId = mView.getTempCustomer().getFAccId();
    int ccCode = mView.getTempCustomer().getCCId();
    int prjCode = mView.getTempCustomer().getPrjId();

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
