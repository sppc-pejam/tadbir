package com.sppcco.core.data.sub_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by b_nematzadeh on 01/18/18.
 */

public class PrintPreview implements Serializable {

  @SerializedName("UserId")
  @Expose
  private int UserId;

  @SerializedName("WorkspaceId")
  @Expose
  private int WorkspaceId;

  @SerializedName("FPId")
  @Expose
  private int FPId;

  @SerializedName("SubsystemId")
  @Expose
  private int SubsystemId;

  @SerializedName("ReportName")
  @Expose
  private String ReportName;

  @SerializedName("ParamTypes")
  @Expose
  private String ParamTypes;


  @SerializedName("ParamValues")
  @Expose
  private String ParamValues;

  public PrintPreview() {
    super();
  }

  public PrintPreview(int userId, int workspaceId, int FPId, int subsystemId, String reportName, String paramTypes, String paramValues) {
    UserId = userId;
    WorkspaceId = workspaceId;
    this.FPId = FPId;
    SubsystemId = subsystemId;
    ReportName = reportName;
    ParamTypes = paramTypes;
    ParamValues = paramValues;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }

  public int getWorkspaceId() {
    return WorkspaceId;
  }

  public void setWorkspaceId(int workspaceId) {
    WorkspaceId = workspaceId;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  public int getSubsystemId() {
    return SubsystemId;
  }

  public void setSubsystemId(int subsystemId) {
    SubsystemId = subsystemId;
  }

  public String getReportName() {
    return ReportName;
  }

  public void setReportName(String reportName) {
    ReportName = reportName;
  }

  public String getParamTypes() {
    return ParamTypes;
  }

  public void setParamTypes(String paramTypes) {
    ParamTypes = paramTypes;
  }

  public String getParamValues() {
    return ParamValues;
  }

  public void setParamValues(String paramValues) {
    ParamValues = paramValues;
  }
}
