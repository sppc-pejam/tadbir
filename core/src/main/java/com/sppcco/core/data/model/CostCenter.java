package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity(tableName = "__CostCenter__",
	primaryKeys = {"_id", "FPId"})
public class CostCenter implements Serializable,BaseColumns {

	@SerializedName("Id")
	@Expose
	@ColumnInfo(name = "_id")
	private int Id;

	@SerializedName("CCCode")
	@Expose
	@ColumnInfo(name = "CCCode")
	private int CCCode;

	@SerializedName("Name")
	@Expose
	@ColumnInfo(name = "Name")
	private String Name;

	@SerializedName("CCDesc")
	@Expose
	@ColumnInfo(name = "CCDesc")
	private String CCDesc;

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

	@SerializedName("CTextCode")
	@Expose
	@ColumnInfo(name = "CTextCode")
	private String CTextCode;

	public CostCenter() {
		super();
	}

	public CostCenter(int id, int cCCode, String name, String cCDesc, float debit, float credit, float budget, int fPId, float currencyBudget, float currencyVal, int currencyId, String cTextCode) {
		super();
		Id = id;
		CCCode = cCCode;
		Name = name;
		CCDesc = cCDesc;
		Debit = debit;
		Credit = credit;
		Budget = budget;
		FPId = fPId;
		CurrencyBudget = currencyBudget;
		CurrencyVal = currencyVal;
		CurrencyId = currencyId;
		CTextCode = cTextCode;
	}


	public CostCenter(int CCCode, String name, String CCDesc, float debit, float credit, float budget, int FPId, float currencyBudget, float currencyVal, int currencyId, String CTextCode) {
		this.CCCode = CCCode;
		Name = name;
		this.CCDesc = CCDesc;
		Debit = debit;
		Credit = credit;
		Budget = budget;
		this.FPId = FPId;
		CurrencyBudget = currencyBudget;
		CurrencyVal = currencyVal;
		CurrencyId = currencyId;
		this.CTextCode = CTextCode;
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
	 * @return the CCCode
	 */
	public int getCCCode() {
		return CCCode;
	}


	/**
	 * @param cCCode the CCCode to set
	 */
	public void setCCCode(int cCCode) {
		CCCode = cCCode;
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
	 * @return the cCDesc
	 */
	public String getCCDesc() {
		return CCDesc;
	}


	/**
	 * @param cCDesc the cCDesc to set
	 */
	public void setCCDesc(String cCDesc) {
		CCDesc = cCDesc;
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


	/**
	 * @return the cTextCode
	 */
	public String getCTextCode() {
		return CTextCode;
	}


	/**
	 * @param cTextCode the cTextCode to set
	 */
	public void setCTextCode(String cTextCode) {
		CTextCode = cTextCode;
	}

}
