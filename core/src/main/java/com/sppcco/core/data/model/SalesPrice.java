package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__SalesPrice__",
  primaryKeys = {"MerchId", "Type", "FPId"})
public class SalesPrice implements Serializable,BaseColumns {

	@ColumnInfo(name = "_id")
	private int Id;

	@SerializedName("MerchId")
	@Expose
	private int MerchId;

	@SerializedName("Val1")
	@Expose
	private float Val1;

	@SerializedName("Val2")
	@Expose
	private float Val2;

	@SerializedName("Type")
	@Expose
	private int Type;

	@SerializedName("LRes")
	@Expose
	private int LRes;

	@SerializedName("DRes")
	@Expose
	private float DRes;

	@SerializedName("TRes")
	@Expose
	private String TRes;

	@SerializedName("FPId")
	@Expose
	private int FPId;


	public SalesPrice() {
		super();
	}


	public SalesPrice(int id, int merchId, float val1, float val2, int type, int LRes, float DRes, String TRes, int FPId) {
		Id = id;
		MerchId = merchId;
		Val1 = val1;
		Val2 = val2;
		Type = type;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		this.FPId = FPId;
	}

	public SalesPrice(int merchId, float val1, float val2, int type, int LRes, float DRes, String TRes, int FPId) {
		MerchId = merchId;
		Val1 = val1;
		Val2 = val2;
		Type = type;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		this.FPId = FPId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getMerchId() {
		return MerchId;
	}

	public void setMerchId(int merchId) {
		MerchId = merchId;
	}

	public float getVal1() {
		return Val1;
	}

	public void setVal1(float val1) {
		Val1 = val1;
	}

	public float getVal2() {
		return Val2;
	}

	public void setVal2(float val2) {
		Val2 = val2;
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
}
