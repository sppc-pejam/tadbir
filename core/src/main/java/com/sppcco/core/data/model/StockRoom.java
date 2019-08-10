package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "__StockRoom__",
  primaryKeys = {"_id", "FPId"},
  indices = {@Index(
    name = "StockRoomIndex", value = {"_id", "FPId", "Code", "Name"}, unique = true
  )})
public class StockRoom implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("Code")
  @Expose
  @ColumnInfo(name = "Code")
  int Code;

  @SerializedName("Name")
  @Expose
  @ColumnInfo(name = "Name")
  String Name;

  @SerializedName("ManagerName")
  @Expose
  @ColumnInfo(name = "ManagerName")
  String ManagerName;

  @SerializedName("SRDesc")
  @Expose
  @ColumnInfo(name = "SRDesc")
  String SRDesc;

  @SerializedName("LRes")
  @Expose
  @ColumnInfo(name = "LRes")
  int LRes;

  @SerializedName("DRes")
  @Expose
  @ColumnInfo(name = "DRes")
  float DRes;

  @SerializedName("TRes")
  @Expose
  @ColumnInfo(name = "TRes")
  String TRes;

  @SerializedName("AccountId")
  @Expose
  @ColumnInfo(name = "AccountId")
  String AccountId;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  int FPId;

  @SerializedName("FAccId")
  @Expose
  @ColumnInfo(name = "FAccId")
  int FAccId;

  @SerializedName("CCId")
  @Expose
  @ColumnInfo(name = "CCId")
  int CCId;

  @SerializedName("PrjId")
  @Expose
  @ColumnInfo(name = "PrjId")
  int PrjId;

  public StockRoom() {
    super();
  }

  public StockRoom(int id, int code, String name, String managerName, String SRDesc, int LRes,
                   float DRes, String TRes, String accountId, int FPId, int FAccId, int CCId, int prjId) {
    Id = id;
    Code = code;
    Name = name;
    ManagerName = managerName;
    this.SRDesc = SRDesc;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    AccountId = accountId;
    this.FPId = FPId;
    this.FAccId = FAccId;
    this.CCId = CCId;
    PrjId = prjId;
  }

  public StockRoom(int code, String name, String managerName, String SRDesc, int LRes, float DRes,
                   String TRes, String accountId, int FPId, int FAccId, int CCId, int prjId) {
    Code = code;
    Name = name;
    ManagerName = managerName;
    this.SRDesc = SRDesc;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    AccountId = accountId;
    this.FPId = FPId;
    this.FAccId = FAccId;
    this.CCId = CCId;
    PrjId = prjId;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getCode() {
    return Code;
  }

  public void setCode(int code) {
    Code = code;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getManagerName() {
    return ManagerName;
  }

  public void setManagerName(String managerName) {
    ManagerName = managerName;
  }

  public String getSRDesc() {
    return SRDesc;
  }

  public void setSRDesc(String SRDesc) {
    this.SRDesc = SRDesc;
  }

  public int getLRes() {
    return LRes;
  }

  public void setLRes(int LRes) {
    this.LRes = LRes;
  }

  public float getDRes() {
    return DRes;
  }

  public void setDRes(float DRes) {
    this.DRes = DRes;
  }

  public String getTRes() {
    return TRes;
  }

  public void setTRes(String TRes) {
    this.TRes = TRes;
  }

  public String getAccountId() {
    return AccountId;
  }

  public void setAccountId(String accountId) {
    AccountId = accountId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  public int getFAccId() {
    return FAccId;
  }

  public void setFAccId(int FAccId) {
    this.FAccId = FAccId;
  }

  public int getCCId() {
    return CCId;
  }

  public void setCCId(int CCId) {
    this.CCId = CCId;
  }

  public int getPrjId() {
    return PrjId;
  }

  public void setPrjId(int prjId) {
    PrjId = prjId;
  }
}
