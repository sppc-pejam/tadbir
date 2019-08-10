package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.OptionDao;
import com.sppcco.core.data.model.Option;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class OptionDataSource implements OptionRepository {

  //private static volatile OptionDataSource INSTANCE;

  private OptionDao optionDao;
  private AppExecutors appExecutors;

  @Inject
  public OptionDataSource(AppExecutors appExecutors, OptionDao optionDao) {
    this.optionDao = optionDao;
    this.appExecutors = appExecutors;
  }

  /*public static OptionDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull OptionDao optionDao) {
    if (INSTANCE == null) {
      synchronized (OptionDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new OptionDataSource(appExecutors, optionDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getOptions(@NonNull GetOptionsCallback callback) {
    Runnable runnable = () -> {
      final List<Option> options = optionDao.getAllOptions();

      appExecutors.mainThread().execute(() -> {
        if(options != null)
          callback.onOptionsLoaded(options);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertOptions(List<Option> options, @NonNull InsertOptionsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = optionDao.insertOptions(options);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onOptionsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertOption(Option option, @NonNull InsertOptionCallback callback) {
    Runnable runnable = () -> {
      final long IOptionId  = optionDao.insertOption(option);

      appExecutors.mainThread().execute(() -> {
        if(IOptionId != 0)
          callback.onOptionInserted(IOptionId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateOptions(@NonNull UpdateOptionsCallback callback, Option... options) {
    Runnable runnable = () -> {
      final int rowNum = optionDao.updateOptions(options);

      appExecutors.mainThread().execute(() -> {
        if(rowNum != 0)
          callback.onOptionsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateOption(Option option, @NonNull UpdateOptionCallback callback) {
    Runnable runnable = () -> {
      final int i  = optionDao.updateOption(option);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onOptionUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteOptions(@NonNull DeleteOptionsCallback callback, Option... options) {
    Runnable runnable = () -> {
      final int i = optionDao.deleteOptions(options);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onOptionsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllOption(@NonNull DeleteAllOptionsCallback callback) {
    Runnable runnable = () -> {
      final int i = optionDao.deleteAllOptions();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onOptionsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteOptionById(int optionId, @NonNull DeleteOptionCallback callback) {
    Runnable runnable = () -> {
      final int i = optionDao.deleteOptionById(optionId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onOptionDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getOptionValueById(int id, @NonNull GetOptionValueCallback callback) {
    Runnable runnable = () -> {
      final String optionValue = optionDao.getOptionValueById(id);
      appExecutors.mainThread().execute(() -> {
        if(optionValue != null)
          callback.onOptionValueLoaded(optionValue);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getOptionValueByIdAndUserId(int id, int userId, @NonNull GetOptionValueCallback callback) {
    Runnable runnable = () -> {
      final String optionValue = optionDao.getOptionValueByIdAndUserId(id, userId);
      appExecutors.mainThread().execute(() -> {
        if(optionValue != null)
          callback.onOptionValueLoaded(optionValue);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountOption(@NonNull GetCountOptionCallback callback) {
    Runnable runnable = () -> {
      final int count = optionDao.getCountOption();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onOptionCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
