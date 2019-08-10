package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.converter.timestamp.TimestampConverter;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(
  tableName = "__SalesOrder__"
)
public class SalesOrder implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("SONo")
  @Expose
  private int SONo;

  @TypeConverters({TimestampConverter.class})
  @SerializedName("SODate")
  @Expose
  private Date SODate;

  @SerializedName("Committed")
  @Expose
  private int Committed;

  @SerializedName("SOReference")
  @Expose
  private int SOReference;

  @SerializedName("AccId")
  @Expose
  private String AccId;

  @SerializedName("AccName")
  private String AccName;

  @SerializedName("FAccId")
  @Expose
  private int FAccId;

  @SerializedName("FAccName")
  private String FAccName;

  @SerializedName("CCId")
  @Expose
  private int CCId;

  @SerializedName("CCName")
  private String CCName;

  @SerializedName("PrjId")
  @Expose
  private int PrjId;

  @SerializedName("PrjName")
  private String PrjName;

  @SerializedName("CustomerId")
  @Expose
  private int CustomerId;

  @SerializedName("Total")
  @Expose
  private double Total;

  @SerializedName("Discount")
  @Expose
  private double Discount;

  @SerializedName("Expense")
  @Expose
  private double Expense;

  @SerializedName("SODesc")
  @Expose
  private String SODesc;

  @SerializedName("CustomerName")
  @Expose
  private String CustomerName;

  @SerializedName("CustomerPhoneNo")
  @Expose
  private String CustomerPhoneNo;

  @SerializedName("CustomerEcCode")
  @Expose
  private String CustomerEcCode;

  @SerializedName("CustomerAddress")
  @Expose
  private String CustomerAddress;

  @SerializedName("CurrencyVal")
  @Expose
  private double CurrencyVal;

  @SerializedName("CurrencyId")
  @Expose
  private int CurrencyId;

  @SerializedName("VerifierId")
  @Expose
  private int VerifierId;

  @SerializedName("VerifierName")
  @Expose
  private String VerifierName;

  @SerializedName("ApproverId")
  @Expose
  private int ApproverId;

  @SerializedName("ApproverName")
  @Expose
  private String ApproverName;

  @SerializedName("UserId")
  @Expose
  private int UserId;

  @TypeConverters({TimestampConverter.class})
  @SerializedName("ETime")
  @Expose
  private Date ETime;

  @TypeConverters({TimestampConverter.class})
  @SerializedName("EDate")
  @Expose
  private Date EDate;

  @SerializedName("FPId")
  @Expose
  private int FPId;

  @TypeConverters({TimestampConverter.class})
  @SerializedName("ReqDate")
  @Expose
  private Date ReqDate;


  public SalesOrder() {
    super();
  }

  public SalesOrder(int id, int SONo, Date SODate, int committed, int SOReference, String accId, String accName, int FAccId, String FAccName, int CCId, String CCName, int prjId, String prjName, int customerId, double total, double discount, double expense, String SODesc, String customerName, String customerPhoneNo, String customerEcCode, String customerAddress, double currencyVal, int currencyId, int verifierId, String verifierName, int approverId, String approverName, int userId, Date ETime, Date EDate, int FPId, Date reqDate) {
    Id = id;
    this.SONo = SONo;
    this.SODate = SODate;
    Committed = committed;
    this.SOReference = SOReference;
    AccId = accId;
    AccName = accName;
    this.FAccId = FAccId;
    this.FAccName = FAccName;
    this.CCId = CCId;
    this.CCName = CCName;
    PrjId = prjId;
    PrjName = prjName;
    CustomerId = customerId;
    Total = total;
    Discount = discount;
    Expense = expense;
    this.SODesc = SODesc;
    CustomerName = customerName;
    CustomerPhoneNo = customerPhoneNo;
    CustomerEcCode = customerEcCode;
    CustomerAddress = customerAddress;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    VerifierId = verifierId;
    VerifierName = verifierName;
    ApproverId = approverId;
    ApproverName = approverName;
    UserId = userId;
    this.ETime = ETime;
    this.EDate = EDate;
    this.FPId = FPId;
    ReqDate = reqDate;
  }


  public SalesOrder(int SONo, Date SODate, int committed, int SOReference, String accId, String accName, int FAccId, String FAccName, int CCId, String CCName, int prjId, String prjName, int customerId, double total, double discount, double expense, String SODesc, String customerName, String customerPhoneNo, String customerEcCode, String customerAddress, double currencyVal, int currencyId, int verifierId, String verifierName, int approverId, String approverName, int userId, Date ETime, Date EDate, int FPId, Date reqDate) {
    this.SONo = SONo;
    this.SODate = SODate;
    Committed = committed;
    this.SOReference = SOReference;
    AccId = accId;
    AccName = accName;
    this.FAccId = FAccId;
    this.FAccName = FAccName;
    this.CCId = CCId;
    this.CCName = CCName;
    PrjId = prjId;
    PrjName = prjName;
    CustomerId = customerId;
    Total = total;
    Discount = discount;
    Expense = expense;
    this.SODesc = SODesc;
    CustomerName = customerName;
    CustomerPhoneNo = customerPhoneNo;
    CustomerEcCode = customerEcCode;
    CustomerAddress = customerAddress;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    VerifierId = verifierId;
    VerifierName = verifierName;
    ApproverId = approverId;
    ApproverName = approverName;
    UserId = userId;
    this.ETime = ETime;
    this.EDate = EDate;
    this.FPId = FPId;
    ReqDate = reqDate;
  }

  public static SalesOrder getSalesOrderWithDefaultValue() {

    SalesOrder salesOrder = new SalesOrder();

    salesOrder.SONo = 0;
    salesOrder.SODate = CalenderManager.getDateStampTime();
    salesOrder.Committed = 0;
    salesOrder.SOReference = 0;
    salesOrder.AccId = "";
    salesOrder.AccName = "";
    salesOrder.FAccId = 0;
    salesOrder.FAccName = "";
    salesOrder.CCId = 0;
    salesOrder.CCName = "";
    salesOrder.PrjId = 0;
    salesOrder.PrjName = "";
    salesOrder.CustomerId = 0;
    salesOrder.Total = 0;
    salesOrder.Discount = 0;
    salesOrder.Expense = 0;
    salesOrder.SODesc = "";
    salesOrder.CustomerName = "";
    salesOrder.CustomerPhoneNo = "";
    salesOrder.CustomerEcCode = "";
    salesOrder.CustomerAddress = "";
    salesOrder.CurrencyVal = 0;
    salesOrder.CurrencyId = 0;
    salesOrder.VerifierId = 0;
    salesOrder.VerifierName = "";
    salesOrder.ApproverId = 0;
    salesOrder.ApproverName = "";
    salesOrder.UserId = BaseApplication.getUserId();
    salesOrder.ETime = CalenderManager.getCurrentDateStampTime();
    salesOrder.EDate = CalenderManager.getCurrentDateStampTime();
    salesOrder.FPId = BaseApplication.getFPId();
    salesOrder.ReqDate = CalenderManager.getCurrentDateStampTime();

    return salesOrder;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getSONo() {
    return SONo;
  }

  public void setSONo(int SONo) {
    this.SONo = SONo;
  }

  public Date getSODate() {
    return SODate;
  }

  public void setSODate(Date SODate) {
    this.SODate = SODate;
  }

  public int getCommitted() {
    return Committed;
  }

  public void setCommitted(int committed) {
    Committed = committed;
  }

  public int getSOReference() {
    return SOReference;
  }

  public void setSOReference(int SOReference) {
    this.SOReference = SOReference;
  }

  public String getAccId() {
    return AccId;
  }

  public void setAccId(String accId) {
    AccId = accId;
  }

  public String getAccName() {
    return AccName;
  }

  public void setAccName(String accName) {
    AccName = accName;
  }

  public int getFAccId() {
    return FAccId;
  }

  public void setFAccId(int FAccId) {
    this.FAccId = FAccId;
  }

  public String getFAccName() {
    return FAccName;
  }

  public void setFAccName(String FAccName) {
    this.FAccName = FAccName;
  }

  public int getCCId() {
    return CCId;
  }

  public void setCCId(int CCId) {
    this.CCId = CCId;
  }

  public String getCCName() {
    return CCName;
  }

  public void setCCName(String CCName) {
    this.CCName = CCName;
  }

  public int getPrjId() {
    return PrjId;
  }

  public void setPrjId(int prjId) {
    PrjId = prjId;
  }

  public String getPrjName() {
    return PrjName;
  }

  public void setPrjName(String prjName) {
    PrjName = prjName;
  }

  public int getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(int customerId) {
    CustomerId = customerId;
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

  public String getSODesc() {
    return SODesc;
  }

  public void setSODesc(String SODesc) {
    this.SODesc = SODesc;
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

  public int getVerifierId() {
    return VerifierId;
  }

  public void setVerifierId(int verifierId) {
    VerifierId = verifierId;
  }

  public String getVerifierName() {
    return VerifierName;
  }

  public void setVerifierName(String verifierName) {
    VerifierName = verifierName;
  }

  public int getApproverId() {
    return ApproverId;
  }

  public void setApproverId(int approverId) {
    ApproverId = approverId;
  }

  public String getApproverName() {
    return ApproverName;
  }

  public void setApproverName(String approverName) {
    ApproverName = approverName;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
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

  public Date getReqDate() {
    return ReqDate;
  }

  public void setReqDate(Date reqDate) {
    ReqDate = reqDate;
  }

}