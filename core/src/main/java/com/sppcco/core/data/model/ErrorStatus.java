package com.sppcco.core.data.model;

import android.provider.BaseColumns;


import com.sppcco.core.framework.application.BaseApplication;

import java.io.Serializable;

import androidx.room.Entity;

/**
 * Created by m_pejam on 06/17/18.
 *
 */

@Entity(tableName = "__ErrorStatus__",
  primaryKeys = {"Id", "ArticleId", "DocType", "FPId"})
public class ErrorStatus implements Serializable, BaseColumns {

  private int Id;

  private int ArticleId;

  private int ErrorType;

  private int DocType;

  private int FPId;

  public ErrorStatus() {
    super();
  }

  public ErrorStatus(int id, int articleId, int errorType, int docType, int FPId) {
    setId(id);
    setArticleId(articleId);
    setErrorType(errorType);
    setDocType(docType);
    setFPId(FPId);
  }

  public static ErrorStatus getErrorStatusWithDefaultValue() {
    ErrorStatus errorStatus = new ErrorStatus();

    errorStatus.setId(0);
    errorStatus.setArticleId(0);
    errorStatus.setErrorType(0);
    errorStatus.setDocType(0);
    errorStatus.FPId = BaseApplication.getFPId();

    return errorStatus;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getArticleId() {
    return ArticleId;
  }

  public void setArticleId(int articleId) {
    ArticleId = articleId;
  }

  public int getErrorType() {
    return ErrorType;
  }

  public void setErrorType(int errorType) {
    ErrorType = errorType;
  }

  public int getDocType() {
    return DocType;
  }

  public void setDocType(int docType) {
    DocType = docType;
  }

  public int getFPId() {
    return FPId;
  }

  public void setFPId(int FPId) {
    this.FPId = FPId;
  }


}
