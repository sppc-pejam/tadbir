package com.sppcco.core.data.remote.service;

import com.sppcco.core.data.model.AccSpAcc;
import com.sppcco.core.data.model.AccVsCC;
import com.sppcco.core.data.model.AccVsDetail;
import com.sppcco.core.data.model.AccVsPrj;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.Broker;
import com.sppcco.core.data.model.Cabinet;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.model.CustomerAndUser;
import com.sppcco.core.data.model.DetailAcc;
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
import com.sppcco.core.data.model.Unit;
import com.sppcco.core.data.model.UserServiceLogin;
import com.sppcco.core.data.sub_model.CompanyInfo;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.data.sub_model.PrintPreview;
import com.sppcco.core.data.sub_model.UserLoginInfo;
import com.sppcco.core.data.sub_model.VersionCode;
import com.sppcco.core.data.sub_model.VersionInfo;
import com.sppcco.core.util.app.CoreConstants;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface ApiService {

  //region Login

  @GET("/")
  Observable<ResponseBody> isServerAvailable();

  @GET("Sys/" + "{AppVersion}/" + Webservice.DIR_USER_LOGIN_ENTITY_NAME + "getCompaniesInfo")
  Observable<List<CompanyInfo>> getCompaniesConnectedInfo(@Path("AppVersion") String appVersion,
                                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_APP_LOGIN_ENTITY_NAME + "ValidationUser")
  Observable<ResponseBody> validationUser(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("username") String userName,
                                          @Query("password") String password,
                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_APP_LOGIN_ENTITY_NAME + "UserAccess")
  Observable<ResponseBody> userAccess(@Path("dbName") String dbName,
                                      @Path("AppVersion") String appVersion,
                                      @Query("username") String userName,
                                      @Query("password") String password,
                                      @Query("companyName") String fName,
                                      @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_USER_LOGIN_ENTITY_NAME + "UserServiceLogin")
  Observable<List<UserServiceLogin>> userServiceLogin(@Path("dbName") String dbName,
                                                      @Path("AppVersion") String appVersion,
                                                      @Query("ugId") int ugId,
                                                      @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_FPID_ENTITY_NAME + "AppFiscalPeriod")
  Observable<List<FiscalPeriod>> appFiscalPeriod(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("date") String currentDate,
                                                 @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_APP_LOGIN_ENTITY_NAME + "UserLoginInfo")
  Observable<List<UserLoginInfo>> userLoginInfo(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("userName") String userName,
                                                @Query("password") String password,
                                                @Query("companyName") String companyName,
                                                @Query("date") String currentDate,
                                                @Query("major") int major,
                                                @Query("minor") int minor,
                                                @Query("key") String key);

  //endregion Login

  // region Validation PreRequest
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_USER_ACCESS_ENTITY_NAME + "IsCompatibleVersion")
  Observable<Integer> isCompatibleVersion(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("major") int major,
                                          @Query("minor") int minor,
                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_USER_ACCESS_ENTITY_NAME + "GetLatestVersion")
  Observable<ResponseBody> checkServerVersion(@Path("dbName") String dbName,
                                              @Path("AppVersion") String appVersion,
                                              @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_USER_ACCESS_ENTITY_NAME + "GetValidationAccessPreRequest")
  Observable<Integer> getValidationAccessPreRequest(@Path("dbName") String dbName,
                                                    @Path("AppVersion") String appVersion,
                                                    @Query("subsystemFlag") int subsystemFlag,
                                                    @Query("ugId") int ugId,
                                                    @Query("DBName") String DBName,
                                                    @Query("key") String key);
  // endregion Validation PreRequest

  //region Application Update
  @GET(CoreConstants.SPPC_APP_LAST_VERSION)
  Observable<List<VersionCode>> checkVersionfromSPPC(@Query("major") int major,
                                                     @Query("minor") int minor,
                                                     @Query("patch") int patch);

  @GET(CoreConstants.SPPC_APP_VERSION_INFO)
  Observable<List<VersionInfo>> getVersionInfofromSPPC(@Query("major") int major,
                                                       @Query("minor") int minor,
                                                       @Query("patch") int patch);

  @GET(CoreConstants.SPPC_APP_VERSION_INFO)
  Observable<ResponseBody> getLastDescriptionPatchChangesfromSPPC(@Query("major") int major,
                                                                  @Query("minor") int minor,
                                                                  @Query("patch") int patch);

  @GET(CoreConstants.SPPC_APP_VERSION_INFO)
  Observable<ResponseBody> getVersionChangesfromSPPC();
  //endregion Application Update

  // region Sync Table

  // region Sync Validation
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_VALIDATION_SYNC_TABLE_NAME + "GetValidationSyncTable")
  Observable<ResponseBody> getValidationSyncTable(@Path("dbName") String dbName,
                                                  @Path("AppVersion") String appVersion,
                                                  @Query("ugId") int ugId,
                                                  @Query("UserId") int userid,
                                                  @Query("fpId") int fpid,
                                                  @Query("key") String key);
  // endregion Sync Validation

  // region Sync Customer
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetCustomerPagesCount")
  Observable<ResponseBody> getCustomerPagesCount(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("UserId") int userid,
                                                 @Query("fpId") int fpid,
                                                 @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetCustomerByPageNumber")
  Observable<List<Customer>> getCustomerByPageNumber(@Path("dbName") String dbName,
                                                     @Path("AppVersion") String appVersion,
                                                     @Query("UserId") int userid,
                                                     @Query("pageNumber") int pageNumber,
                                                     @Query("fpId") int fpid,
                                                     @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetAllCustomer")
  Observable<List<Customer>> getAllCustomer(@Path("dbName") String dbName,
                                            @Path("AppVersion") String appVersion,
                                            @Query("UserId") int userid,
                                            @Query("fpId") int fpid,
                                            @Query("key") String key);
  // endregion Sync Customer

  // region Sync Merchandise
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_ENTITY_NAME + "GetMerchandisePagesCount")
  Observable<ResponseBody> getMerchandisePagesCount(@Path("dbName") String dbName,
                                                    @Path("AppVersion") String appVersion,
                                                    @Query("fpId") int fpid,
                                                    @Query("ugid") int ugId,
                                                    @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_ENTITY_NAME + "GetMerchandiseByPageNumber")
  Observable<List<Merchandise>> getMerchandiseByPageNumber(@Path("dbName") String dbName,
                                                           @Path("AppVersion") String appVersion,
                                                           @Query("pageNumber") int pageNumber,
                                                           @Query("fpId") int fpid,
                                                           @Query("ugid") int ugId,
                                                           @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_ENTITY_NAME + "GetAllMerchandise")
  Observable<List<Merchandise>> getAllMerchandise(@Path("dbName") String dbName,
                                                  @Path("AppVersion") String appVersion,
                                                  @Query("fpId") int fpid,
                                                  @Query("ugid") int ugId,
                                                  @Query("key") String key);

  // endregion Sync Merchandise

  // region Sync Unit
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_UNIT_ENTITY_NAME + "GetAllUnit")
  Observable<List<Unit>> getAllUnit(@Path("dbName") String dbName,
                                    @Path("AppVersion") String appVersion,
                                    @Query("fpId") int fpid,
                                    @Query("key") String key);
  // endregion Sync Unit

  // region Sync SalesPrice
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_PRICE_ENTITY_NAME + "GetSalesPricePagesCount")
  Observable<ResponseBody> getSalesPricePagesCount(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("fpId") int fpid,
                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_PRICE_ENTITY_NAME + "GetSalesPriceByPageNumber")
  Observable<List<SalesPrice>> getSalesPriceByPageNumber(@Path("dbName") String dbName,
                                                         @Path("AppVersion") String appVersion,
                                                         @Query("pageNumber") int pageNumber,
                                                         @Query("fpId") int fpid,
                                                         @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_PRICE_ENTITY_NAME + "GetAllSalesPrice")
  Observable<List<SalesPrice>> getAllSalesPrice(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("key") String key);
  // endregion Sync SalesPrice

  // region Sync SalesDiscount
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_DISCOUNT_ENTITY_NAME + "GetSalesDiscountPagesCount")
  Observable<ResponseBody> getSalesDiscountPagesCount(@Path("dbName") String dbName,
                                                      @Path("AppVersion") String appVersion,
                                                      @Query("fpId") int fpid,
                                                      @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_DISCOUNT_ENTITY_NAME + "GetSalesDiscountByPageNumber")
  Observable<List<SalesDiscount>> getSalesDiscountByPageNumber(@Path("dbName") String dbName,
                                                               @Path("AppVersion") String appVersion,
                                                               @Query("pageNumber") int pageNumber,
                                                               @Query("fpId") int fpid,
                                                               @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_DISCOUNT_ENTITY_NAME + "GetAllSalesDiscount")
  Observable<List<SalesDiscount>> getAllSalesDiscount(@Path("dbName") String dbName,
                                                      @Path("AppVersion") String appVersion,
                                                      @Query("fpId") int fpid,
                                                      @Query("key") String key);
  // endregion Sync SalesDiscount

  // region Sync Account
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_ENTITY_NAME + "GetAccountPagesCount")
  Observable<ResponseBody> getAccountPagesCount(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_ENTITY_NAME + "GetAccountByPageNumber")
  Observable<List<Account>> getAccountByPageNumber(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("pageNumber") int pageNumber,
                                                   @Query("fpId") int fpid,
                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_ENTITY_NAME + "GetAllAccount")
  Observable<List<Account>> getAllAccount(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("fpId") int fpid,
                                          @Query("key") String key);
  // endregion Sync Account

  // region Sync AccVsDetail
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAccVsDetailPagesCount")
  Observable<ResponseBody> getAccVsDetailPagesCount(@Path("dbName") String dbName,
                                                    @Path("AppVersion") String appVersion,
                                                    @Query("fpId") int fpid,
                                                    @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAccVsDetailByPageNumber")
  Observable<List<AccVsDetail>> getAccVsDetailByPageNumber(@Path("dbName") String dbName,
                                                           @Path("AppVersion") String appVersion,
                                                           @Query("pageNumber") int pageNumber,
                                                           @Query("fpId") int fpid,
                                                           @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAllAccVsDetail")
  Observable<List<AccVsDetail>> getAllAccVsDetail(@Path("dbName") String dbName,
                                                  @Path("AppVersion") String appVersion,
                                                  @Query("fpId") int fpid,
                                                  @Query("key") String key);
  // endregion Sync AccVsDetail

  // region Sync AccVsCC
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAccVsCCPagesCount")
  Observable<ResponseBody> getAccVsCCPagesCount(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAccVsCCByPageNumber")
  Observable<List<AccVsCC>> getAccVsCCByPageNumber(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("pageNumber") int pageNumber,
                                                   @Query("fpId") int fpid,
                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAllAccVsCC")
  Observable<List<AccVsCC>> getAllAccVsCC(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("fpId") int fpid,
                                          @Query("key") String key);
  // endregion Sync AccVsCC

  // region Sync AccVsCC
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAccVsPrjPagesCount")
  Observable<ResponseBody> getAccVsPrjPagesCount(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("fpId") int fpid,
                                                 @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAccVsPrjByPageNumber")
  Observable<List<AccVsPrj>> getAccVsPrjByPageNumber(@Path("dbName") String dbName,
                                                     @Path("AppVersion") String appVersion,
                                                     @Query("pageNumber") int pageNumber,
                                                     @Query("fpId") int fpid,
                                                     @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCOUNT_VECTOR_ENTITY_NAME + "GetAllAccVsPrj")
  Observable<List<AccVsPrj>> getAllAccVsPrj(@Path("dbName") String dbName,
                                            @Path("AppVersion") String appVersion,
                                            @Query("fpId") int fpid,
                                            @Query("key") String key);
  // endregion Sync AccVsPrj

  // region Sync DetailAcc
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_DETAIL_ACC_ENTITY_NAME + "GetDetailAccPagesCount")
  Observable<ResponseBody> getDetailAccPagesCount(@Path("dbName") String dbName,
                                                  @Path("AppVersion") String appVersion,
                                                  @Query("fpId") int fpid,
                                                  @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_DETAIL_ACC_ENTITY_NAME + "GetDetailAccByPageNumber")
  Observable<List<DetailAcc>> getDetailAccByPageNumber(@Path("dbName") String dbName,
                                                       @Path("AppVersion") String appVersion,
                                                       @Query("pageNumber") int pageNumber,
                                                       @Query("fpId") int fpid,
                                                       @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_DETAIL_ACC_ENTITY_NAME + "GetAllDetailAcc")
  Observable<List<DetailAcc>> getAllDetailAcc(@Path("dbName") String dbName,
                                              @Path("AppVersion") String appVersion,
                                              @Query("fpId") int fpid,
                                              @Query("key") String key);
  // endregion Sync DetailAcc

  // region Sync CostCenter
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_COST_CENTER_ENTITY_NAME + "GetCostCenterPagesCount")
  Observable<ResponseBody> getCostCenterPagesCount(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("fpId") int fpid,
                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_COST_CENTER_ENTITY_NAME + "GetCostCenterByPageNumber")
  Observable<List<CostCenter>> getCostCenterByPageNumber(@Path("dbName") String dbName,
                                                         @Path("AppVersion") String appVersion,
                                                         @Query("pageNumber") int pageNumber,
                                                         @Query("fpId") int fpid,
                                                         @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_COST_CENTER_ENTITY_NAME + "GetAllCostCenter")
  Observable<List<CostCenter>> getAllCostCenter(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("key") String key);
  // endregion Sync CostCenter

  // region Sync Project
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PROJECT_ENTITY_NAME + "GetProjectPagesCount")
  Observable<ResponseBody> getProjectPagesCount(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PROJECT_ENTITY_NAME + "GetProjectByPageNumber")
  Observable<List<Project>> getProjectByPageNumber(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("pageNumber") int pageNumber,
                                                   @Query("fpId") int fpid,
                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PROJECT_ENTITY_NAME + "GetAllProject")
  Observable<List<Project>> getAllProject(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("fpId") int fpid,
                                          @Query("key") String key);
  // endregion Sync Project

  // region Sync Image
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_IMAGE_ENTITY_NAME + "GetAllImage")
  Observable<List<Image>> getAllImage(@Path("dbName") String dbName,
                                      @Path("AppVersion") String appVersion,
                                      @Query("sysId") int sysId,
                                      @Query("formId") int formId,
                                      @Query("fpId") int fpid,
                                      @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_IMAGE_ENTITY_NAME + "GetImagePagesCount")
  Observable<ResponseBody> getImagePagesCount(@Path("dbName") String dbName,
                                              @Path("AppVersion") String appVersion,
                                              @Query("sysId") int sysId,
                                              @Query("formId") int formId,
                                              @Query("fpId") int fpid,
                                              @Query("key") String key);

  @Streaming
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_IMAGE_ENTITY_NAME + "GetImageByPageNumber")
  Observable<List<Image>> getImageByPageNumber(@Path("dbName") String dbName,
                                               @Path("AppVersion") String appVersion,
                                               @Query("sysId") int sysId,
                                               @Query("formId") int formId,
                                               @Query("pageNumber") int pageNumber,
                                               @Query("fpId") int fpid,
                                               @Query("key") String key);

  @Streaming
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_IMAGE_ENTITY_NAME + "GetAllImagesById")
  Observable<ResponseBody> getImageById(@Path("dbName") String dbName,
                                        @Path("AppVersion") String appVersion,
                                        @Query("merchId") int merchId,
                                        @Query("sysId") int sysId,
                                        @Query("formId") int formId,
                                        @Query("fpId") int fpid,
                                        @Query("showAll") int showAll,
                                        @Query("key") String key);

  @Streaming
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_IMAGE_ENTITY_NAME + "GetImageIds")
  Observable<ResponseBody> GetImageIds(@Path("dbName") String dbName,
                                       @Path("AppVersion") String appVersion,
                                       @Query("merchId") int merchId,
                                       @Query("sysId") int sysId,
                                       @Query("formId") int formId,
                                       @Query("fpId") int fpid,
                                       @Query("key") String key);

  // endregion Sync Image

  // region Sync StockRoom
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_STOCK_ENTITY_NAME + "GetAllStockRoom")
  Observable<List<StockRoom>> getAllStockRoom(@Path("dbName") String dbName,
                                              @Path("AppVersion") String appVersion,
                                              @Query("fpId") int fpid,
                                              @Query("ugId") int ugId,
                                              @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_STOCK_ENTITY_NAME + "GetCountStockRoom")
  Observable<ResponseBody> getCountStockRoom(@Path("dbName") String dbName,
                                             @Path("AppVersion") String appVersion,
                                             @Query("fpId") int fpid,
                                             @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_STOCK_ENTITY_NAME + "GetEmptyStockRoom")
  Observable<List<StockRoom>> getEmptyStockRoom(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("key") String key);
  // endregion Sync StockRoom

  // region Sync MerchStock
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_STOCK_ENTITY_NAME + "GetAllMerchStock")
  Observable<List<MerchStock>> getAllMerchStock(@Path("dbName") String dbName,
                                                @Path("AppVersion") String appVersion,
                                                @Query("fpId") int fpid,
                                                @Query("ugId") int ugId,
                                                @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_STOCK_ENTITY_NAME + "GetMerchStockPagesCount")
  Observable<ResponseBody> getMerchStockPagesCount(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("fpId") int fpid,
                                                   @Query("ugId") int UgId,
                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_STOCK_ENTITY_NAME + "GetMerchStockByPageNumber")
  Observable<List<MerchStock>> getMerchStockByPageNumber(@Path("dbName") String dbName,
                                                         @Path("AppVersion") String appVersion,
                                                         @Query("pageNumber") int pageNumber,
                                                         @Query("fpId") int fpid,
                                                         @Query("ugId") int ugId,
                                                         @Query("key") String key);
  // endregion Sync MerchStock

  // region Sync MerchTax
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_ENTITY_NAME + "GetAllMerchTax")
  Observable<List<MerchTax>> getAllMerchTax(@Path("dbName") String dbName,
                                            @Path("AppVersion") String appVersion,
                                            @Query("fpId") int fpid,
                                            @Query("ugId") int ugId,
                                            @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_ENTITY_NAME + "GetMerchTaxPagesCount")
  Observable<ResponseBody> getMerchTaxPagesCount(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("fpId") int fpid,
                                                 @Query("ugId") int UgId,
                                                 @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_ENTITY_NAME + "GetMerchTaxByPageNumber")
  Observable<List<MerchTax>> getMerchTaxByPageNumber(@Path("dbName") String dbName,
                                                     @Path("AppVersion") String appVersion,
                                                     @Query("pageNumber") int pageNumber,
                                                     @Query("fpId") int fpid,
                                                     @Query("ugId") int ugId,
                                                     @Query("key") String key);
  // endregion Sync MerchTax

  // region Sync AccSPAcc
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCSPACC_ENTITY_NAME + "GetAllAccSpAcc")
  Observable<List<AccSpAcc>> getAllAccSpAcc(@Path("dbName") String dbName,
                                            @Path("AppVersion") String appVersion,
                                            @Query("fpId") int fpid,
                                            @Query("key") String key);
  // endregion Sync AccSPAcc

  // region Sync Cabinet
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CABINET_ENTITY_NAME + "GetAllCabinet")
  Observable<List<Cabinet>> getAllCabinet(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("fpId") int fpid,
                                          @Query("ugId") int ugId,
                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CABINET_ENTITY_NAME + "GetCountCabinet")
  Observable<ResponseBody> getCountCabinet(@Path("dbName") String dbName,
                                           @Path("AppVersion") String appVersion,
                                           @Query("fpId") int fpid,
                                           @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CABINET_ENTITY_NAME + "GetEmptyCabinet")
  Observable<List<Cabinet>> getEmptyCabinet(@Path("dbName") String dbName,
                                            @Path("AppVersion") String appVersion,
                                            @Query("fpId") int fpid,
                                            @Query("key") String key);
  // endregion Sync Cabinet

  // region Sync Option
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MANAGE_APPLICATION_SETTINGS_ENTITY_NAME + "GetAllOptions")
  Observable<List<Option>> getAllOptions(@Path("dbName") String dbName,
                                         @Path("AppVersion") String appVersion,
                                         @Query("userId") int userId,
                                         @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_ACCESS_RIGHT_ENTITY_NAME + "GetAllRights")
  Observable<List<Rights>> getAllRights(@Path("dbName") String dbName,
                                        @Path("AppVersion") String appVersion,
                                        @Query("UgId") int ugId,
                                        @Query("key") String key);
  // endregion Sync Option

  // region Sync Broker

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_BROKER_ENTITY_NAME + "GetAllBroker")
  Observable<List<Broker>> getAllBroker(@Path("dbName") String dbName,
                                        @Path("AppVersion") String appVersion,
                                        @Query("fpId") int fpid,
                                        @Query("key") String key);

  // endregion Sync Broker

  // endregion Sync Table

  // region SalesOrder Operation

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "InsertSO")
  Observable<ResponseBody> insertSO(@Path("dbName") String dbName,
                                    @Path("AppVersion") String appVersion,
                                    @Body SalesOrder salesOrder,
                                    @Query("SysDBName") String sysDbName,
                                    @Query("WSId") int wsid,
                                    @Query("SubSystem") int subSystem,
                                    @Query("FormId") int formId,
                                    @Query("EDesc") String eventDesc,
                                    @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "InsertSOA")
  Observable<ResponseBody> insertSOA(@Path("dbName") String dbName,
                                     @Path("AppVersion") String appVersion,
                                     @Body SOArticle soArticle,
                                     @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "GetLatestSO")
  Observable<ResponseBody> getLatestSO(@Path("dbName") String dbName,
                                       @Path("AppVersion") String appVersion,
                                       @Query("fpId") int fpid,
                                       @Query("userid") int userid,
                                       @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "GetPreviousSO")
  Observable<List<SalesOrder>> getPreviousSO(@Path("dbName") String dbName,
                                             @Path("AppVersion") String appVersion,
                                             @Query("fpId") int fpid,
                                             @Query("userid") int userid,
                                             @Query("lastMonth") int lastMonth,
                                             @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "GetPreviousSOArticle")
  Observable<List<SOArticle>> getPreviousSOArticle(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("fpId") int fpid,
                                                   @Query("userid") int userid,
                                                   @Query("lastMonth") int lastMonth,
                                                   @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "GetValidationSalesOrderResult")
  Observable<ResponseBody> getValidationSalesOrderResult(@Path("dbName") String dbName,
                                                         @Path("AppVersion") String appVersion,
                                                         @Body SalesOrder salesOrder,
                                                         @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "GetValidationSOArticleResult")
  Observable<ResponseBody> getValidationSOArticleResult(@Path("dbName") String dbName,
                                                        @Path("AppVersion") String appVersion,
                                                        @Body SOArticle soArticle,
                                                        @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "DeleteSalesOrder")
  Observable<ResponseBody> deleteSalesOrder(@Path("dbName") String dbName,
                                            @Path("AppVersion") String appVersion,
                                            @Query("soId") int soid,
                                            @Query("fpId") int fpid,
                                            @Query("userId") int userid,
                                            @Query("SysDBName") String sysDbName,
                                            @Query("WSId") int wsid,
                                            @Query("SubSystem") int subSystem,
                                            @Query("FormId") int formId,
                                            @Query("EDesc") String eventDesc,
                                            @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "InsertSOStatus")
  Observable<ResponseBody> insertSOStatus(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Body SOStatus soStatus,
                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_SALES_ORDER_ENTITY_NAME + "GetPreviousSOStatus")
  Observable<List<SOStatus>> getPreviousSOStatus(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("fpId") int fpid,
                                                 @Query("userid") int userid,
                                                 @Query("lastMonth") int lastMonth,
                                                 @Query("key") String key);

  // endregion SalesOrder Operation

  // region PreFactor Operation

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "InsertSPFactor")
  Observable<ResponseBody> insertSPFactor(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Body SPFactor spFactor,
                                          @Query("SysDBName") String sysDbName,
                                          @Query("WSId") int wsid,
                                          @Query("SubSystem") int subSystem,
                                          @Query("FormId") int formId,
                                          @Query("EDesc") String eventDesc,
                                          @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "InsertSPArticle")
  Observable<ResponseBody> insertSPArticle(@Path("dbName") String dbName,
                                           @Path("AppVersion") String appVersion,
                                           @Body SPArticle spArticle,
                                           @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "InsertSPTax")
  Observable<ResponseBody> insertSPTax(@Path("dbName") String dbName,
                                       @Path("AppVersion") String appVersion,
                                       @Body SPTax spTax,
                                       @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "GetLatestSPFactor")
  Observable<ResponseBody> getLatestSPFactor(@Path("dbName") String dbName,
                                             @Path("AppVersion") String appVersion,
                                             @Query("factorType") int factorType,
                                             @Query("fpId") int fpid,
                                             @Query("userId") int userid,
                                             @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "GetPreviousSP")
  Observable<List<SPFactor>> getPreviousSP(@Path("dbName") String dbName,
                                           @Path("AppVersion") String appVersion,
                                           @Query("factorType") int factorType,
                                           @Query("fpId") int fpid,
                                           @Query("userId") int userid,
                                           @Query("lastMonth") int lastMonth,
                                           @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "GetPreviousSPArticle")
  Observable<List<SPArticle>> getPreviousSPArticle(@Path("dbName") String dbName,
                                                   @Path("AppVersion") String appVersion,
                                                   @Query("factorType") int factorType,
                                                   @Query("fpId") int fpid,
                                                   @Query("userId") int userid,
                                                   @Query("lastMonth") int lastMonth,
                                                   @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "GetValidationSPFactorResult")
  Observable<ResponseBody> getValidationSPFactorResult(@Path("dbName") String dbName,
                                                       @Path("AppVersion") String appVersion,
                                                       @Body SPFactor spFactor,
                                                       @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "GetValidationSPArticleResult")
  Observable<ResponseBody> getValidationSPArticleResult(@Path("dbName") String dbName,
                                                        @Path("AppVersion") String appVersion,
                                                        @Body SPArticle spArticle,
                                                        @Query("sDate") String sDate,
                                                        @Query("eDate") String eDate,
                                                        @Query("key") String key);


  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "DeleteSPFactor")
  Observable<ResponseBody> deleteSPFactor(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Query("spId") int spid,
                                          @Query("factorType") int factorType,
                                          @Query("fpId") int fpid,
                                          @Query("userId") int userid,
                                          @Query("SysDBName") String sysDbName,
                                          @Query("WSId") int wsid,
                                          @Query("SubSystem") int subSystem,
                                          @Query("FormId") int formId,
                                          @Query("EDesc") String eventDesc,
                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "UpdateDiscountSPFactor")
  Observable<ResponseBody> updateDiscountSPFactor(@Path("dbName") String dbName,
                                                  @Path("AppVersion") String appVersion,
                                                  @Query("SPId") int spid,
                                                  @Query("FPId") int fpid,
                                                  @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "InsertSPStatus")
  Observable<ResponseBody> insertSPStatus(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Body SPStatus spStatus,
                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_PRE_FACTOR_ENTITY_NAME + "GetPreviousSPStatus")
  Observable<List<SPStatus>> getPreviousSPStatus(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("factorType") int factorType,
                                                 @Query("fpId") int fpid,
                                                 @Query("userid") int userid,
                                                 @Query("lastMonth") int lastMonth,
                                                 @Query("key") String key);

  // endregion PreFactor Operation

  // region Table Status (SP & SO)
  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_TABLE_STATUS_ENTITY_NAME + "RebuildTableStatus")
  Observable<ResponseBody> rebuildTableStatus(@Path("dbName") String dbName,
                                              @Path("AppVersion") String appVersion,
                                              @Query("fpId") int fpid,
                                              @Query("userId") int userid,
                                              @Query("key") String key);
  // endregion Table Status (SP & SO)

  // region Merchandise Operation

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_MERCHANDISE_INVENTORY_ENTITY_NAME + "CalculateInventory")
  Observable<ResponseBody> getMerchInventory(@Path("dbName") String dbName,
                                             @Path("AppVersion") String appVersion,
                                             @Query("merchId") int merchId,
                                             @Query("stockId") int stockId,
                                             @Query("cabinetId") int cabinetId,
                                             @Query("startDate") String startDate,
                                             @Query("endDate") String endDate,
                                             @Query("committed") int committed,
                                             @Query("fpId") int fpid,
                                             @Query("key") String key);

  // endregion Merchandise Operation

  // region Customer Operation

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetCustomerCredit")
  Observable<ResponseBody> getCustomerCredit(@Path("dbName") String dbName,
                                             @Path("AppVersion") String appVersion,
                                             @Query("accId") String accId,
                                             @Query("faccId") int faccId,
                                             @Query("ccId") int ccId,
                                             @Query("prjId") int prjId,
                                             @Query("customerId") int customerId,
                                             @Query("date") String startDate,
                                             @Query("fpId") int fpid,
                                             @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetValidationCustomerResult")
  Observable<ResponseBody> getValidationCustomerResult(@Path("dbName") String dbName,
                                                       @Path("AppVersion") String appVersion,
                                                       @Body Customer customer,
                                                       @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "InsertCustomer")
  Observable<ResponseBody> insertCustomer(@Path("dbName") String dbName,
                                          @Path("AppVersion") String appVersion,
                                          @Body Customer customer,
                                          @Query("SysDBName") String sysDbName,
                                          @Query("WSId") int wsid,
                                          @Query("SubSystem") int subSystem,
                                          @Query("FormId") int formId,
                                          @Query("EDesc") String eventDesc,
                                          @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "getLastPostedCustomerInfo")
  Observable<ResponseBody> getLastPostedCustomerInfo(@Path("dbName") String dbName,
                                                     @Path("AppVersion") String appVersion,
                                                     @Query("CustomerName") String customerName,
                                                     @Query("UserId") int userId,
                                                     @Query("FPId") int fpId,
                                                     @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetPostedCustomersInfo")
  Observable<List<PostedCustomerInfo>> getPostedCustomersInfo(@Path("dbName") String dbName,
                                                              @Path("AppVersion") String appVersion,
                                                              @Query("UserId") int userId,
                                                              @Query("FPId") int fpId,
                                                              @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "DeletePostedCustomerInfo")
  Observable<ResponseBody> deletePostedCustomerInfo(@Path("dbName") String dbName,
                                                    @Path("AppVersion") String appVersion,
                                                    @Query("Id") int id,
                                                    @Query("Name") String customerName,
                                                    @Query("UserId") int userId,
                                                    @Query("FPId") int fpId,
                                                    @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "ControlStatusCustomer")
  Observable<ResponseBody> controlStatusCustomer(@Path("dbName") String dbName,
                                                 @Path("AppVersion") String appVersion,
                                                 @Query("Id") int id,
                                                 @Query("Name") String customerName,
                                                 @Query("FPId") int fpId,
                                                 @Query("key") String key);


  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "AgainApproveRequestForPostedCustomer")
  Observable<ResponseBody> againApproveRequestForPostedCustomer(@Path("dbName") String dbName,
                                                                @Path("AppVersion") String appVersion,
                                                                @Query("Id") int id,
                                                                @Query("Name") String customerName,
                                                                @Query("NumberOfRequest") int NumberOfRequest,
                                                                @Query("UserId") int userId,
                                                                @Query("FPId") int fpId,
                                                                @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetCountOfPostedCustomerInfo")
  Observable<ResponseBody> getCountOfPostedCustomerInfo(@Path("dbName") String dbName,
                                                        @Path("AppVersion") String appVersion,
                                                        @Query("UserId") int userId,
                                                        @Query("FPId") int fpId,
                                                        @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "CountOfRowsThatNeedSync")
  Observable<ResponseBody> getCountOfRowsThatNeedSync(@Path("dbName") String dbName,
                                                      @Path("AppVersion") String appVersion,
                                                      @Query("UserId") int userId,
                                                      @Query("FPId") int fpId,
                                                      @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "RowsThatNeedSync")
  Observable<List<PostedCustomerInfo>> getRowsThatNeedSync(@Path("dbName") String dbName,
                                                           @Path("AppVersion") String appVersion,
                                                           @Query("UserId") int userId,
                                                           @Query("FPId") int fpId,
                                                           @Query("key") String key);

  @POST("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "UpdateRowsThatNeedSync")
  Observable<ResponseBody> updateRowsThatNeedSync(@Path("dbName") String dbName,
                                                  @Path("AppVersion") String appVersion,
                                                  @Query("Status") int status,
                                                  @Query("UserId") int userId,
                                                  @Query("FPId") int fpId,
                                                  @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetCustomerAndUserPagesCount")
  Observable<ResponseBody> getCustomerAndUserPagesCount(@Path("dbName") String dbName,
                                                        @Path("AppVersion") String appVersion,
                                                        @Query("FPId") int fpid,
                                                        @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetCustomerAndUserByPageNumber")
  Observable<List<CustomerAndUser>> getCustomerAndUserByPageNumber(@Path("dbName") String dbName,
                                                                   @Path("AppVersion") String appVersion,
                                                                   @Query("PageNumber") int pageNumber,
                                                                   @Query("FPId") int fpid,
                                                                   @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetAllCustomerAndUser")
  Observable<List<CustomerAndUser>> getAllCustomerAndUser(@Path("dbName") String dbName,
                                                          @Path("AppVersion") String appVersion,
                                                          @Query("FPId") int fpid,
                                                          @Query("key") String key);

  @GET("{dbName}/" + "{AppVersion}/" + Webservice.DIR_CUSTOMER_ENTITY_NAME + "GetAccVectorBalance")
  Observable<ResponseBody> getAccVectorBalance(@Path("dbName") String dbName,
                                               @Path("AppVersion") String appVersion,
                                               @Query("accId") String accId,
                                               @Query("faccId") int faccId,
                                               @Query("ccId") int ccId,
                                               @Query("prjId") int prjId,
                                               @Query("customerId") int customerId,
                                               @Query("date") String startDate,
                                               @Query("fpId") int fpid,
                                               @Query("key") String key);

  @GET
  Observable<ResponseBody> getCustomerInfoCreated(@Url String Url);

  // endregion Customer Operation

  // region Print Preview
  @POST
  Observable<ResponseBody> getPrintPreview(@Url String printPreviewUrl,
                                           @Body PrintPreview printPreview);
  // endregion Print Preview

}