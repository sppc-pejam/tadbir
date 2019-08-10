package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.util.converter.timestamp.TimestampConverter;

import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.TypeConverters;

@Entity(tableName = "__MerchTax__",
  primaryKeys = {"_id", "MerchId", "FPId"},
  indices = {@Index(value = "MerchId")}
)
public class MerchTax implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("MerchId")
  @Expose
  @ColumnInfo(name = "MerchId")
  private int MerchId;

  @SerializedName("TaxPercent")
  @Expose
  @ColumnInfo(name = "TaxPercent")
  private double TaxPercent;

  @SerializedName("AvarezPercent")
  @Expose
  @ColumnInfo(name = "AvarezPercent")
  private double AvarezPercent;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  @TypeConverters({TimestampConverter.class})
  @SerializedName("EffectDate")
  @Expose
  @ColumnInfo(name = "EffectDate")
  private Date EffectDate;

  public MerchTax() {
    super();
  }

  public MerchTax(int id, int merchId, double taxPercent, double avarezPercent, int FPId, Date effectDate) {
    Id = id;
    MerchId = merchId;
    TaxPercent = taxPercent;
    AvarezPercent = avarezPercent;
    this.FPId = FPId;
    EffectDate = effectDate;
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

  public double getTaxPercent() {
    return TaxPercent;
  }

  public void setTaxPercent(double taxPercent) {
    TaxPercent = taxPercent;
  }

  public double getAvarezPercent() {
    return AvarezPercent;
  }

  public void setAvarezPercent(double avarezPercent) {
    AvarezPercent = avarezPercent;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  public Date getEffectDate() {
    return EffectDate;
  }

  public void setEffectDate(Date effectDate) {
    EffectDate = effectDate;
  }
}
