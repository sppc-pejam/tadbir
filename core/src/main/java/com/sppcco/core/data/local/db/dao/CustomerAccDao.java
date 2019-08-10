package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.sub_model.CustomerAcc;

import androidx.room.Dao;
import androidx.room.Query;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

@Dao
public interface CustomerAccDao {

  @Query("SELECT \n" +
    "(SELECT Name FROM __Account__ WHERE FullId = :fullId) AS accountName,\n" +
    "(SELECT Name FROM __DetailAcc__ WHERE _id = :detId) AS detailAccName,\n" +
    "(SELECT Name FROM __CostCenter__ WHERE _id = :ccId) AS costCenterName ,\n" +
    "(SELECT Name FROM __Project__ WHERE _id = :prjId) AS projectName ,\n" +
    "(SELECT T1||' '||T2||' '||T3 FROM __DetailAcc__ WHERE _id = :detId) AS buyerAccount")
  CustomerAcc getCustomerAcc(String fullId, int detId, int ccId, int prjId);
}
