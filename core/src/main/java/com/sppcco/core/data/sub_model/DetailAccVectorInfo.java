package com.sppcco.core.data.sub_model;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

@Entity
public class DetailAccVectorInfo implements Serializable {

  @NonNull
  @PrimaryKey
  private String code = "";
  private String accountName;
  private String parentAccount;
  private String accLevel;

  public DetailAccVectorInfo() {
    super();
  }

  @NonNull
  public String getCode() {
    return code;
  }

  public void setCode(@NonNull String code) {
    this.code = code;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getParentAccount() {
    return parentAccount;
  }

  public void setParentAccount(String parentAccount) {
    this.parentAccount = parentAccount;
  }

  public String getAccLevel() {
    return accLevel;
  }

  public void setAccLevel(String accLevel) {
    this.accLevel = accLevel;
  }

  public static final DiffUtil.ItemCallback<DetailAccVectorInfo>  DIFF_CALLBACK =  new DiffUtil.ItemCallback<DetailAccVectorInfo>() {
    @Override
    public boolean areItemsTheSame(DetailAccVectorInfo oldItem, DetailAccVectorInfo newItem) {
      return oldItem.getCode() == newItem.getCode();
    }

    @Override
    public boolean areContentsTheSame(DetailAccVectorInfo oldItem, DetailAccVectorInfo newItem) {
      return oldItem.getAccountName().equals(newItem.getAccountName());
    }
  };
}
