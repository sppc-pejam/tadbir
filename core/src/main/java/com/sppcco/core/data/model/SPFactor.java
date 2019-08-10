package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.enums.FactorType;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.converter.timestamp.TimestampConverter;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "__SPFactor__")
public class SPFactor implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("FactorNo")
  @Expose
  @ColumnInfo(name = "FactorNo")
  int FactorNo;

  @SerializedName("SPDate")
  @Expose
  @ColumnInfo(name = "SPDate")
  @TypeConverters({TimestampConverter.class})
  Date SPDate;

  @SerializedName("FactorType")
  @Expose
  @ColumnInfo(name = "FactorType")
  int FactorType;

  @SerializedName("Committed")
  @Expose
  @ColumnInfo(name = "Committed")
  int Committed;

  @SerializedName("SPReference")
  @Expose
  @ColumnInfo(name = "SPReference")
  int SPReference;

  @SerializedName("AccountId")
  @Expose
  @ColumnInfo(name = "AccountId")
  String AccountId;

  @SerializedName("SPAccountId")
  @Expose
  @ColumnInfo(name = "SPAccountId")
  String SPAccountId;

  @SerializedName("CustomerId")
  @Expose
  @ColumnInfo(name = "CustomerId")
  int CustomerId;

  @SerializedName("FAccId")
  @Expose
  @ColumnInfo(name = "FAccId")
  int FAccId;

  @SerializedName("PreAccId")
  @Expose
  @ColumnInfo(name = "PreAccId")
  String PreAccId;

  @SerializedName("PreAccPercent")
  @Expose
  @ColumnInfo(name = "PreAccPercent")
  double PreAccPercent;

  @SerializedName("Total")
  @Expose
  @ColumnInfo(name = "Total")
  double Total;

  @SerializedName("Discount")
  @Expose
  @ColumnInfo(name = "Discount")
  double Discount;

  @SerializedName("Expense")
  @Expose
  @ColumnInfo(name = "Expense")
  double Expense;

  @SerializedName("SPDesc")
  @Expose
  @ColumnInfo(name = "SPDesc")
  String SPDesc;

  @SerializedName("CustomerName")
  @Expose
  @ColumnInfo(name = "CustomerName")
  String CustomerName;

  @SerializedName("CustomerPhoneNo")
  @Expose
  @ColumnInfo(name = "CustomerPhoneNo")
  String CustomerPhoneNo;

  @SerializedName("CustomerEcCode")
  @Expose
  @ColumnInfo(name = "CustomerEcCode")
  String CustomerEcCode;

  @SerializedName("CustomerAddress")
  @Expose
  @ColumnInfo(name = "CustomerAddress")
  String CustomerAddress;

  @SerializedName("BrokerId")
  @Expose
  @ColumnInfo(name = "BrokerId")
  int BrokerId;

  @SerializedName("BrokerShare")
  @Expose
  @ColumnInfo(name = "BrokerShare")
  double BrokerShare;

  @SerializedName("BrokerPercent")
  @Expose
  @ColumnInfo(name = "BrokerPercent")
  double BrokerPercent;

  @SerializedName("CurrencyVal")
  @Expose
  @ColumnInfo(name = "CurrencyVal")
  double CurrencyVal;

  @SerializedName("CurrencyId")
  @Expose
  @ColumnInfo(name = "CurrencyId")
  int CurrencyId;

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

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  int UserId;

  @SerializedName("SecId")
  @Expose
  @ColumnInfo(name = "SecId")
  int SecId;

  @SerializedName("ETime")
  @Expose
  @ColumnInfo(name = "ETime")
  @TypeConverters({TimestampConverter.class})
  Date ETime;

  @SerializedName("EDate")
  @Expose
  @ColumnInfo(name = "EDate")
  @TypeConverters({TimestampConverter.class})
  Date EDate;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  int FPId;

  @SerializedName("CCId")
  @Expose
  @ColumnInfo(name = "CCId")
  int CCId;

  @SerializedName("PrjId")
  @Expose
  @ColumnInfo(name = "PrjId")
  int PrjId;

  @SerializedName("SPFAccId")
  @Expose
  @ColumnInfo(name = "SPFAccId")
  int SPFAccId;

  @SerializedName("SPCCId")
  @Expose
  @ColumnInfo(name = "SPCCId")
  int SPCCId;

  @SerializedName("SPPrjId")
  @Expose
  @ColumnInfo(name = "SPPrjId")
  int SPPrjId;

  @SerializedName("RONo")
  @Expose
  @ColumnInfo(name = "RONo")
  int RONo;

  @SerializedName("SPRefNo")
  @Expose
  @ColumnInfo(name = "SPRefNo")
  String SPRefNo;

  @SerializedName("ContractNo")
  @Expose
  @ColumnInfo(name = "ContractNo")
  String ContractNo;

  @SerializedName("ShipmentNo")
  @Expose
  @ColumnInfo(name = "ShipmentNo")
  String ShipmentNo;

  @SerializedName("Cancelled")
  @Expose
  @ColumnInfo(name = "Cancelled")
  int Cancelled;

  @SerializedName("SvcJobNo")
  @Expose
  @ColumnInfo(name = "SvcJobNo")
  int SvcJobNo;

  @SerializedName("SvcDesc")
  @Expose
  @ColumnInfo(name = "SvcDesc")
  String SvcDesc;

  @SerializedName("SvcCommPercent")
  @Expose
  @ColumnInfo(name = "SvcCommPercent")
  double SvcCommPercent;

  @SerializedName("SvcCommAmount")
  @Expose
  @ColumnInfo(name = "SvcCommAmount")
  double SvcCommAmount;

  @SerializedName("PreFAccId")
  @Expose
  @ColumnInfo(name = "PreFAccId")
  int PreFAccId;

  @SerializedName("PreCCId")
  @Expose
  @ColumnInfo(name = "PreCCId")
  int PreCCId;

  @SerializedName("PrePrjId")
  @Expose
  @ColumnInfo(name = "PrePrjId")
  int PrePrjId;

  @SerializedName("PRetFAccId")
  @Expose
  @ColumnInfo(name = "PRetFAccId")
  int PRetFAccId;

  @SerializedName("PRetCCId")
  @Expose
  @ColumnInfo(name = "PRetCCId")
  int PRetCCId;

  @SerializedName("PRetPrjId")
  @Expose
  @ColumnInfo(name = "PRetPrjId")
  int PRetPrjId;

  @SerializedName("TEnable")
  @Expose
  @ColumnInfo(name = "TEnable")
  int TEnable;

  @SerializedName("TValue")
  @Expose
  @ColumnInfo(name = "TValue")
  double TValue;

  @SerializedName("TRS")
  @Expose
  @ColumnInfo(name = "TRS")
  int TRS;

  public SPFactor() {
    super();
  }

  public SPFactor(
    int id, int factorNo, Date SPDate, int factorType, int committed, int SPReference, String accountId,
    String SPAccountId, int customerId, int FAccId, String preAccId, double preAccPercent, double total,
    double discount, double expense, String SPDesc, String customerName, String customerPhoneNo,
    String customerEcCode, String customerAddress, int brokerId, double brokerShare, double brokerPercent,
    double currencyVal, int currencyId, int LRes, double DRes, String TRes, int userId, int secId,
    Date ETime, Date EDate, int FPId, int CCId, int prjId, int SPFAccId, int SPCCId, int SPPrjId,
    int RONo, String SPRefNo, String contractNo, String shipmentNo, int cancelled, int svcJobNo, String svcDesc,
    double svcCommPercent, double svcCommAmount, int preFAccId, int preCCId, int prePrjId, int PRetFAccId,
    int PRetCCId, int PRetPrjId, int TEnable, double TValue, int TRS) {

    Id = id;
    FactorNo = factorNo;
    this.SPDate = SPDate;
    FactorType = factorType;
    Committed = committed;
    this.SPReference = SPReference;
    AccountId = accountId;
    this.SPAccountId = SPAccountId;
    CustomerId = customerId;
    this.FAccId = FAccId;
    PreAccId = preAccId;
    PreAccPercent = preAccPercent;
    Total = total;
    Discount = discount;
    Expense = expense;
    this.SPDesc = SPDesc;
    CustomerName = customerName;
    CustomerPhoneNo = customerPhoneNo;
    CustomerEcCode = customerEcCode;
    CustomerAddress = customerAddress;
    BrokerId = brokerId;
    BrokerShare = brokerShare;
    BrokerPercent = brokerPercent;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    UserId = userId;
    SecId = secId;
    this.ETime = ETime;
    this.EDate = EDate;
    this.FPId = FPId;
    this.CCId = CCId;
    PrjId = prjId;
    this.SPFAccId = SPFAccId;
    this.SPCCId = SPCCId;
    this.SPPrjId = SPPrjId;
    this.RONo = RONo;
    this.SPRefNo = SPRefNo;
    ContractNo = contractNo;
    ShipmentNo = shipmentNo;
    Cancelled = cancelled;
    SvcJobNo = svcJobNo;
    SvcDesc = svcDesc;
    SvcCommPercent = svcCommPercent;
    SvcCommAmount = svcCommAmount;
    PreFAccId = preFAccId;
    PreCCId = preCCId;
    PrePrjId = prePrjId;
    this.PRetFAccId = PRetFAccId;
    this.PRetCCId = PRetCCId;
    this.PRetPrjId = PRetPrjId;
    this.TEnable = TEnable;
    this.TValue = TValue;
    this.TRS = TRS;
  }



  public SPFactor( int factorNo, Date SPDate, int factorType, int committed, int SPReference, String accountId, String SPAccountId, int customerId, int FAccId, String preAccId, double preAccPercent, double total, double discount, double expense, String SPDesc, String customerName, String customerPhoneNo, String customerEcCode, String customerAddress, int brokerId, double brokerShare, double brokerPercent, double currencyVal, int currencyId, int LRes, double DRes, String TRes, int userId, int secId, Date ETime, Date EDate, int FPId, int CCId, int prjId, int SPFAccId, int SPCCId, int SPPrjId, int RONo, String SPRefNo, String contractNo, String shipmentNo, int cancelled, int svcJobNo, String svcDesc, double svcCommPercent, double svcCommAmount, int preFAccId, int preCCId, int prePrjId, int PRetFAccId, int PRetCCId, int PRetPrjId, int TEnable, double TValue, int TRS) {
    FactorNo = factorNo;
    this.SPDate = SPDate;
    FactorType = factorType;
    Committed = committed;
    this.SPReference = SPReference;
    AccountId = accountId;
    this.SPAccountId = SPAccountId;
    CustomerId = customerId;
    this.FAccId = FAccId;
    PreAccId = preAccId;
    PreAccPercent = preAccPercent;
    Total = total;
    Discount = discount;
    Expense = expense;
    this.SPDesc = SPDesc;
    CustomerName = customerName;
    CustomerPhoneNo = customerPhoneNo;
    CustomerEcCode = customerEcCode;
    CustomerAddress = customerAddress;
    BrokerId = brokerId;
    BrokerShare = brokerShare;
    BrokerPercent = brokerPercent;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    UserId = userId;
    SecId = secId;
    this.ETime = ETime;
    this.EDate = EDate;
    this.FPId = FPId;
    this.CCId = CCId;
    PrjId = prjId;
    this.SPFAccId = SPFAccId;
    this.SPCCId = SPCCId;
    this.SPPrjId = SPPrjId;
    this.RONo = RONo;
    this.SPRefNo = SPRefNo;
    ContractNo = contractNo;
    ShipmentNo = shipmentNo;
    Cancelled = cancelled;
    SvcJobNo = svcJobNo;
    SvcDesc = svcDesc;
    SvcCommPercent = svcCommPercent;
    SvcCommAmount = svcCommAmount;
    PreFAccId = preFAccId;
    PreCCId = preCCId;
    PrePrjId = prePrjId;
    this.PRetFAccId = PRetFAccId;
    this.PRetCCId = PRetCCId;
    this.PRetPrjId = PRetPrjId;
    this.TEnable = TEnable;
    this.TValue = TValue;
    this.TRS = TRS;
  }


  public static SPFactor getSPFactorWithDefaultValue() {

    SPFactor spFactor = new SPFactor();

    spFactor.FactorNo = 0;
    spFactor.SPDate = CalenderManager.getDateStampTime();
    spFactor.FactorType = com.sppcco.core.enums.FactorType.SP_PRESALES.getValue();
    spFactor.Committed = 0;
    spFactor.SPReference = 0;
    spFactor.AccountId = "0";
    spFactor.SPAccountId = "0";
    spFactor.CustomerId = 0;
    spFactor.FAccId = 0;
    spFactor.PreAccId = "0";
    spFactor.PreAccPercent = 0;
    spFactor.Total = 0;
    spFactor.Discount = 0;
    spFactor.Expense = 0;
    spFactor.SPDesc = "";
    spFactor.CustomerName = "";
    spFactor.CustomerPhoneNo = "";
    spFactor.CustomerEcCode = "";
    spFactor.CustomerAddress = "";
    spFactor.BrokerId = 0;
    spFactor.BrokerShare = 0;
    spFactor.BrokerPercent = 0;
    spFactor.CurrencyVal = 0;
    spFactor.CurrencyId = 0;
    spFactor.LRes = 0;
    spFactor.DRes = 0;
    spFactor.TRes = "0";
    spFactor.UserId = BaseApplication.getUserId();
    spFactor.SecId = 0;
    spFactor.ETime = CalenderManager.getCurrentDateStampTime();
    spFactor.EDate = CalenderManager.getCurrentDateStampTime();
    spFactor.FPId = BaseApplication.getFPId();
    spFactor.CCId = 0;
    spFactor.PrjId = 0;
    spFactor.SPFAccId = 0;
    spFactor.SPCCId = 0;
    spFactor.SPPrjId = 0;
    spFactor.RONo = 0;
    spFactor.SPRefNo = "";
    spFactor.ContractNo = "";
    spFactor.ShipmentNo = "";
    spFactor.Cancelled = 0;
    spFactor.SvcJobNo = 0;
    spFactor.SvcDesc = "";
    spFactor.SvcCommPercent = 0;
    spFactor.SvcCommAmount = 0;
    spFactor.PreFAccId = 0;
    spFactor.PreCCId = 0;
    spFactor.PrePrjId = 0;
    spFactor.PRetFAccId = 0;
    spFactor.PRetCCId = 0;
    spFactor.PRetPrjId = 0;
    spFactor.TEnable = 0;
    spFactor.TValue = 0;
    spFactor.TRS = BaseApplication.getFPId();

    return spFactor;
  }


  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getFactorNo() {
    return FactorNo;
  }

  public void setFactorNo(int factorNo) {
    FactorNo = factorNo;
  }

  public Date getSPDate() {
    return SPDate;
  }

  public void setSPDate(Date SPDate) {
    this.SPDate = SPDate;
  }

  public int getFactorType() {
    return FactorType;
  }

  public void setFactorType(int factorType) {
    FactorType = factorType;
  }

  public int getCommitted() {
    return Committed;
  }

  public void setCommitted(int committed) {
    Committed = committed;
  }

  public int getSPReference() {
    return SPReference;
  }

  public void setSPReference(int SPReference) {
    this.SPReference = SPReference;
  }

  public String getAccountId() {
    return AccountId;
  }

  public void setAccountId(String accountId) {
    AccountId = accountId;
  }

  public String getSPAccountId() {
    return SPAccountId;
  }

  public void setSPAccountId(String SPAccountId) {
    this.SPAccountId = SPAccountId;
  }

  public int getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(int customerId) {
    CustomerId = customerId;
  }

  public int getFAccId() {
    return FAccId;
  }

  public void setFAccId(int FAccId) {
    this.FAccId = FAccId;
  }

  public String getPreAccId() {
    return PreAccId;
  }

  public void setPreAccId(String preAccId) {
    PreAccId = preAccId;
  }

  public double getPreAccPercent() {
    return PreAccPercent;
  }

  public void setPreAccPercent(double preAccPercent) {
    PreAccPercent = preAccPercent;
  }

  public double getTotal() {
    return Total;
  }

  public void setTotal(double total) {
    Total = total;
  }

  public double getDiscount() {
    return Discount;
  }

  public void setDiscount(double discount) {
    Discount = discount;
  }

  public double getExpense() {
    return Expense;
  }

  public void setExpense(double expense) {
    Expense = expense;
  }

  public String getSPDesc() {
    return SPDesc;
  }

  public void setSPDesc(String SPDesc) {
    this.SPDesc = SPDesc;
  }

  public String getCustomerName() {
    return CustomerName;
  }

  public void setCustomerName(String customerName) {
    CustomerName = customerName;
  }

  public String getCustomerPhoneNo() {
    return CustomerPhoneNo;
  }

  public void setCustomerPhoneNo(String customerPhoneNo) {
    CustomerPhoneNo = customerPhoneNo;
  }

  public String getCustomerEcCode() {
    return CustomerEcCode;
  }

  public void setCustomerEcCode(String customerEcCode) {
    CustomerEcCode = customerEcCode;
  }

  public String getCustomerAddress() {
    return CustomerAddress;
  }

  public void setCustomerAddress(String customerAddress) {
    CustomerAddress = customerAddress;
  }

  public int getBrokerId() {
    return BrokerId;
  }

  public void setBrokerId(int brokerId) {
    BrokerId = brokerId;
  }

  public double getBrokerShare() {
    return BrokerShare;
  }

  public void setBrokerShare(double brokerShare) {
    BrokerShare = brokerShare;
  }

  public double getBrokerPercent() {
    return BrokerPercent;
  }

  public void setBrokerPercent(double brokerPercent) {
    BrokerPercent = brokerPercent;
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

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }

  public int getSecId() {
    return SecId;
  }

  public void setSecId(int secId) {
    SecId = secId;
  }

  public Date getETime() {
    return ETime;
  }

  public void setETime(Date ETime) {
    this.ETime = ETime;
  }

  public Date getEDate() {
    return EDate;
  }

  public void setEDate(Date EDate) {
    this.EDate = EDate;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
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

  public int getSPFAccId() {
    return SPFAccId;
  }

  public void setSPFAccId(int SPFAccId) {
    this.SPFAccId = SPFAccId;
  }

  public int getSPCCId() {
    return SPCCId;
  }

  public void setSPCCId(int SPCCId) {
    this.SPCCId = SPCCId;
  }

  public int getSPPrjId() {
    return SPPrjId;
  }

  public void setSPPrjId(int SPPrjId) {
    this.SPPrjId = SPPrjId;
  }

  public int getRONo() {
    return RONo;
  }

  public void setRONo(int RONo) {
    this.RONo = RONo;
  }

  public String getSPRefNo() {
    return SPRefNo;
  }

  public void setSPRefNo(String SPRefNo) {
    this.SPRefNo = SPRefNo;
  }

  public String getContractNo() {
    return ContractNo;
  }

  public void setContractNo(String contractNo) {
    ContractNo = contractNo;
  }

  public String getShipmentNo() {
    return ShipmentNo;
  }

  public void setShipmentNo(String shipmentNo) {
    ShipmentNo = shipmentNo;
  }

  public int getCancelled() {
    return Cancelled;
  }

  public void setCancelled(int cancelled) {
    Cancelled = cancelled;
  }

  public int getSvcJobNo() {
    return SvcJobNo;
  }

  public void setSvcJobNo(int svcJobNo) {
    SvcJobNo = svcJobNo;
  }

  public String getSvcDesc() {
    return SvcDesc;
  }

  public void setSvcDesc(String svcDesc) {
    SvcDesc = svcDesc;
  }

  public double getSvcCommPercent() {
    return SvcCommPercent;
  }

  public void setSvcCommPercent(double svcCommPercent) {
    SvcCommPercent = svcCommPercent;
  }

  public double getSvcCommAmount() {
    return SvcCommAmount;
  }

  public void setSvcCommAmount(double svcCommAmount) {
    SvcCommAmount = svcCommAmount;
  }

  public int getPreFAccId() {
    return PreFAccId;
  }

  public void setPreFAccId(int preFAccId) {
    PreFAccId = preFAccId;
  }

  public int getPreCCId() {
    return PreCCId;
  }

  public void setPreCCId(int preCCId) {
    PreCCId = preCCId;
  }

  public int getPrePrjId() {
    return PrePrjId;
  }

  public void setPrePrjId(int prePrjId) {
    PrePrjId = prePrjId;
  }

  public int getPRetFAccId() {
    return PRetFAccId;
  }

  public void setPRetFAccId(int PRetFAccId) {
    this.PRetFAccId = PRetFAccId;
  }

  public int getPRetCCId() {
    return PRetCCId;
  }

  public void setPRetCCId(int PRetCCId) {
    this.PRetCCId = PRetCCId;
  }

  public int getPRetPrjId() {
    return PRetPrjId;
  }

  public void setPRetPrjId(int PRetPrjId) {
    this.PRetPrjId = PRetPrjId;
  }

  public int getTEnable() {
    return TEnable;
  }

  public void setTEnable(int TEnable) {
    this.TEnable = TEnable;
  }

  public double getTValue() {
    return TValue;
  }

  public void setTValue(double TValue) {
    this.TValue = TValue;
  }

  public int getTRS() {
    return TRS;
  }

  public void setTRS(int TRS) {
    this.TRS = TRS;
  }
}
