package com.sppcco.core.data.model;


import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "__UserServiceLogin__")
public class UserServiceLogin implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("ugId")
  @Expose
  private int ugId;

  @SerializedName("LogonName")
  @Expose
  private String logonName;

  @SerializedName("RealName")
  @Expose
  private String realName;

  @SerializedName("SvcPassword")
  @Expose
  private String svcPassword;

  @SerializedName("Email")
  @Expose
  private String email;

  @SerializedName("ServiceAccess")
  @Expose
  private int serviceAccess;

  @SerializedName("MCheckSum")
  @Expose
  private String checkSum;

  @SerializedName("LastWokspaceType")
  @Expose
  private int lastWokspaceType;

  @SerializedName("LastWokspace")
  @Expose
  private String lastWokspace;

  @SerializedName("LastFPId")
  @Expose
  private int lastFPId;

  @SerializedName("ImageId")
  @Expose
  private int imageId;

  @SerializedName("ImageGuid")
  @Expose
  private String imageGuid;

  @SerializedName("WebSkin")
  @Expose
  private String webSkin;

  @SerializedName("GroupId")
  @Expose
  private int groupId;

  public UserServiceLogin() {
    super();
  }

  @Ignore
  public UserServiceLogin(int id, int ugId, String logonName, String realName, String svcPassword, String email,
                          int serviceAccess, String checkSum, int lastWokspaceType, String lastWokspace,
                          int lastFPId, int imageId, String imageGuid, String webSkin, int groupId) {
    Id = id;
    this.ugId = ugId;
    this.logonName = logonName;
    this.realName = realName;
    this.svcPassword = svcPassword;
    this.email = email;
    this.serviceAccess = serviceAccess;
    this.checkSum = checkSum;
    this.lastWokspaceType = lastWokspaceType;
    this.lastWokspace = lastWokspace;
    this.lastFPId = lastFPId;
    this.imageId = imageId;
    this.imageGuid = imageGuid;
    this.webSkin = webSkin;
    this.groupId = groupId;
  }

  @Ignore
  public UserServiceLogin(int ugId, String logonName, String realName, String svcPassword, String email,
                          int serviceAccess, String checkSum, int lastWokspaceType, String lastWokspace,
                          int lastFPId, int imageId, String imageGuid, String webSkin, int groupId) {
    this.ugId = ugId;
    this.logonName = logonName;
    this.realName = realName;
    this.svcPassword = svcPassword;
    this.email = email;
    this.serviceAccess = serviceAccess;
    this.checkSum = checkSum;
    this.lastWokspaceType = lastWokspaceType;
    this.lastWokspace = lastWokspace;
    this.lastFPId = lastFPId;
    this.imageId = imageId;
    this.imageGuid = imageGuid;
    this.webSkin = webSkin;
    this.groupId = groupId;
  }


  public static UserServiceLogin getUserServiceLoginWithDefaultValue() {
    UserServiceLogin userServiceLogin = new UserServiceLogin();

    userServiceLogin.setUgId(0);
    userServiceLogin.setLogonName("");
    userServiceLogin.setRealName("");
    userServiceLogin.setSvcPassword("");
    userServiceLogin.setEmail(null);
    userServiceLogin.setServiceAccess(0);
    userServiceLogin.setCheckSum("");
    userServiceLogin.setLastWokspaceType(0);
    userServiceLogin.setLastWokspace("");
    userServiceLogin.setLastFPId(0);
    userServiceLogin.setImageId(0);
    userServiceLogin.setImageGuid(null);
    userServiceLogin.setWebSkin("");
    userServiceLogin.setGroupId(0);

    return userServiceLogin;
  }


  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getUgId() {
    return ugId;
  }

  public void setUgId(int ugId) {
    this.ugId = ugId;
  }

  public String getLogonName() {
    return logonName;
  }

  public void setLogonName(String logonName) {
    this.logonName = logonName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getSvcPassword() {
    return svcPassword;
  }

  public void setSvcPassword(String svcPassword) {
    this.svcPassword = svcPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getServiceAccess() {
    return serviceAccess;
  }

  public void setServiceAccess(int serviceAccess) {
    this.serviceAccess = serviceAccess;
  }

  public String getCheckSum() {
    return checkSum;
  }

  public void setCheckSum(String checkSum) {
    this.checkSum = checkSum;
  }

  public int getLastWokspaceType() {
    return lastWokspaceType;
  }

  public void setLastWokspaceType(int lastWokspaceType) {
    this.lastWokspaceType = lastWokspaceType;
  }

  public String getLastWokspace() {
    return lastWokspace;
  }

  public void setLastWokspace(String lastWokspace) {
    this.lastWokspace = lastWokspace;
  }

  public int getLastFPId() {
    return lastFPId;
  }

  public void setLastFPId(int lastFPId) {
    this.lastFPId = lastFPId;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getImageGuid() {
    return imageGuid;
  }

  public void setImageGuid(String imageGuid) {
    this.imageGuid = imageGuid;
  }

  public String getWebSkin() {
    return webSkin;
  }

  public void setWebSkin(String webSkin) {
    this.webSkin = webSkin;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }
}
