package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__DetailAcc__",
	primaryKeys = {"_id", "FPId"})
public class DetailAcc implements Serializable,BaseColumns {

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

	@SerializedName("T1")
	@Expose
	@ColumnInfo(name = "T1")
	private String T1;

	@SerializedName("T2")
	@Expose
	@ColumnInfo(name = "T2")
	private String T2;

	@SerializedName("T3")
	@Expose
	@ColumnInfo(name = "T3")
	private String T3;

	@SerializedName("T4")
	@Expose
	@ColumnInfo(name = "T4")
	private String T4;

	@SerializedName("T5")
	@Expose
	@ColumnInfo(name = "T5")
	private String T5;

	@SerializedName("T6")
	@Expose
	@ColumnInfo(name = "T6")
	private String T6;

	@SerializedName("T7")
	@Expose
	@ColumnInfo(name = "T7")
	private String T7;

	@SerializedName("T8")
	@Expose
	@ColumnInfo(name = "T8")
	private String T8;

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

	@SerializedName("AccDesc")
	@Expose
	@ColumnInfo(name = "AccDesc")
	private String AccDesc;

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

	public DetailAcc() {
		super();
	}

	public DetailAcc(int id, String name, int accLevel, String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8, float debit, float credit, float budget, float currencyBudget, float currencyVal, int currencyId, String accDesc, int LRes, float DRes, String TRes, int FPId) {
		Id = id;
		Name = name;
		AccLevel = accLevel;
		T1 = t1;
		T2 = t2;
		T3 = t3;
		T4 = t4;
		T5 = t5;
		T6 = t6;
		T7 = t7;
		T8 = t8;
		Debit = debit;
		Credit = credit;
		Budget = budget;
		CurrencyBudget = currencyBudget;
		CurrencyVal = currencyVal;
		CurrencyId = currencyId;
		AccDesc = accDesc;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		this.FPId = FPId;
	}

	public DetailAcc(String name, int accLevel, String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8, float debit, float credit, float budget, float currencyBudget, float currencyVal, int currencyId, String accDesc, int LRes, float DRes, String TRes, int FPId) {
		Name = name;
		AccLevel = accLevel;
		T1 = t1;
		T2 = t2;
		T3 = t3;
		T4 = t4;
		T5 = t5;
		T6 = t6;
		T7 = t7;
		T8 = t8;
		Debit = debit;
		Credit = credit;
		Budget = budget;
		CurrencyBudget = currencyBudget;
		CurrencyVal = currencyVal;
		CurrencyId = currencyId;
		AccDesc = accDesc;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		this.FPId = FPId;
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
	 * @return the t1
	 */
	public String getT1() {
		return T1;
	}


	/**
	 * @param t1 the t1 to set
	 */
	public void setT1(String t1) {
		T1 = t1;
	}


	/**
	 * @return the t2
	 */
	public String getT2() {
		return T2;
	}


	/**
	 * @param t2 the t2 to set
	 */
	public void setT2(String t2) {
		T2 = t2;
	}


	/**
	 * @return the t3
	 */
	public String getT3() {
		return T3;
	}


	/**
	 * @param t3 the t3 to set
	 */
	public void setT3(String t3) {
		T3 = t3;
	}


	/**
	 * @return the t4
	 */
	public String getT4() {
		return T4;
	}


	/**
	 * @param t4 the t4 to set
	 */
	public void setT4(String t4) {
		T4 = t4;
	}


	/**
	 * @return the t5
	 */
	public String getT5() {
		return T5;
	}


	/**
	 * @param t5 the t5 to set
	 */
	public void setT5(String t5) {
		T5 = t5;
	}


	/**
	 * @return the t6
	 */
	public String getT6() {
		return T6;
	}


	/**
	 * @param t6 the t6 to set
	 */
	public void setT6(String t6) {
		T6 = t6;
	}


	/**
	 * @return the t7
	 */
	public String getT7() {
		return T7;
	}


	/**
	 * @param t7 the t7 to set
	 */
	public void setT7(String t7) {
		T7 = t7;
	}


	/**
	 * @return the t8
	 */
	public String getT8() {
		return T8;
	}


	/**
	 * @param t8 the t8 to set
	 */
	public void setT8(String t8) {
		T8 = t8;
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

}
