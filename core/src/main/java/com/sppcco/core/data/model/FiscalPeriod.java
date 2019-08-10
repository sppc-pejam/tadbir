package com.sppcco.core.data.model;


import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "__FiscalPeriod__")
public class FiscalPeriod implements Serializable,BaseColumns {

	@SerializedName("Id")
	@Expose
	@PrimaryKey
	@ColumnInfo(name = "_id")
	private int Id;

	@SerializedName("FPNo")
	@Expose
	@ColumnInfo(name = "FPNo")
	private int FPNo;

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

	@SerializedName("FPDesc")
	@Expose
	@ColumnInfo(name = "FPDesc")
	private String FPDesc;

	@SerializedName("SType")
	@Expose
	@ColumnInfo(name = "SType")
	private String SType;

	@SerializedName("LRes")
	@Expose
	@ColumnInfo(name = "LRes")
	private String LRes;

	@SerializedName("DRes")
	@Expose
	@ColumnInfo(name = "DRes")
	private String DRes;

	@SerializedName("TRes")
	@Expose
	@ColumnInfo(name = "TRes")
	private String TRes;

	public FiscalPeriod() {
		super();
	}

	public FiscalPeriod(int id, int FPNo, String name, String startDate, String endDate, String FPDesc, String SType, String LRes, String DRes, String TRes) {
		Id = id;
		this.FPNo = FPNo;
		this.Name = name;
		this.StartDate = startDate;
		this.EndDate = endDate;
		this.FPDesc = FPDesc;
		this.SType = SType;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
	}

	public FiscalPeriod(int FPNo, String name, String startDate, String endDate, String FPDesc, String SType, String LRes, String DRes, String TRes) {
		this.FPNo = FPNo;
		this.Name = name;
		this.StartDate = startDate;
		this.EndDate = endDate;
		this.FPDesc = FPDesc;
		this.SType = SType;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
	}

	public static FiscalPeriod getFiscalPeriodWithDefaultValue() {
		FiscalPeriod fiscalPeriod = new FiscalPeriod();

		fiscalPeriod.setFPNo(0);
		fiscalPeriod.setName("");
		fiscalPeriod.setStartDate("");
		fiscalPeriod.setEndDate("");
		fiscalPeriod.setFPDesc("");
		fiscalPeriod.setSType("0");
		fiscalPeriod.setLRes(null) ;
		fiscalPeriod.setDRes(null);
		fiscalPeriod.setTRes(null);

		return fiscalPeriod;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getFPNo() {
		return FPNo;
	}

	public void setFPNo(int FPNo) {
		this.FPNo = FPNo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getFPDesc() {
		return FPDesc;
	}

	public void setFPDesc(String FPDesc) {
		this.FPDesc = FPDesc;
	}

	public String getSType() {
		return SType;
	}

	public void setSType(String SType) {
		this.SType = SType;
	}

	public String getLRes() {
		return LRes;
	}

	public void setLRes(String LRes) {
		this.LRes = LRes;
	}

	public String getDRes() {
		return DRes;
	}

	public void setDRes(String DRes) {
		this.DRes = DRes;
	}

	public String getTRes() {
		return TRes;
	}

	public void setTRes(String TRes) {
		this.TRes = TRes;
	}
}
