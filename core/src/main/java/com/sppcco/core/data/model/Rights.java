package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__Rights__",
  primaryKeys = {"ugId", "SubSystem","FormId"})
public class Rights implements Serializable, BaseColumns {


  @SerializedName("ugId")
  @Expose
  @ColumnInfo(name = "ugId")
  private int ugId;

  @SerializedName("SubSystem")
  @Expose
  @ColumnInfo(name = "SubSystem")
  private int SubSystem;

  @SerializedName("FormId")
  @Expose
  @ColumnInfo(name = "FormId")
  private int FormId;

  @SerializedName("accessRight")
  @Expose
  @ColumnInfo(name = "accessRight")
  private String accessRight;


  public Rights() {
    super();
  }

  public Rights(int ugId, int subSystem, int formId, String accessRight) {
    this.ugId = ugId;
    SubSystem = subSystem;
    FormId = formId;
    this.accessRight = accessRight;
  }

  public int getUgId() {
    return ugId;
  }

  public void setUgId(int ugId) {
    this.ugId = ugId;
  }

  public int getSubSystem() {
    return SubSystem;
  }

  public void setSubSystem(int subSystem) {
    SubSystem = subSystem;
  }

  public int getFormId() {
    return FormId;
  }

  public void setFormId(int formId) {
    FormId = formId;
  }

  public String getAccessRight() {
    return accessRight;
  }

  public void setAccessRight(String accessRight) {
    this.accessRight = accessRight;
  }

}
