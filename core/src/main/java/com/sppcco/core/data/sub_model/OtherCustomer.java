package com.sppcco.core.data.sub_model;

import java.io.Serializable;

public class OtherCustomer implements Serializable {

  private String Name;
  private String PhoneNo;
  private String EcCode;
  private String Address;
  private String AccId;
  private int FAccId;
  private int CCId;
  private int PrjId;

  public OtherCustomer() {
    super();
  }

  public OtherCustomer(String name, String phoneNo, String ecCode, String address,
                       String accId, int FAccId, int CCId, int prjId) {
    Name = name;
    PhoneNo = phoneNo;
    EcCode = ecCode;
    Address = address;
    AccId = accId;
    this.FAccId = FAccId;
    this.CCId = CCId;
    PrjId = prjId;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getPhoneNo() {
    return PhoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    PhoneNo = phoneNo;
  }

  public String getEcCode() {
    return EcCode;
  }

  public void setEcCode(String ecCode) {
    EcCode = ecCode;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String address) {
    Address = address;
  }

  public String getAccId() {
    return AccId;
  }

  public void setAccId(String accId) {
    AccId = accId;
  }

  public int getFAccId() {
    return FAccId;
  }

  public void setFAccId(int FAccId) {
    this.FAccId = FAccId;
  }

  public int getCCId() {
    return CCId;
  }

  public void setCCId(int CCId) {
    this.CCId = CCId;
  }

  public int getPrjId() {
    return PrjId;
  }

  public void setPrjId(int prjId) {
    PrjId = prjId;
  }
}
