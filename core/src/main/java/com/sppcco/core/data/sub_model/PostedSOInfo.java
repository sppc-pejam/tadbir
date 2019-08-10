package com.sppcco.core.data.sub_model;

import java.util.Date;

import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by m_pejam on 01/25/18.
 * ApprovedSOInfo
 */

@Entity
public class PostedSOInfo {

  @PrimaryKey
  private int SOId;
  private int SONo;
  private String SODate;
  private int SOReference;
  private int CustomerId;
  private String CustomerName;
  private boolean Posted;
  private Date PostDate;

  public int getSOId() {
    return SOId;
  }

  public void setSOId(int SOId) {
    this.SOId = SOId;
  }

  public int getSONo() {
    return SONo;
  }

  public void setSONo(int SONo) {
    this.SONo = SONo;
  }

  public String getSODate() {
    return SODate;
  }

  public void setSODate(String SODate) {
    this.SODate = SODate;
  }

  public int getSOReference() {
    return SOReference;
  }

  public void setSOReference(int SOReference) {
    this.SOReference = SOReference;
  }

  public PostedSOInfo() {
    super();
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

  public static final DiffUtil.ItemCallback<PostedSOInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<PostedSOInfo>() {
    @Override
    public boolean areItemsTheSame(PostedSOInfo oldItem, PostedSOInfo newItem) {
      return oldItem.getSONo() == newItem.getSONo();
    }

    @Override
    public boolean areContentsTheSame(PostedSOInfo oldItem, PostedSOInfo newItem) {
      return oldItem.getSONo() == newItem.getSONo();
    }
  };
}
