package com.sppcco.core.data.sub_model;

import java.util.Date;

/**
 * Created by m_pejam on 01/25/18.
 * ApprovedSOInfo
 */

public class ApprovedSOInfo {

  private int SOId;
  private int SONo;
  private Date EDate;
  private int CustomerId;
  private String CustomerName;
  private boolean Approved;
  private Date ApprovalDate;
  private boolean Edited;
  private Date EditedDate;
  private int Faulted;
  private Date FaultalDate;
  private boolean Retrieved;
  private Date RetrievalDate;

  public ApprovedSOInfo() {
    super();
  }

  public ApprovedSOInfo(int SOId, int SONo, Date EDate, int customerId, String customerName, boolean approved, Date approvalDate, boolean edited, Date editedDate, int faulted, Date faultalDate, boolean retrieved, Date retrievalDate) {
    this.SOId = SOId;
    this.SONo = SONo;
    this.EDate = EDate;
    CustomerId = customerId;
    CustomerName = customerName;
    Approved = approved;
    ApprovalDate = approvalDate;
    Edited = edited;
    EditedDate = editedDate;
    Faulted = faulted;
    FaultalDate = faultalDate;
    Retrieved = retrieved;
    RetrievalDate = retrievalDate;
  }

  public int getSOId() {
    return SOId;
  }

  public void setSOId(int SOId) {
    this.SOId = SOId;
  }

  public int getSONo() {
    return SONo;
  }

  public void setSONo(int SONo) {
    this.SONo = SONo;
  }

  public Date getEDate() {
    return EDate;
  }

  public void setEDate(Date EDate) {
    this.EDate = EDate;
  }

  public int getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(int customerId) {
    CustomerId = customerId;
  }

  public String getCustomerName() {
    return CustomerName;
  }

  public void setCustomerName(String customerName) {
    CustomerName = customerName;
  }

  public boolean isApproved() {
    return Approved;
  }

  public void setApproved(boolean approved) {
    Approved = approved;
  }

  public Date getApprovalDate() {
    return ApprovalDate;
  }

  public void setApprovalDate(Date approvalDate) {
    ApprovalDate = approvalDate;
  }

  public boolean isEdited() {
    return Edited;
  }

  public void setEdited(boolean edited) {
    Edited = edited;
  }

  public Date getEditedDate() {
    return EditedDate;
  }

  public void setEditedDate(Date editedDate) {
    EditedDate = editedDate;
  }

  public int getFaulted() {
    return Faulted;
  }

  public void setFaulted(int faulted) {
    Faulted = faulted;
  }

  public Date getFaultalDate() {
    return FaultalDate;
  }

  public void setFaultalDate(Date faultalDate) {
    FaultalDate = faultalDate;
  }

  public boolean isRetrieved() {
    return Retrieved;
  }

  public void setRetrieved(boolean retrieved) {
    Retrieved = retrieved;
  }

  public Date getRetrievalDate() {
    return RetrievalDate;
  }

  public void setRetrievalDate(Date retrievalDate) {
    RetrievalDate = retrievalDate;
  }
}
