package com.sppcco.core.data.sub_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VersionInfo implements Serializable {

  @SerializedName("fileName")
  private String fileName;

  @SerializedName("versionNumber")
  private String versionNumber;

  @SerializedName("downloadLink")
  private String downloadLink;

  @SerializedName("imgUrl")
  private List<String> imgUrl;

  @SerializedName("releaseDate")
  private String releaseDate;

  @SerializedName("description")
  private List<String> description;

  public VersionInfo(String fileName, String versionNumber, String downloadLink, List<String> imgURL, String releaseDate, List<String> description) {
    this.fileName = fileName;
    this.versionNumber = versionNumber;
    this.downloadLink = downloadLink;
    this.imgUrl = imgURL;
    this.releaseDate = releaseDate;
    this.description = description;
  }

  public String getFileName() {
    return fileName + ".apk";
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public String getDownloadLink() {
    return downloadLink;
  }

  public void setDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
  }

  public List<String> getImgURL() {
    return imgUrl;
  }

  public void setImgURL(List<String> imgURL) {
    this.imgUrl = imgURL;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public List<String> getDescription() {
    return description;
  }

  public void setDescription(List<String> description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return
      "VersionInfo{" +
        "versionNumber = '" + versionNumber + '\'' +
        ",downloadLink = '" + downloadLink + '\'' +
        ",imgUrl = '" + imgUrl + '\'' +
        ",releaseDate = '" + releaseDate + '\'' +
        ",desc = '" + description + '\'' +
        "}";
  }
}