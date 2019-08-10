package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__Project__",
  primaryKeys = {"_id", "FPId"})
public class Project implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("PCode")
  @Expose
  @ColumnInfo(name = "PCode")
  private int PCode;

  @SerializedName("Name")
  @Expose
  @ColumnInfo(name = "Name")
  private String Name;

  @SerializedName("StartDate")
  @Expose
  @ColumnInfo(name = "StartDate")
  private String StartDate;

  @SerializedName("EndDate")
  @Expose
  @ColumnInfo(name = "EndDate")
  private String EndDate;

  @SerializedName("Progress")
  @Expose
  @ColumnInfo(name = "Progress")
  private int Progress;

  @SerializedName("Mojri")
  @Expose
  @ColumnInfo(name = "Mojri")
  private String Mojri;

  @SerializedName("KarFarma")
  @Expose
  @ColumnInfo(name = "KarFarma")
  private String KarFarma;

  @SerializedName("PDesc")
  @Expose
  @ColumnInfo(name = "PDesc")
  private String PDesc;

  @SerializedName("Debit")
  @Expose
  @ColumnInfo(name = "Debit")
  private float Debit;

  @SerializedName("Credit")
  @Expose
  @ColumnInfo(name = "Credit")
  private float Credit;

  @SerializedName("Budget")
  @Expose
  @ColumnInfo(name = "Budget")
  private float Budget;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  @SerializedName("CurrencyBudget")
  @Expose
  @ColumnInfo(name = "CurrencyBudget")
  private float CurrencyBudget;

  @SerializedName("CurrencyVal")
  @Expose
  @ColumnInfo(name = "CurrencyVal")
  private float CurrencyVal;


  @SerializedName("CurrencyId")
  @Expose
  @ColumnInfo(name = "CurrencyId")
  private int CurrencyId;


  public Project() {
    super();
  }


  public Project(int id, int PCode, String name, String startDate, String endDate, int progress, String mojri, String karFarma, String PDesc, float debit, float credit, float budget, int FPId, float currencyBudget, float currencyVal, int currencyId) {
    Id = id;
    this.PCode = PCode;
    Name = name;
    StartDate = startDate;
    EndDate = endDate;
    Progress = progress;
    Mojri = mojri;
    KarFarma = karFarma;
    this.PDesc = PDesc;
    Debit = debit;
    Credit = credit;
    Budget = budget;
    this.FPId = FPId;
    CurrencyBudget = currencyBudget;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
  }


  public Project(int PCode, String name, String startDate, String endDate, int progress, String mojri, String karFarma, String PDesc, float debit, float credit, float budget, int FPId, float currencyBudget, float currencyVal, int currencyId) {
    this.PCode = PCode;
    Name = name;
    StartDate = startDate;
    EndDate = endDate;
    Progress = progress;
    Mojri = mojri;
    KarFarma = karFarma;
    this.PDesc = PDesc;
    Debit = debit;
    Credit = credit;
    Budget = budget;
    this.FPId = FPId;
    CurrencyBudget = currencyBudget;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
  }

  /**
   * @return the id
   */
  public int getId() {
    return Id;
  }


  /**
   * @param id the id to set
   */
  public void setId(int id) {
    Id = id;
  }


  /**
   * @return the pCode
   */
  public int getPCode() {
    return PCode;
  }


  /**
   * @param pCode the pCode to set
   */
  public void setPCode(int pCode) {
    PCode = pCode;
  }


  /**
   * @return the name
   */
  public String getName() {
    return Name;
  }


  /**
   * @param name the name to set
   */
  public void setName(String name) {
    Name = name;
  }


  /**
   * @return the startDate
   */
  public String getStartDate() {
    return StartDate;
  }


  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(String startDate) {
    StartDate = startDate;
  }


  /**
   * @return the endDate
   */
  public String getEndDate() {
    return EndDate;
  }


  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(String endDate) {
    EndDate = endDate;
  }


  /**
   * @return the progress
   */
  public int getProgress() {
    return Progress;
  }


  /**
   * @param progress the progress to set
   */
  public void setProgress(int progress) {
    Progress = progress;
  }


  /**
   * @return the mojri
   */
  public String getMojri() {
    return Mojri;
  }


  /**
   * @param mojri the mojri to set
   */
  public void setMojri(String mojri) {
    Mojri = mojri;
  }


  /**
   * @return the karFarma
   */
  public String getKarFarma() {
    return KarFarma;
  }


  /**
   * @param karFarma the karFarma to set
   */
  public void setKarFarma(String karFarma) {
    KarFarma = karFarma;
  }


  /**
   * @return the pDesc
   */
  public String getPDesc() {
    return PDesc;
  }


  /**
   * @param pDesc the pDesc to set
   */
  public void setPDesc(String pDesc) {
    PDesc = pDesc;
  }


  /**
   * @return the debit
   */
  public float getDebit() {
    return Debit;
  }


  /**
   * @param debit the debit to set
   */
  public void setDebit(float debit) {
    Debit = debit;
  }


  /**
   * @return the credit
   */
  public float getCredit() {
    return Credit;
  }


  /**
   * @param credit the credit to set
   */
  public void setCredit(float credit) {
    Credit = credit;
  }


  /**
   * @return the budget
   */
  public float getBudget() {
    return Budget;
  }


  /**
   * @param budget the budget to set
   */
  public void setBudget(float budget) {
    Budget = budget;
  }


  /**
   * @return the fPId
   */
  public int getFPId() {
    return FPId;
  }


  /**
   * @param fPId the fPId to set
   */
  public void setFPId(int fPId) {
    FPId = fPId;
  }


  /**
   * @return the currencyBudget
   */
  public float getCurrencyBudget() {
    return CurrencyBudget;
  }


  /**
   * @param currencyBudget the currencyBudget to set
   */
  public void setCurrencyBudget(float currencyBudget) {
    CurrencyBudget = currencyBudget;
  }


  /**
   * @return the currencyVal
   */
  public float getCurrencyVal() {
    return CurrencyVal;
  }


  /**
   * @param currencyVal the currencyVal to set
   */
  public void setCurrencyVal(float currencyVal) {
    CurrencyVal = currencyVal;
  }


  /**
   * @return the currencyId
   */
  public int getCurrencyId() {
    return CurrencyId;
  }


  /**
   * @param currencyId the currencyId to set
   */
  public void setCurrencyId(int currencyId) {
    CurrencyId = currencyId;
  }

}
