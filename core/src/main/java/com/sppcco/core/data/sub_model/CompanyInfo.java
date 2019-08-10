package com.sppcco.core.data.sub_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ${BRANCH} on 12/31/2018.
 */
public class CompanyInfo implements Serializable {

  @SerializedName("dbName")
  private String dbName;

  @SerializedName("companyName")
  private String companyName;

  @SerializedName("wsId")
  private int WSId;

  public CompanyInfo() {
    super();
  }

  public CompanyInfo(String dbName, String companyName, int WSId) {
    this.dbName = dbName;
    this.companyName = companyName;
    this.WSId = WSId;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public int getWSId() {
    return WSId;
  }

  public void setWSId(int WSId) {
    this.WSId = WSId;
  }
}
