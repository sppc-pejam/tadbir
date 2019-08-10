package com.sppcco.core.data.local.db.repository;


import com.sppcco.core.data.model.Account;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface AccountRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetAccountsCallback {
    void onAccountsLoaded(List<Account> accounts);
    void onDataNotAvailable();
  }

  void getAccounts(@NonNull GetAccountsCallback callback);


  interface GetAccountCallback {
    void onAccountLoaded(Account account);
    void onDataNotAvailable();
  }

  void getAccount(int accountId, @NonNull GetAccountCallback callback);

  interface GetAccountByFullIdCallback {
    void onAccountLoaded(Account account);
    void onDataNotAvailable();
  }

  void getAccountByFullId(String fullId, @NonNull GetAccountByFullIdCallback callback);


  // Insert
  interface InsertAccountsCallback {
    void onAccountsInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertAccounts(List<Account> accounts, @NonNull InsertAccountsCallback callback);

  interface InsertAccountCallback {
    void onAccountInserted(long accountId);
    void onDataNotAvailable();
  }

  void insertAccount(Account account, @NonNull InsertAccountCallback callback);


  // Update

  interface UpdateAccountsCallback {
    void onAccountsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccounts(@NonNull UpdateAccountsCallback callback, Account... accounts);


  interface UpdateAccountCallback {
    void onAccountUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccount(Account account, @NonNull UpdateAccountCallback callback);

  // Delete


  interface DeleteAccountsCallback {
    void onAccountsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAccounts(@NonNull DeleteAccountsCallback callback, Account... accounts);


  interface DeleteAllAccountCallback {
    void onAccountsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllAccount(@NonNull DeleteAllAccountCallback callback);


  interface DeleteAccountCallback {
    void onAccountDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAccountById(int accountId, @NonNull DeleteAccountCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetAccountNameCallback{
    void onAccountNameLoaded(String s);
    void onDataNotAvailable();
  }
  void GetAccountNameFromAccountFullId(int accountFullId, @NonNull GetAccountNameCallback callback);

  // Count(*)
  interface GetCountAccountCallback {
    void onAccountCounted(int count);
    void onDataNotAvailable();
  }
  void getCountAccount(@NonNull GetCountAccountCallback callback);

  interface GetAccountsByDetIdCallback {
    void onAccountLoaded(List<Account> accounts);
    void onDataNotAvailable();
  }
  void getAccountsByDetId(int detId, @NonNull GetAccountsByDetIdCallback callback);

  interface GetIsDependentOnAccountCallback {
    void onIsDependent(int count);
    void onDataNotAvailable();
  }
  void getIsDependentOnAccount(String fullId, @NonNull GetIsDependentOnAccountCallback callback);
  void is_CC_Prj_DependentOnAccount(String fullId, @NonNull GetIsDependentOnAccountCallback callback);


  interface GetCountAccountsRelatedCallback {
    void onAccountRelated(int count);
    void onDataNotAvailable();
  }
  void getCountAccountsRelated(@NonNull GetCountAccountsRelatedCallback callback);

  interface GetCountAccountsRelatedDetailAccCallback {
    void onAccountRelated(int count);
    void onDataNotAvailable();
  }
  void getCountAccountsRelatedDetailAcc(int detId, @NonNull GetCountAccountsRelatedDetailAccCallback callback);

  interface GetCountAccountsRelatedCostCenterCallback {
    void onAccountRelated(int count);
    void onDataNotAvailable();
  }
  void getCountAccountsRelatedCostCenter(int ccId, @NonNull GetCountAccountsRelatedCostCenterCallback callback);

  interface GetCountAccountsRelatedProjectCallback {
    void onAccountRelated(int count);
    void onDataNotAvailable();
  }
  void getCountAccountsRelatedProject(int prjId, @NonNull GetCountAccountsRelatedProjectCallback callback);
}
