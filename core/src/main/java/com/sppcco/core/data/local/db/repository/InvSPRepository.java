package com.sppcco.core.data.local.db.repository;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public interface InvSPRepository {

  interface GetSumOfSPArticleDiscountsCallback {
    void onSumOfSPArticleDiscountsLoaded(double sumOfDiscount);
    void onDataNotAvailable();
  }
  void getSumOfSPArticleDiscounts(int SPId, int FPId, @NonNull GetSumOfSPArticleDiscountsCallback callback);

  interface GetComputeSPTotalCallback {
    void onComputeSPTotalLoaded(double total);
    void onDataNotAvailable();
  }
  void computeSPTotal(int SPId, int FPId, @NonNull GetComputeSPTotalCallback callback);

}
