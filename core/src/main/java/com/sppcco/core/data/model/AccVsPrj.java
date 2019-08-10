package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__AccVsPrj__",
  primaryKeys = {"FullId", "PrjId", "FPId"})
public class AccVsPrj implements Serializable, BaseColumns {

  @NotNull
  @SerializedName("FullId")
  @Expose
  @ColumnInfo(name = "FullId")
  private String FullId;

  @SerializedName("PrjId")
  @Expose
  @ColumnInfo(name = "PrjId")
  private int PrjId;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  public AccVsPrj() {
    super();
  }

  public AccVsPrj(@NotNull String fullId, int prjId, int FPId) {
    FullId = fullId;
    PrjId = prjId;
    this.FPId = FPId;
  }

  public String getFullId() {
    return FullId;
  }

  public void setFullId(String fullId) {
    FullId = fullId;
  }

  public int getPrjId() {
    return PrjId;
  }

  public void setPrjId(int prjId) {
    PrjId = prjId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
