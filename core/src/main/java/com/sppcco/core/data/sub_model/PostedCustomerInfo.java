package com.sppcco.core.data.sub_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.converter.timestamp.TimestampConverter;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.io.Serializable;
import java.util.Date;

import androidx.room.TypeConverters;

public class PostedCustomerInfo implements Serializable{

  @SerializedName("Id")
  @Expose
  private int Id;

  @SerializedName("Name")
  @Expose
  private String Name;

  @SerializedName("SubscriptionNo")
  @Expose
  private String SubscriptionNo;

  @SerializedName("UserId")
  @Expose
  private int UserId;

  @SerializedName("FPId")
  @Expose
  private int FPId;

  @SerializedName("Status")
  @Expose
  private int Status;

  @SerializedName("Check")
  @Expose
  private int Check;

  @SerializedName("NumberOfRequest")
  @Expose
  private int NumberOfRequest;

  @SerializedName("PostDate")
  @Expose
  @TypeConverters({TimestampConverter.class})
  private Date PostDate;

  @SerializedName("CheckDate")
  @Expose
  @TypeConverters({TimestampConverter.class})
  private Date CheckDate;

  @SerializedName("RequestOfCheck")
  @Expose
  private int RequestOfCheck;

  @SerializedName("LastTimeForRequestOfCheck")
  @Expose
  @TypeConverters({TimestampConverter.class})
  private Date LastTimeForRequestOfCheck;

  @SerializedName("NeedSync")
  @Expose
  private int NeedSync;


  public PostedCustomerInfo() {
    super();
  }


  public static PostedCustomerInfo getPostedCustomerInfoWithDefaultValue() {
    PostedCustomerInfo postedCustomerInfo = new PostedCustomerInfo();

    postedCustomerInfo.setId(0);
    postedCustomerInfo.setName("");
    postedCustomerInfo.setSubscriptionNo("");
    postedCustomerInfo.setUserId(BaseApplication.getUserId());
    postedCustomerInfo.setFPId(BaseApplication.getFPId());
    postedCustomerInfo.setStatus(0);
    postedCustomerInfo.setCheck(0);
    postedCustomerInfo.setNumberOfRequest(1);
    postedCustomerInfo.setPostDate(CalenderManager.getCurrentDateStampTime());
    postedCustomerInfo.setCheckDate(CalenderManager.getCurrentDateStampTime());
    postedCustomerInfo.setRequestOfCheck(1);
    postedCustomerInfo.setLastTimeForRequestOfCheck(CalenderManager.getCurrentDateStampTime());
    postedCustomerInfo.setNeedSync(0);

    return postedCustomerInfo;
  }




  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getSubscriptionNo() {
    return SubscriptionNo;
  }

  public void setSubscriptionNo(String subscriptionNo) {
    SubscriptionNo = subscriptionNo;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  public int getStatus() {
    return Status;
  }

  public void setStatus(int status) {
    Status = status;
  }

  public int getCheck() {
    return Check;
  }

  public void setCheck(int check) {
    Check = check;
  }

  public int getNumberOfRequest() {
    return NumberOfRequest;
  }

  public void setNumberOfRequest(int numberOfRequest) {
    NumberOfRequest = numberOfRequest;
  }

  public Date getPostDate() {
    return PostDate;
  }

  public void setPostDate(Date postDate) {
    PostDate = postDate;
  }

  public Date getCheckDate() {
    return CheckDate;
  }

  public void setCheckDate(Date checkDate) {
    CheckDate = checkDate;
  }

  public int getRequestOfCheck() {
    return RequestOfCheck;
  }

  public void setRequestOfCheck(int requestOfCheck) {
    RequestOfCheck = requestOfCheck;
  }

  public Date getLastTimeForRequestOfCheck() {
    return LastTimeForRequestOfCheck;
  }

  public void setLastTimeForRequestOfCheck(Date lastTimeForRequestOfCheck) {
    LastTimeForRequestOfCheck = lastTimeForRequestOfCheck;
  }

  public int getNeedSync() {
    return NeedSync;
  }

  public void setNeedSync(int needSync) {
    NeedSync = needSync;
  }


}
