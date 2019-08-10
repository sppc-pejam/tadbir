package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.converter.timestamp.TimestampConverter;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(
  tableName = "__SPStatus__",
  indices = {@Index(value = "SPId", unique = true)},
  foreignKeys = @ForeignKey(
    entity = SPFactor.class,
    parentColumns = "_id",
    childColumns = "SPId",
    onDelete = ForeignKey.CASCADE)
)
public class SPStatus implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("SPId")
  @Expose
  @ColumnInfo(name = "SPId")
  private int SPId;

  @SerializedName("FactorNo")
  @Expose
  @ColumnInfo(name = "FactorNo")
  private int FactorNo;

  @SerializedName("FactorType")
  @Expose
  @ColumnInfo(name = "FactorType")
  private int FactorType;

  @SerializedName("SPRefId")
  @Expose
  @ColumnInfo(name = "SPRefId")
  private int SPRefId;

  @SerializedName("SPReference")
  @Expose
  @ColumnInfo(name = "SPReference")
  private int SPReference;

  @SerializedName("FPId")
  @Expose
  @ColumnInfo(name = "FPId")
  private int FPId;

  @SerializedName("UserId")
  @Expose
  @ColumnInfo(name = "UserId")
  private int UserId;

  @SerializedName("Approved")
  @Expose
  @ColumnInfo(name = "Approved")
  private boolean Approved;

  @SerializedName("ApprovalDate")
  @Expose
  @ColumnInfo(name = "ApprovalDate")
  @TypeConverters({TimestampConverter.class})
  private Date ApprovalDate;

  @SerializedName("Edited")
  @Expose
  @ColumnInfo(name = "Edited")
  private boolean Edited;

  @SerializedName("EditedDate")
  @Expose
  @ColumnInfo(name = "EditedDate")
  @TypeConverters({TimestampConverter.class})
  private Date EditedDate;

  @SerializedName("Faulted")
  @Expose
  @ColumnInfo(name = "Faulted")
  private int Faulted;

  @SerializedName("FaultalDate")
  @Expose
  @ColumnInfo(name = "FaultalDate")
  @TypeConverters({TimestampConverter.class})
  private Date FaultalDate;

  @SerializedName("Reservation")
  @Expose
  @ColumnInfo(name = "Reservation")
  private int Reservation;

  @SerializedName("ReservationDate")
  @Expose
  @ColumnInfo(name = "ReservationDate")
  @TypeConverters({TimestampConverter.class})
  private Date ReservationDate;

  @SerializedName("Retrieved")
  @Expose
  @ColumnInfo(name = "Retrieved")
  private boolean Retrieved;

  @SerializedName("RetrievalDate")
  @Expose
  @ColumnInfo(name = "RetrievalDate")
  @TypeConverters({TimestampConverter.class})
  private Date RetrievalDate;

  @SerializedName("Posted")
  @Expose
  @ColumnInfo(name = "Posted")
  private boolean Posted;

  @SerializedName("PostedDate")
  @Expose
  @ColumnInfo(name = "PostedDate")
  @TypeConverters({TimestampConverter.class})
  private Date PostDate;

  @SerializedName("PostedLatitude")
  @Expose
  @ColumnInfo(name = "PostedLatitude")
  private double PostedLatitude;

  @SerializedName("PostedLongitude")
  @Expose
  @ColumnInfo(name = "PostedLongitude")
  private double PostedLongitude;

  public SPStatus() {
    super();
  }


  public SPStatus(int id, int SPId, int factorNo, int factorType, int SPRefId, int SPReference, int FPId, int userId,
                  boolean approved, Date approvalDate, boolean edited, Date editedDate,
                  int faulted, Date faultalDate, int reservation, Date reservationDate,
                  boolean retrieved, Date retrievalDate, boolean posted, Date postDate,
                  double postedLatitude, double postedLongitude) {

    setId(id);
    setSPId(SPId);
    setFactorNo(factorNo);
    setFactorType(factorType);
    setSPRefId(SPRefId);
    setSPReference(SPReference);
    setFPId(FPId);
    setUserId(userId);
    setApproved(approved);
    setApprovalDate(approvalDate);
    setEdited(edited);
    setEditedDate(editedDate);
    setFaulted(faulted);
    setFaultalDate(faultalDate);
    setReservation(reservation);
    setReservationDate(reservationDate);
    setRetrieved(retrieved);
    setRetrievalDate(retrievalDate);
    setPosted(posted);
    setPostDate(postDate);
    setPostedLatitude(postedLatitude);
    setPostedLongitude(postedLongitude);
  }

  public SPStatus(int SPId, int factorNo, int factorType,int SPRefId, int SPReference, int FPId, int userId,
                  boolean approved, Date approvalDate, boolean edited, Date editedDate,
                  int faulted, Date faultalDate, int reservation, Date reservationDate,
                  boolean retrieved, Date retrievalDate, boolean posted, Date postDate,
                  double postedLatitude, double postedLongitude) {

    setSPId(SPId);
    setFactorNo(factorNo);
    setFactorType(factorType);
    setSPRefId(SPRefId);
    setSPReference(SPReference);
    setFPId(FPId);
    setUserId(userId);
    setApproved(approved);
    setApprovalDate(approvalDate);
    setEdited(edited);
    setEditedDate(editedDate);
    setFaulted(faulted);
    setFaultalDate(faultalDate);
    setReservation(reservation);
    setReservationDate(reservationDate);
    setRetrieved(retrieved);
    setRetrievalDate(retrievalDate);
    setPosted(posted);
    setPostDate(postDate);
    setPostedLatitude(postedLatitude);
    setPostedLongitude(postedLongitude);
  }

  public static SPStatus getSPStatusWithDefaultValue() {
    SPStatus spStatus = new SPStatus();

    spStatus.setSPId(0);
    spStatus.setFactorNo(0);
    spStatus.setFactorType(0);
    spStatus.setSPRefId(0);
    spStatus.setSPReference(0);
    spStatus.setFPId(BaseApplication.getFPId());
    spStatus.setUserId(BaseApplication.getUserId());
    spStatus.setApproved(false);
    spStatus.setApprovalDate(CalenderManager.getCurrentDateStampTime());
    spStatus.setEdited(false);
    spStatus.setEditedDate(CalenderManager.getCurrentDateStampTime());
    spStatus.setFaulted(0);
    spStatus.setFaultalDate(CalenderManager.getCurrentDateStampTime());
    spStatus.setReservation(0);
    spStatus.setReservationDate(CalenderManager.getCurrentDateStampTime());
    spStatus.setRetrieved(false);
    spStatus.setRetrievalDate(CalenderManager.getCurrentDateStampTime());
    spStatus.setPosted(false);
    spStatus.setPostDate(CalenderManager.getCurrentDateStampTime());
    spStatus.setPostedLatitude(0);
    spStatus.setPostedLongitude(0);

    return spStatus;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getSPId() {
    return SPId;
  }

  public void setSPId(int SPId) {
    this.SPId = SPId;
  }

  public int getFactorNo() {
    return FactorNo;
  }

  public void setFactorNo(int factorNo) {
    FactorNo = factorNo;
  }

  public int getFactorType() {
    return FactorType;
  }

  public void setFactorType(int factorType) {
    FactorType = factorType;
  }

  public int getSPRefId() {
    return SPRefId;
  }

  public void setSPRefId(int SPRefId) {
    this.SPRefId = SPRefId;
  }

  public int getSPReference() {
    return SPReference;
  }

  public void setSPReference(int SPReference) {
    this.SPReference = SPReference;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
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

  public int getReservation() {
    return Reservation;
  }

  public void setReservation(int reservation) {
    Reservation = reservation;
  }

  public Date getReservationDate() {
    return ReservationDate;
  }

  public void setReservationDate(Date reservationDate) {
    ReservationDate = reservationDate;
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

  public boolean isPosted() {
    return Posted;
  }

  public void setPosted(boolean posted) {
    Posted = posted;
  }

  public Date getPostDate() {
    return PostDate;
  }

  public void setPostDate(Date postDate) {
    PostDate = postDate;
  }

  public double getPostedLatitude() {
    return PostedLatitude;
  }

  public void setPostedLatitude(double postedLatitude) {
    PostedLatitude = postedLatitude;
  }

  public double getPostedLongitude() {
    return PostedLongitude;
  }

  public void setPostedLongitude(double postedLongitude) {
    PostedLongitude = postedLongitude;
  }

  @Override
  public String toString() {
    return "SPStatus{" +
      "Id=" + Id +
      ", SPId=" + SPId +
      ", FactorNo=" + FactorNo +
      ", FactorType=" + FactorType +
      ", SPReference=" + SPReference +
      ", FPId=" + FPId +
      ", UserId=" + UserId +
      ", Approved=" + Approved +
      ", ApprovalDate='" + ApprovalDate + '\'' +
      ", Edited=" + Edited +
      ", EditedDate='" + EditedDate + '\'' +
      ", Faulted=" + Faulted +
      ", FaultalDate='" + FaultalDate + '\'' +
      ", Reservation=" + Reservation +
      ", ReservationDate='" + ReservationDate + '\'' +
      ", Retrieved=" + Retrieved +
      ", RetrievalDate='" + RetrievalDate + '\'' +
      ", Posted=" + Posted +
      ", PostDate='" + PostDate + '\'' +
      ", PostedLatitude=" + PostedLatitude +
      ", PostedLongitude=" + PostedLongitude +
      '}';
  }
}
