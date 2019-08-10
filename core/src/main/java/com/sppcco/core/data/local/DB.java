package com.sppcco.core.data.local;

import android.app.Application;
import android.content.Context;


import com.sppcco.core.data.local.db.dao.AccSpAccDao;
import com.sppcco.core.data.local.db.dao.AccVectorInfoDao;
import com.sppcco.core.data.local.db.dao.AccVsCCDao;
import com.sppcco.core.data.local.db.dao.AccVsDetailDao;
import com.sppcco.core.data.local.db.dao.AccVsPrjDao;
import com.sppcco.core.data.local.db.dao.AccountDao;
import com.sppcco.core.data.local.db.dao.ApiServiceInfoDao;
import com.sppcco.core.data.local.db.dao.BinAppendixDao;
import com.sppcco.core.data.local.db.dao.BrokerDao;
import com.sppcco.core.data.local.db.dao.CabinetDao;
import com.sppcco.core.data.local.db.dao.CostCenterDao;
import com.sppcco.core.data.local.db.dao.CustomerAccDao;
import com.sppcco.core.data.local.db.dao.CustomerAndUserDao;
import com.sppcco.core.data.local.db.dao.CustomerDao;
import com.sppcco.core.data.local.db.dao.DetailAccDao;
import com.sppcco.core.data.local.db.dao.ErrorStatusDao;
import com.sppcco.core.data.local.db.dao.FiscalPeriodDao;
import com.sppcco.core.data.local.db.dao.ImageDao;
import com.sppcco.core.data.local.db.dao.InvSPDao;
import com.sppcco.core.data.local.db.dao.MerchInfoDao;
import com.sppcco.core.data.local.db.dao.MerchStockDao;
import com.sppcco.core.data.local.db.dao.MerchTaxDao;
import com.sppcco.core.data.local.db.dao.MerchandiseDao;
import com.sppcco.core.data.local.db.dao.OptionDao;
import com.sppcco.core.data.local.db.dao.PostedPrefactorInfoDao;
import com.sppcco.core.data.local.db.dao.PostedSOInfoDao;
import com.sppcco.core.data.local.db.dao.ProjectDao;
import com.sppcco.core.data.local.db.dao.RightsDao;
import com.sppcco.core.data.local.db.dao.SOArticleDao;
import com.sppcco.core.data.local.db.dao.SOStatusDao;
import com.sppcco.core.data.local.db.dao.SPArticleDao;
import com.sppcco.core.data.local.db.dao.SPFactorDao;
import com.sppcco.core.data.local.db.dao.SPStatusDao;
import com.sppcco.core.data.local.db.dao.SPTaxDao;
import com.sppcco.core.data.local.db.dao.SalesDiscountDao;
import com.sppcco.core.data.local.db.dao.SalesOrderDao;
import com.sppcco.core.data.local.db.dao.SalesPriceDao;
import com.sppcco.core.data.local.db.dao.StockRoomDao;
import com.sppcco.core.data.local.db.dao.TablesStatusDao;
import com.sppcco.core.data.local.db.dao.UnitDao;
import com.sppcco.core.data.local.db.dao.UserServiceLoginDao;
import com.sppcco.core.data.local.db.repository.DataGenerator;
import com.sppcco.core.data.model.AccSpAcc;
import com.sppcco.core.data.model.AccVsCC;
import com.sppcco.core.data.model.AccVsDetail;
import com.sppcco.core.data.model.AccVsPrj;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.ApiServiceInfo;
import com.sppcco.core.data.model.BinAppendix;
import com.sppcco.core.data.model.Broker;
import com.sppcco.core.data.model.Cabinet;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.model.CustomerAndUser;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.ErrorStatus;
import com.sppcco.core.data.model.FiscalPeriod;
import com.sppcco.core.data.model.Image;
import com.sppcco.core.data.model.MerchStock;
import com.sppcco.core.data.model.MerchTax;
import com.sppcco.core.data.model.Merchandise;
import com.sppcco.core.data.model.Option;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.model.Rights;
import com.sppcco.core.data.model.SOArticle;
import com.sppcco.core.data.model.SOStatus;
import com.sppcco.core.data.model.SPArticle;
import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.model.SPStatus;
import com.sppcco.core.data.model.SPTax;
import com.sppcco.core.data.model.SalesDiscount;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.model.SalesPrice;
import com.sppcco.core.data.model.StockRoom;
import com.sppcco.core.data.model.TablesStatus;
import com.sppcco.core.data.model.Unit;
import com.sppcco.core.data.model.UserServiceLogin;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.data.sub_model.PostedPrefactorInfo;
import com.sppcco.core.data.sub_model.PostedSOInfo;
import com.sppcco.core.util.app.AppExecutors;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.converter.timestamp.TimestampConverter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {
  Account.class,
  ApiServiceInfo.class,
  CostCenter.class,
  Customer.class,
  DetailAcc.class,
  FiscalPeriod.class,
  Merchandise.class,
  Project.class,
  SalesOrder.class,
  SalesPrice.class,
  SalesDiscount.class,
  SOArticle.class,
  Unit.class,
  UserServiceLogin.class,
  SOStatus.class,
  TablesStatus.class,
  BinAppendix.class,
  Image.class,
  Option.class,
  SPFactor.class,
  SPArticle.class,
  SPStatus.class,
  StockRoom.class,
  Cabinet.class,
  MerchStock.class,
  ErrorStatus.class,
  MerchInfo.class,
  PostedSOInfo.class,
  PostedPrefactorInfo.class,
  AccVsDetail.class,
  AccVsCC.class,
  AccVsPrj.class,
  AccVectorInfo.class,
  Broker.class,
  MerchTax.class,
  AccSpAcc.class,
  SPTax.class,
  Rights.class,
  CustomerAndUser.class
}, version = CoreConstants.DB_VERSION, exportSchema = false)
@TypeConverters({TimestampConverter.class})
public abstract class DB extends RoomDatabase {

  private static Application m_Application;

  private static AppExecutors m_appExecutors;

  private static DB dbInstance;

  private static final Object sLock = new Object();


  private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

  public abstract AccountDao getAccountDao();

  public abstract ApiServiceInfoDao getApiServiceInfoDao();

  public abstract CostCenterDao getCostCenterDao();

  public abstract CustomerDao getCustomerDao();

  public abstract DetailAccDao getDetailAccDao();

  public abstract FiscalPeriodDao getFiscalPeriodDao();

  public abstract MerchandiseDao getMerchandiseDao();

  public abstract ProjectDao getProjectDao();

  public abstract SalesOrderDao getSalesOrderDao();

  public abstract SalesPriceDao getSalesPriceDao();

  public abstract SalesDiscountDao getSalesDiscountDao();

  public abstract SOArticleDao getSOArticleDao();

  public abstract UnitDao getUnitDao();

  public abstract UserServiceLoginDao getUserServiceLoginDao();

  public abstract SOStatusDao getSOStatusDao();

  public abstract TablesStatusDao getTablesStatusDao();

  public abstract ImageDao getImageDao();

  public abstract BinAppendixDao getBinAppendixDao();

  public abstract OptionDao getOptionDao();

  public abstract SPFactorDao getSPFactorDao();

  public abstract SPArticleDao getSPArticleDao();

  public abstract SPStatusDao getSPStatusDao();

  public abstract StockRoomDao getStockRoomDao();

  public abstract CabinetDao getCabinetDaoDao();

  public abstract MerchStockDao getMerchStockDao();

  public abstract InvSPDao getInvSPDao();

  public abstract ErrorStatusDao getErrorStatusDao();

  public abstract MerchInfoDao getMerchInfoDao();

  public abstract PostedSOInfoDao getPostedSOInfoDao();

  public abstract PostedPrefactorInfoDao getPostedPrefactorInfoDao();

  public abstract AccVsDetailDao getAccVsDetailDao();

  public abstract AccVsCCDao getAccVsCCDao();

  public abstract AccVsPrjDao getAccVsPrjDao();

  public abstract AccVectorInfoDao getAccVectorInfoDao();

  public abstract BrokerDao getBrokerDao();

  public abstract CustomerAccDao getCustomerAccDao();

  public abstract MerchTaxDao getMerchTaxDao();

  public abstract AccSpAccDao getAccSpAccDao();

  public abstract SPTaxDao getSPTaxDao();

  public abstract RightsDao getRightsDao();

  public abstract CustomerAndUserDao getCustomerAndUserDao();


  public static DB getInstance(Application application, AppExecutors appExecutors) {
    synchronized (sLock) {
      if (dbInstance == null) {
        m_appExecutors = appExecutors;
        m_Application = application;
        dbInstance = Room.databaseBuilder(application,
          DB.class, CoreConstants.DB_NAME)
          //.addMigrations(FROM_2_TO_2_1)
          .addCallback(callback)
          .fallbackToDestructiveMigration()
          .build();
        dbInstance.updateDatabaseCreated(application);
      }
      return dbInstance;
    }
  }

  /**
   * Migrate from: version 2.0.0 => 2.1.0
   */
  private static final Migration FROM_2_TO_2_1 = new Migration(160020000, 160020100) {
    @Override
    public void migrate(@NonNull final SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE __TablesStatus__ ADD COLUMN [Update] INTEGER default 0 NOT NULL");
    }
  };

  public static RoomDatabase.Callback callback = new Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);

      /*m_appExecutors.diskIO().execute(() -> {
        // Generate the data for pre-population
        DB database = DB.getInstance(m_Application, m_appExecutors);
        List<TablesStatus> tablesStatuses = DataGenerator.generateTablesStatus();
        insertData(database, tablesStatuses);

        // notify that the database was created and it's ready to be used
        database.setDatabaseCreated();
      });*/
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }


  };

  private void updateDatabaseCreated(final Context context) {
    if (context.getDatabasePath(CoreConstants.DB_NAME).exists()) {
      setDatabaseCreated();
    }
  }

  private void setDatabaseCreated() {
    mIsDatabaseCreated.postValue(true);
  }

  private static void insertData(final DB db, final List<TablesStatus> tablesStatuses) {
    db.runInTransaction(() -> db.getTablesStatusDao().insertTablesStatuss(tablesStatuses));
  }

}
