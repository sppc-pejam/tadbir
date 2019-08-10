package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
  tableName = "__Image__",
  indices = {@Index(
    name = "ImageIndex", value = {"ObjectId", "ObjectFPId", "_id"}, unique = true
  )}
)
public class Image implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("ImageData")
  @Expose
  //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
  @ColumnInfo(name = "ImageData")
  private String ImageData;

  @SerializedName("FileName")
  @Expose
  @ColumnInfo(name = "FileName")
  private String FileName;

  @SerializedName("Ext")
  @Expose
  @ColumnInfo(name = "Ext")
  private String Ext;

  @SerializedName("FileSize")
  @Expose
  @ColumnInfo(name = "FileSize")
  private int FileSize;

  @SerializedName("MCheckSum")
  @Expose
  @ColumnInfo(name = "MCheckSum")
  private String MCheckSum;

  @SerializedName("ImageGuid")
  @Expose
  @ColumnInfo(name = "ImageGuid")
  private String ImageGuid;

  @SerializedName("WsId")
  @Expose
  @ColumnInfo(name = "WsId")
  private int WsId;

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

  @SerializedName("Thumbnail")
  @Expose
  //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
  @ColumnInfo(name = "Thumbnail")
  private String Thumbnail;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  @SerializedName("UserName")
  @Expose
  @ColumnInfo(name = "UserName")
  private String UserName;

  @SerializedName("IDate")
  @Expose
  @ColumnInfo(name = "IDate")
  private String IDate;

  @SerializedName("ITime")
  @Expose
  @ColumnInfo(name = "ITime")
  private String ITime;

  @SerializedName("IDesc")
  @Expose
  @ColumnInfo(name = "IDesc")
  private String IDesc;

  public Image() {
    super();
  }

  public Image(int id, String imageData, String fileName, String ext, int fileSize, String MCheckSum, String imageGuid,
               int wsId, int sysId, int formId, int subFormId, int objectId, int objectFPId, String thumbnail,
               int userId, String userName, String IDate, String ITime, String IDesc) {
    Id = id;
    ImageData = imageData;
    FileName = fileName;
    Ext = ext;
    FileSize = fileSize;
    this.MCheckSum = MCheckSum;
    ImageGuid = imageGuid;
    WsId = wsId;
    SysId = sysId;
    FormId = formId;
    SubFormId = subFormId;
    ObjectId = objectId;
    ObjectFPId = objectFPId;
    Thumbnail = thumbnail;
    UserId = userId;
    UserName = userName;
    this.IDate = IDate;
    this.ITime = ITime;
    this.IDesc = IDesc;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getImageData() {
    return ImageData;
  }

  public void setImageData(String imageData) {
    ImageData = imageData;
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

  public int getFileSize() {
    return FileSize;
  }

  public void setFileSize(int fileSize) {
    FileSize = fileSize;
  }

  public String getMCheckSum() {
    return MCheckSum;
  }

  public void setMCheckSum(String MCheckSum) {
    this.MCheckSum = MCheckSum;
  }

  public String getImageGuid() {
    return ImageGuid;
  }

  public void setImageGuid(String imageGuid) {
    ImageGuid = imageGuid;
  }

  public int getWsId() {
    return WsId;
  }

  public void setWsId(int wsId) {
    WsId = wsId;
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

  public String getThumbnail() {
    return Thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    Thumbnail = thumbnail;
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

  public String getIDesc() {
    return IDesc;
  }

  public void setIDesc(String IDesc) {
    this.IDesc = IDesc;
  }
}
