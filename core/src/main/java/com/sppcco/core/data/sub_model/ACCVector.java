package com.sppcco.core.data.sub_model;


import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;

import java.io.Serializable;

/**
 * Created by m_pejam on 01/18/18.
 */

public class ACCVector implements Serializable {

  private Account account;
  private DetailAcc detailAcc;
  private CostCenter costCenter;
  private Project project;
  private String accCode;

  public ACCVector() {
    super();
  }

  public ACCVector(Account account, DetailAcc detailAcc, CostCenter costCenter, Project project, String accCode) {
    this.account = account;
    this.detailAcc = detailAcc;
    this.costCenter = costCenter;
    this.project = project;
    this.accCode = accCode;
  }

  public ACCVector(Account account, DetailAcc detailAcc, CostCenter costCenter, Project project) {
    this.account = account;
    this.detailAcc = detailAcc;
    this.costCenter = costCenter;
    this.project = project;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public DetailAcc getDetailAcc() {
    return detailAcc;
  }

  public void setDetailAcc(DetailAcc detailAcc) {
    this.detailAcc = detailAcc;
  }

  public CostCenter getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(CostCenter costCenter) {
    this.costCenter = costCenter;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public String getAccCode() {
    return accCode;
  }

  public void setAccCode(String accCode) {
    this.accCode = accCode;
  }
}
