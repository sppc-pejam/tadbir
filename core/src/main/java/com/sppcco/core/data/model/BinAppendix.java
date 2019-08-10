package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "__BinAppendix__")
public class BinAppendix implements Serializable,BaseColumns {

  @ColumnInfo(name = "AppxId")
  @PrimaryKey(autoGenerate = true)
  private int AppxId;

  @SerializedName("SysId")
  @Expose
  @ColumnInfo(name = "SysId")
  private int SysId;

  @SerializedName("FormId")
  @Expose
  @ColumnInfo(name = "FormId")
  private int FormId;

  @SerializedName("SubFormId")
  @Expose
  @ColumnInfo(name = "SubFormId")
  private int SubFormId;

  @SerializedName("ObjectId")
  @Expose
  @ColumnInfo(name = "ObjectId")
  private int ObjectId;

  @SerializedName("ObjectFPId")
  @Expose
  @ColumnInfo(name = "ObjectFPId")
  private int ObjectFPId;

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "Id")
  private int Id;

  @SerializedName("ImageId")
  @Expose
  @ColumnInfo(name = "ImageId")
  private int ImageId;

  @SerializedName("ImageGuid")
  @Expose
  @ColumnInfo(name = "ImageGuid")
  private String ImageGuid;

  @SerializedName("FileName")
  @Expose
  @ColumnInfo(name = "FileName")
  private String FileName;

  @SerializedName("Ext")
  @Expose
  @ColumnInfo(name = "Ext")
  private String Ext;

  @SerializedName("Title")
  @Expose
  @ColumnInfo(name = "Title")
  private String Title;

  @SerializedName("IDesc")
  @Expose
  @ColumnInfo(name = "IDesc")
  private String IDesc;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  @SerializedName("UserName")
  @Expose
  @ColumnInfo(name = "UserName")
  private String UserName;

  @SerializedName("AuthorizedViewUserId")
  @Expose
  @ColumnInfo(name = "AuthorizedViewUserId")
  private int AuthorizedViewUserId;

  @SerializedName("OldAppendixNo")
  @Expose
  @ColumnInfo(name = "OldAppendixNo")
  private int OldAppendixNo;

  @SerializedName("IDate")
  @Expose
  @ColumnInfo(name = "IDate")
  private String IDate;

  @SerializedName("ITime")
  @Expose
  @ColumnInfo(name = "ITime")
  private String ITime;

  public BinAppendix() {
    super();
  }

  public BinAppendix(int appxId, int sysId, int formId, int subFormId, int objectId, int objectFPId, int id, int imageId, String imageGuid,
                     String fileName, String ext, String title, String IDesc, int userId, String userName, int authorizedViewUserId,
                     int oldAppendixNo, String IDate, String ITime) {
    AppxId = appxId;
    SysId = sysId;
    FormId = formId;
    SubFormId = subFormId;
    ObjectId = objectId;
    ObjectFPId = objectFPId;
    Id = id;
    ImageId = imageId;
    ImageGuid = imageGuid;
    FileName = fileName;
    Ext = ext;
    Title = title;
    this.IDesc = IDesc;
    UserId = userId;
    UserName = userName;
    AuthorizedViewUserId = authorizedViewUserId;
    OldAppendixNo = oldAppendixNo;
    this.IDate = IDate;
    this.ITime = ITime;
  }

  public BinAppendix(int sysId, int formId, int subFormId, int objectId, int objectFPId, int id, int imageId, String imageGuid,
                     String fileName, String ext, String title, String IDesc, int userId, String userName, int authorizedViewUserId,
                     int oldAppendixNo, String IDate, String ITime) {
    SysId = sysId;
    FormId = formId;
    SubFormId = subFormId;
    ObjectId = objectId;
    ObjectFPId = objectFPId;
    Id = id;
    ImageId = imageId;
    ImageGuid = imageGuid;
    FileName = fileName;
    Ext = ext;
    Title = title;
    this.IDesc = IDesc;
    UserId = userId;
    UserName = userName;
    AuthorizedViewUserId = authorizedViewUserId;
    OldAppendixNo = oldAppendixNo;
    this.IDate = IDate;
    this.ITime = ITime;
  }

  public int getAppxId() {
    return AppxId;
  }

  public void setAppxId(int appxId) {
    AppxId = appxId;
  }

  public int getSysId() {
    return SysId;
  }

  public void setSysId(int sysId) {
    SysId = sysId;
  }

  public int getFormId() {
    return FormId;
  }

  public void setFormId(int formId) {
    FormId = formId;
  }

  public int getSubFormId() {
    return SubFormId;
  }

  public void setSubFormId(int subFormId) {
    SubFormId = subFormId;
  }

  public int getObjectId() {
    return ObjectId;
  }

  public void setObjectId(int objectId) {
    ObjectId = objectId;
  }

  public int getObjectFPId() {
    return ObjectFPId;
  }

  public void setObjectFPId(int objectFPId) {
    ObjectFPId = objectFPId;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getImageId() {
    return ImageId;
  }

  public void setImageId(int imageId) {
    ImageId = imageId;
  }

  public String getImageGuid() {
    return ImageGuid;
  }

  public void setImageGuid(String imageGuid) {
    ImageGuid = imageGuid;
  }

  public String getFileName() {
    return FileName;
  }

  public void setFileName(String fileName) {
    FileName = fileName;
  }

  public String getExt() {
    return Ext;
  }

  public void setExt(String ext) {
    Ext = ext;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getIDesc() {
    return IDesc;
  }

  public void setIDesc(String IDesc) {
    this.IDesc = IDesc;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public int getAuthorizedViewUserId() {
    return AuthorizedViewUserId;
  }

  public void setAuthorizedViewUserId(int authorizedViewUserId) {
    AuthorizedViewUserId = authorizedViewUserId;
  }

  public int getOldAppendixNo() {
    return OldAppendixNo;
  }

  public void setOldAppendixNo(int oldAppendixNo) {
    OldAppendixNo = oldAppendixNo;
  }

  public String getIDate() {
    return IDate;
  }

  public void setIDate(String IDate) {
    this.IDate = IDate;
  }

  public String getITime() {
    return ITime;
  }

  public void setITime(String ITime) {
    this.ITime = ITime;
  }
}
