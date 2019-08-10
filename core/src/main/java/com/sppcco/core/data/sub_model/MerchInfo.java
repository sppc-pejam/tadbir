package com.sppcco.core.data.sub_model;


import java.io.Serializable;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Created by m_pejam on 04/21/18.
 * MerchInfo
 */

@Entity
public class MerchInfo implements Serializable{
  @PrimaryKey
  private int merchId;
  private String merchCode;
  private String merchName;
  private String merchDesc;
  private int merchUnitId;
  private String merchUnitName;
  private int stockId;
  private int stockCode;
  private String stockName;
  private String stockAccountId;
  private int stockFAccId;
  private int stockCCId;
  private int stockPrjId;
  private int cabinetId;
  private int cabinetCode;
  private String cabinetName;
  private String merchThumbnail;
  private int merchThumbnailCount;
  private double custSalesPrice;
  private double custSalesDiscount;


  public MerchInfo() {
  }


  public int getMerchId() {
    return merchId;
  }

  public void setMerchId(int merchId) {
    this.merchId = merchId;
  }

  public String getMerchCode() {
    return merchCode;
  }

  public void setMerchCode(String merchCode) {
    this.merchCode = merchCode;
  }

  public String getMerchName() {
    return merchName;
  }

  public void setMerchName(String merchName) {
    this.merchName = merchName;
  }

  public String getMerchDesc() {
    return merchDesc;
  }

  public void setMerchDesc(String merchDesc) {
    this.merchDesc = merchDesc;
  }

  public int getMerchUnitId() {
    return merchUnitId;
  }

  public void setMerchUnitId(int merchUnitId) {
    this.merchUnitId = merchUnitId;
  }

  public String getMerchUnitName() {
    return merchUnitName;
  }

  public void setMerchUnitName(String merchUnitName) {
    this.merchUnitName = merchUnitName;
  }

  public int getStockId() {
    return stockId;
  }

  public void setStockId(int stockId) {
    this.stockId = stockId;
  }

  public int getStockCode() {
    return stockCode;
  }

  public void setStockCode(int stockCode) {
    this.stockCode = stockCode;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public String getStockAccountId() {
    return stockAccountId;
  }

  public void setStockAccountId(String stockAccountId) {
    this.stockAccountId = stockAccountId;
  }

  public int getStockFAccId() {
    return stockFAccId;
  }

  public void setStockFAccId(int stockFAccId) {
    this.stockFAccId = stockFAccId;
  }

  public int getStockCCId() {
    return stockCCId;
  }

  public void setStockCCId(int stockCCId) {
    this.stockCCId = stockCCId;
  }

  public int getStockPrjId() {
    return stockPrjId;
  }

  public void setStockPrjId(int stockPrjId) {
    this.stockPrjId = stockPrjId;
  }

  public int getCabinetId() {
    return cabinetId;
  }

  public void setCabinetId(int cabinetId) {
    this.cabinetId = cabinetId;
  }

  public int getCabinetCode() {
    return cabinetCode;
  }

  public void setCabinetCode(int cabinetCode) {
    this.cabinetCode = cabinetCode;
  }

  public String getCabinetName() {
    return cabinetName;
  }

  public void setCabinetName(String cabinetName) {
    this.cabinetName = cabinetName;
  }

  public String getMerchThumbnail() {
    return merchThumbnail;
  }

  public void setMerchThumbnail(String merchThumbnail) {
    this.merchThumbnail = merchThumbnail;
  }

  public int getMerchThumbnailCount() {
    return merchThumbnailCount;
  }

  public void setMerchThumbnailCount(int merchThumbnailCount) {
    this.merchThumbnailCount = merchThumbnailCount;
  }

  public double getCustSalesPrice() {
    return custSalesPrice;
  }

  public void setCustSalesPrice(double custSalesPrice) {
    this.custSalesPrice = custSalesPrice;
  }

  public double getCustSalesDiscount() {
    return custSalesDiscount;
  }

  public void setCustSalesDiscount(double custSalesDiscount) {
    this.custSalesDiscount = custSalesDiscount;
  }


  /*public static final DiffCallback<MerchInfo> DIFF_CALLBACK = new DiffCallback<MerchInfo>() {
    @Override
    public boolean areItemsTheSame(MerchInfo oldItem, MerchInfo newItem) {
      return oldItem.getMerchId() == newItem.getMerchId();
    }

    @Override
    public boolean areContentsTheSame(MerchInfo oldItem, MerchInfo newItem) {
      return oldItem.getMerchName().equals(newItem.getMerchName());
    }
  };*/

  public static final DiffUtil.ItemCallback<MerchInfo>  DIFF_CALLBACK =  new DiffUtil.ItemCallback<MerchInfo>() {
    @Override
    public boolean areItemsTheSame(MerchInfo oldItem, MerchInfo newItem) {
      return oldItem.getMerchId() == newItem.getMerchId();
    }

    @Override
    public boolean areContentsTheSame(MerchInfo oldItem, MerchInfo newItem) {
      return oldItem.getMerchName().equals(newItem.getMerchName());
    }
  };


}
