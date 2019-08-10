package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.AccSpAccDao;
import com.sppcco.core.data.model.AccSpAcc;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class AccSpAccDataSource implements AccSpAccRepository {

  //private static volatile AccSpAccDataSource INSTANCE;

  private AccSpAccDao AccSpAccDao;
  private AppExecutors appExecutors;

  @Inject
  public AccSpAccDataSource(AppExecutors appExecutors, AccSpAccDao AccSpAccDao) {
    this.AccSpAccDao = AccSpAccDao;
    this.appExecutors = appExecutors;
  }

  /*public static AccSpAccDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull AccSpAccDao AccSpAccDao) {
    if (INSTANCE == null) {
      synchronized (AccSpAccDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new AccSpAccDataSource(appExecutors, AccSpAccDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAllAccSpAcc(@NonNull final GetAllAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final List<AccSpAcc> AccSpAccs = AccSpAccDao.getAllAccSpAcc();

      appExecutors.mainThread().execute(() -> {
        if (AccSpAccs != null)
          callback.onAllAccSpAccLoaded(AccSpAccs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccSpAccBySpId(final int SpId, @NonNull final GetAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final AccSpAcc AccSpAcc = AccSpAccDao.getAccSpAccBySpId(SpId);

      appExecutors.mainThread().execute(() -> {
        if (AccSpAcc != null)
          callback.onAccSpAccLoaded(AccSpAcc);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccSpAccByAccId(String accId, @NonNull GetAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final AccSpAcc AccSpAcc = AccSpAccDao.getAccSpAccByAccId(accId);

      appExecutors.mainThread().execute(() -> {
        if (AccSpAcc != null)
          callback.onAccSpAccLoaded(AccSpAcc);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccSpAccs(final List<AccSpAcc> AccSpAccs, @NonNull final InsertAccSpAccsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = AccSpAccDao.insertAccSpAccs(AccSpAccs);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onAccSpAccsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccSpAcc(final AccSpAcc AccSpAcc, @NonNull final InsertAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final long lAccSpAccId = AccSpAccDao.insertAccSpAcc(AccSpAcc);

      appExecutors.mainThread().execute(() -> {
        if (lAccSpAccId != 0)
          callback.onAccSpAccInserted(lAccSpAccId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccSpAccs(@NonNull final UpdateAccSpAccsCallback callback, final AccSpAcc... AccSpAccs) {
    Runnable runnable = () -> {
      final int rowNum = AccSpAccDao.updateAccSpAccs(AccSpAccs);

      appExecutors.mainThread().execute(() -> {
        if (rowNum != 0)
          callback.onAccSpAccsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccSpAcc(final AccSpAcc AccSpAcc, @NonNull final UpdateAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final int i = AccSpAccDao.updateAccSpAcc(AccSpAcc);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccSpAccUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAccSpAccs(@NonNull final DeleteAccSpAccsCallback callback, final AccSpAcc... AccSpAccs) {
    Runnable runnable = () -> {
      final int i = AccSpAccDao.deleteAccSpAccs(AccSpAccs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccSpAccsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllAccSpAcc(@NonNull final DeleteAllAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final int i = AccSpAccDao.deleteAllAccSpAcc();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onAccSpAccsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void getCountAccSpAcc(@NonNull GetCountAccSpAccCallback callback) {
    Runnable runnable = () -> {
      final int count = AccSpAccDao.getCountAccSpAcc();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onAccSpAccCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccIdFromSpId(int SpId, @NonNull GetAccIdFromSpIdCallback callback) {
    Runnable runnable = () -> {
      final String strAccId = AccSpAccDao.getAccIdFromSpId(SpId);

      appExecutors.mainThread().execute(() -> {
        if (strAccId != null)
          callback.onAccIdLoaded(strAccId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getTaxAvarezFromSpId(int SpecialOfTaxId, int SpecialOfAvarezId, @NonNull GetTaxAvarezCallback callback) {
    Runnable runnable = () -> {
      final AccSpAccDao.TaxAvarez cTaxAvarezFromSpId = AccSpAccDao.getCTaxAvarezFromSpId(SpecialOfTaxId, SpecialOfAvarezId);

      appExecutors.mainThread().execute(() -> {
        if (cTaxAvarezFromSpId != null)
          callback.onAccIdLoaded(cTaxAvarezFromSpId.taxAccId, cTaxAvarezFromSpId.avarezAccId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAvailableSpAccount(@NonNull GetAvailableSpAccountCallback callback) {
    Runnable runnable = () -> {
      final Account account = AccSpAccDao.getAvailableSpAccount();

      appExecutors.mainThread().execute(() -> {
        if (account != null)
          callback.onAccIdLoaded(account);
        else
          callback.onAccIdLoaded(null);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
