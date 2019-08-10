package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__AccVsCC__",
  primaryKeys = {"FullId", "CCId", "FPId"})
public class AccVsCC implements Serializable, BaseColumns {

  @NotNull
  @SerializedName("FullId")
  @Expose
  @ColumnInfo(name = "FullId")
  private String FullId;

  @SerializedName("CCId")
  @Expose
  @ColumnInfo(name = "CCId")
  private int CCId;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  public AccVsCC() {
    super();
  }

  public AccVsCC(@NotNull String fullId, int ccId, int FPId) {
    FullId = fullId;
    CCId = ccId;
    this.FPId = FPId;
  }

  public String getFullId() {
    return FullId;
  }

  public void setFullId(String fullId) {
    FullId = fullId;
  }

  public int getCCId() {
    return CCId;
  }

  public void setCCId(int ccId) {
    CCId = ccId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
