package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.framework.application.BaseApplication;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
  tableName = "__SPTax__"
)
public class SPTax implements Serializable, BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("SPId")
  @Expose
  @ColumnInfo(name = "SPId")
  private int SPId;

  @SerializedName("TaxAccount")
  @Expose
  @ColumnInfo(name = "TaxAccount")
  String TaxAccount;

  @SerializedName("TaxFAccId")
  @Expose
  @ColumnInfo(name = "TaxFAccId")
  int TaxFAccId;

  @SerializedName("TaxCCId")
  @Expose
  @ColumnInfo(name = "TaxCCId")
  int TaxCCId;

  @SerializedName("TaxPrjId")
  @Expose
  @ColumnInfo(name = "TaxPrjId")
  int TaxPrjId;

  @SerializedName("TaxAmount")
  @Expose
  @ColumnInfo(name = "TaxAmount")
  private double TaxAmount;

  @SerializedName("SPTaxDesc")
  @Expose
  @ColumnInfo(name = "SPTaxDesc")
  private String SPTaxDesc;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  int FPId;

  public SPTax() {
    super();
  }

  public SPTax(int id, int SPId, String taxAccount, int taxFAccId, int taxCCId, int taxPrjId, double taxAmount, String SPTaxDesc, int FPId) {
    Id = id;
    this.SPId = SPId;
    TaxAccount = taxAccount;
    TaxFAccId = taxFAccId;
    TaxCCId = taxCCId;
    TaxPrjId = taxPrjId;
    TaxAmount = taxAmount;
    this.SPTaxDesc = SPTaxDesc;
    this.FPId = FPId;
  }


  public static SPTax getSPTaxWithDefaultValue() {
    SPTax spTax = new SPTax();

    spTax.setSPId(0);
    spTax.setTaxAccount("");
    spTax.setTaxFAccId(0);
    spTax.setTaxCCId(0);
    spTax.setTaxPrjId(0);
    spTax.setTaxAmount(0);
    spTax.setSPTaxDesc("");
    spTax.setFPId(BaseApplication.getFPId());

    return spTax;
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

  public String getTaxAccount() {
    return TaxAccount;
  }

  public void setTaxAccount(String taxAccount) {
    TaxAccount = taxAccount;
  }

  public int getTaxFAccId() {
    return TaxFAccId;
  }

  public void setTaxFAccId(int taxFAccId) {
    TaxFAccId = taxFAccId;
  }

  public int getTaxCCId() {
    return TaxCCId;
  }

  public void setTaxCCId(int taxCCId) {
    TaxCCId = taxCCId;
  }

  public int getTaxPrjId() {
    return TaxPrjId;
  }

  public void setTaxPrjId(int taxPrjId) {
    TaxPrjId = taxPrjId;
  }

  public double getTaxAmount() {
    return TaxAmount;
  }

  public void setTaxAmount(double taxAmount) {
    TaxAmount = taxAmount;
  }

  public String getSPTaxDesc() {
    return SPTaxDesc;
  }

  public void setSPTaxDesc(String SPTaxDesc) {
    this.SPTaxDesc = SPTaxDesc;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
