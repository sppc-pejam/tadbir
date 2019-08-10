package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "__MerchStock__",
  primaryKeys = {"MerchId", "StockId", "OpCode", "TopMerchId", "FPId"},
  indices = {@Index(
    name = "MerchStockIndex", value = {"MerchId", "StockId", "OpCode", "TopMerchId", "FPId"}, unique = true
  )}
  )
public class MerchStock implements Serializable,BaseColumns {

  @SerializedName("MerchId")
  @Expose
  @ColumnInfo(name = "MerchId")
  private int MerchId;

  @SerializedName("StockId")
  @Expose
  @ColumnInfo(name = "StockId")
  private int StockId;

  @SerializedName("OpCode")
  @Expose
  @ColumnInfo(name = "OpCode")
  private int OpCode;

  @SerializedName("TopMerchId")
  @Expose
  @ColumnInfo(name = "TopMerchId")
  private int TopMerchId;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  public MerchStock() {
    super();
  }

  public MerchStock(int merchId, int stockId, int opCode, int topMerchId, int fpId) {
    MerchId = merchId;
    StockId = stockId;
    OpCode = opCode;
    TopMerchId = topMerchId;
    FPId = fpId;
  }

  public int getMerchId() {
    return MerchId;
  }

  public void setMerchId(int merchId) {
    MerchId = merchId;
  }

  public int getStockId() {
    return StockId;
  }

  public void setStockId(int stockId) {
    StockId = stockId;
  }

  public int getOpCode() {
    return OpCode;
  }

  public void setOpCode(int opCode) {
    OpCode = opCode;
  }

  public int getTopMerchId() {
    return TopMerchId;
  }

  public void setTopMerchId(int topMerchId) {
    TopMerchId = topMerchId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int fpId) {
    FPId = fpId;
  }
}
