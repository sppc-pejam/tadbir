package com.sppcco.core.data.sub_model;

import java.util.Date;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostedPrefactorInfo {

  @PrimaryKey
  private int SPId;
  private int FactorNo;
  private String SPDate;
  private int SPReference;
  private int CustomerId;
  private String CustomerName;
  private double Total;
  private boolean Posted;
  private Date PostDate;

  public PostedPrefactorInfo() {
    super();
  }

  public int getSPId() {
    return SPId;
  }

  public void setSPId(int SPId) {
    this.SPId = SPId;
  }

  public int getFactorNo() {
    return FactorNo;
  }

  public void setFactorNo(int factorNo) {
    FactorNo = factorNo;
  }

  public String getSPDate() {
    return SPDate;
  }

  public void setSPDate(String SPDate) {
    this.SPDate = SPDate;
  }

  public int getSPReference() {
    return SPReference;
  }

  public void setSPReference(int SPReference) {
    this.SPReference = SPReference;
  }

  public int getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(int customerId) {
    CustomerId = customerId;
  }

  public String getCustomerName() {
    return CustomerName;
  }

  public void setCustomerName(String customerName) {
    CustomerName = customerName;
  }

  public double getTotal() {
    return Total;
  }

  public void setTotal(double total) {
    Total = total;
  }

  public boolean isPosted() {
    return Posted;
  }

  public void setPosted(boolean posted) {
    Posted = posted;
  }

  public Date getPostDate() {
    return PostDate;
  }

  public void setPostDate(Date postDate) {
    PostDate = postDate;
  }

  public static final DiffUtil.ItemCallback<PostedPrefactorInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<PostedPrefactorInfo>() {
    @Override
    public boolean areItemsTheSame(PostedPrefactorInfo oldItem, PostedPrefactorInfo newItem) {
      return oldItem.getFactorNo() == newItem.getFactorNo();
    }

    @Override
    public boolean areContentsTheSame(PostedPrefactorInfo oldItem, PostedPrefactorInfo newItem) {
      return oldItem.getFactorNo() == newItem.getFactorNo();
    }
  };

}
