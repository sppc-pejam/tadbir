package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "__Customer__",
  primaryKeys = {"_id", "FPId"})
public class Customer implements Serializable, BaseColumns {


  //---------------------------------------------
  public static final int SELLER_BUYER = 0;
  public static final int SELLER = 1;
  public static final int BUYER = 2;
  //---------------------------------------------


  @SerializedName("Id")
  @Expose
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("Type")
  @Expose
  @ColumnInfo(name = "Type")
  private int Type;

  @SerializedName("AccId")
  @Expose
  @ColumnInfo(name = "AccId")
  private String AccId;

  @SerializedName("FAccId")
  @Expose
  @ColumnInfo(name = "FAccId")
  private int FAccId;

  @SerializedName("PrePayAccId")
  @Expose
  @ColumnInfo(name = "PrePayAccId")
  private String PrePayAccId;

  @SerializedName("PreRcvAccId")
  @Expose
  @ColumnInfo(name = "PreRcvAccId")
  private String PreRcvAccId;

  @SerializedName("PrePayDepr")
  @Expose
  @ColumnInfo(name = "PrePayDepr")
  private float PrePayDepr;

  @SerializedName("PreRcvDepr")
  @Expose
  @ColumnInfo(name = "PreRcvDepr")
  private float PreRcvDepr;

  @SerializedName("Credit")
  @Expose
  @ColumnInfo(name = "Credit")
  private float Credit;

  @SerializedName("Name")
  @Expose
  @ColumnInfo(name = "Name")
  private String Name;

  @SerializedName("PhoneNo")
  @Expose
  @ColumnInfo(name = "PhoneNo")
  private String PhoneNo;

  @SerializedName("FaxNo")
  @Expose
  @ColumnInfo(name = "FaxNo")
  private String FaxNo;

  @SerializedName("Email")
  @Expose
  @ColumnInfo(name = "Email")
  private String Email;

  @SerializedName("EcCode")
  @Expose
  @ColumnInfo(name = "EcCode")
  private String EcCode;

  @SerializedName("Address")
  @Expose
  @ColumnInfo(name = "Address")
  private String Address;

  @SerializedName("CDesc")
  @Expose
  @ColumnInfo(name = "CDesc")
  private String CDesc;

  @SerializedName("CurrencyVal")
  @Expose
  @ColumnInfo(name = "CurrencyVal")
  private float CurrencyVal;

  @SerializedName("CurrencyId")
  @Expose
  @ColumnInfo(name = "CurrencyId")
  private int CurrencyId;

  @SerializedName("ZipCode")
  @Expose
  @ColumnInfo(name = "ZipCode")
  private String ZipCode;

  @SerializedName("InitBal")
  @Expose
  @ColumnInfo(name = "InitBal")
  private float InitBal;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  @SerializedName("SecId")
  @Expose
  @ColumnInfo(name = "SecId")
  private int SecId;

  @SerializedName("ETime")
  @Expose
  @ColumnInfo(name = "ETime")
  private String ETime;

  @SerializedName("EDate")
  @Expose
  @ColumnInfo(name = "EDate")
  private String EDate;

  @SerializedName("LRes")
  @Expose
  @ColumnInfo(name = "LRes")
  private int LRes;

  @SerializedName("DRes")
  @Expose
  @ColumnInfo(name = "DRes")
  private float DRes;

  @SerializedName("TRes")
  @Expose
  @ColumnInfo(name = "TRes")
  private String TRes;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  @SerializedName("Discount1")
  @Expose
  @ColumnInfo(name = "Discount1")
  private float Discount1;

  @SerializedName("Discount2")
  @Expose
  @ColumnInfo(name = "Discount2")
  private float Discount2;

  @SerializedName("GroupName")
  @Expose
  @ColumnInfo(name = "GroupName")
  private String GroupName;

  @SerializedName("Province")
  @Expose
  @ColumnInfo(name = "Province")
  private String Province;

  @SerializedName("City")
  @Expose
  @ColumnInfo(name = "City")
  private String City;

  @SerializedName("Zone")
  @Expose
  @ColumnInfo(name = "Zone")
  private String Zone;

  @SerializedName("SubscriptionNo")
  @Expose
  @ColumnInfo(name = "SubscriptionNo")
  private String SubscriptionNo;

  @SerializedName("CCId")
  @Expose
  @ColumnInfo(name = "CCId")
  private int CCId;

  @SerializedName("PrjId")
  @Expose
  @ColumnInfo(name = "PrjId")
  private int PrjId;

  @SerializedName("PrePayFAccId")
  @Expose
  @ColumnInfo(name = "PrePayFAccId")
  private int PrePayFAccId;

  @SerializedName("PrePayCCId")
  @Expose
  @ColumnInfo(name = "PrePayCCId")
  private int PrePayCCId;

  @SerializedName("PrePayPrjId")
  @Expose
  @ColumnInfo(name = "PrePayPrjId")
  private int PrePayPrjId;

  @SerializedName("PreRcvFAccId")
  @Expose
  @ColumnInfo(name = "PreRcvFAccId")
  private int PreRcvFAccId;

  @SerializedName("PreRcvCCId")
  @Expose
  @ColumnInfo(name = "PreRcvCCId")
  private int PreRcvCCId;

  @SerializedName("PreRcvPrjId")
  @Expose
  @ColumnInfo(name = "PreRcvPrjId")
  private int PreRcvPrjId;

  @SerializedName("TownShip")
  @Expose
  @ColumnInfo(name = "TownShip")
  private String TownShip;

  @SerializedName("PostalCode")
  @Expose
  @ColumnInfo(name = "PostalCode")
  private String PostalCode;

  @SerializedName("RegisterCode")
  @Expose
  @ColumnInfo(name = "RegisterCode")
  private String RegisterCode;

  @SerializedName("Active")
  @Expose
  @ColumnInfo(name = "Active")
  private int Active;

  @SerializedName("MashmoolMaliat")
  @Expose
  @ColumnInfo(name = "MashmoolMaliat")
  private int MashmoolMaliat;

  @SerializedName("MobileNo")
  @Expose
  @ColumnInfo(name = "MobileNo")
  private String MobileNo;

  @SerializedName("MohlatePardakht")
  @Expose
  @ColumnInfo(name = "MohlatePardakht")
  private int MohlatePardakht;


  public Customer() {
    super();
  }


  public Customer(int id, int type, String accId, int FAccId, String prePayAccId, String preRcvAccId, float prePayDepr, float preRcvDepr, float credit, String name,
                  String phoneNo, String faxNo, String email, String ecCode, String address, String CDesc, float currencyVal, int currencyId, String zipCode,
                  float initBal, int userId, int secId, String ETime, String EDate, int LRes, float DRes, String TRes, int FPId, float discount1, float discount2,
                  String groupName, String province, String city, String zone, String subscriptionNo, int CCId, int prjId, int prePayFAccId, int prePayCCId,
                  int prePayPrjId, int preRcvFAccId, int preRcvCCId, int preRcvPrjId, String townShip, String postalCode, String registerCode, int active,
                  int mashmoolMaliat, String mobileNo, int mohlatePardakht) {
    Id = id;
    Type = type;
    AccId = accId;
    this.FAccId = FAccId;
    PrePayAccId = prePayAccId;
    PreRcvAccId = preRcvAccId;
    PrePayDepr = prePayDepr;
    PreRcvDepr = preRcvDepr;
    Credit = credit;
    Name = name;
    PhoneNo = phoneNo;
    FaxNo = faxNo;
    Email = email;
    EcCode = ecCode;
    Address = address;
    this.CDesc = CDesc;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    ZipCode = zipCode;
    InitBal = initBal;
    UserId = userId;
    SecId = secId;
    this.ETime = ETime;
    this.EDate = EDate;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    this.FPId = FPId;
    Discount1 = discount1;
    Discount2 = discount2;
    GroupName = groupName;
    Province = province;
    City = city;
    Zone = zone;
    SubscriptionNo = subscriptionNo;
    this.CCId = CCId;
    PrjId = prjId;
    PrePayFAccId = prePayFAccId;
    PrePayCCId = prePayCCId;
    PrePayPrjId = prePayPrjId;
    PreRcvFAccId = preRcvFAccId;
    PreRcvCCId = preRcvCCId;
    PreRcvPrjId = preRcvPrjId;
    TownShip = townShip;
    PostalCode = postalCode;
    RegisterCode = registerCode;
    Active = active;
    MashmoolMaliat = mashmoolMaliat;
    MobileNo = mobileNo;
    MohlatePardakht = mohlatePardakht;
  }

  public Customer(int type, String accId, int FAccId, String prePayAccId, String preRcvAccId, float prePayDepr, float preRcvDepr, float credit, String name,
                  String phoneNo, String faxNo, String email, String ecCode, String address, String CDesc, float currencyVal, int currencyId, String zipCode,
                  float initBal, int userId, int secId, String ETime, String EDate, int LRes, float DRes, String TRes, int FPId, float discount1, float discount2,
                  String groupName, String province, String city, String zone, String subscriptionNo, int CCId, int prjId, int prePayFAccId, int prePayCCId,
                  int prePayPrjId, int preRcvFAccId, int preRcvCCId, int preRcvPrjId, String townShip, String postalCode, String registerCode, int active,
                  int mashmoolMaliat, String mobileNo, int mohlatePardakht) {
    Type = type;
    AccId = accId;
    this.FAccId = FAccId;
    PrePayAccId = prePayAccId;
    PreRcvAccId = preRcvAccId;
    PrePayDepr = prePayDepr;
    PreRcvDepr = preRcvDepr;
    Credit = credit;
    Name = name;
    PhoneNo = phoneNo;
    FaxNo = faxNo;
    Email = email;
    EcCode = ecCode;
    Address = address;
    this.CDesc = CDesc;
    CurrencyVal = currencyVal;
    CurrencyId = currencyId;
    ZipCode = zipCode;
    InitBal = initBal;
    UserId = userId;
    SecId = secId;
    this.ETime = ETime;
    this.EDate = EDate;
    this.LRes = LRes;
    this.DRes = DRes;
    this.TRes = TRes;
    this.FPId = FPId;
    Discount1 = discount1;
    Discount2 = discount2;
    GroupName = groupName;
    Province = province;
    City = city;
    Zone = zone;
    SubscriptionNo = subscriptionNo;
    this.CCId = CCId;
    PrjId = prjId;
    PrePayFAccId = prePayFAccId;
    PrePayCCId = prePayCCId;
    PrePayPrjId = prePayPrjId;
    PreRcvFAccId = preRcvFAccId;
    PreRcvCCId = preRcvCCId;
    PreRcvPrjId = preRcvPrjId;
    TownShip = townShip;
    PostalCode = postalCode;
    RegisterCode = registerCode;
    Active = active;
    MashmoolMaliat = mashmoolMaliat;
    MobileNo = mobileNo;
    MohlatePardakht = mohlatePardakht;
  }


  public static Customer getCustomerWithDefaultValue(int type) {
    Customer customer = new Customer();

    customer.setType(type);
    customer.setAccId("0");
    customer.setFAccId(0);
    customer.setPrePayAccId("0");
    customer.setPreRcvAccId("0");
    customer.setPrePayDepr(0);
    customer.setPreRcvDepr(0);
    customer.setCredit(0);
    customer.setName("");
    customer.setPhoneNo("");
    customer.setFaxNo("");
    customer.setEmail("");
    customer.setEcCode("");
    customer.setAddress("");
    customer.setCDesc("");
    customer.setCurrencyVal(0);
    customer.setCurrencyId(0);
    customer.setZipCode("0");
    customer.setInitBal(0);
    customer.setUserId(BaseApplication.getUserId());
    customer.setSecId(0);
    customer.setETime(CalenderManager.stampDateToString(CalenderManager.getCurrentDateStampTime()));
    customer.setEDate(CalenderManager.stampDateToString(CalenderManager.getCurrentDateStampTime()));
    customer.setETime("");
    customer.setEDate("");
    customer.setLRes(0);
    customer.setDRes(0);
    customer.setTRes("0");
    customer.setFPId(BaseApplication.getFPId());
    customer.setDiscount1(0);
    customer.setDiscount2(0);
    customer.setGroupName("");
    customer.setProvince("");
    customer.setCity("");
    customer.setZone("");
    customer.setSubscriptionNo("");
    customer.setCCId(0);
    customer.setPrjId(0);
    customer.setPrePayFAccId(0);
    customer.setPrePayCCId(0);
    customer.setPrePayPrjId(0);
    customer.setPreRcvFAccId(0);
    customer.setPreRcvCCId(0);
    customer.setPreRcvPrjId(0);
    customer.setTownShip("");
    customer.setPostalCode("");
    customer.setRegisterCode("");
    customer.setActive(0);
    customer.setMashmoolMaliat(0);
    customer.setMobileNo("");
    customer.setMohlatePardakht(0);

    return customer;
  }


  /**
   * @return the id
   */
  public int getId() {
    return Id;
  }


  /**
   * @param id the id to set
   */
  public void setId(int id) {
    Id = id;
  }


  /**
   * @return the type
   */
  public int getType() {
    return Type;
  }


  /**
   * @param type the type to set
   */
  public void setType(int type) {
    Type = type;
  }


  /**
   * @return the accId
   */
  public String getAccId() {
    return AccId;
  }


  /**
   * @param accId the accId to set
   */
  public void setAccId(String accId) {
    AccId = accId;
  }


  /**
   * @return the fAccId
   */
  public int getFAccId() {
    return FAccId;
  }


  /**
   * @param fAccId the fAccId to set
   */
  public void setFAccId(int fAccId) {
    FAccId = fAccId;
  }


  /**
   * @return the prePayAccId
   */
  public String getPrePayAccId() {
    return PrePayAccId;
  }


  /**
   * @param prePayAccId the prePayAccId to set
   */
  public void setPrePayAccId(String prePayAccId) {
    PrePayAccId = prePayAccId;
  }


  /**
   * @return the preRcvAccId
   */
  public String getPreRcvAccId() {
    return PreRcvAccId;
  }


  /**
   * @param preRcvAccId the preRcvAccId to set
   */
  public void setPreRcvAccId(String preRcvAccId) {
    PreRcvAccId = preRcvAccId;
  }


  /**
   * @return the prePayDepr
   */
  public float getPrePayDepr() {
    return PrePayDepr;
  }


  /**
   * @param prePayDepr the prePayDepr to set
   */
  public void setPrePayDepr(float prePayDepr) {
    PrePayDepr = prePayDepr;
  }


  /**
   * @return the preRcvDepr
   */
  public float getPreRcvDepr() {
    return PreRcvDepr;
  }


  /**
   * @param preRcvDepr the preRcvDepr to set
   */
  public void setPreRcvDepr(float preRcvDepr) {
    PreRcvDepr = preRcvDepr;
  }


  /**
   * @return the credit
   */
  public float getCredit() {
    return Credit;
  }


  /**
   * @param credit the credit to set
   */
  public void setCredit(float credit) {
    Credit = credit;
  }


  /**
   * @return the name
   */
  public String getName() {
    return Name;
  }


  /**
   * @param name the name to set
   */
  public void setName(String name) {
    Name = name;
  }


  /**
   * @return the phoneNo
   */
  public String getPhoneNo() {
    return PhoneNo;
  }


  /**
   * @param phoneNo the phoneNo to set
   */
  public void setPhoneNo(String phoneNo) {
    PhoneNo = phoneNo;
  }


  /**
   * @return the faxNo
   */
  public String getFaxNo() {
    return FaxNo;
  }


  /**
   * @param faxNo the faxNo to set
   */
  public void setFaxNo(String faxNo) {
    FaxNo = faxNo;
  }


  /**
   * @return the email
   */
  public String getEmail() {
    return Email;
  }


  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    Email = email;
  }


  /**
   * @return the ecCode
   */
  public String getEcCode() {
    return EcCode;
  }


  /**
   * @param ecCode the ecCode to set
   */
  public void setEcCode(String ecCode) {
    EcCode = ecCode;
  }


  /**
   * @return the address
   */
  public String getAddress() {
    return Address;
  }


  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    Address = address;
  }


  /**
   * @return the cDesc
   */
  public String getCDesc() {
    return CDesc;
  }


  /**
   * @param cDesc the cDesc to set
   */
  public void setCDesc(String cDesc) {
    CDesc = cDesc;
  }


  /**
   * @return the currencyVal
   */
  public float getCurrencyVal() {
    return CurrencyVal;
  }


  /**
   * @param currencyVal the currencyVal to set
   */
  public void setCurrencyVal(float currencyVal) {
    CurrencyVal = currencyVal;
  }


  /**
   * @return the currencyId
   */
  public int getCurrencyId() {
    return CurrencyId;
  }


  /**
   * @param currencyId the currencyId to set
   */
  public void setCurrencyId(int currencyId) {
    CurrencyId = currencyId;
  }


  /**
   * @return the zipCode
   */
  public String getZipCode() {
    return ZipCode;
  }


  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(String zipCode) {
    ZipCode = zipCode;
  }


  /**
   * @return the initBal
   */
  public float getInitBal() {
    return InitBal;
  }


  /**
   * @param initBal the initBal to set
   */
  public void setInitBal(float initBal) {
    InitBal = initBal;
  }


  /**
   * @return the userId
   */
  public int getUserId() {
    return UserId;
  }


  /**
   * @param userId the userId to set
   */
  public void setUserId(int userId) {
    UserId = userId;
  }


  /**
   * @return the secId
   */
  public int getSecId() {
    return SecId;
  }


  /**
   * @param secId the secId to set
   */
  public void setSecId(int secId) {
    SecId = secId;
  }


  /**
   * @return the eTime
   */
  public String getETime() {
    return ETime;
  }


  /**
   * @param eTime the eTime to set
   */
  public void setETime(String eTime) {
    ETime = eTime;
  }


  /**
   * @return the eDate
   */
  public String getEDate() {
    return EDate;
  }


  /**
   * @param eDate the eDate to set
   */
  public void setEDate(String eDate) {
    EDate = eDate;
  }


  /**
   * @return the lRes
   */
  public int getLRes() {
    return LRes;
  }


  /**
   * @param lRes the lRes to set
   */
  public void setLRes(int lRes) {
    LRes = lRes;
  }


  /**
   * @return the dRes
   */
  public float getDRes() {
    return DRes;
  }


  /**
   * @param dRes the dRes to set
   */
  public void setDRes(float dRes) {
    DRes = dRes;
  }


  /**
   * @return the tRes
   */
  public String getTRes() {
    return TRes;
  }


  /**
   * @param tRes the tRes to set
   */
  public void setTRes(String tRes) {
    TRes = tRes;
  }


  /**
   * @return the fPId
   */
  public int getFPId() {
    return FPId;
  }


  /**
   * @param fPId the fPId to set
   */
  public void setFPId(int fPId) {
    FPId = fPId;
  }


  /**
   * @return the discount1
   */
  public float getDiscount1() {
    return Discount1;
  }


  /**
   * @param discount1 the discount1 to set
   */
  public void setDiscount1(float discount1) {
    Discount1 = discount1;
  }


  /**
   * @return the discount2
   */
  public float getDiscount2() {
    return Discount2;
  }


  /**
   * @param discount2 the discount2 to set
   */
  public void setDiscount2(float discount2) {
    Discount2 = discount2;
  }


  /**
   * @return the groupName
   */
  public String getGroupName() {
    return GroupName;
  }


  /**
   * @param groupName the groupName to set
   */
  public void setGroupName(String groupName) {
    GroupName = groupName;
  }


  /**
   * @return the province
   */
  public String getProvince() {
    return Province;
  }


  /**
   * @param province the province to set
   */
  public void setProvince(String province) {
    Province = province;
  }


  /**
   * @return the city
   */
  public String getCity() {
    return City;
  }


  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    City = city;
  }


  /**
   * @return the zone
   */
  public String getZone() {
    return Zone;
  }


  /**
   * @param zone the zone to set
   */
  public void setZone(String zone) {
    Zone = zone;
  }


  /**
   * @return the subscriptionNo
   */
  public String getSubscriptionNo() {
    return SubscriptionNo;
  }


  /**
   * @param subscriptionNo the subscriptionNo to set
   */
  public void setSubscriptionNo(String subscriptionNo) {
    SubscriptionNo = subscriptionNo;
  }


  /**
   * @return the cCId
   */
  public int getCCId() {
    return CCId;
  }


  /**
   * @param cCId the cCId to set
   */
  public void setCCId(int cCId) {
    CCId = cCId;
  }


  /**
   * @return the prjId
   */
  public int getPrjId() {
    return PrjId;
  }


  /**
   * @param prjId the prjId to set
   */
  public void setPrjId(int prjId) {
    PrjId = prjId;
  }


  /**
   * @return the prePayFAccId
   */
  public int getPrePayFAccId() {
    return PrePayFAccId;
  }


  /**
   * @param prePayFAccId the prePayFAccId to set
   */
  public void setPrePayFAccId(int prePayFAccId) {
    PrePayFAccId = prePayFAccId;
  }


  /**
   * @return the prePayCCId
   */
  public int getPrePayCCId() {
    return PrePayCCId;
  }


  /**
   * @param prePayCCId the prePayCCId to set
   */
  public void setPrePayCCId(int prePayCCId) {
    PrePayCCId = prePayCCId;
  }


  /**
   * @return the prePayPrjId
   */
  public int getPrePayPrjId() {
    return PrePayPrjId;
  }


  /**
   * @param prePayPrjId the prePayPrjId to set
   */
  public void setPrePayPrjId(int prePayPrjId) {
    PrePayPrjId = prePayPrjId;
  }


  /**
   * @return the preRcvFAccId
   */
  public int getPreRcvFAccId() {
    return PreRcvFAccId;
  }


  /**
   * @param preRcvFAccId the preRcvFAccId to set
   */
  public void setPreRcvFAccId(int preRcvFAccId) {
    PreRcvFAccId = preRcvFAccId;
  }


  /**
   * @return the preRcvCCId
   */
  public int getPreRcvCCId() {
    return PreRcvCCId;
  }


  /**
   * @param preRcvCCId the preRcvCCId to set
   */
  public void setPreRcvCCId(int preRcvCCId) {
    PreRcvCCId = preRcvCCId;
  }


  /**
   * @return the preRcvPrjId
   */
  public int getPreRcvPrjId() {
    return PreRcvPrjId;
  }


  /**
   * @param preRcvPrjId the preRcvPrjId to set
   */
  public void setPreRcvPrjId(int preRcvPrjId) {
    PreRcvPrjId = preRcvPrjId;
  }


  /**
   * @return the townShip
   */
  public String getTownShip() {
    return TownShip;
  }


  /**
   * @param townShip the townShip to set
   */
  public void setTownShip(String townShip) {
    TownShip = townShip;
  }


  /**
   * @return the postalCode
   */
  public String getPostalCode() {
    return PostalCode;
  }


  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(String postalCode) {
    PostalCode = postalCode;
  }


  /**
   * @return the registerCode
   */
  public String getRegisterCode() {
    return RegisterCode;
  }


  /**
   * @param registerCode the registerCode to set
   */
  public void setRegisterCode(String registerCode) {
    RegisterCode = registerCode;
  }


  /**
   * @return the active
   */
  public int getActive() {
    return Active;
  }


  /**
   * @param active the active to set
   */
  public void setActive(int active) {
    Active = active;
  }


  /**
   * @return the mashmoolMaliat
   */
  public int getMashmoolMaliat() {
    return MashmoolMaliat;
  }


  /**
   * @param mashmoolMaliat the mashmoolMaliat to set
   */
  public void setMashmoolMaliat(int mashmoolMaliat) {
    MashmoolMaliat = mashmoolMaliat;
  }


  /**
   * @return the mobileNo
   */
  public String getMobileNo() {
    return MobileNo;
  }


  /**
   * @param mobileNo the mobileNo to set
   */
  public void setMobileNo(String mobileNo) {
    MobileNo = mobileNo;
  }


  /**
   * @return the mohlatePardakht
   */
  public int getMohlatePardakht() {
    return MohlatePardakht;
  }


  /**
   * @param mohlatePardakht the mohlatePardakht to set
   */
  public void setMohlatePardakht(int mohlatePardakht) {
    MohlatePardakht = mohlatePardakht;
  }
}
