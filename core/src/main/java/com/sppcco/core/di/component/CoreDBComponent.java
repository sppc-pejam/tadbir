package com.sppcco.core.di.component;

import com.sppcco.core.data.local.DB;
import com.sppcco.core.di.module.CoreDBModule;

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
import com.sppcco.core.data.local.db.repository.AccSpAccRepository;
import com.sppcco.core.data.local.db.repository.AccVectorInfoRepository;
import com.sppcco.core.data.local.db.repository.AccVsCCRepository;
import com.sppcco.core.data.local.db.repository.AccVsDetailRepository;
import com.sppcco.core.data.local.db.repository.AccVsPrjRepository;
import com.sppcco.core.data.local.db.repository.AccountRepository;
import com.sppcco.core.data.local.db.repository.ApiServiceInfoRepository;
import com.sppcco.core.data.local.db.repository.BinAppendixRepository;
import com.sppcco.core.data.local.db.repository.BrokerRepository;
import com.sppcco.core.data.local.db.repository.CabinetRepository;
import com.sppcco.core.data.local.db.repository.CostCenterRepository;
import com.sppcco.core.data.local.db.repository.CustomerAccRepository;
import com.sppcco.core.data.local.db.repository.CustomerAndUserRepository;
import com.sppcco.core.data.local.db.repository.CustomerRepository;
import com.sppcco.core.data.local.db.repository.DBAgentRepository;
import com.sppcco.core.data.local.db.repository.DetailAccRepository;
import com.sppcco.core.data.local.db.repository.ErrorStatusRepository;
import com.sppcco.core.data.local.db.repository.FiscalPeriodRepository;
import com.sppcco.core.data.local.db.repository.ImageRepository;
import com.sppcco.core.data.local.db.repository.InvSPRepository;
import com.sppcco.core.data.local.db.repository.MerchInfoRepository;
import com.sppcco.core.data.local.db.repository.MerchStockRepository;
import com.sppcco.core.data.local.db.repository.MerchTaxRepository;
import com.sppcco.core.data.local.db.repository.MerchandiseRepository;
import com.sppcco.core.data.local.db.repository.OptionRepository;
import com.sppcco.core.data.local.db.repository.ProjectRepository;
import com.sppcco.core.data.local.db.repository.QueryGenerator;
import com.sppcco.core.data.local.db.repository.RightsRepository;
import com.sppcco.core.data.local.db.repository.SOArticleRepository;
import com.sppcco.core.data.local.db.repository.SOStatusRepository;
import com.sppcco.core.data.local.db.repository.SPArticleRepository;
import com.sppcco.core.data.local.db.repository.SPFactorRepository;
import com.sppcco.core.data.local.db.repository.SPStatusRepository;
import com.sppcco.core.data.local.db.repository.SPTaxRepository;
import com.sppcco.core.data.local.db.repository.SalesDiscountRepository;
import com.sppcco.core.data.local.db.repository.SalesOrderRepository;
import com.sppcco.core.data.local.db.repository.SalesPriceRepository;
import com.sppcco.core.data.local.db.repository.StockRoomRepository;
import com.sppcco.core.data.local.db.repository.TablesStatusRepository;
import com.sppcco.core.data.local.db.repository.UnitRepository;
import com.sppcco.core.data.local.db.repository.UserServiceLoginRepository;

import javax.inject.Singleton;

import dagger.Component;

//@DBScope
@Singleton
@Component(modules = CoreDBModule.class)
public interface CoreDBComponent {

  DB getDatabaseInstance();

  QueryGenerator getQueryGenerator();


  AccountDao accountDao();

  AccountRepository accountRepository();


  ApiServiceInfoDao apiServiceInfoDao();

  ApiServiceInfoRepository apiServiceInfoRepository();


  CostCenterDao costCenterDao();

  CostCenterRepository costCenterRepository();


  CustomerDao customerDao();

  CustomerRepository customerRepository();


  DetailAccDao detailAccDao();

  DetailAccRepository detailAccRepository();


  FiscalPeriodDao fiscalPeriodDao();

  FiscalPeriodRepository fiscalPeriodRepository();


  MerchandiseDao merchandiseDao();

  MerchandiseRepository merchandiseRepository();


  ProjectDao projectDao();

  ProjectRepository projectRepository();


  SOArticleDao sOArticleDao();

  SOArticleRepository sOArticleRepository();


  SalesOrderDao salesOrderDao();

  SalesOrderRepository salesOrderRepository();


  SalesPriceDao salesPriceDao();

  SalesPriceRepository salesPriceRepository();


  UnitDao unitDao();

  UnitRepository unitRepository();


  UserServiceLoginDao userServiceLoginDao();

  UserServiceLoginRepository userServiceLoginRepository();


  SOStatusDao sOStatusDao();

  SOStatusRepository sOStatusRepository();


  TablesStatusDao tablesStatusDao();

  TablesStatusRepository tablesStatusRepository();


  ImageDao imageDao();

  ImageRepository imageRepository();


  BinAppendixDao binAppendixDao();

  BinAppendixRepository binAppendixRepository();


  OptionDao optionDao();

  OptionRepository optionRepository();

  DBAgentRepository dbAgentRepository();


  SPFactorDao sPFactorDao();

  SPFactorRepository sPFactorRepository();


  SPArticleDao sPArticleDao();

  SPArticleRepository sPArticleRepository();


  SPStatusDao sPStatusDao();

  SPStatusRepository sPStatusRepository();


  StockRoomDao stockRoomDao();

  StockRoomRepository stockRoomRepository();


  CabinetDao cabinetDao();

  CabinetRepository cabinetRepository();


  MerchStockDao merchStockDao();

  MerchStockRepository merchStockRepository();


  InvSPDao invSPDao();

  InvSPRepository invSPRepository();


  ErrorStatusDao errorStatusDao();

  ErrorStatusRepository errorStatusRepository();


  SalesDiscountDao salesDiscountDao();

  SalesDiscountRepository salesDiscountRepository();


  MerchInfoDao merchInfoDao();

  MerchInfoRepository merchInfoRepository();

  PostedSOInfoDao postedSOInfoDao();

  PostedPrefactorInfoDao postedPrefactorInfoDao();

  AccVsDetailDao accVsDetailDao();

  AccVsDetailRepository accVsDetailRepository();

  AccVsCCDao accVsCCDao();

  AccVsCCRepository accVsCCRepository();

  AccVsPrjDao accVsPrjDao();

  AccVsPrjRepository accVsPrjRepository();

  AccVectorInfoDao accVectorInfoDao();

  AccVectorInfoRepository accVectorInfoRepository();

  BrokerDao brokerDao();

  BrokerRepository brokerRepository();

  CustomerAccDao customerAccDao();

  CustomerAccRepository customerAccRepository();

  MerchTaxDao merchTaxDao();

  MerchTaxRepository merchTaxRepository();

  AccSpAccDao accSpAccDao();

  AccSpAccRepository accSpAccRepository();

  SPTaxDao spTaxDao();

  SPTaxRepository spTaxRepository();

  RightsDao rightsDao();

  RightsRepository rightsRepository();

  CustomerAndUserDao customerAndUserDao();

  CustomerAndUserRepository customerAndUserRepository();

}

