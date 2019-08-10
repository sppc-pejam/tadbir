package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(
	tableName = "__Merchandise__",
	primaryKeys = {"_id", "FPId"},
	indices = {@Index(
		name = "MerchandiseIndex", value = {"_id", "FPId", "Code", "UnitId"}, unique = true
	)}
	)
public class Merchandise implements Serializable,BaseColumns {

	@SerializedName("Id")
	@Expose
	@ColumnInfo(name = "_id")
	private int Id;

	@SerializedName("Code")
	@Expose
	@ColumnInfo(name = "Code")
	private String Code;

	@SerializedName("Name")
	@Expose
	@ColumnInfo(name = "Name")
	private String Name;

	@SerializedName("UnitId")
	@Expose
	@ColumnInfo(name = "UnitId")
	private int UnitId;

	@SerializedName("MType")
	@Expose
	@ColumnInfo(name = "MType")
	private int MType;

	@SerializedName("ValuationId")
	@Expose
	@ColumnInfo(name = "ValuationId")
	private int ValuationId;

	@SerializedName("Inventory")
	@Expose
	@ColumnInfo(name = "Inventory")
	private float Inventory;

	@SerializedName("MaxInventory")
	@Expose
	@ColumnInfo(name = "MaxInventory")
	private float MaxInventory;

	@SerializedName("MinInventory")
	@Expose
	@ColumnInfo(name = "MinInventory")
	private float MinInventory;

	@SerializedName("OrderLimit")
	@Expose
	@ColumnInfo(name = "OrderLimit")
	private float OrderLimit;

	@SerializedName("UnitPrice")
	@Expose
	@ColumnInfo(name = "UnitPrice")
	private float UnitPrice;

	@SerializedName("MaxPrice")
	@Expose
	@ColumnInfo(name = "MaxPrice")
	private float MaxPrice;

	@SerializedName("MinPrice")
	@Expose
	@ColumnInfo(name = "MinPrice")
	private float MinPrice;

	@SerializedName("BarCode")
	@Expose
	@ColumnInfo(name = "BarCode")
	private String BarCode;

	@SerializedName("MDesc")
	@Expose
	@ColumnInfo(name = "MDesc")
	private String MDesc;

	@SerializedName("MPicture")
	@Expose
	@ColumnInfo(name = "MPicture")
	private String MPicture;

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

	@SerializedName("AccountId")
	@Expose
	@ColumnInfo(name = "AccountId")
	private String AccountId;

	@SerializedName("MLevel")
	@Expose
	@ColumnInfo(name = "MLevel")
	private int MLevel;

	@SerializedName("IsGroup")
	@Expose
	@ColumnInfo(name = "IsGroup")
	private int IsGroup;

	@SerializedName("G1")
	@Expose
	@ColumnInfo(name = "G1")
	private String G1;

	@SerializedName("G2")
	@Expose
	@ColumnInfo(name = "G2")
	private String G2;

	@SerializedName("G3")
	@Expose
	@ColumnInfo(name = "G3")
	private String G3;

	@SerializedName("G4")
	@Expose
	@ColumnInfo(name = "G4")
	private String G4;

	@SerializedName("G5")
	@Expose
	@ColumnInfo(name = "G5")
	private String G5;

	@SerializedName("G6")
	@Expose
	@ColumnInfo(name = "G6")
	private String G6;

	@SerializedName("G7")
	@Expose
	@ColumnInfo(name = "G7")
	private String G7;

	@SerializedName("FPId")
	@Expose
	@ColumnInfo(name = "FPId")
	private int FPId;

	@SerializedName("PartNo")
	@Expose
	@ColumnInfo(name = "PartNo")
	private String PartNo;

	@SerializedName("SpecNo")
	@Expose
	@ColumnInfo(name = "SpecNo")
	private String SpecNo;

	@SerializedName("Model")
	@Expose
	@ColumnInfo(name = "Model")
	private String Model;

	@SerializedName("ExType")
	@Expose
	@ColumnInfo(name = "ExType")
	private String ExType;

	@SerializedName("ManufactYear")
	@Expose
	@ColumnInfo(name = "ManufactYear")
	private int ManufactYear;

	@SerializedName("OpCond")
	@Expose
	@ColumnInfo(name = "OpCond")
	private String OpCond;

	@SerializedName("ReservedTxt1")
	@Expose
	@ColumnInfo(name = "ReservedTxt1")
	private String ReservedTxt1;

	@SerializedName("ReservedTxt2")
	@Expose
	@ColumnInfo(name = "ReservedTxt2")
	private String ReservedTxt2;

	@SerializedName("ReservedTxt3")
	@Expose
	@ColumnInfo(name = "ReservedTxt3")
	private String ReservedTxt3;

	@SerializedName("ReservedDbl1")
	@Expose
	@ColumnInfo(name = "ReservedDbl1")
	private float ReservedDbl1;

	@SerializedName("ReservedDbl2")
	@Expose
	@ColumnInfo(name = "ReservedDbl2")
	private float ReservedDbl2;

	@SerializedName("ReservedDT1")
	@Expose
	@ColumnInfo(name = "ReservedDT1")
	private String ReservedDT1;


	public Merchandise() {
		super();
	}

	public Merchandise(int id, String code, String name, int unitId, int MType, int valuationId, float inventory, float maxInventory, float minInventory, float orderLimit, float unitPrice, float maxPrice, float minPrice, String barCode, String MDesc, String MPicture, int LRes, float DRes, String TRes, String accountId, int MLevel, int isGroup, String g1, String g2, String g3, String g4, String g5, String g6, String g7, int FPId, String partNo, String specNo, String model, String exType, int manufactYear, String opCond, String reservedTxt1, String reservedTxt2, String reservedTxt3, float reservedDbl1, float reservedDbl2, String reservedDT1) {
		Id = id;
		Code = code;
		Name = name;
		UnitId = unitId;
		this.MType = MType;
		ValuationId = valuationId;
		Inventory = inventory;
		MaxInventory = maxInventory;
		MinInventory = minInventory;
		OrderLimit = orderLimit;
		UnitPrice = unitPrice;
		MaxPrice = maxPrice;
		MinPrice = minPrice;
		BarCode = barCode;
		this.MDesc = MDesc;
		this.MPicture = MPicture;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		AccountId = accountId;
		this.MLevel = MLevel;
		IsGroup = isGroup;
		G1 = g1;
		G2 = g2;
		G3 = g3;
		G4 = g4;
		G5 = g5;
		G6 = g6;
		G7 = g7;
		this.FPId = FPId;
		PartNo = partNo;
		SpecNo = specNo;
		Model = model;
		ExType = exType;
		ManufactYear = manufactYear;
		OpCond = opCond;
		ReservedTxt1 = reservedTxt1;
		ReservedTxt2 = reservedTxt2;
		ReservedTxt3 = reservedTxt3;
		ReservedDbl1 = reservedDbl1;
		ReservedDbl2 = reservedDbl2;
		ReservedDT1 = reservedDT1;
	}


	public Merchandise(String code, String name, int unitId, int MType, int valuationId, float inventory, float maxInventory, float minInventory, float orderLimit, float unitPrice, float maxPrice, float minPrice, String barCode, String MDesc, String MPicture, int LRes, float DRes, String TRes, String accountId, int MLevel, int isGroup, String g1, String g2, String g3, String g4, String g5, String g6, String g7, int FPId, String partNo, String specNo, String model, String exType, int manufactYear, String opCond, String reservedTxt1, String reservedTxt2, String reservedTxt3, float reservedDbl1, float reservedDbl2, String reservedDT1) {
		Code = code;
		Name = name;
		UnitId = unitId;
		this.MType = MType;
		ValuationId = valuationId;
		Inventory = inventory;
		MaxInventory = maxInventory;
		MinInventory = minInventory;
		OrderLimit = orderLimit;
		UnitPrice = unitPrice;
		MaxPrice = maxPrice;
		MinPrice = minPrice;
		BarCode = barCode;
		this.MDesc = MDesc;
		this.MPicture = MPicture;
		this.LRes = LRes;
		this.DRes = DRes;
		this.TRes = TRes;
		AccountId = accountId;
		this.MLevel = MLevel;
		IsGroup = isGroup;
		G1 = g1;
		G2 = g2;
		G3 = g3;
		G4 = g4;
		G5 = g5;
		G6 = g6;
		G7 = g7;
		this.FPId = FPId;
		PartNo = partNo;
		SpecNo = specNo;
		Model = model;
		ExType = exType;
		ManufactYear = manufactYear;
		OpCond = opCond;
		ReservedTxt1 = reservedTxt1;
		ReservedTxt2 = reservedTxt2;
		ReservedTxt3 = reservedTxt3;
		ReservedDbl1 = reservedDbl1;
		ReservedDbl2 = reservedDbl2;
		ReservedDT1 = reservedDT1;
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
	 * @return the code
	 */
	public String getCode() {
		return Code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		Code = code;
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
	 * @return the unitId
	 */
	public int getUnitId() {
		return UnitId;
	}


	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(int unitId) {
		UnitId = unitId;
	}


	/**
	 * @return the mType
	 */
	public int getMType() {
		return MType;
	}


	/**
	 * @param mType the mType to set
	 */
	public void setMType(int mType) {
		MType = mType;
	}


	/**
	 * @return the valuationId
	 */
	public int getValuationId() {
		return ValuationId;
	}


	/**
	 * @param valuationId the valuationId to set
	 */
	public void setValuationId(int valuationId) {
		ValuationId = valuationId;
	}


	/**
	 * @return the inventory
	 */
	public float getInventory() {
		return Inventory;
	}


	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(float inventory) {
		Inventory = inventory;
	}


	/**
	 * @return the maxInventory
	 */
	public float getMaxInventory() {
		return MaxInventory;
	}


	/**
	 * @param maxInventory the maxInventory to set
	 */
	public void setMaxInventory(float maxInventory) {
		MaxInventory = maxInventory;
	}


	/**
	 * @return the minInventory
	 */
	public float getMinInventory() {
		return MinInventory;
	}


	/**
	 * @param minInventory the minInventory to set
	 */
	public void setMinInventory(float minInventory) {
		MinInventory = minInventory;
	}


	/**
	 * @return the orderLimit
	 */
	public float getOrderLimit() {
		return OrderLimit;
	}


	/**
	 * @param orderLimit the orderLimit to set
	 */
	public void setOrderLimit(float orderLimit) {
		OrderLimit = orderLimit;
	}


	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return UnitPrice;
	}


	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		UnitPrice = unitPrice;
	}


	/**
	 * @return the maxPrice
	 */
	public float getMaxPrice() {
		return MaxPrice;
	}


	/**
	 * @param maxPrice the maxPrice to set
	 */
	public void setMaxPrice(float maxPrice) {
		MaxPrice = maxPrice;
	}


	/**
	 * @return the minPrice
	 */
	public float getMinPrice() {
		return MinPrice;
	}


	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(float minPrice) {
		MinPrice = minPrice;
	}


	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return BarCode;
	}


	/**
	 * @param barCode the barCode to set
	 */
	public void setBarCode(String barCode) {
		BarCode = barCode;
	}


	/**
	 * @return the mDesc
	 */
	public String getMDesc() {
		return MDesc;
	}


	/**
	 * @param mDesc the mDesc to set
	 */
	public void setMDesc(String mDesc) {
		MDesc = mDesc;
	}


	/**
	 * @return the mPicture
	 */
	public String getMPicture() {
		return MPicture;
	}


	/**
	 * @param mPicture the mPicture to set
	 */
	public void setMPicture(String mPicture) {
		MPicture = mPicture;
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
	 * @return the accountId
	 */
	public String getAccountId() {
		return AccountId;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}


	/**
	 * @return the mLevel
	 */
	public int getMLevel() {
		return MLevel;
	}


	/**
	 * @param mLevel the mLevel to set
	 */
	public void setMLevel(int mLevel) {
		MLevel = mLevel;
	}


	/**
	 * @return the isGroup
	 */
	public int getIsGroup() {
		return IsGroup;
	}


	/**
	 * @param isGroup the isGroup to set
	 */
	public void setIsGroup(int isGroup) {
		IsGroup = isGroup;
	}


	/**
	 * @return the g1
	 */
	public String getG1() {
		return G1;
	}


	/**
	 * @param g1 the g1 to set
	 */
	public void setG1(String g1) {
		G1 = g1;
	}


	/**
	 * @return the g2
	 */
	public String getG2() {
		return G2;
	}


	/**
	 * @param g2 the g2 to set
	 */
	public void setG2(String g2) {
		G2 = g2;
	}


	/**
	 * @return the g3
	 */
	public String getG3() {
		return G3;
	}


	/**
	 * @param g3 the g3 to set
	 */
	public void setG3(String g3) {
		G3 = g3;
	}


	/**
	 * @return the g4
	 */
	public String getG4() {
		return G4;
	}


	/**
	 * @param g4 the g4 to set
	 */
	public void setG4(String g4) {
		G4 = g4;
	}


	/**
	 * @return the g5
	 */
	public String getG5() {
		return G5;
	}


	/**
	 * @param g5 the g5 to set
	 */
	public void setG5(String g5) {
		G5 = g5;
	}


	/**
	 * @return the g6
	 */
	public String getG6() {
		return G6;
	}


	/**
	 * @param g6 the g6 to set
	 */
	public void setG6(String g6) {
		G6 = g6;
	}


	/**
	 * @return the g7
	 */
	public String getG7() {
		return G7;
	}


	/**
	 * @param g7 the g7 to set
	 */
	public void setG7(String g7) {
		G7 = g7;
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
	 * @return the partNo
	 */
	public String getPartNo() {
		return PartNo;
	}


	/**
	 * @param partNo the partNo to set
	 */
	public void setPartNo(String partNo) {
		PartNo = partNo;
	}


	/**
	 * @return the specNo
	 */
	public String getSpecNo() {
		return SpecNo;
	}


	/**
	 * @param specNo the specNo to set
	 */
	public void setSpecNo(String specNo) {
		SpecNo = specNo;
	}


	/**
	 * @return the model
	 */
	public String getModel() {
		return Model;
	}


	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		Model = model;
	}


	/**
	 * @return the exType
	 */
	public String getExType() {
		return ExType;
	}


	/**
	 * @param exType the exType to set
	 */
	public void setExType(String exType) {
		ExType = exType;
	}


	/**
	 * @return the manufactYear
	 */
	public int getManufactYear() {
		return ManufactYear;
	}


	/**
	 * @param manufactYear the manufactYear to set
	 */
	public void setManufactYear(int manufactYear) {
		ManufactYear = manufactYear;
	}


	/**
	 * @return the opCond
	 */
	public String getOpCond() {
		return OpCond;
	}


	/**
	 * @param opCond the opCond to set
	 */
	public void setOpCond(String opCond) {
		OpCond = opCond;
	}


	/**
	 * @return the reservedTxt1
	 */
	public String getReservedTxt1() {
		return ReservedTxt1;
	}


	/**
	 * @param reservedTxt1 the reservedTxt1 to set
	 */
	public void setReservedTxt1(String reservedTxt1) {
		ReservedTxt1 = reservedTxt1;
	}


	/**
	 * @return the reservedTxt2
	 */
	public String getReservedTxt2() {
		return ReservedTxt2;
	}


	/**
	 * @param reservedTxt2 the reservedTxt2 to set
	 */
	public void setReservedTxt2(String reservedTxt2) {
		ReservedTxt2 = reservedTxt2;
	}


	/**
	 * @return the reservedTxt3
	 */
	public String getReservedTxt3() {
		return ReservedTxt3;
	}


	/**
	 * @param reservedTxt3 the reservedTxt3 to set
	 */
	public void setReservedTxt3(String reservedTxt3) {
		ReservedTxt3 = reservedTxt3;
	}


	/**
	 * @return the reservedDbl1
	 */
	public float getReservedDbl1() {
		return ReservedDbl1;
	}


	/**
	 * @param reservedDbl1 the reservedDbl1 to set
	 */
	public void setReservedDbl1(float reservedDbl1) {
		ReservedDbl1 = reservedDbl1;
	}


	/**
	 * @return the reservedDbl2
	 */
	public float getReservedDbl2() {
		return ReservedDbl2;
	}


	/**
	 * @param reservedDbl2 the reservedDbl2 to set
	 */
	public void setReservedDbl2(float reservedDbl2) {
		ReservedDbl2 = reservedDbl2;
	}


	/**
	 * @return the reservedDT1
	 */
	public String getReservedDT1() {
		return ReservedDT1;
	}


	/**
	 * @param reservedDT1 the reservedDT1 to set
	 */
	public void setReservedDT1(String reservedDT1) {
		ReservedDT1 = reservedDT1;
	}




	/*public static final DiffCallback<Merchandise> DIFF_CALLBACK = new DiffCallback<Merchandise>() {
		@Override
		public boolean areItemsTheSame(Merchandise oldItem, Merchandise newItem) {
			return oldItem.getId() == newItem.getId();
		}

		@Override
		public boolean areContentsTheSame(Merchandise oldItem, Merchandise newItem) {
			return oldItem.getName().equals(newItem.getName());
		}
	};*/

	public static final DiffUtil.ItemCallback<Merchandise>  DIFF_CALLBACK =  new DiffUtil.ItemCallback<Merchandise>() {
		@Override
		public boolean areItemsTheSame(Merchandise oldItem, Merchandise newItem) {
			return oldItem.getId() == newItem.getId();
		}

		@Override
		public boolean areContentsTheSame(Merchandise oldItem, Merchandise newItem) {
			return oldItem.getName().equals(newItem.getName());
		}
	};

}
