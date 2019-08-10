package com.sppcco.core.data.sub_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by b_nematzadeh on 01/28/2019.
 */
public class UserLoginInfo implements Serializable {

  @SerializedName("UserId")
  @Expose
  private int UserId;

  @SerializedName("FPNo")
  @Expose
  private int FPNo;

  @SerializedName("FPName")
  @Expose
  private String FPName;

  @SerializedName("StartDate")
  @Expose
  private String StartDate;

  @SerializedName("EndDate")
  @Expose
  private String EndDate;

  @SerializedName("RealName")
  @Expose
  private String RealName;

  @SerializedName("UserName")
  @Expose
  private String UserName;

  @SerializedName("Password")
  @Expose
  private String Password;

  @SerializedName("Email")
  @Expose
  private String Email;

  @SerializedName("ServiceAccess")
  @Expose
  private String ServiceAccess;

  @SerializedName("CheckSum")
  @Expose
  private String CheckSum;

  @SerializedName("GroupId")
  @Expose
  private int GroupId;

  @SerializedName("ValidationFlag")
  @Expose
  private int ValidationFlag;

  @SerializedName("Major")
  @Expose
  private int Major;

  @SerializedName("Minor")
  @Expose
  private int Minor;

  public UserLoginInfo(int userId, int fpNo, String fpName, String startDate, String endDate, String realName,
                       String userName, String password, String email, String serviceAccess, String checkSum,
                       int groupId, int validationFlag, int major, int minor) {

    UserId = userId;
    FPNo = fpNo;
    FPName = fpName;
    StartDate = startDate;
    EndDate = endDate;
    RealName = realName;
    UserName = userName;
    Password = password;
    Email = email;
    ServiceAccess = serviceAccess;
    CheckSum = checkSum;
    GroupId = groupId;
    ValidationFlag = validationFlag;
    Major = major;
    Minor = minor;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }

  public int getFPNo() {
    return FPNo;
  }

  public void setFPNo(int FPNo) {
    this.FPNo = FPNo;
  }

  public String getFPName() {
    return FPName;
  }

  public void setFPName(String FPName) {
    this.FPName = FPName;
  }

  public String getStartDate() {
    return StartDate;
  }

  public void setStartDate(String startDate) {
    StartDate = startDate;
  }

  public String getEndDate() {
    return EndDate;
  }

  public void setEndDate(String endDate) {
    EndDate = endDate;
  }

  public String getRealName() {
    return RealName;
  }

  public void setRealName(String realName) {
    RealName = realName;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getServiceAccess() {
    return ServiceAccess;
  }

  public void setServiceAccess(String serviceAccess) {
    ServiceAccess = serviceAccess;
  }

  public String getCheckSum() {
    return CheckSum;
  }

  public void setCheckSum(String checkSum) {
    CheckSum = checkSum;
  }

  public int getGroupId() {
    return GroupId;
  }

  public void setGroupId(int groupId) {
    GroupId = groupId;
  }

  public int getValidationFlag() {
    return ValidationFlag;
  }

  public void setValidationFlag(int validationFlag) {
    ValidationFlag = validationFlag;
  }

  public int getMajor() {
    return Major;
  }

  public void setMajor(int major) {
    Major = major;
  }

  public int getMinor() {
    return Minor;
  }

  public void setMinor(int minor) {
    Minor = minor;
  }
}
