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
public class AccVectorInfo implements Serializable {

  @NonNull
  @PrimaryKey
  private String code = "";
  private String accountName;
  private String parentAccount;

  public AccVectorInfo() {
    super();
  }

  /*public AccVectorInfo(@NonNull String code, String accountName, String parentAccount) {
    this.code = code;
    this.accountName = accountName;
    this.parentAccount = parentAccount;
  }*/

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


  public static final DiffUtil.ItemCallback<AccVectorInfo>  DIFF_CALLBACK =  new DiffUtil.ItemCallback<AccVectorInfo>() {
    @Override
    public boolean areItemsTheSame(AccVectorInfo oldItem, AccVectorInfo newItem) {
      return oldItem.getCode() == newItem.getCode();
    }

    @Override
    public boolean areContentsTheSame(AccVectorInfo oldItem, AccVectorInfo newItem) {
      return oldItem.getAccountName().equals(newItem.getAccountName());
    }
  };
}
