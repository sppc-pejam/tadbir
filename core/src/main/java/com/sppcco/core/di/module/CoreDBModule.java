package com.sppcco.core.di.module;


import android.app.Application;


import com.sppcco.core.data.local.DB;
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
import com.sppcco.core.data.local.db.repository.AccSpAccDataSource;
import com.sppcco.core.data.local.db.repository.AccSpAccRepository;
import com.sppcco.core.data.local.db.repository.AccVectorInfoDataSource;
import com.sppcco.core.data.local.db.repository.AccVectorInfoRepository;
import com.sppcco.core.data.local.db.repository.AccVsCCDataSource;
import com.sppcco.core.data.local.db.repository.AccVsCCRepository;
import com.sppcco.core.data.local.db.repository.AccVsDetailDataSource;
import com.sppcco.core.data.local.db.repository.AccVsDetailRepository;
import com.sppcco.core.data.local.db.repository.AccVsPrjDataSource;
import com.sppcco.core.data.local.db.repository.AccVsPrjRepository;
import com.sppcco.core.data.local.db.repository.AccountDataSource;
import com.sppcco.core.data.local.db.repository.AccountRepository;
import com.sppcco.core.data.local.db.repository.ApiServiceInfoDataSource;
import com.sppcco.core.data.local.db.repository.ApiServiceInfoRepository;
import com.sppcco.core.data.local.db.repository.BinAppendixDataSource;
import com.sppcco.core.data.local.db.repository.BinAppendixRepository;
import com.sppcco.core.data.local.db.repository.BrokerDataSource;
import com.sppcco.core.data.local.db.repository.BrokerRepository;
import com.sppcco.core.data.local.db.repository.CabinetDataSource;
import com.sppcco.core.data.local.db.repository.CabinetRepository;
import com.sppcco.core.data.local.db.repository.CostCenterDataSource;
import com.sppcco.core.data.local.db.repository.CostCenterRepository;
import com.sppcco.core.data.local.db.repository.CustomerAccDataSource;
import com.sppcco.core.data.local.db.repository.CustomerAccRepository;
import com.sppcco.core.data.local.db.repository.CustomerAndUserDataSource;
import com.sppcco.core.data.local.db.repository.CustomerAndUserRepository;
import com.sppcco.core.data.local.db.repository.CustomerDataSource;
import com.sppcco.core.data.local.db.repository.CustomerRepository;
import com.sppcco.core.data.local.db.repository.DBAgentDataSource;
import com.sppcco.core.data.local.db.repository.DBAgentRepository;
import com.sppcco.core.data.local.db.repository.DetailAccDataSource;
import com.sppcco.core.data.local.db.repository.DetailAccRepository;
import com.sppcco.core.data.local.db.repository.ErrorStatusDataSource;
import com.sppcco.core.data.local.db.repository.ErrorStatusRepository;
import com.sppcco.core.data.local.db.repository.FiscalPeriodDataSource;
import com.sppcco.core.data.local.db.repository.FiscalPeriodRepository;
import com.sppcco.core.data.local.db.repository.ImageDataSource;
import com.sppcco.core.data.local.db.repository.ImageRepository;
import com.sppcco.core.data.local.db.repository.InvSPDataSource;
import com.sppcco.core.data.local.db.repository.InvSPRepository;
import com.sppcco.core.data.local.db.repository.MerchInfoDataSource;
import com.sppcco.core.data.local.db.repository.MerchInfoRepository;
import com.sppcco.core.data.local.db.repository.MerchStockDataSource;
import com.sppcco.core.data.local.db.repository.MerchStockRepository;
import com.sppcco.core.data.local.db.repository.MerchTaxDataSource;
import com.sppcco.core.data.local.db.repository.MerchTaxRepository;
import com.sppcco.core.data.local.db.repository.MerchandiseDataSource;
import com.sppcco.core.data.local.db.repository.MerchandiseRepository;
import com.sppcco.core.data.local.db.repository.OptionDataSource;
import com.sppcco.core.data.local.db.repository.OptionRepository;
import com.sppcco.core.data.local.db.repository.ProjectDataSource;
import com.sppcco.core.data.local.db.repository.ProjectRepository;
import com.sppcco.core.data.local.db.repository.QueryGenerator;
import com.sppcco.core.data.local.db.repository.RightsDataSource;
import com.sppcco.core.data.local.db.repository.RightsRepository;
import com.sppcco.core.data.local.db.repository.SOArticleDataSource;
import com.sppcco.core.data.local.db.repository.SOArticleRepository;
import com.sppcco.core.data.local.db.repository.SOStatusDataSource;
import com.sppcco.core.data.local.db.repository.SOStatusRepository;
import com.sppcco.core.data.local.db.repository.SPArticleDataSource;
import com.sppcco.core.data.local.db.repository.SPArticleRepository;
import com.sppcco.core.data.local.db.repository.SPFactorDataSource;
import com.sppcco.core.data.local.db.repository.SPFactorRepository;
import com.sppcco.core.data.local.db.repository.SPStatusDataSource;
import com.sppcco.core.data.local.db.repository.SPStatusRepository;
import com.sppcco.core.data.local.db.repository.SPTaxDataSource;
import com.sppcco.core.data.local.db.repository.SPTaxRepository;
import com.sppcco.core.data.local.db.repository.SalesDiscountDataSource;
import com.sppcco.core.data.local.db.repository.SalesDiscountRepository;
import com.sppcco.core.data.local.db.repository.SalesOrderDataSource;
import com.sppcco.core.data.local.db.repository.SalesOrderRepository;
import com.sppcco.core.data.local.db.repository.SalesPriceDataSource;
import com.sppcco.core.data.local.db.repository.SalesPriceRepository;
import com.sppcco.core.data.local.db.repository.StockRoomDataSource;
import com.sppcco.core.data.local.db.repository.StockRoomRepository;
import com.sppcco.core.data.local.db.repository.TablesStatusDataSource;
import com.sppcco.core.data.local.db.repository.TablesStatusRepository;
import com.sppcco.core.data.local.db.repository.UnitDataSource;
import com.sppcco.core.data.local.db.repository.UnitRepository;
import com.sppcco.core.data.local.db.repository.UserServiceLoginDataSource;
import com.sppcco.core.data.local.db.repository.UserServiceLoginRepository;
import com.sppcco.core.util.app.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CoreDBModule {


  private Application mApplication;
  private AppExecutors mAppExecutors;

  public CoreDBModule(Application application, AppExecutors appExecutors) {

    mApplication = application;
    mAppExecutors = appExecutors;
  }

  @Provides
  @Singleton
  DB providesDatabase() {
    return DB.getInstance(mApplication, mAppExecutors);
  }

  @Provides
  //@DBScope
  @Singleton
  QueryGenerator providesQueryGenerator() {
    return new QueryGenerator();
  }

  @Provides
  //@DBScope
  @Singleton
  AccountDao providesAccountDao(DB db) {
    return db.getAccountDao();
  }

  @Provides
  //@DBScope
  @Singleton
  AccountRepository accountRepository(AppExecutors appExecutors, AccountDao accountDao) {
    return new AccountDataSource(appExecutors, accountDao);
  }


  @Provides
  //@DBScope
  @Singleton
  ApiServiceInfoDao providesApiServiceInfoDao(DB db) {
    return db.getApiServiceInfoDao();
  }

  @Provides
  //@DBScope
  @Singleton
  ApiServiceInfoRepository apiServiceInfoRepository(AppExecutors appExecutors, ApiServiceInfoDao apiServiceInfoDao) {
    return new ApiServiceInfoDataSource(appExecutors, apiServiceInfoDao);
  }


  @Provides
  @Singleton
  CostCenterDao providesCostCenterDao(DB db) {
    return db.getCostCenterDao();
  }

  @Provides
  @Singleton
  CostCenterRepository costCenterRepository(AppExecutors appExecutors, CostCenterDao costCenterDao) {
    return new CostCenterDataSource(appExecutors, costCenterDao);
  }

  @Provides
  @Singleton
  CustomerDao providesCustomerDao(DB db) {
    return db.getCustomerDao();
  }

  @Provides
  @Singleton
  CustomerRepository customerRepository(AppExecutors appExecutors, CustomerDao customerDao) {
    return new CustomerDataSource(appExecutors, customerDao);
  }


  @Provides
  @Singleton
  DetailAccDao providesDetailAccDao(DB db) {
    return db.getDetailAccDao();
  }

  @Provides
  @Singleton
  DetailAccRepository detailAccRepository(AppExecutors appExecutors, DetailAccDao detailAccDao) {
    return new DetailAccDataSource(appExecutors, detailAccDao);
  }

  @Provides
  @Singleton
  FiscalPeriodDao providesFiscalPeriodDao(DB db) {
    return db.getFiscalPeriodDao();
  }

  @Provides
  @Singleton
  FiscalPeriodRepository fiscalPeriodRepository(AppExecutors appExecutors, FiscalPeriodDao fiscalPeriodDao) {
    return new FiscalPeriodDataSource(appExecutors, fiscalPeriodDao);
  }

  @Provides
  @Singleton
  MerchandiseDao providesMerchandiseDao(DB db) {
    return db.getMerchandiseDao();
  }

  @Provides
  @Singleton
  MerchandiseRepository merchandiseRepository(AppExecutors appExecutors, MerchandiseDao merchandiseDao) {
    return new MerchandiseDataSource(appExecutors, merchandiseDao);
  }

  @Provides
  @Singleton
  ProjectDao providesProjectDao(DB db) {
    return db.getProjectDao();
  }

  @Provides
  @Singleton
  ProjectRepository projectRepository(AppExecutors appExecutors, ProjectDao projectDao) {
    return new ProjectDataSource(appExecutors, projectDao);
  }


  @Provides
  @Singleton
  SalesOrderDao providesSalesOrderDao(DB db) {
    return db.getSalesOrderDao();
  }

  @Provides
  @Singleton
  SalesOrderRepository salesOrderRepository(AppExecutors appExecutors, SalesOrderDao salesOrderDao) {
    return new SalesOrderDataSource(appExecutors, salesOrderDao);
  }

  @Provides
  @Singleton
  SalesPriceDao providesSalesPriceDao(DB db) {
    return db.getSalesPriceDao();
  }

  @Provides
  @Singleton
  SalesPriceRepository salesPriceRepository(AppExecutors appExecutors, SalesPriceDao salesPriceDao) {
    return new SalesPriceDataSource(appExecutors, salesPriceDao);
  }

  @Provides
  @Singleton
  SOArticleDao providesSOArticleDao(DB db) {
    return db.getSOArticleDao();
  }

  @Provides
  @Singleton
  SOArticleRepository sOArticleRepository(AppExecutors appExecutors, SOArticleDao sOArticleDao) {
    return new SOArticleDataSource(appExecutors, sOArticleDao);
  }

  @Provides
  @Singleton
  UnitDao providesUnitDao(DB db) {
    return db.getUnitDao();
  }

  @Provides
  @Singleton
  UnitRepository unitRepository(AppExecutors appExecutors, UnitDao unitDao) {
    return new UnitDataSource(appExecutors, unitDao);
  }

  @Provides
  @Singleton
  UserServiceLoginDao providesUserServiceLoginDao(DB db) {
    return db.getUserServiceLoginDao();
  }

  @Provides
  @Singleton
  UserServiceLoginRepository userServiceLoginRepository(AppExecutors appExecutors, UserServiceLoginDao userServiceLoginDao) {
    return new UserServiceLoginDataSource(appExecutors, userServiceLoginDao);
  }

  @Provides
  @Singleton
  SOStatusDao providesSOStatusDao(DB db) {
    return db.getSOStatusDao();
  }

  @Provides
  @Singleton
  SOStatusRepository sOStatusRepository(AppExecutors appExecutors, SOStatusDao sOStatusDao) {
    return new SOStatusDataSource(appExecutors, sOStatusDao);
  }

  @Provides
  @Singleton
  TablesStatusDao providesTablesStatusDao(DB db) {
    return db.getTablesStatusDao();
  }

  @Provides
  @Singleton
  TablesStatusRepository tablesStatusRepository(AppExecutors appExecutors, TablesStatusDao tablesStatusDao) {
    return new TablesStatusDataSource(appExecutors, tablesStatusDao);
  }

  @Provides
  @Singleton
  ImageDao providesImageDao(DB db) {
    return db.getImageDao();
  }

  @Provides
  @Singleton
  ImageRepository imageRepository(AppExecutors appExecutors, ImageDao imageDao) {
    return new ImageDataSource(appExecutors, imageDao);
  }

  @Provides
  @Singleton
  BinAppendixDao providesBinAppendixDao(DB db) {
    return db.getBinAppendixDao();
  }

  @Provides
  @Singleton
  BinAppendixRepository binAppendixRepository(AppExecutors appExecutors, BinAppendixDao binAppendixDao) {
    return new BinAppendixDataSource(appExecutors, binAppendixDao);
  }

  @Provides
  @Singleton
  OptionDao providesOptionDao(DB db) {
    return db.getOptionDao();
  }

  @Provides
  @Singleton
  OptionRepository optionRepository(AppExecutors appExecutors, OptionDao optionDao) {
    return new OptionDataSource(appExecutors, optionDao);
  }


  @Provides
  @Singleton
  DBAgentRepository dbAgentRepository(AppExecutors appExecutors, DB db) {
    return new DBAgentDataSource(appExecutors, db);
  }

  @Provides
  @Singleton
  SPFactorDao providesSPFactorDao(DB db) {
    return db.getSPFactorDao();
  }

  @Provides
  @Singleton
  SPFactorRepository sPFactorRepository(AppExecutors appExecutors, SPFactorDao spFactorDao) {
    return new SPFactorDataSource(appExecutors, spFactorDao);
  }

  @Provides
  @Singleton
  SPArticleDao providesSPArticleDao(DB db) {
    return db.getSPArticleDao();
  }

  @Provides
  @Singleton
  SPArticleRepository sPArticleRepository(AppExecutors appExecutors, SPArticleDao spArticleDao) {
    return new SPArticleDataSource(appExecutors, spArticleDao);
  }

  @Provides
  @Singleton
  SPStatusDao providesSPStatusDao(DB db) {
    return db.getSPStatusDao();
  }

  @Provides
  @Singleton
  SPStatusRepository sPStatusRepository(AppExecutors appExecutors, SPStatusDao sPStatusDao) {
    return new SPStatusDataSource(appExecutors, sPStatusDao);
  }

  @Provides
  @Singleton
  StockRoomDao providesStockRoomDao(DB db) {
    return db.getStockRoomDao();
  }

  @Provides
  @Singleton
  StockRoomRepository stockRoomRepository(AppExecutors appExecutors, StockRoomDao stockRoomDao) {
    return new StockRoomDataSource(appExecutors, stockRoomDao);
  }

  @Provides
  @Singleton
  CabinetDao providesCabinetDao(DB db) {
    return db.getCabinetDaoDao();
  }

  @Provides
  @Singleton
  CabinetRepository cabinetRepository(AppExecutors appExecutors, CabinetDao cabinetDao) {
    return new CabinetDataSource(appExecutors, cabinetDao);
  }

  @Provides
  @Singleton
  MerchStockDao providesMerchStockDao(DB db) {
    return db.getMerchStockDao();
  }

  @Provides
  @Singleton
  MerchStockRepository merchStockRepository(AppExecutors appExecutors, MerchStockDao merchStockDao) {
    return new MerchStockDataSource(appExecutors, merchStockDao);
  }

  @Provides
  @Singleton
  InvSPDao providesInvSPDao(DB db) {
    return db.getInvSPDao();
  }

  @Provides
  @Singleton
  InvSPRepository invSPRepository(AppExecutors appExecutors, InvSPDao invSPDao) {
    return new InvSPDataSource(appExecutors, invSPDao);
  }

  @Provides
  @Singleton
  ErrorStatusDao providesErrorStatusDao(DB db) {
    return db.getErrorStatusDao();
  }

  @Provides
  @Singleton
  ErrorStatusRepository errorStatusRepository(AppExecutors appExecutors, ErrorStatusDao errorStatusDao) {
    return new ErrorStatusDataSource(appExecutors, errorStatusDao);
  }

  @Provides
  @Singleton
  SalesDiscountDao providesSalesDiscountDao(DB db) {
    return db.getSalesDiscountDao();
  }

  @Provides
  @Singleton
  SalesDiscountRepository salesDiscountRepository(AppExecutors appExecutors, SalesDiscountDao salesDiscountDao) {
    return new SalesDiscountDataSource(appExecutors, salesDiscountDao);
  }

  @Provides
  @Singleton
  MerchInfoDao providesMerchInfoDao(DB db) {
    return db.getMerchInfoDao();
  }

  @Provides
  @Singleton
  MerchInfoRepository merchInfoDaoRepository(AppExecutors appExecutors, MerchInfoDao merchInfoDao) {
    return new MerchInfoDataSource(appExecutors, merchInfoDao);
  }

  @Provides
  @Singleton
  PostedSOInfoDao providesPostedSOInfoDao(DB db) {
    return db.getPostedSOInfoDao();
  }

  @Provides
  @Singleton
  PostedPrefactorInfoDao providesPostedPrefactorInfoDao(DB db) {
    return db.getPostedPrefactorInfoDao();
  }

  @Provides
  @Singleton
  AccVsDetailDao providesAccVsDetailDao(DB db) {
    return db.getAccVsDetailDao();
  }

  @Provides
  @Singleton
  AccVsDetailRepository accVsDetailRepository(AppExecutors appExecutors, AccVsDetailDao accVsDetailDao) {
    return new AccVsDetailDataSource(appExecutors, accVsDetailDao);
  }

  @Provides
  @Singleton
  AccVsCCDao providesAccVsCCDao(DB db) {
    return db.getAccVsCCDao();
  }

  @Provides
  @Singleton
  AccVsCCRepository accVsCCRepository(AppExecutors appExecutors, AccVsCCDao accVsCCDao) {
    return new AccVsCCDataSource(appExecutors, accVsCCDao);
  }

  @Provides
  @Singleton
  AccVsPrjDao providesAccVsPrjDao(DB db) {
    return db.getAccVsPrjDao();
  }

  @Provides
  @Singleton
  AccVsPrjRepository accVsPrjDaoRepository(AppExecutors appExecutors, AccVsPrjDao accVsPrjDao) {
    return new AccVsPrjDataSource(appExecutors, accVsPrjDao);
  }

  @Provides
  @Singleton
  AccVectorInfoDao providesAccVectorInfoDao(DB db) {
    return db.getAccVectorInfoDao();
  }

  @Provides
  @Singleton
  AccVectorInfoRepository accVectorInfoRepository(AppExecutors appExecutors, AccVectorInfoDao accVectorInfoDao) {
    return new AccVectorInfoDataSource(appExecutors, accVectorInfoDao);
  }

  @Provides
  @Singleton
  BrokerDao providesBrokerDao(DB db) {
    return db.getBrokerDao();
  }

  @Provides
  @Singleton
  BrokerRepository brokerRepository(AppExecutors appExecutors, BrokerDao brokerDao) {
    return new BrokerDataSource(appExecutors, brokerDao);
  }

  @Provides
  @Singleton
  CustomerAccDao providesCustomerAccDao(DB db) {
    return db.getCustomerAccDao();
  }

  @Provides
  @Singleton
  CustomerAccRepository customerAccRepository(AppExecutors appExecutors, CustomerAccDao customerAccDao) {
    return new CustomerAccDataSource(appExecutors, customerAccDao);
  }

  @Provides
  @Singleton
  MerchTaxDao providesMerchTaxDao(DB db) {
    return db.getMerchTaxDao();
  }

  @Provides
  @Singleton
  MerchTaxRepository merchTaxRepository(AppExecutors appExecutors, MerchTaxDao merchTaxDao) {
    return new MerchTaxDataSource(appExecutors, merchTaxDao);
  }

  @Provides
  @Singleton
  AccSpAccDao providesAccSpAccDao(DB db) {
    return db.getAccSpAccDao();
  }

  @Provides
  @Singleton
  AccSpAccRepository accSpAccRepository(AppExecutors appExecutors, AccSpAccDao accSpAccDao) {
    return new AccSpAccDataSource(appExecutors, accSpAccDao);
  }

  @Provides
  @Singleton
  SPTaxDao providesSPTaxDao(DB db) {
    return db.getSPTaxDao();
  }

  @Provides
  @Singleton
  SPTaxRepository spTaxRepository(AppExecutors appExecutors, SPTaxDao spTaxDao) {
    return new SPTaxDataSource(appExecutors, spTaxDao);
  }

  @Provides
  @Singleton
  RightsDao providesRightsDao(DB db) {
    return db.getRightsDao();
  }

  @Provides
  @Singleton
  RightsRepository rightsRepository(AppExecutors appExecutors, RightsDao rightsDao) {
    return new RightsDataSource(appExecutors, rightsDao);
  }

  @Provides
  @Singleton
  CustomerAndUserDao providesCustomerAndUserDao(DB db) {
    return db.getCustomerAndUserDao();
  }

  @Provides
  @Singleton
  CustomerAndUserRepository customerAndUserRepository(AppExecutors appExecutors, CustomerAndUserDao customerAndUserDao) {
    return new CustomerAndUserDataSource(appExecutors, customerAndUserDao);
  }


}
