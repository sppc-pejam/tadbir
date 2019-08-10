package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__Options__",
  primaryKeys = {"_id", "UserId"})
public class Option implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("OptionVal")
  @Expose
  @ColumnInfo(name = "OptionVal")
  private String OptionVal;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  public Option() {
    super();
  }


  public Option(int id, String optionVal, int userId) {
    Id = id;
    OptionVal = optionVal;
    UserId = userId;
  }

  public Option(String optionVal, int userId) {
    OptionVal = optionVal;
    UserId = userId;
  }


  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getOptionVal() {
    return OptionVal;
  }

  public void setOptionVal(String optionVal) {
    OptionVal = optionVal;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }
}
