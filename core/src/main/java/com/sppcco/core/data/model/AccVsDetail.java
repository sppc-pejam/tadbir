package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__AccVsDetail__",
  primaryKeys = {"FullId", "DetId", "FPId"})
public class AccVsDetail implements Serializable, BaseColumns {

  @NotNull
  @SerializedName("FullId")
  @Expose
  @ColumnInfo(name = "FullId")
  private String FullId;

  @SerializedName("DetId")
  @Expose
  @ColumnInfo(name = "DetId")
  private int DetId;

  @SerializedName("DetFullId")
  @Expose
  @ColumnInfo(name = "DetFullId")
  private String DetFullId;

  @SerializedName("Necessary")
  @Expose
  @ColumnInfo(name = "Necessary")
  private int Necessary;

  @SerializedName("TRes")
  @Expose
  @ColumnInfo(name = "TRes")
  private String TRes;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  public AccVsDetail() {
    super();
  }

  public AccVsDetail(@NotNull String fullId, int detId, String detFullId, int necessary, String TRes, int FPId) {
    FullId = fullId;
    DetId = detId;
    DetFullId = detFullId;
    Necessary = necessary;
    this.TRes = TRes;
    this.FPId = FPId;
  }

  public String getFullId() {
    return FullId;
  }

  public void setFullId(String fullId) {
    FullId = fullId;
  }

  public int getDetId() {
    return DetId;
  }

  public void setDetId(int detId) {
    DetId = detId;
  }

  public String getDetFullId() {
    return DetFullId;
  }

  public void setDetFullId(String detFullId) {
    DetFullId = detFullId;
  }

  public int getNecessary() {
    return Necessary;
  }

  public void setNecessary(int necessary) {
    Necessary = necessary;
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
