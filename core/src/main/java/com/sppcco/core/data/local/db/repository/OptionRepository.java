package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.Option;

import java.util.List;

import androidx.annotation.NonNull;

public interface OptionRepository {

  // ________________________________________ CRUD ________________________________________ //

  // select
  interface GetOptionsCallback {
    void onOptionsLoaded(List<Option> options);
    void onDataNotAvailable();
  }
  void getOptions(@NonNull OptionRepository.GetOptionsCallback callback);


  // Insert
  interface InsertOptionsCallback {
    void onOptionsInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertOptions(List<Option> options, @NonNull OptionRepository.InsertOptionsCallback callback);

  interface InsertOptionCallback {
    void onOptionInserted(long optionId);
    void onDataNotAvailable();
  }
  void insertOption(Option option, @NonNull OptionRepository.InsertOptionCallback callback);


  // Update

  interface UpdateOptionsCallback {
    void onOptionsUpdated(int i);
    void onDataNotAvailable();
  }
  void updateOptions(@NonNull OptionRepository.UpdateOptionsCallback callback, Option... options);


  interface UpdateOptionCallback {
    void onOptionUpdated(int i);
    void onDataNotAvailable();
  }
  void updateOption(Option option, @NonNull OptionRepository.UpdateOptionCallback callback);


  // Delete

  interface DeleteOptionsCallback {
    void onOptionsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteOptions(@NonNull OptionRepository.DeleteOptionsCallback callback, Option... options);


  interface DeleteAllOptionsCallback {
    void onOptionsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllOption(@NonNull OptionRepository.DeleteAllOptionsCallback callback);


  interface DeleteOptionCallback {
    void onOptionDeleted(int id);
    void onDataNotAvailable();
  }
  void deleteOptionById(int optionId, @NonNull OptionRepository.DeleteOptionCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetOptionValueCallback{
    void onOptionValueLoaded(String optionValue);
    void onDataNotAvailable();
  }
  void getOptionValueById(int id, @NonNull OptionRepository.GetOptionValueCallback callback);
  void getOptionValueByIdAndUserId(int id, int userId, @NonNull OptionRepository.GetOptionValueCallback callback);

  // Count(*)
  interface GetCountOptionCallback {
    void onOptionCounted(int count);
    void onDataNotAvailable();
  }
  void getCountOption(@NonNull OptionRepository.GetCountOptionCallback callback);
}
