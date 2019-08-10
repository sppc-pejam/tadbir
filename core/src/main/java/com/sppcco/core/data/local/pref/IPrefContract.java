

package com.sppcco.core.data.local.pref;

public interface IPrefContract {

  String getFPName();
  void setFPName(String fpName);

  int getFPId();
  void setFPId(int fpId);

  int getWSId();
  void setWSId(int wsId);

  String getSDate();
  void setSDate(String sDate);

  String getEDate();
  void setEDate(String eDate);

  String getCompanyName();
  void setCompanyName(String companyName);

  String getDatabaseName();
  void setDatabaseName(String databaseName);

  String getBaseUrl();
  void setBaseUrl(String ipAddress);

  String getIpAddress();
  void setIpAddress(String ipAddress);

  String getPortNumber();
  void setPortNumber(String portNumber);

  String getWebservicePortNumber();
  void setWebservicePortNumber(String portNumber);

  String getKey();
  void setKey(String key);

  int getCurrentUserId();
  void setCurrentUserId(int userId);

  String getCurrentUserName();
  void setCurrentUserName(String userName);

  String getCurrentPassword();
  void setCurrentPassword(String password);

  int getCurrentGroupId();
  void setCurrentGroupId(int groupId);

  String getCurrentUserEmail();
  void setCurrentUserEmail(String email);

  String getCurrentUserProfilePicUrl();
  void setCurrentUserProfilePicUrl(String profilePicUrl);

  String getAccessToken();
  void setAccessToken(String accessToken);

  boolean getFirstEntryMenuStatus();
  void setFirstEntryMenuStatus(boolean firstEntryMenuStatus);

  boolean getFirstEntrySOActivityStatus();
  void setFirstEntrySOActivityStatus(boolean firstEntrySOActivityStatus);

  boolean getFirstEntrySOArticleActivityStatus();
  void setFirstEntrySOArticleActivityStatus(boolean firstEntrySOArticleActivityStatus);

  boolean isLogged();
  void setLogged(boolean logged);

  boolean isShowImages();
  void setShowImages(boolean status);

  boolean getEmptyStatusSyncPreviousSO();
  void setEmptyStatusSyncPreviousSO(boolean canSync);

  boolean getCanSyncPreviousSO();
  void setCanSyncPreviousSO(boolean canSync);

  boolean getEmptyStatusSyncPreviousPrefactor();
  void setEmptyStatusSyncPreviousPrefactor(boolean canSync);

  boolean getCanSyncPreviousPrefactor();
  void setCanSyncPreviousPrefactor(boolean canSync);

  boolean getFirstSync();
  void setFirstSync(boolean firstSync);

  boolean getIsMerchStock();
  void setIsMerchStock(boolean isMerchStock);

  void clearPreferences();

  int getTempInsertedSPId();
  void setTempInsertedSPId(int fpId);

  int getTempInsertedSOId();
  void setTempInsertedSOId(int fpId);

  int getLatestInsertedStockId();
  void setLatestInsertedStockId(int stockId);

  int getLatestInsertedCabinetId();
  void setLatestInsertedCabinetId(int cabinetId);

  String getDateLastSyncedTables();
  void setDateLastSyncedTables(String date);

  String getDateLastSyncedImages();
  void setDateLastSyncedImages(String date);

  boolean getCheckUpdate();
  void setCheckUpdate(boolean canSync);

  //Sync Validation ============================================

  boolean getExistMerchStock();
  void setExistMerchStock(boolean canSync);

  boolean getExistStockAccess();
  void setExistStockAccess(boolean canSync);

  boolean getExistMerchInMerchStock();
  void setExistMerchInMerchStock(boolean canSync);

  boolean getExistStock();
  void setExistStock(boolean canSync);
}
