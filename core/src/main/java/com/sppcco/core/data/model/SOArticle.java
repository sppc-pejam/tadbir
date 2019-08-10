package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.framework.application.BaseApplication;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(
  tableName = "__SOArticle__",
  indices = {@Index(value = "SOId")},
  foreignKeys = @ForeignKey(
    entity = SalesOrder.class,
    parentColumns = "_id",
    childColumns = "SOId",
    onDelete = ForeignKey.CASCADE)
)
public class SOArticle implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("SOId")
  @Expose
  private int SOId;

  @SerializedName("MerchandiseId")
  @Expose
  private int MerchandiseId;

  @SerializedName("MerchandiseCode")
  private String MerchandiseCode;

  @SerializedName("MerchandiseName")
  private String MerchandiseName;

  @SerializedName("Amount")
  @Expose
  private double Amount;

  @SerializedName("UnitId")
  @Expose
  private int UnitId;

  @SerializedName("UnitName")
  private String UnitName;

  @SerializedName("UnitPrice")
  @Expose
  private double UnitPrice;

  @SerializedName("TotalPrice")
  private double TotalPrice;

  @SerializedName("SOADesc")
  @Expose
  private String SOADesc;

  @SerializedName("Remainder")
  @Expose
  private double Remainder;

  @SerializedName("FPId")
  @Expose
  private int FPId;

  @SerializedName("Amount1")
  @Expose
  private double Amount1;

  @SerializedName("Amount2")
  @Expose
  private double Amount2;

  @SerializedName("SerialNo1")
  @Expose
  private String SerialNo1;

  @SerializedName("SerialNo2")
  @Expose
  private String SerialNo2;

  public SOArticle() {

  }

  public SOArticle(int id, int SOId, int merchandiseId, String merchandiseCode, String merchandiseName, double amount, int unitId, String unitName, double unitPrice, double totalPrice, String SOADesc, double remainder, int FPId, double amount1, double amount2, String serialNo1, String serialNo2) {
    Id = id;
    this.SOId = SOId;
    MerchandiseId = merchandiseId;
    MerchandiseCode = merchandiseCode;
    MerchandiseName = merchandiseName;
    Amount = amount;
    UnitId = unitId;
    UnitName = unitName;
    UnitPrice = unitPrice;
    TotalPrice = totalPrice;
    this.SOADesc = SOADesc;
    Remainder = remainder;
    this.FPId = FPId;
    Amount1 = amount1;
    Amount2 = amount2;
    SerialNo1 = serialNo1;
    SerialNo2 = serialNo2;
  }


  public SOArticle(int SOId, int merchandiseId, String merchandiseCode, String merchandiseName, double amount, int unitId, String unitName, double unitPrice, double totalPrice, String SOADesc, double remainder, int FPId, double amount1, double amount2, String serialNo1, String serialNo2) {
    this.SOId = SOId;
    MerchandiseId = merchandiseId;
    MerchandiseCode = merchandiseCode;
    MerchandiseName = merchandiseName;
    Amount = amount;
    UnitId = unitId;
    UnitName = unitName;
    UnitPrice = unitPrice;
    TotalPrice = totalPrice;
    this.SOADesc = SOADesc;
    Remainder = remainder;
    this.FPId = FPId;
    Amount1 = amount1;
    Amount2 = amount2;
    SerialNo1 = serialNo1;
    SerialNo2 = serialNo2;
  }

  public static SOArticle getSOArticleWithDefaultValue() {
    SOArticle soArticle = new SOArticle();

    soArticle.SOId = 0;
    soArticle.MerchandiseId = 0;
    soArticle.MerchandiseCode = "";
    soArticle.MerchandiseName = "";
    soArticle.Amount = 0;
    soArticle.UnitId = 0;
    soArticle.UnitName = "";
    soArticle.UnitPrice = 0;
    soArticle.TotalPrice = 0;
    soArticle.SOADesc = "";
    soArticle.Remainder = 0;
    soArticle.FPId = BaseApplication.getFPId();
    soArticle.Amount1 = 0;
    soArticle.Amount2 = 0;
    soArticle.SerialNo1 = "";
    soArticle.SerialNo2 = "";

    return soArticle;
  }


  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getSOId() {
    return SOId;
  }

  public void setSOId(int SOId) {
    this.SOId = SOId;
  }

  public int getMerchandiseId() {
    return MerchandiseId;
  }

  public void setMerchandiseId(int merchandiseId) {
    MerchandiseId = merchandiseId;
  }

  public String getMerchandiseCode() {
    return MerchandiseCode;
  }

  public void setMerchandiseCode(String merchandiseCode) {
    MerchandiseCode = merchandiseCode;
  }

  public String getMerchandiseName() {
    return MerchandiseName;
  }

  public void setMerchandiseName(String merchandiseName) {
    MerchandiseName = merchandiseName;
  }

  public double getAmount() {
    return Amount;
  }

  public void setAmount(double amount) {
    Amount = amount;
  }

  public int getUnitId() {
    return UnitId;
  }

  public void setUnitId(int unitId) {
    UnitId = unitId;
  }

  public String getUnitName() {
    return UnitName;
  }

  public void setUnitName(String unitName) {
    UnitName = unitName;
  }

  public double getUnitPrice() {
    return UnitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    UnitPrice = unitPrice;
  }

  public double getTotalPrice() {
    return TotalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    TotalPrice = totalPrice;
  }

  public String getSOADesc() {
    return SOADesc;
  }

  public void setSOADesc(String SOADesc) {
    this.SOADesc = SOADesc;
  }

  public double getRemainder() {
    return Remainder;
  }

  public void setRemainder(double remainder) {
    Remainder = remainder;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  public double getAmount1() {
    return Amount1;
  }

  public void setAmount1(double amount1) {
    Amount1 = amount1;
  }

  public double getAmount2() {
    return Amount2;
  }

  public void setAmount2(double amount2) {
    Amount2 = amount2;
  }

  public String getSerialNo1() {
    return SerialNo1;
  }

  public void setSerialNo1(String serialNo1) {
    SerialNo1 = serialNo1;
  }

  public String getSerialNo2() {
    return SerialNo2;
  }

  public void setSerialNo2(String serialNo2) {
    SerialNo2 = serialNo2;
  }


  public static final DiffUtil.ItemCallback<SOArticle> DIFF_CALLBACK = new DiffUtil.ItemCallback<SOArticle>() {
    @Override
    public boolean areItemsTheSame(SOArticle oldItem, SOArticle newItem) {
      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull SOArticle oldItem, @NonNull SOArticle newItem) {
      return oldItem.getId() == newItem.getId();
    }
  };
}
