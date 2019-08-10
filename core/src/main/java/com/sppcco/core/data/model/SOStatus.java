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


/**
 * Created by m_pejam on 01/24/18.
 *
 */
@Entity(
  tableName = "__SOStatus__",
  indices = {@Index(value = "SOId", unique = true)},
  foreignKeys = @ForeignKey(
    entity = SalesOrder.class,
    parentColumns = "_id",
    childColumns = "SOId",
    onDelete = ForeignKey.CASCADE)
)

public class SOStatus implements Serializable,BaseColumns {

  @SerializedName("Id")
  @Expose
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "_id")
  private int Id;

  @SerializedName("SOId")
  @Expose
  @ColumnInfo(name = "SOId")
  private int SOId;

  @SerializedName("SONo")
  @Expose
  @ColumnInfo(name = "SONo")
  private int SONo;

  @SerializedName("SORefId")
  @Expose
  @ColumnInfo(name = "SORefId")
  private int SORefId;

  @SerializedName("SOReference")
  @Expose
  @ColumnInfo(name = "SOReference")
  private int SOReference;

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


  public SOStatus() {
    super();
  }

  public SOStatus(int id, int SOId, int SONo, int SORefId, int SOReference, int FPId, int userId,
                  boolean approved, Date approvalDate, boolean edited, Date editedDate,
                  int faulted, Date faultalDate, int reservation, Date reservationDate,
                  boolean retrieved, Date retrievalDate, boolean posted, Date postDate,
                  double postedLatitude, double postedLongitude) {

    setId(id);
    setSOId(SOId);
    setSONo(SONo);
    setSORefId(SORefId);
    setSOReference(SOReference);
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

  public SOStatus(int SOId, int SONo, int SORefId, int SOReference, int FPId, int userId,
                  boolean approved, Date approvalDate, boolean edited, Date editedDate,
                  int faulted, Date faultalDate, int reservation, Date reservationDate,
                  boolean retrieved, Date retrievalDate, boolean posted, Date postDate,
                  double postedLatitude, double postedLongitude) {

    setSOId(SOId);
    setSONo(SONo);
    setSORefId(SORefId);
    setSOReference(SOReference);
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


  public static SOStatus getSOStatusWithDefaultValue() {
    SOStatus soStatus = new SOStatus();

    soStatus.setSOId(0);
    soStatus.setSONo(0);
    soStatus.setSORefId(0);
    soStatus.setSOReference(0);
    soStatus.setFPId(BaseApplication.getFPId());
    soStatus.setUserId(BaseApplication.getUserId());
    soStatus.setApproved(false);
    soStatus.setApprovalDate(CalenderManager.getCurrentDateStampTime());
    soStatus.setEdited(false);
    soStatus.setEditedDate(CalenderManager.getCurrentDateStampTime());
    soStatus.setFaulted(0);
    soStatus.setFaultalDate(CalenderManager.getCurrentDateStampTime());
    soStatus.setReservation(0);
    soStatus.setReservationDate(CalenderManager.getCurrentDateStampTime());
    soStatus.setRetrieved(false);
    soStatus.setRetrievalDate(CalenderManager.getCurrentDateStampTime());
    soStatus.setPosted(false);
    soStatus.setPostDate(CalenderManager.getCurrentDateStampTime());
    soStatus.setPostedLatitude(0);
    soStatus.setPostedLongitude(0);

    return soStatus;
  }


  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
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

  public int getSORefId() {
    return SORefId;
  }

  public void setSORefId(int SORefId) {
    this.SORefId = SORefId;
  }

  public int getSOReference() {
    return SOReference;
  }

  public void setSOReference(int SOReference) {
    this.SOReference = SOReference;
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
    return "SOStatus{" +
      "Id=" + Id +
      ", SOId=" + SOId +
      ", SONo=" + SONo +
      ", SOReference=" + SOReference +
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