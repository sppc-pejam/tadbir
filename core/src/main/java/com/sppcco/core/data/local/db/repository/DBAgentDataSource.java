package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.DB;
import com.sppcco.core.util.app.AppExecutors;

import androidx.annotation.NonNull;


/**
 * Created by m_pejam on 01/16/18.
 *
 */

public class DBAgentDataSource implements DBAgentRepository {

  //private static volatile DBAgentDataSource INSTANCE;

  private AppExecutors appExecutors;
  private DB db;

  public DBAgentDataSource(AppExecutors appExecutors, DB db) {
    this.appExecutors = appExecutors;
    this.db = db;
  }

  /*public static DBAgentDataSource getInstance(@NonNull AppExecutors appExecutors, DB db) {
    if (INSTANCE == null) {
      synchronized (DBAgentDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new DBAgentDataSource(appExecutors, db);
        }
      }
    }
    return INSTANCE;
  }*/


  @Override
  public void clearAllTables(@NonNull TablesClearCallback callback) {
    Runnable runnable = () -> {
      db.clearAllTables();

      /*db.getAccountDao().deleteAllAccount();
      db.getApiServiceInfoDao().deleteAllApiServiceInfo();
      db.getCostCenterDao().deleteAllCostCenter();
      db.getCustomerDao().deleteAllCustomer();
      db.getDetailAccDao().deleteAllDetailAcc();
      db.getFiscalPeriodDao().deleteAllFiscalPeriod();
      db.getMerchandiseDao().deleteAllMerchandise();
      db.getProjectDao().deleteAllProject();
      db.getSalesOrderDao().deleteAllSalesOrder();
      db.getSalesPriceDao().deleteAllSalesPrice();
      db.getSOArticleDao().deleteAllSOArticle();
      db.getUnitDao().deleteAllUnit();
      db.getUserServiceLoginDao().deleteAllUserServiceLogin();
      db.getSOStatusDao().deleteAllSOStatus();
      db.getTablesStatusDao().deleteAllTablesStatus();
      db.getImageDao().deleteAllImage();
      db.getBinAppendixDao().deleteAllBinAppendix();
      db.getOptionDao().deleteAllOptions();
      db.getSPFactorDao().deleteAllSPFactor();
      db.getSPArticleDao().deleteAllSPArticle();
      db.getSPStatusDao().deleteAllSPStatus();
      db.getStockRoomDao().deleteAllStockRoom();
      db.getCabinetDaoDao().deleteAllCabinet();
      db.getMerchStockDao().deleteAllMerchStock();
      db.getErrorStatusDao().deleteAllErrorStatus();
      db.getSalesDiscountDao().deleteAllSalesDiscount();*/


      appExecutors.mainThread().execute(callback::onTablesCleared);
    };
    appExecutors.diskIO().execute(runnable);
  }
}
