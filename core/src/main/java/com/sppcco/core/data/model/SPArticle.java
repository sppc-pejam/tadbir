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
  tableName = "__SPArticle__",
  indices = {@Index(value = "SPId")},
  foreignKeys = @ForeignKey(
    entity = SPFactor.class,
    parentColumns = "_id",
    childColumns = "SPId",
    onDelete = ForeignKey.CASCADE)
)
public class SPArticle implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("SPId")
  @Expose
  @ColumnInfo(name = "SPId")
  private int SPId;

  @SerializedName("MerchandiseId")
  @Expose
  @ColumnInfo(name = "MerchandiseId")
  private int MerchandiseId;

  @SerializedName("MerchandiseCode")
  private String MerchandiseCode;

  @SerializedName("MerchandiseName")
  private String MerchandiseName;

  @SerializedName("Amount")
  @Expose
  @ColumnInfo(name = "Amount")
  private double Amount;

  @SerializedName("StockRoomId")
  @Expose
  @ColumnInfo(name = "StockRoomId")
  private int StockRoomId;

  @SerializedName("UnitId")
  @Expose
  @ColumnInfo(name = "UnitId")
  private int UnitId;

  @SerializedName("UnitName")
  private String UnitName;

  @SerializedName("UnitPrice")
  @Expose
  @ColumnInfo(name = "UnitPrice")
  private double UnitPrice;

  @SerializedName("TotalPrice")
  private double TotalPrice;

  @SerializedName("SPADesc")
  @Expose
  @ColumnInfo(name = "SPADesc")
  private String SPADesc;

  @SerializedName("SPARes1")
  @Expose
  @ColumnInfo(name = "SPARes1")
  private int SPARes1;

  @SerializedName("SPARes2")
  @Expose
  @ColumnInfo(name = "SPARes2")
  private int SPARes2;

  @SerializedName("CommissionType")
  @Expose
  @ColumnInfo(name = "CommissionType")
  private int CommissionType;

  @SerializedName("CommissionVal")
  @Expose
  @ColumnInfo(name = "CommissionVal")
  private double CommissionVal;

  @SerializedName("CurrencyVal")
  @Expose
  @ColumnInfo(name = "CurrencyVal")
  private double CurrencyVal;

  @SerializedName("CurrencyId")
  @Expose
  @ColumnInfo(name = "CurrencyId")
  private int CurrencyId;

  @SerializedName("LRes")
  @Expose
  @ColumnInfo(name = "LRes")
  int LRes;

  @SerializedName("DRes")
  @Expose
  @ColumnInfo(name = "DRes")
  double DRes;

  @SerializedName("TRes")
  @Expose
  @ColumnInfo(name = "TRes")
  String TRes;

  @SerializedName("Remainder")
  @Expose
  @ColumnInfo(name = "Remainder")
  private double Remainder;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  int FPId;

  @SerializedName("Amount1")
  @Expose
  @ColumnInfo(name = "Amount1")
  private double Amount1;

  @SerializedName("Amount2")
  @Expose
  @ColumnInfo(name = "Amount2")
  private double Amount2;

  @SerializedName("SerialNo1")
  @Expose
  @ColumnInfo(name = "SerialNo1")
  private String SerialNo1;

  @SerializedName("SerialNo2")
  @Expose
  @ColumnInfo(name = "SerialNo2")
  private String SerialNo2;

  @SerializedName("RowAccId")
  @Expose
  @ColumnInfo(name = "RowAccId")
  private String RowAccId;

  @SerializedName("RowFAccId")
  @Expose
  @ColumnInfo(name = "RowFAccId")
  private int RowFAccId;

  @SerializedName("RowCCId")
  @Expose
  @ColumnInfo(name = "RowCCId")
  private int RowCCId;

  @SerializedName("RowPrjId")
  @Expose
  @ColumnInfo(name = "RowPrjId")
  private int RowPrjId;

  @SerializedName("HasGuaranty")
  @Expose
  @ColumnInfo(name = "HasGuaranty")
  private int HasGuaranty;

  @SerializedName("CabinetId")
  @Expose
  @ColumnInfo(name = "CabinetId")
  private int CabinetId;

  @SerializedName("StockAccId")
  @Expose
  @ColumnInfo(name = "StockAccId")
  private String StockAccId;

  @SerializedName("StockFAccId")
  @Expose
  @ColumnInfo(name = "StockFAccId")
  private int StockFAccId;

  @SerializedName("StockCCId")
  @Expose
  @ColumnInfo(name = "StockCCId")
  private int StockCCId;

  @SerializedName("StockPrjId")
  @Expose
  @ColumnInfo(name = "StockPrjId")
  private int StockPrjId;

  @SerializedName("NetPrice")
  @Expose
  @ColumnInfo(name = "NetPrice")
  private double NetPrice;

  @SerializedName("T1")
  @Expose
  @ColumnInfo(name = "T1")
  private double T1;

  @SerializedName("T2")
  @Expose
  @ColumnInfo(name = "T12")
  private double T2;

  public SPArticle(){
    super();
  }

  public SPArticle(int id, int SPId, int merchandiseId, String merchandiseCode, String merchandiseName, double amount, int stockRoomId, int unitId, String unitName, double unitPrice, double totalPrice, String SPADesc, int SPARes1, int SPARes2, int commissionType, double commissionVal, double currencyVal, int currencyId, int LRes, double DRes, String TRes, double remainder, int FPId, double amount1, double amount2, String serialNo1, String serialNo2, String rowAccId, int rowFAccId, int rowCCId, int rowPrjId, int hasGuaranty, int cabinetId, String stockAccId, int stockFAccId, int stockCCId, int stockPrjId, double netPrice, double t1, double t2) {
    Id = id;
    this.SPId = SPId;
    MerchandiseId = merchandiseId;
    MerchandiseCode = merchandiseCode;
    MerchandiseName = merchandiseName;
    Amount = amount;
    StockRoomId = stockRoomId;
    UnitId = unitId;
    UnitName = unitName;
    UnitPrice = unitPrice;
    TotalPrice = totalPrice;
    this.SPADesc = SPADesc;
    this.SPARes1 = SPARes1;
    this.SPARes2 = SPARes2;
    CommissionType = commissionType;
    CommissionVal = commissionVal;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    Remainder = remainder;
    this.FPId = FPId;
    Amount1 = amount1;
    Amount2 = amount2;
    SerialNo1 = serialNo1;
    SerialNo2 = serialNo2;
    RowAccId = rowAccId;
    RowFAccId = rowFAccId;
    RowCCId = rowCCId;
    RowPrjId = rowPrjId;
    HasGuaranty = hasGuaranty;
    CabinetId = cabinetId;
    StockAccId = stockAccId;
    StockFAccId = stockFAccId;
    StockCCId = stockCCId;
    StockPrjId = stockPrjId;
    NetPrice = netPrice;
    T1 = t1;
    T2 = t2;
  }


  public SPArticle(int SPId, int merchandiseId, String merchandiseCode, String merchandiseName, double amount, int stockRoomId, int unitId, String unitName, double unitPrice, double totalPrice, String SPADesc, int SPARes1, int SPARes2, int commissionType, double commissionVal, double currencyVal, int currencyId, int LRes, double DRes, String TRes, double remainder, int FPId, double amount1, double amount2, String serialNo1, String serialNo2, String rowAccId, int rowFAccId, int rowCCId, int rowPrjId, int hasGuaranty, int cabinetId, String stockAccId, int stockFAccId, int stockCCId, int stockPrjId, double netPrice, double t1, double t2) {
    this.SPId = SPId;
    MerchandiseId = merchandiseId;
    MerchandiseCode = merchandiseCode;
    MerchandiseName = merchandiseName;
    Amount = amount;
    StockRoomId = stockRoomId;
    UnitId = unitId;
    UnitName = unitName;
    UnitPrice = unitPrice;
    TotalPrice = totalPrice;
    this.SPADesc = SPADesc;
    this.SPARes1 = SPARes1;
    this.SPARes2 = SPARes2;
    CommissionType = commissionType;
    CommissionVal = commissionVal;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    Remainder = remainder;
    this.FPId = FPId;
    Amount1 = amount1;
    Amount2 = amount2;
    SerialNo1 = serialNo1;
    SerialNo2 = serialNo2;
    RowAccId = rowAccId;
    RowFAccId = rowFAccId;
    RowCCId = rowCCId;
    RowPrjId = rowPrjId;
    HasGuaranty = hasGuaranty;
    CabinetId = cabinetId;
    StockAccId = stockAccId;
    StockFAccId = stockFAccId;
    StockCCId = stockCCId;
    StockPrjId = stockPrjId;
    NetPrice = netPrice;
    T1 = t1;
    T2 = t2;
  }


  public static SPArticle getSPArticleWithDefaultValue() {
    SPArticle spArticle = new SPArticle();

    spArticle.SPId = 0;
    spArticle.MerchandiseId = 0;
    spArticle.MerchandiseCode = "";
    spArticle.MerchandiseName = "";
    spArticle.Amount = 0;
    spArticle.StockRoomId = 0;
    spArticle.UnitId = 0;
    spArticle.UnitName = "";
    spArticle.UnitPrice = 0;
    spArticle.TotalPrice = 0;
    spArticle.SPADesc = "";
    spArticle.SPARes1 = 0;
    spArticle.SPARes2 = 0;
    spArticle.CommissionType = 0;
    spArticle.CommissionVal = 0;
    spArticle.CurrencyVal = 0;
    spArticle.CurrencyId = 0;
    spArticle.LRes = 0;
    spArticle.DRes = 0;
    spArticle.TRes = "";
    spArticle.Remainder = 0;
    spArticle.FPId = BaseApplication.getFPId();
    spArticle.Amount1 = 0;
    spArticle.Amount2 = 0;
    spArticle.SerialNo1 = "";
    spArticle.SerialNo2 = "";
    spArticle.RowAccId = "";
    spArticle.RowFAccId = 0;
    spArticle.RowCCId = 0;
    spArticle.RowPrjId = 0;
    spArticle.HasGuaranty = 0;
    spArticle.CabinetId = 0;
    spArticle.StockAccId = "";
    spArticle.StockFAccId = 0;
    spArticle.StockCCId = 0;
    spArticle.StockPrjId = 0;
    spArticle.NetPrice = 0;
    spArticle.T1 = 0;
    spArticle.T2 = 0;

    return spArticle;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getSPId() {
    return SPId;
  }

  public void setSPId(int SPId) {
    this.SPId = SPId;
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

  public int getStockRoomId() {
    return StockRoomId;
  }

  public void setStockRoomId(int stockRoomId) {
    StockRoomId = stockRoomId;
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

  public String getSPADesc() {
    return SPADesc;
  }

  public void setSPADesc(String SPADesc) {
    this.SPADesc = SPADesc;
  }

  public int getSPARes1() {
    return SPARes1;
  }

  public void setSPARes1(int SPARes1) {
    this.SPARes1 = SPARes1;
  }

  public int getSPARes2() {
    return SPARes2;
  }

  public void setSPARes2(int SPARes2) {
    this.SPARes2 = SPARes2;
  }

  public int getCommissionType() {
    return CommissionType;
  }

  public void setCommissionType(int commissionType) {
    CommissionType = commissionType;
  }

  public double getCommissionVal() {
    return CommissionVal;
  }

  public void setCommissionVal(double commissionVal) {
    CommissionVal = commissionVal;
  }

  public double getCurrencyVal() {
    return CurrencyVal;
  }

  public void setCurrencyVal(double currencyVal) {
    CurrencyVal = currencyVal;
  }

  public int getCurrencyId() {
    return CurrencyId;
  }

  public void setCurrencyId(int currencyId) {
    CurrencyId = currencyId;
  }

  public int getLRes() {
    return LRes;
  }

  public void setLRes(int LRes) {
    this.LRes = LRes;
  }

  public double getDRes() {
    return DRes;
  }

  public void setDRes(double DRes) {
    this.DRes = DRes;
  }

  public String getTRes() {
    return TRes;
  }

  public void setTRes(String TRes) {
    this.TRes = TRes;
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

  public String getRowAccId() {
    return RowAccId;
  }

  public void setRowAccId(String rowAccId) {
    RowAccId = rowAccId;
  }

  public int getRowFAccId() {
    return RowFAccId;
  }

  public void setRowFAccId(int rowFAccId) {
    RowFAccId = rowFAccId;
  }

  public int getRowCCId() {
    return RowCCId;
  }

  public void setRowCCId(int rowCCId) {
    RowCCId = rowCCId;
  }

  public int getRowPrjId() {
    return RowPrjId;
  }

  public void setRowPrjId(int rowPrjId) {
    RowPrjId = rowPrjId;
  }

  public int getHasGuaranty() {
    return HasGuaranty;
  }

  public void setHasGuaranty(int hasGuaranty) {
    HasGuaranty = hasGuaranty;
  }

  public int getCabinetId() {
    return CabinetId;
  }

  public void setCabinetId(int cabinetId) {
    CabinetId = cabinetId;
  }

  public String getStockAccId() {
    return StockAccId;
  }

  public void setStockAccId(String stockAccId) {
    StockAccId = stockAccId;
  }

  public int getStockFAccId() {
    return StockFAccId;
  }

  public void setStockFAccId(int stockFAccId) {
    StockFAccId = stockFAccId;
  }

  public int getStockCCId() {
    return StockCCId;
  }

  public void setStockCCId(int stockCCId) {
    StockCCId = stockCCId;
  }

  public int getStockPrjId() {
    return StockPrjId;
  }

  public void setStockPrjId(int stockPrjId) {
    StockPrjId = stockPrjId;
  }

  public double getNetPrice() {
    return NetPrice;
  }

  public void setNetPrice(double netPrice) {
    NetPrice = netPrice;
  }

  public double getT1() {
    return T1;
  }

  public void setT1(double t1) {
    T1 = t1;
  }

  public double getT2() {
    return T2;
  }

  public void setT2(double t2) {
    T2 = t2;
  }



  public static final DiffUtil.ItemCallback<SPArticle>  DIFF_CALLBACK =  new DiffUtil.ItemCallback<SPArticle>() {
    @Override
    public boolean areItemsTheSame(SPArticle oldItem, SPArticle newItem) {
      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull SPArticle oldItem, @NonNull SPArticle newItem) {
      return oldItem.getId() == newItem.getId();
    }
  };
}
