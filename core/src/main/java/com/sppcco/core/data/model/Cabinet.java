package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "__Cabinet__",
  primaryKeys = {"_id", "StockRoomId", "FPId"},
  indices = {@Index(
    name = "CabinetIndex", value = {"_id", "StockRoomId", "FPId", "Code", "Name"}, unique = true
  )}
  )
public class Cabinet implements BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("Code")
  @Expose
  @ColumnInfo(name = "Code")
  private int Code;

  @SerializedName("Name")
  @Expose
  @ColumnInfo(name = "Name")
  private String Name;

  @SerializedName("StockRoomId")
  @Expose
  @ColumnInfo(name = "StockRoomId")
  private int StockRoomId;

  @SerializedName("CDesc")
  @Expose
  @ColumnInfo(name = "CDesc")
  private String CDesc;

  @SerializedName("LRes")
  @Expose
  @ColumnInfo(name = "LRes")
  private int LRes;

  @SerializedName("DRes")
  @Expose
  @ColumnInfo(name = "DRes")
  private float DRes;

  @SerializedName("TRes")
  @Expose
  @ColumnInfo(name = "TRes")
  private String TRes;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  public Cabinet() {
    super();
  }

  public Cabinet(int id, int code, String name, int stockRoomId, String CDesc,
                 int LRes, float DRes, String TRes, int FPId) {
    Id = id;
    Code = code;
    Name = name;
    StockRoomId = stockRoomId;
    this.CDesc = CDesc;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    this.FPId = FPId;
  }

  public Cabinet(int code, String name, int stockRoomId, String CDesc,
                 int LRes, float DRes, String TRes, int FPId) {
    Code = code;
    Name = name;
    StockRoomId = stockRoomId;
    this.CDesc = CDesc;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    this.FPId = FPId;
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

  public int getStockRoomId() {
    return StockRoomId;
  }

  public void setStockRoomId(int stockRoomId) {
    StockRoomId = stockRoomId;
  }

  public String getCDesc() {
    return CDesc;
  }

  public void setCDesc(String CDesc) {
    this.CDesc = CDesc;
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

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
