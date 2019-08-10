package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Created by m_pejam on 01/24/18.
 *
 */

@Entity(tableName = "__TablesStatus__")
public class TablesStatus implements Serializable,BaseColumns {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @ColumnInfo(name = "TableName")
  private String TableName;

  @ColumnInfo(name = "EnglishName")
  private String EnglishName;

  @ColumnInfo(name = "PersianName")
  private String PersianName;

  @ColumnInfo(name = "LastUpdateDate")
  private String LastUpdateDate;

  @ColumnInfo(name = "LastUpdateRowCount")
  private int LastUpdateRowCount;

  @ColumnInfo(name = "FPId")
  private int FPId;

  public TablesStatus() {
    super();
  }

  public TablesStatus(int id, String tableName, String englishName, String persianName, String lastUpdateDate, int lastUpdateRowCount, int FPId) {
    Id = id;
    TableName = tableName;
    EnglishName = englishName;
    PersianName = persianName;
    LastUpdateDate = lastUpdateDate;
    LastUpdateRowCount = lastUpdateRowCount;
    this.FPId = FPId;
  }

  public TablesStatus(String tableName, String englishName, String persianName, String lastUpdateDate, int lastUpdateRowCount, int FPId) {
    TableName = tableName;
    EnglishName = englishName;
    PersianName = persianName;
    LastUpdateDate = lastUpdateDate;
    LastUpdateRowCount = lastUpdateRowCount;
    this.FPId = FPId;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getTableName() {
    return TableName;
  }

  public void setTableName(String tableName) {
    TableName = tableName;
  }

  public String getEnglishName() {
    return EnglishName;
  }

  public void setEnglishName(String englishName) {
    EnglishName = englishName;
  }

  public String getPersianName() {
    return PersianName;
  }

  public void setPersianName(String persianName) {
    PersianName = persianName;
  }

  public String getLastUpdateDate() {
    return LastUpdateDate;
  }

  public void setLastUpdateDate(String lastUpdateDate) {
    LastUpdateDate = lastUpdateDate;
  }

  public int getLastUpdateRowCount() {
    return LastUpdateRowCount;
  }

  public void setLastUpdateRowCount(int lastUpdateRowCount) {
    LastUpdateRowCount = lastUpdateRowCount;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }
}
