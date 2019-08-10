package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__SalesDiscount__",
  primaryKeys = {"MerchId", "Type", "FPId"})
public class SalesDiscount implements Serializable, BaseColumns {

  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("MerchId")
  @Expose
  private int MerchId;

  @SerializedName("Discount")
  @Expose
  private float Discount;

  @SerializedName("Type")
  @Expose
  private int Type;

  @SerializedName("FPId")
  @Expose
  private int FPId;


  public SalesDiscount() {
    super();
  }


  public SalesDiscount(int id, int merchId, float discount, int type, int FPId) {
    Id = id;
    MerchId = merchId;
    Discount = discount;
    Type = type;
    this.FPId = FPId;
  }

  public SalesDiscount(int merchId, float discount, int type, int FPId) {
    MerchId = merchId;
    Discount = discount;
    Type = type;
    this.FPId = FPId;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getMerchId() {
    return MerchId;
  }

  public void setMerchId(int merchId) {
    MerchId = merchId;
  }

  public float getDiscount() {
    return Discount;
  }

  public void setDiscount(float discount) {
    Discount = discount;
  }

  public int getType() {
    return Type;
  }

  public void setType(int type) {
    Type = type;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
