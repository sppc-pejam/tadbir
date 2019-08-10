package com.sppcco.core.data.remote.service;


public final class Webservice {

  // region Base Address
  public static final String FAKE_STRING_URL = "http://www.google.com/";
  //private static final String SERVICE_VERSION = "/V3.0/";
  //public static final String APP_VERSION = VERSION.replace("/","");
  // endregion Base Address

  //region Business Service
  // Webservice .dll

  public static final String BUSINESS_ENTITY = "AppEntity";
  //public static final String BUSINESS_SERVICE_NAME = "V3.0";
  //public static final String BUSINESS_GROUP_NAME = "AppGroup";
  //public static final String BUSINESS_ENTITY_NAME = "AppEntity";
  /*public static final String BUSINESS_IMAGE_ID_FUNC = "getImageByImageId";
  public static final String BUSINESS_PRINT_PREVIEW_ID_FUNC = "getPrintPreview";
  public static final String BUSINESS_CUSTOMER_INFO_CREATED_ID_FUNC = "getCustomerInfo";*/

  //endregion Business Service
  // Image

  //public static final String BUSINESS_IMAGE_ENTITY = "ImageEntity";
  public static final String BUSINESS_IMAGE_ID_FUNC = "getImageByImageId";

  // PrintPreview
  //public static final String BUSINESS_PRINT_PREVIEW_ENTITY = "PrintEntity";
  public static final String BUSINESS_PRINT_PREVIEW_ID_FUNC = "getPrintPreview";

  // CustomerInfoCreated
  //public static final String BUSINESS_CUSTOMER_INFO_CREATED_ENTITY = "CustomerEntity";
  public static final String BUSINESS_CUSTOMER_INFO_CREATED_ID_FUNC = "getCustomerInfo";

  // Image .dll
  public static final String DIR_IMAGE_DLL_GROUP_NAME = "Inventory";
  public static final String DIR_IMAGE_DLL_ENTITY_NAME = "Image";
  public static final String DIR_IMAGE_DLL_FUNCTION_NAME = "getImageByImageId";

  //endregion Business Service

  // WEBService
  //private static final String SERVER_WEBSERVICE_NAME = "Webservice/";
  //private static final String SERVICE_WEBSERVICE_NAME = SERVER_WEBSERVICE_NAME + "serviceWeb/";
  //private static final String DIR_WEBSERVICE_GROUP_NAME = SERVICE_WEBSERVICE_NAME + "WebserviceGroup/";
  //public static final String DIR_WEBSERVICE_ENTITY_NAME = DIR_WEBSERVICE_GROUP_NAME + "WebserviceDataConnection/";

  //region Database Service

  // region Inventory
  private static final String DIR_INVENTORY_GROUP_NAME = "Inventory/";
  static final String DIR_CUSTOMER_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Customer/";
  static final String DIR_STOCK_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Stock/";
  static final String DIR_CABINET_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Cabinet/";
  public static final String DIR_IMAGE_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Image/";
  static final String DIR_UNIT_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Unit/";
  static final String DIR_SALES_PRICE_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "SalesPrice/";
  static final String DIR_SALES_DISCOUNT_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "SalesDiscount/";
  static final String DIR_MERCHANDISE_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Merchandise/";
  static final String DIR_MERCHANDISE_INVENTORY_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "MerchandiseInventory/";
  static final String DIR_BROKER_ENTITY_NAME = DIR_INVENTORY_GROUP_NAME + "Broker/";

  // endregion Inventory

  // region SalesPurchase
  private static final String DIR_SALES_PURCHASE_GROUP_NAME = "SalesPurchase/";
  static final String DIR_PRE_FACTOR_ENTITY_NAME = DIR_SALES_PURCHASE_GROUP_NAME + "PreFactor/";
  static final String DIR_SALES_ORDER_ENTITY_NAME = DIR_SALES_PURCHASE_GROUP_NAME + "SalesOrder/";
  static final String DIR_TABLE_STATUS_ENTITY_NAME = DIR_SALES_PURCHASE_GROUP_NAME + "TableStatus/";
  // endregion SalesPurchase

  // region Account
  private static final String DIR_ACCOUNT_GROUP_NAME = "Account/";
  static final String DIR_ACCOUNT_VECTOR_ENTITY_NAME = DIR_ACCOUNT_GROUP_NAME + "AccountVector/";
  static final String DIR_ACCOUNT_ENTITY_NAME = DIR_ACCOUNT_GROUP_NAME + "Account/";
  static final String DIR_PROJECT_ENTITY_NAME = DIR_ACCOUNT_GROUP_NAME + "Project/";
  static final String DIR_DETAIL_ACC_ENTITY_NAME = DIR_ACCOUNT_GROUP_NAME + "DetailAcc/";
  static final String DIR_COST_CENTER_ENTITY_NAME = DIR_ACCOUNT_GROUP_NAME + "CostCenter/";
  static final String DIR_ACCSPACC_ENTITY_NAME = DIR_ACCOUNT_GROUP_NAME + "AccSpAcc/";
  // endregion Account

  // region User Account
  private static final String DIR_APP_ACCOUNT_GROUP_NAME = "AppAccount/";
  static final String DIR_APP_LOGIN_ENTITY_NAME = DIR_APP_ACCOUNT_GROUP_NAME + "AppLogin/";
  static final String DIR_USER_LOGIN_ENTITY_NAME = DIR_APP_ACCOUNT_GROUP_NAME + "UserLogin/";
  static final String DIR_USER_ACCESS_ENTITY_NAME = DIR_APP_ACCOUNT_GROUP_NAME + "UserAccess/";
  static final String DIR_FPID_ENTITY_NAME = DIR_APP_ACCOUNT_GROUP_NAME + "FiscalPeriod/";
  // endregion User Account

  // region Utilities
  private static final String DIR_UTILITIES_GROUP_NAME = "Utilities/";
  static final String DIR_MANAGE_APPLICATION_SETTINGS_ENTITY_NAME = DIR_UTILITIES_GROUP_NAME + "ManageApplicationSettings/";
  static final String DIR_ACCESS_RIGHT_ENTITY_NAME = DIR_UTILITIES_GROUP_NAME + "AccessRight/";
  static final String DIR_VALIDATION_SYNC_TABLE_NAME = DIR_UTILITIES_GROUP_NAME + "ValidationSync/";
  // endregion Utilities

  //endregion Database Service
}
