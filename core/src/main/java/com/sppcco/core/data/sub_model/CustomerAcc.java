package com.sppcco.core.data.sub_model;

public class CustomerAcc {

  private String accountName;
  private String detailAccName;
  private String costCenterName;
  private String projectName;
  private String buyerAccount;

  public CustomerAcc() {
    super();
  }

  /*public CustomerAcc(String accountName, String detailAccName, String costCenterName, String projectName, String buyerAccount) {
    this.accountName = accountName;
    this.detailAccName = detailAccName;
    this.costCenterName = costCenterName;
    this.projectName = projectName;
    this.buyerAccount = buyerAccount;
  }*/

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getDetailAccName() {
    return detailAccName;
  }

  public void setDetailAccName(String detailAccName) {
    this.detailAccName = detailAccName;
  }

  public String getCostCenterName() {
    return costCenterName;
  }

  public void setCostCenterName(String costCenterName) {
    this.costCenterName = costCenterName;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getBuyerAccount() {
    return buyerAccount;
  }

  public void setBuyerAccount(String buyerAccount) {
    this.buyerAccount = buyerAccount;
  }
}
