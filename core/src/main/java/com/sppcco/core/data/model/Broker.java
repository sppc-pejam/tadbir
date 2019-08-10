package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * Created by b_nematzadeh on 11/16/2018.
 */

@Entity(tableName = "__Broker__",
  primaryKeys = {"_id", "FPId"})
public class Broker implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("Name")
  @Expose
  @ColumnInfo(name = "Name")
  private String Name;

  @SerializedName("PhoneNo")
  @Expose
  @ColumnInfo(name = "PhoneNo")
  private String PhoneNo;

  @SerializedName("FaxNo")
  @Expose
  @ColumnInfo(name = "FaxNo")
  private String FaxNo;

  @SerializedName("Email")
  @Expose
  @ColumnInfo(name = "Email")
  private String Email;

  @SerializedName("EcCode")
  @Expose
  @ColumnInfo(name = "EcCode")
  private String EcCode;

  @SerializedName("Address")
  @Expose
  @ColumnInfo(name = "Address")
  private String Address;

  @SerializedName("ZipCode")
  @Expose
  @ColumnInfo(name = "ZipCode")
  private String ZipCode;

  @SerializedName("BDesc")
  @Expose
  @ColumnInfo(name = "BDesc")
  private String BDesc;

  @SerializedName("AccId")
  @Expose
  @ColumnInfo(name = "AccId")
  private String AccId;

  @SerializedName("FAccId")
  @Expose
  @ColumnInfo(name = "FAccId")
  private int FAccId;

  @SerializedName("CostAccId")
  @Expose
  @ColumnInfo(name = "CostAccId")
  private String CostAccId;

  @SerializedName("CostFAccId")
  @Expose
  @ColumnInfo(name = "CostFAccId")
  private int CostFAccId;

  @SerializedName("IncomeAccId")
  @Expose
  @ColumnInfo(name = "IncomeAccId")
  private String IncomeAccId;

  @SerializedName("IncomeFAccId")
  @Expose
  @ColumnInfo(name = "IncomeFAccId")
  private int IncomeFAccId;

  @SerializedName("CommissionPercent")
  @Expose
  @ColumnInfo(name = "CommissionPercent")
  private float CommissionPercent;

  @SerializedName("CommissionAmount")
  @Expose
  @ColumnInfo(name = "CommissionAmount")
  private float CommissionAmount;

  @SerializedName("CurrencyVal")
  @Expose
  @ColumnInfo(name = "CurrencyVal")
  private float CurrencyVal;

  @SerializedName("CurrencyId")
  @Expose
  @ColumnInfo(name = "CurrencyId")
  private int CurrencyId;

  @SerializedName("InitBal")
  @Expose
  @ColumnInfo(name = "InitBal")
  private float InitBal;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  @SerializedName("SecId")
  @Expose
  @ColumnInfo(name = "SecId")
  private int SecId;

  @SerializedName("Type")
  @Expose
  @ColumnInfo(name = "Type")
  private int Type;

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

  @SerializedName("CCId")
  @Expose
  @ColumnInfo(name = "CCId")
  private int CCId;

  @SerializedName("PrjId")
  @Expose
  @ColumnInfo(name = "PrjId")
  private int PrjId;

  @SerializedName("CostCCId")
  @Expose
  @ColumnInfo(name = "CostCCId")
  private int CostCCId;

  @SerializedName("CostPrjId")
  @Expose
  @ColumnInfo(name = "CostPrjId")
  private int CostPrjId;

  @SerializedName("IncomeCCId")
  @Expose
  @ColumnInfo(name = "IncomeCCId")
  private int IncomeCCId;

  @SerializedName("IncomePrjId")
  @Expose
  @ColumnInfo(name = "IncomePrjId")
  private int IncomePrjId;

  public Broker() {
    super();
  }

  public Broker(int id, String name, String phoneNo, String faxNo, String email, String ecCode, String address, String zipCode,
                String BDesc, String accId, int FAccId, String costAccId, int costFAccId, String incomeAccId, int incomeFAccId,
                float commissionPercent, float commissionAmount, float currencyVal, int currencyId, float initBal, int userId,
                int secId, int type, int LRes, float DRes, String TRes, int FPId, int CCId, int prjId, int costCCId, int costPrjId,
                int incomeCCId, int incomePrjId) {
    Id = id;
    Name = name;
    PhoneNo = phoneNo;
    FaxNo = faxNo;
    Email = email;
    EcCode = ecCode;
    Address = address;
    ZipCode = zipCode;
    this.BDesc = BDesc;
    AccId = accId;
    this.FAccId = FAccId;
    CostAccId = costAccId;
    CostFAccId = costFAccId;
    IncomeAccId = incomeAccId;
    IncomeFAccId = incomeFAccId;
    CommissionPercent = commissionPercent;
    CommissionAmount = commissionAmount;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    InitBal = initBal;
    UserId = userId;
    SecId = secId;
    Type = type;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    this.FPId = FPId;
    this.CCId = CCId;
    PrjId = prjId;
    CostCCId = costCCId;
    CostPrjId = costPrjId;
    IncomeCCId = incomeCCId;
    IncomePrjId = incomePrjId;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getPhoneNo() {
    return PhoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    PhoneNo = phoneNo;
  }

  public String getFaxNo() {
    return FaxNo;
  }

  public void setFaxNo(String faxNo) {
    FaxNo = faxNo;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getEcCode() {
    return EcCode;
  }

  public void setEcCode(String ecCode) {
    EcCode = ecCode;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String address) {
    Address = address;
  }

  public String getZipCode() {
    return ZipCode;
  }

  public void setZipCode(String zipCode) {
    ZipCode = zipCode;
  }

  public String getBDesc() {
    return BDesc;
  }

  public void setBDesc(String BDesc) {
    this.BDesc = BDesc;
  }

  public String getAccId() {
    return AccId;
  }

  public void setAccId(String accId) {
    AccId = accId;
  }

  public int getFAccId() {
    return FAccId;
  }

  public void setFAccId(int FAccId) {
    this.FAccId = FAccId;
  }

  public String getCostAccId() {
    return CostAccId;
  }

  public void setCostAccId(String costAccId) {
    CostAccId = costAccId;
  }

  public int getCostFAccId() {
    return CostFAccId;
  }

  public void setCostFAccId(int costFAccId) {
    CostFAccId = costFAccId;
  }

  public String getIncomeAccId() {
    return IncomeAccId;
  }

  public void setIncomeAccId(String incomeAccId) {
    IncomeAccId = incomeAccId;
  }

  public int getIncomeFAccId() {
    return IncomeFAccId;
  }

  public void setIncomeFAccId(int incomeFAccId) {
    IncomeFAccId = incomeFAccId;
  }

  public float getCommissionPercent() {
    return CommissionPercent;
  }

  public void setCommissionPercent(float commissionPercent) {
    CommissionPercent = commissionPercent;
  }

  public float getCommissionAmount() {
    return CommissionAmount;
  }

  public void setCommissionAmount(float commissionAmount) {
    CommissionAmount = commissionAmount;
  }

  public float getCurrencyVal() {
    return CurrencyVal;
  }

  public void setCurrencyVal(float currencyVal) {
    CurrencyVal = currencyVal;
  }

  public int getCurrencyId() {
    return CurrencyId;
  }

  public void setCurrencyId(int currencyId) {
    CurrencyId = currencyId;
  }

  public float getInitBal() {
    return InitBal;
  }

  public void setInitBal(float initBal) {
    InitBal = initBal;
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

  public int getType() {
    return Type;
  }

  public void setType(int type) {
    Type = type;
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

  public int getCostCCId() {
    return CostCCId;
  }

  public void setCostCCId(int costCCId) {
    CostCCId = costCCId;
  }

  public int getCostPrjId() {
    return CostPrjId;
  }

  public void setCostPrjId(int costPrjId) {
    CostPrjId = costPrjId;
  }

  public int getIncomeCCId() {
    return IncomeCCId;
  }

  public void setIncomeCCId(int incomeCCId) {
    IncomeCCId = incomeCCId;
  }

  public int getIncomePrjId() {
    return IncomePrjId;
  }

  public void setIncomePrjId(int incomePrjId) {
    IncomePrjId = incomePrjId;
  }

  public static final DiffUtil.ItemCallback<Broker> DIFF_CALLBACK = new DiffUtil.ItemCallback<Broker>() {
    @Override
    public boolean areItemsTheSame(Broker oldItem, Broker newItem) {
      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(Broker oldItem, Broker newItem) {
      return oldItem.getName().equals(newItem.getName());
    }
  };

}
