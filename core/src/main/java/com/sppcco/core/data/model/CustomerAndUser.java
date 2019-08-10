package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;


@Entity(
  tableName = "__CustomerAndUser__",
  primaryKeys = "CustomerId",
  indices = {@Index(value = "CustomerId")}
)
public class CustomerAndUser implements Serializable, BaseColumns {


  @SerializedName("CustomerId")
  @Expose
  @ColumnInfo(name = "CustomerId")
  private int CustomerId;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  @SerializedName("UserName")
  @Expose
  @ColumnInfo(name = "UserName")
  private String UserName;

  @SerializedName("FPId")
  @Expose
  private int FPId;


  public CustomerAndUser() {

  }

  // region Getter Setter

  public int getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(int customerId) {
    CustomerId = customerId;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  // endregion Getter Setter

}
