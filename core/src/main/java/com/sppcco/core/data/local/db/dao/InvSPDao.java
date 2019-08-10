package com.sppcco.core.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

/**
 * Created by m_pejam on 04/24/18.
 *
 */

@Dao
public interface InvSPDao {

  @Query("SELECT SUM(Remainder) FROM __SPArticle__ sp WHERE sp.SPId = :SPId AND sp.FPId = :FPId ")
  double getSumOfSPArticleDiscounts(int SPId, int FPId);

  @Query("SELECT SUM( Amount * UnitPrice ) FROM __SPArticle__ sp WHERE sp.SPId = :SPId AND sp.FPId = :FPId ")
  double computeSPTotal(int SPId, int FPId);
}
