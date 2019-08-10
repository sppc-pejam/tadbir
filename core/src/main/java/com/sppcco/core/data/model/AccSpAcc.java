package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(
  tableName = "__AccSpAcc__",
  primaryKeys = {"SpId", "FPId"}
)
public class AccSpAcc implements Serializable, BaseColumns {

  @SerializedName("SpId")
  @Expose
  @ColumnInfo(name = "SpId")
  private int SpId;

  @SerializedName("AccId")
  @Expose
  @ColumnInfo(name = "AccId")
  private String AccId;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  int FPId;

  public AccSpAcc() {
    super();
  }

  public int getSpId() {
    return SpId;
  }

  public void setSpId(int spId) {
    SpId = spId;
  }

  public String getAccId() {
    return AccId;
  }

  public void setAccId(String accId) {
    AccId = accId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
