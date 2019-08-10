package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.AccountDao;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public class AccountDataSource implements AccountRepository {

  //private static volatile AccountDataSource INSTANCE;

  private AccountDao accountDao;
  private AppExecutors appExecutors;

  @Inject
  public AccountDataSource(AppExecutors appExecutors, AccountDao accountDao) {
    this.accountDao = accountDao;
    this.appExecutors = appExecutors;
  }

  /*public static AccountDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull AccountDao accountDao) {
    if (INSTANCE == null) {
      synchronized (AccountDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new AccountDataSource(appExecutors, accountDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAccounts(@NonNull final GetAccountsCallback callback) {
    Runnable runnable = () -> {
      final List<Account> accounts = accountDao.getAllAccount();

      appExecutors.mainThread().execute(() -> {
        if (accounts != null)
          callback.onAccountsLoaded(accounts);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccount(final int accountId, @NonNull final GetAccountCallback callback) {
    Runnable runnable = () -> {
      final Account account = accountDao.getAccountById(accountId);

      appExecutors.mainThread().execute(() -> {
        if (account != null)
          callback.onAccountLoaded(account);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccountByFullId(String fullId, @NonNull GetAccountByFullIdCallback callback) {
    Runnable runnable = () -> {
      final Account account = accountDao.getAccountByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if (account != null)
          callback.onAccountLoaded(account);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccount(@NonNull GetCountAccountCallback callback) {
    Runnable runnable = () -> {
      final int count = accountDao.getCountAccount();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onAccountCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccounts(final List<Account> accounts, @NonNull final InsertAccountsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = accountDao.insertAccounts(accounts);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onAccountsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccount(final Account account, @NonNull final InsertAccountCallback callback) {
    Runnable runnable = () -> {
      final long lAccountId = accountDao.insertAccount(account);

      appExecutors.mainThread().execute(() -> {
        if (lAccountId != 0)
          callback.onAccountInserted(lAccountId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccounts(@NonNull final UpdateAccountsCallback callback, final Account... accounts) {
    Runnable runnable = () -> {
      final int rowNum = accountDao.updateAccounts(accounts);

      appExecutors.mainThread().execute(() -> {
        if (rowNum != 0)
          callback.onAccountsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccount(final Account account, @NonNull final UpdateAccountCallback callback) {
    Runnable runnable = () -> {
      final int i = accountDao.updateAccount(account);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccountUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAccounts(@NonNull final DeleteAccountsCallback callback, final Account... accounts) {
    Runnable runnable = () -> {
      final int i = accountDao.deleteAccounts(accounts);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccountsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllAccount(@NonNull final DeleteAllAccountCallback callback) {
    Runnable runnable = () -> {
      final int i = accountDao.deleteAllAccount();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccountsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAccountById(final int accountId, @NonNull final DeleteAccountCallback callback) {
    Runnable runnable = () -> {
      final int i = accountDao.deleteAccountById(accountId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccountDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void GetAccountNameFromAccountFullId(final int accountFullId, @NonNull final GetAccountNameCallback callback) {
    Runnable runnable = () -> {
      final String s = accountDao.GetAccountNameFromAccountFullId(accountFullId);
      appExecutors.mainThread().execute(() -> {
        if (s != null)
          callback.onAccountNameLoaded(s);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccountsByDetId(int detId, @NonNull GetAccountsByDetIdCallback callback) {
    Runnable runnable = () -> {
      final List<Account> accounts = accountDao.getAccountsByDetId(detId);
      appExecutors.mainThread().execute(() -> {
        if (accounts != null)
          callback.onAccountLoaded(accounts);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getIsDependentOnAccount(String fullId, @NonNull GetIsDependentOnAccountCallback callback) {
    Runnable runnable = () -> {
      final int isDependent = accountDao.getIsDependentOnAccount(fullId);
      appExecutors.mainThread().execute(() -> {
        if (isDependent >= 0)
          callback.onIsDependent(isDependent);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void is_CC_Prj_DependentOnAccount(String fullId, @NonNull GetIsDependentOnAccountCallback callback) {
    Runnable runnable = () -> {
      final int isDependent = accountDao.is_CC_Prj_DependentOnAccount(fullId);
      appExecutors.mainThread().execute(() -> {
        if (isDependent >= 0)
          callback.onIsDependent(isDependent);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccountsRelated(@NonNull GetCountAccountsRelatedCallback callback) {
    Runnable runnable = () -> {
      final int count = accountDao.getCountAccountsRelated();
      appExecutors.mainThread().execute(() -> {
        if (count >= 0)
          callback.onAccountRelated(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccountsRelatedDetailAcc(int detId, @NonNull GetCountAccountsRelatedDetailAccCallback callback) {
    Runnable runnable = () -> {
      final int count = accountDao.getCountAccountsRelatedDetailAcc(detId);
      appExecutors.mainThread().execute(() -> {
        if (count >= 0)
          callback.onAccountRelated(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccountsRelatedCostCenter(int ccId, @NonNull GetCountAccountsRelatedCostCenterCallback callback) {
    Runnable runnable = () -> {
      final int count = accountDao.getCountAccountsRelatedCostCenter(ccId);
      appExecutors.mainThread().execute(() -> {
        if (count >= 0)
          callback.onAccountRelated(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccountsRelatedProject(int prjId, @NonNull GetCountAccountsRelatedProjectCallback callback) {
    Runnable runnable = () -> {
      final int count = accountDao.getCountAccountsRelatedProject(prjId);
      appExecutors.mainThread().execute(() -> {
        if (count >= 0)
          callback.onAccountRelated(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
