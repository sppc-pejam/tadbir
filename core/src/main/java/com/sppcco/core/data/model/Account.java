package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__Account__",
  primaryKeys = {"FullId", "FPId"})
public class Account implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("Name")
  @Expose
  @ColumnInfo(name = "Name")
  private String Name;

  @SerializedName("AccLevel")
  @Expose
  @ColumnInfo(name = "AccLevel")
  private int AccLevel;

  @SerializedName("Category")
  @Expose
  @ColumnInfo(name = "Category")
  private int Category;

  @SerializedName("ParentId")
  @Expose
  @ColumnInfo(name = "ParentId")
  private String ParentId;

  @NotNull
  @SerializedName("FullId")
  @Expose
  @ColumnInfo(name = "FullId")
  private String FullId;

  @SerializedName("Debit")
  @Expose
  @ColumnInfo(name = "Debit")
  private float Debit;

  @SerializedName("Credit")
  @Expose
  @ColumnInfo(name = "Credit")
  private float Credit;

  @SerializedName("AccDesc")
  @Expose
  @ColumnInfo(name = "AccDesc")
  private String AccDesc;

  @SerializedName("Budget")
  @Expose
  @ColumnInfo(name = "Budget")
  private float Budget;

  @SerializedName("SType")
  @Expose
  @ColumnInfo(name = "SType")
  private int SType;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  @SerializedName("GrParentId")
  @Expose
  @ColumnInfo(name = "GrParentId")
  private String GrParentId;

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


  public Account() {
    super();
  }


  public Account(int id, String name, int accLevel, int category, String parentId, @NotNull String fullId, float debit, float credit, String accDesc, float budget, int sType, int fPId, String grParentId, float currencyBudget, float currencyVal, int currencyId, int lRes, float dRes, String tRes) {
    super();
    Id = id;
    Name = name;
    AccLevel = accLevel;
    Category = category;
    ParentId = parentId;
    FullId = fullId;
    Debit = debit;
    Credit = credit;
    AccDesc = accDesc;
    Budget = budget;
    SType = sType;
    FPId = fPId;
    GrParentId = grParentId;
    CurrencyBudget = currencyBudget;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    LRes = lRes;
    DRes = dRes;
    TRes = tRes;
  }

  public Account(String name, int accLevel, int category, String parentId, @NotNull String fullId, float debit, float credit, String accDesc, float budget, int SType, int FPId, String grParentId, float currencyBudget, float currencyVal, int currencyId, int LRes, float DRes, String TRes) {
    Name = name;
    AccLevel = accLevel;
    Category = category;
    ParentId = parentId;
    FullId = fullId;
    Debit = debit;
    Credit = credit;
    AccDesc = accDesc;
    Budget = budget;
    this.SType = SType;
    this.FPId = FPId;
    GrParentId = grParentId;
    CurrencyBudget = currencyBudget;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
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
   * @return the accLevel
   */
  public int getAccLevel() {
    return AccLevel;
  }


  /**
   * @param accLevel the accLevel to set
   */
  public void setAccLevel(int accLevel) {
    AccLevel = accLevel;
  }


  /**
   * @return the category
   */
  public int getCategory() {
    return Category;
  }


  /**
   * @param category the category to set
   */
  public void setCategory(int category) {
    Category = category;
  }


  /**
   * @return the parentId
   */
  public String getParentId() {
    return ParentId;
  }


  /**
   * @param parentId the parentId to set
   */
  public void setParentId(String parentId) {
    ParentId = parentId;
  }


  /**
   * @return the fullId
   */
  @NotNull
  public String getFullId() {
    return FullId;
  }


  /**
   * @param fullId the fullId to set
   */
  public void setFullId(@NotNull String fullId) {
    FullId = fullId;
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
   * @return the accDesc
   */
  public String getAccDesc() {
    return AccDesc;
  }


  /**
   * @param accDesc the accDesc to set
   */
  public void setAccDesc(String accDesc) {
    AccDesc = accDesc;
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
   * @return the sType
   */
  public int getSType() {
    return SType;
  }


  /**
   * @param sType the sType to set
   */
  public void setSType(int sType) {
    SType = sType;
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
   * @return the grParentId
   */
  public String getGrParentId() {
    return GrParentId;
  }


  /**
   * @param grParentId the grParentId to set
   */
  public void setGrParentId(String grParentId) {
    GrParentId = grParentId;
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


  /**
   * @return the lRes
   */
  public int getLRes() {
    return LRes;
  }


  /**
   * @param lRes the lRes to set
   */
  public void setLRes(int lRes) {
    LRes = lRes;
  }


  /**
   * @return the dRes
   */
  public float getDRes() {
    return DRes;
  }


  /**
   * @param dRes the dRes to set
   */
  public void setDRes(float dRes) {
    DRes = dRes;
  }


  /**
   * @return the tRes
   */
  public String getTRes() {
    return TRes;
  }


  /**
   * @param tRes the tRes to set
   */
  public void setTRes(String tRes) {
    TRes = tRes;
  }

}
