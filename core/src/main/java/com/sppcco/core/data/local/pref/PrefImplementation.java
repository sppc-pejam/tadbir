
package com.sppcco.core.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.sppcco.core.util.app.CoreConstants;

import androidx.annotation.NonNull;


public class PrefImplementation implements IPrefContract {

  private SharedPreferences mSharedPreferences;


  public final long NULL_LONG_INDEX = -1L;
  private final int NULL_INT_INDEX = 0;
  private final String NULL_STRING_INDEX = "";

  private final String PREF_KEY_FPNAME = "PREF_KEY_FPNAME";
  private final String PREF_KEY_SDATE = "PREF_KEY_SDATE";
  private final String PREF_KEY_EDATE = "PREF_KEY_EDATE";
  private final String PREF_KEY_FPID = "PREF_KEY_FPID";
  private final String PREF_KEY_WSID = "PREF_KEY_WSID";
  private final String PREF_KEY_COMPANY = "PREF_KEY_COMPANY";
  private final String PREF_KEY_DATABASE_NAME = "PREF_KEY_DATABASE_NAME";
  private final String PREF_KEY_BASE_URL = "PREF_KEY_BASE_URL";
  private final String PREF_KEY_IP_ADDRESS = "PREF_KEY_IP_ADDRESS";
  private final String PREF_KEY_PORT_NUMBER = "PREF_KEY_PORT_NUMBER";
  private final String PREF_KEY_WEBSERVICE_PORT_NUMBER = "PREF_KEY_WEBSERVICE_PORT_NUMBER";
  private final String PREF_KEY_KEY = "PREF_KEY_KEY";
  private final String PREF_KEY_LOGGED = "PREF_KEY_LOGGED";

  private final String PREF_KEY_FIRST_ENTRY_MENU = "PREF_KEY_FIRST_ENTRY_MENU";
  private final String PREF_KEY_FIRST_ENTRY_SO_ACTIVITY = "PREF_KEY_FIRST_ENTRY_SO_ACTIVITY";
  private final String PREF_KEY_FIRST_ENTRY_SO_ARTICLE_ACTIVITY = "PREF_KEY_FIRST_ENTRY_SO_ARTICLE_ACTIVITY";

  private final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
  private final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
  private final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
  private final String PREF_KEY_CURRENT_PASSWORD = "PREF_KEY_CURRENT_PASSWORD";
  private final String PREF_KEY_CURRENT_GROUP_ID = "PREF_KEY_CURRENT_GROUP_ID";
  private final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
  private final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
    = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
  private final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

  private final String PREF_KEY_SHOW_IMAGES = "PREF_KEY_SHOW_IMAGES";
  private final String PREF_KEY_FIRST_SYNC = "PREF_KEY_FIRST_SYNC";
  private final String PREF_EMPTY_STATUS_SYNC_SO = "PREF_EMPTY_STATUS_SYNC_SO";
  private final String PREF_CAN_SYNC_SO = "PREF_CAN_SYNC_SO";
  private final String PREF_EMPTY_STATUS_SYNC_PREFACTOR = "PREF_EMPTY_STATUS_SYNC_PREFACTOR";
  private final String PREF_CAN_SYNC_PREFACTOR = "PREF_CAN_SYNC_PREFACTOR";
  private final String PREF_TEMP_INSERTED_SPID = "PREF_TEMP_INSERTED_SPID";
  private final String PREF_TEMP_INSERTED_SOID = "PREF_TEMP_INSERTED_SOID";
  private final String PREF_LATEST_INSERTED_STOCKID = "PREF_LATEST_INSERTED_STOCKID";
  private final String PREF_LATEST_INSERTED_CABINETID = "PREF_LATEST_INSERTED_CABINETID";

  private final String PREF_LAST_DATE_SYNC_TABLES = "PREF_LAST_DATE_SYNC_TABLES";
  private final String PREF_LAST_DATE_SYNC_IMAGES = "PREF_LAST_DATE_SYNC_IMAGES";

  private final String PREF_KEY_IS_MERCH_STOCK = "PREF_KEY_IS_MERCH_STOCK";
  private final String PREF_KEY_CHECK_UPDATE = "PREF_KEY_CHECK_UPDATE";

  private final String PREF_KEY_EXIST_MERCH_STOCK = "PREF_KEY_EXIST_MERCH_STOCK";
  private final String PREF_KEY_EXIST_STOCK_ACCESS = "PREF_KEY_EXIST_STOCK_ACCESS";
  private final String PREF_KEY_EXIST_MERCH_IN_MERCH_STOCK = "PREF_KEY_EXIST_MERCH_IN_MERCH_STOCK";
  private final String PREF_KEY_EXIST_STOCK = "PREF_KEY_EXIST_STOCK";


  public PrefImplementation(Context context, String prefFileName) {
    setSharedPreferences(context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE));
  }


  private SharedPreferences getSharedPreferences() {
    return mSharedPreferences;
  }

  private void setSharedPreferences(SharedPreferences sharedPreferences) {
    this.mSharedPreferences = sharedPreferences;
  }


  @Override
  public String getFPName() {
    String companyName = getSharedPreferences().getString(PREF_KEY_FPNAME, NULL_STRING_INDEX);
    return companyName == NULL_STRING_INDEX ? null : companyName;
  }

  @Override
  public void setFPName(String fpName) {
    String name = fpName == null ? NULL_STRING_INDEX : fpName;
    getSharedPreferences().edit().putString(PREF_KEY_FPNAME, name).apply();
  }


  @Override
  public int getFPId() {
    int nId = getSharedPreferences().getInt(PREF_KEY_FPID, NULL_INT_INDEX);
    return nId == NULL_INT_INDEX ? 0 : nId;
  }

  @Override
  public void setFPId(int fpId) {
    int nId = fpId == 0 ? NULL_INT_INDEX : fpId;
    getSharedPreferences().edit().putInt(PREF_KEY_FPID, nId).apply();
  }

  @Override
  public int getWSId() {
    int nId = getSharedPreferences().getInt(PREF_KEY_WSID, NULL_INT_INDEX);
    return nId == NULL_INT_INDEX ? 0 : nId;
  }

  @Override
  public void setWSId(int wsId) {
    int nId = wsId == 0 ? NULL_INT_INDEX : wsId;
    getSharedPreferences().edit().putInt(PREF_KEY_WSID, nId).apply();
  }

  @Override
  public String getSDate() {
    String sDate = getSharedPreferences().getString(PREF_KEY_SDATE, NULL_STRING_INDEX);
    return sDate == NULL_STRING_INDEX ? null : sDate;
  }

  @Override
  public void setSDate(String sDate) {
    String date = sDate == null ? NULL_STRING_INDEX : sDate;
    getSharedPreferences().edit().putString(PREF_KEY_SDATE, date).apply();
  }

  @Override
  public String getEDate() {
    String eDate = getSharedPreferences().getString(PREF_KEY_EDATE, NULL_STRING_INDEX);
    return eDate == NULL_STRING_INDEX ? null : eDate;
  }

  @Override
  public void setEDate(String eDate) {
    String date = eDate == null ? NULL_STRING_INDEX : eDate;
    getSharedPreferences().edit().putString(PREF_KEY_EDATE, date).apply();
  }

  @Override
  public String getCompanyName() {
    String companyName = getSharedPreferences().getString(PREF_KEY_COMPANY, NULL_STRING_INDEX);
    return companyName == NULL_STRING_INDEX ? null : companyName;
  }

  @Override
  public void setCompanyName(String companyName) {
    String name = companyName == null ? NULL_STRING_INDEX : companyName;
    getSharedPreferences().edit().putString(PREF_KEY_COMPANY, name).apply();
  }

  @Override
  public String getDatabaseName() {
    String dataBaseName = getSharedPreferences().getString(PREF_KEY_DATABASE_NAME, NULL_STRING_INDEX);
    return dataBaseName == NULL_STRING_INDEX ? null : dataBaseName;
  }

  @Override
  public void setDatabaseName(String databaseName) {
    String name = databaseName == null ? NULL_STRING_INDEX : databaseName;
    getSharedPreferences().edit().putString(PREF_KEY_DATABASE_NAME, name).apply();
  }

  @Override
  public String getBaseUrl() {

    String strBaseUrl = getSharedPreferences().getString(PREF_KEY_BASE_URL, CoreConstants.FAKE_URL);
    return (strBaseUrl == NULL_STRING_INDEX) ? CoreConstants.FAKE_URL : strBaseUrl;
  }

  @Override
  public void setBaseUrl(@NonNull String baseUrl) {
    getSharedPreferences().edit().putString(PREF_KEY_BASE_URL, baseUrl).apply();
  }

  @Override
  public String getIpAddress() {
    String ipAddress = getSharedPreferences().getString(PREF_KEY_IP_ADDRESS, NULL_STRING_INDEX);
    return ipAddress == NULL_STRING_INDEX ? null : ipAddress;
  }

  @Override
  public void setIpAddress(String ipAddress) {
    String ip = ipAddress == null ? NULL_STRING_INDEX : ipAddress;
    getSharedPreferences().edit().putString(PREF_KEY_IP_ADDRESS, ip).apply();
  }

  @Override
  public String getPortNumber() {
    String portNumber = getSharedPreferences().getString(PREF_KEY_PORT_NUMBER, NULL_STRING_INDEX);
    return portNumber == NULL_STRING_INDEX ? null : portNumber;
  }

  @Override
  public void setPortNumber(String portNumber) {
    String port = portNumber == null ? NULL_STRING_INDEX : portNumber;
    getSharedPreferences().edit().putString(PREF_KEY_PORT_NUMBER, port).apply();
  }

  @Override
  public String getWebservicePortNumber() {
    String portNumber = getSharedPreferences().getString(PREF_KEY_WEBSERVICE_PORT_NUMBER, NULL_STRING_INDEX);
    return portNumber == NULL_STRING_INDEX ? null : portNumber;
  }

  @Override
  public void setWebservicePortNumber(String portNumber) {
    String port = portNumber == null ? NULL_STRING_INDEX : portNumber;
    getSharedPreferences().edit().putString(PREF_KEY_WEBSERVICE_PORT_NUMBER, port).apply();
  }

  @Override
  public String getKey() {
    String key = getSharedPreferences().getString(PREF_KEY_KEY, NULL_STRING_INDEX);
    return key == NULL_STRING_INDEX ? null : key;
  }

  @Override
  public void setKey(String key) {
    String serviceId = key == null ? NULL_STRING_INDEX : key;
    getSharedPreferences().edit().putString(PREF_KEY_KEY, serviceId).apply();
  }

  @Override
  public int getCurrentUserId() {
    int userId = getSharedPreferences().getInt(PREF_KEY_CURRENT_USER_ID, NULL_INT_INDEX);
    return userId == NULL_INT_INDEX ? 0 : userId;
  }

  @Override
  public void setCurrentUserId(int userId) {
    int id = userId == 0 ? NULL_INT_INDEX : userId;
    getSharedPreferences().edit().putInt(PREF_KEY_CURRENT_USER_ID, id).apply();
  }

  @Override
  public String getCurrentUserName() {
    return getSharedPreferences().getString(PREF_KEY_CURRENT_USER_NAME, null);
  }

  @Override
  public void setCurrentUserName(String userName) {
    getSharedPreferences().edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
  }

  @Override
  public String getCurrentPassword() {
    return getSharedPreferences().getString(PREF_KEY_CURRENT_PASSWORD, null);
  }

  @Override
  public void setCurrentPassword(String password) {
    String pass = password == null ? NULL_STRING_INDEX : password;
    getSharedPreferences().edit().putString(PREF_KEY_CURRENT_PASSWORD, password).apply();
  }

  @Override
  public int getCurrentGroupId() {
    int groupId = getSharedPreferences().getInt(PREF_KEY_CURRENT_GROUP_ID, NULL_INT_INDEX);
    return groupId == NULL_INT_INDEX ? 0 : groupId;
  }

  @Override
  public void setCurrentGroupId(int groupId) {
    int id = groupId == 0 ? NULL_INT_INDEX : groupId;
    getSharedPreferences().edit().putInt(PREF_KEY_CURRENT_GROUP_ID, id).apply();
  }

  @Override
  public String getCurrentUserEmail() {
    return getSharedPreferences().getString(PREF_KEY_CURRENT_USER_EMAIL, null);
  }

  @Override
  public void setCurrentUserEmail(String email) {
    getSharedPreferences().edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
  }

  @Override
  public String getCurrentUserProfilePicUrl() {
    return getSharedPreferences().getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
  }

  @Override
  public void setCurrentUserProfilePicUrl(String profilePicUrl) {
    getSharedPreferences().edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
  }

  @Override
  public String getAccessToken() {
    return getSharedPreferences().getString(PREF_KEY_ACCESS_TOKEN, null);
  }

  @Override
  public void setAccessToken(String accessToken) {
    getSharedPreferences().edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
  }


  @Override
  public boolean getFirstEntryMenuStatus() {
    return getSharedPreferences().getBoolean(PREF_KEY_FIRST_ENTRY_MENU, false);
  }

  @Override
  public void setFirstEntryMenuStatus(boolean firstEntryMenuStatus) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_FIRST_ENTRY_MENU, firstEntryMenuStatus).apply();
  }

  @Override
  public boolean getFirstEntrySOActivityStatus() {
    return getSharedPreferences().getBoolean(PREF_KEY_FIRST_ENTRY_SO_ACTIVITY, false);
  }

  @Override
  public void setFirstEntrySOActivityStatus(boolean firstEntrySOActivityStatus) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_FIRST_ENTRY_SO_ACTIVITY, firstEntrySOActivityStatus).apply();
  }

  @Override
  public boolean getFirstEntrySOArticleActivityStatus() {
    return getSharedPreferences().getBoolean(PREF_KEY_FIRST_ENTRY_SO_ARTICLE_ACTIVITY, false);
  }

  @Override
  public void setFirstEntrySOArticleActivityStatus(boolean firstEntrySOArticleActivityStatus) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_FIRST_ENTRY_SO_ARTICLE_ACTIVITY, firstEntrySOArticleActivityStatus).apply();
  }


  @Override
  public boolean isLogged() {
    return getSharedPreferences().getBoolean(PREF_KEY_LOGGED, false);
  }

  @Override
  public void setLogged(boolean logged) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_LOGGED, logged).apply();
  }

  @Override
  public boolean isShowImages() {
    return getSharedPreferences().getBoolean(PREF_KEY_SHOW_IMAGES, false);
  }


  @Override
  public void setShowImages(boolean isShowImages) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_SHOW_IMAGES, isShowImages).apply();
  }

  @Override
  public boolean getFirstSync() {
    return getSharedPreferences().getBoolean(PREF_KEY_FIRST_SYNC, false);
  }

  @Override
  public void setFirstSync(boolean firstSync) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_FIRST_SYNC, firstSync).apply();
  }

  @Override
  public boolean getIsMerchStock() {
    return getSharedPreferences().getBoolean(PREF_KEY_IS_MERCH_STOCK, false);
  }

  @Override
  public void setIsMerchStock(boolean isMerchStock) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_IS_MERCH_STOCK, isMerchStock).apply();
  }

  @Override
  public boolean getCanSyncPreviousSO() {
    return getSharedPreferences().getBoolean(PREF_CAN_SYNC_SO, false);
  }

  @Override
  public void setCanSyncPreviousSO(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_CAN_SYNC_SO, canSync).apply();
  }

  @Override
  public boolean getEmptyStatusSyncPreviousSO() {
    return getSharedPreferences().getBoolean(PREF_EMPTY_STATUS_SYNC_SO, false);
  }

  @Override
  public void setEmptyStatusSyncPreviousSO(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_EMPTY_STATUS_SYNC_SO, canSync).apply();
  }

  @Override
  public boolean getEmptyStatusSyncPreviousPrefactor() {
    return getSharedPreferences().getBoolean(PREF_EMPTY_STATUS_SYNC_PREFACTOR, false);
  }

  @Override
  public void setEmptyStatusSyncPreviousPrefactor(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_EMPTY_STATUS_SYNC_PREFACTOR, canSync).apply();
  }

  @Override
  public boolean getCanSyncPreviousPrefactor() {
    return getSharedPreferences().getBoolean(PREF_CAN_SYNC_PREFACTOR, false);
  }

  @Override
  public void setCanSyncPreviousPrefactor(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_CAN_SYNC_PREFACTOR, canSync).apply();
  }


  @Override
  public void clearPreferences() {
    getSharedPreferences().edit().clear().apply();
  }

  @Override
  public int getTempInsertedSPId() {
    int nId = getSharedPreferences().getInt(PREF_TEMP_INSERTED_SPID, NULL_INT_INDEX);
    return nId == NULL_INT_INDEX ? 0 : nId;
  }

  @Override
  public void setTempInsertedSPId(int spId) {
    int nId = spId == 0 ? NULL_INT_INDEX : spId;
    getSharedPreferences().edit().putInt(PREF_TEMP_INSERTED_SPID, nId).apply();
  }

  @Override
  public int getTempInsertedSOId() {
    int nId = getSharedPreferences().getInt(PREF_TEMP_INSERTED_SOID, NULL_INT_INDEX);
    return nId == NULL_INT_INDEX ? 0 : nId;
  }

  @Override
  public void setTempInsertedSOId(int soId) {
    int nId = soId == 0 ? NULL_INT_INDEX : soId;
    getSharedPreferences().edit().putInt(PREF_TEMP_INSERTED_SOID, nId).apply();
  }

  @Override
  public int getLatestInsertedStockId() {
    int nId = getSharedPreferences().getInt(PREF_LATEST_INSERTED_STOCKID, NULL_INT_INDEX);
    return nId == NULL_INT_INDEX ? 0 : nId;
  }

  @Override
  public void setLatestInsertedStockId(int stockId) {
    int nId = stockId == 0 ? NULL_INT_INDEX : stockId;
    getSharedPreferences().edit().putInt(PREF_LATEST_INSERTED_STOCKID, nId).apply();
  }

  @Override
  public int getLatestInsertedCabinetId() {
    int nId = getSharedPreferences().getInt(PREF_LATEST_INSERTED_CABINETID, NULL_INT_INDEX);
    return nId == NULL_INT_INDEX ? 0 : nId;
  }

  @Override
  public void setLatestInsertedCabinetId(int cabinetId) {
    int nId = cabinetId == 0 ? NULL_INT_INDEX : cabinetId;
    getSharedPreferences().edit().putInt(PREF_LATEST_INSERTED_CABINETID, nId).apply();
  }

  @Override
  public String getDateLastSyncedTables() {
    String date = getSharedPreferences().getString(PREF_LAST_DATE_SYNC_TABLES, NULL_STRING_INDEX);
    return date == NULL_STRING_INDEX ? null : date;
  }

  @Override
  public void setDateLastSyncedTables(String date) {
    String lastDate = date == null ? NULL_STRING_INDEX : date;
    getSharedPreferences().edit().putString(PREF_LAST_DATE_SYNC_TABLES, lastDate).apply();
  }

  @Override
  public String getDateLastSyncedImages() {
    String date = getSharedPreferences().getString(PREF_LAST_DATE_SYNC_IMAGES, NULL_STRING_INDEX);
    return date == NULL_STRING_INDEX ? null : date;
  }

  @Override
  public void setDateLastSyncedImages(String date) {
    String lastDate = date == null ? NULL_STRING_INDEX : date;
    getSharedPreferences().edit().putString(PREF_LAST_DATE_SYNC_IMAGES, lastDate).apply();
  }

  @Override
  public boolean getCheckUpdate() {
    return getSharedPreferences().getBoolean(PREF_KEY_CHECK_UPDATE, false);
  }

  @Override
  public void setCheckUpdate(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_CHECK_UPDATE, canSync).apply();
  }

  @Override
  public boolean getExistMerchStock() {
    return getSharedPreferences().getBoolean(PREF_KEY_EXIST_MERCH_STOCK, false);
  }

  @Override
  public void setExistMerchStock(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_EXIST_MERCH_STOCK, canSync).apply();
  }

  @Override
  public boolean getExistStockAccess() {
    return getSharedPreferences().getBoolean(PREF_KEY_EXIST_STOCK_ACCESS, false);
  }

  @Override
  public void setExistStockAccess(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_EXIST_STOCK_ACCESS, canSync).apply();
  }

  @Override
  public boolean getExistMerchInMerchStock() {
    return getSharedPreferences().getBoolean(PREF_KEY_EXIST_MERCH_IN_MERCH_STOCK, false);
  }

  @Override
  public void setExistMerchInMerchStock(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_EXIST_MERCH_IN_MERCH_STOCK, canSync).apply();
  }

  @Override
  public boolean getExistStock() {
    return getSharedPreferences().getBoolean(PREF_KEY_EXIST_STOCK, false);
  }

  @Override
  public void setExistStock(boolean canSync) {
    getSharedPreferences().edit().putBoolean(PREF_KEY_EXIST_STOCK, canSync).apply();
  }

}
