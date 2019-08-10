package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "__Unit__",
	primaryKeys = {"_id", "FPId"},
	indices = {@Index(
		name = "IdIndex", value = {"_id", "FPId", "Name"}, unique = true
	)})
public class Unit implements Serializable,BaseColumns {

	@SerializedName("Id")
	@Expose
	@ColumnInfo(name = "_id")
	private int Id;

	@SerializedName("Name")
	@Expose
	private String Name;

	@SerializedName("UDesc")
	@Expose
	private String UDesc;

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


	public Unit() {
		super();
	}


	public Unit(int id, String name, String UDesc, int LRes, float DRes, String TRes, int FPId) {
		Id = id;
		Name = name;
		this.UDesc = UDesc;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		this.FPId = FPId;
	}

	public Unit(String name, String UDesc, int LRes, float DRes, String TRes, int FPId) {
		Name = name;
		this.UDesc = UDesc;
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
	 * @return the uDesc
	 */
	public String getUDesc() {
		return UDesc;
	}


	/**
	 * @param uDesc the uDesc to set
	 */
	public void setUDesc(String uDesc) {
		UDesc = uDesc;
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
