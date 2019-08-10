package com.sppcco.core.util.message;

import android.graphics.drawable.Drawable;


import com.sppcco.core.R;
import com.sppcco.core.enums.MessageCode;
import com.sppcco.core.enums.MessageType;
import com.sppcco.core.framework.application.BaseApplication;

import androidx.annotation.ColorRes;


public class Message {

  private static final int LENGTH_SHORT = -1;
  private static final int LENGTH_LONG = 0;

  //region
  private MessageCode mMessageCode;

  private String mTitle;
  private String mContent;
  private String mAction;

  private MessageType mMessageType;

  private Drawable mMessageIcon;

  @ColorRes
  private int mBackgroundColor;
  @ColorRes
  private int mContentTextColor;
  @ColorRes
  private int mActionTextColor;

  private int mDuration;

//endregion


  public Message(MessageCode mMessageCode,
                 String mTitle, String mContent, String mAction, MessageType mMessageType,
                 Drawable mMessageIcon,
                 int mBackgroundColor, int mContentTextColor, int mActionTextColor,
                 int mDuration) {

    setMessageCode(mMessageCode);
    setTitle(mTitle);
    setContent(mContent);
    setAction(mAction);
    setMessageType(mMessageType);
    setMessageIcon(mMessageIcon);
    setBackgroundColor(mBackgroundColor);
    setContentTextColor(mContentTextColor);
    setActionTextColor(mActionTextColor);
    setDuration(mDuration);
  }

  public static Message getMessageForCode(MessageCode messageCode) {
    switch (messageCode) {

      case EA_EMPTY_CUSTOMER:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_msg_empty_customer),
          BaseApplication.getResourceString(R.string.cpt_customer),
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_LONG
        );

      case E_EMPTY_ARTICLE:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_missing_row),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case WA_FIRST_SYNC:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_warning),
          BaseApplication.getResourceString(R.string.wrn_msg_necessary_sync),
          BaseApplication.getResourceString(R.string.cpt_sync),
          MessageType.WARNING,
          getMessageTypeIcon(MessageType.WARNING),
          getMessageTypeColor(MessageType.WARNING),
          getContentColor(MessageType.WARNING),
          getActionColor(MessageType.WARNING),
          LENGTH_LONG
        );

      case E_INPUT_NAME:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_input_name),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_LONG
        );


      case E_INPUT_POSTAL_CODE:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_input_postal_code),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_LONG
        );

      case E_INPUT_NATIONAL_CODE:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_input_national_code),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_LONG
        );

      case E_USER_NOT_ALLOW:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_user_not_allow),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case E_USER_NOT_ALLOW_APPEND:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_user_not_allow_append),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case E_USER_NOT_ALLOW_MODIFY:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_user_not_allow_modify),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case E_USER_NOT_ALLOW_DELETE:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_user_not_allow_delete),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case E_USER_NOT_ALLOW_NOT_BUYER:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_user_not_allow_not_buyer),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case E_REPEATED_CUSTOMER_NAME:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_repeated_customer_name),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case E_REPEATED_CUSTOMER_SUBSCRIPTION_NO:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_repeated_customer_subscription_no),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

      case S_SENT:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_warning),
          BaseApplication.getResourceString(R.string.msg_sent),
          null,
          MessageType.SUCCESS,
          getMessageTypeIcon(MessageType.SUCCESS),
          getMessageTypeColor(MessageType.SUCCESS),
          getContentColor(MessageType.SUCCESS),
          getActionColor(MessageType.SUCCESS),
          LENGTH_LONG
        );

      case E_NOT_SENT:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.msg_not_sent),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_LONG
        );

      case W_NO_ITEM_NEED_SYNC:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_warning),
          BaseApplication.getResourceString(R.string.wrn_msg_no_item_need_sync),
          null,
          MessageType.WARNING,
          getMessageTypeIcon(MessageType.WARNING),
          getMessageTypeColor(MessageType.WARNING),
          getContentColor(MessageType.WARNING),
          getActionColor(MessageType.WARNING),
          LENGTH_LONG
        );

      case E_USER_NOT_ACCESS:
        return new Message(
          messageCode,
          BaseApplication.getResourceString(R.string.cpt_error),
          BaseApplication.getResourceString(R.string.err_user_not_access),
          null,
          MessageType.DANGER,
          getMessageTypeIcon(MessageType.DANGER),
          getMessageTypeColor(MessageType.DANGER),
          getContentColor(MessageType.DANGER),
          getActionColor(MessageType.DANGER),
          LENGTH_SHORT
        );

    }

    return null;
  }

  private static int getMessageTypeColor(MessageType messageType) {

    int nTypeColor = R.color.secondary;
    if (messageType == MessageType.PRIMARY)
      nTypeColor = R.color.primary_dark;
    else if (messageType == MessageType.ACCENT)
      nTypeColor = R.color.secondary_dark;
    else if (messageType == MessageType.DANGER)
      nTypeColor = R.color.bts_danger_color_dark;
    else if (messageType == MessageType.SUCCESS)
      nTypeColor = R.color.bts_success_color_dark;
    else if (messageType == MessageType.WARNING)
      nTypeColor = R.color.bts_warning_color_dark;
    return nTypeColor;
  }

  private static int getContentColor(MessageType messageType) {

    int nTypeColor = R.color.secondary_light;
    if (messageType == MessageType.PRIMARY)
      nTypeColor = R.color.primary_light;
    else if (messageType == MessageType.ACCENT)
      nTypeColor = R.color.accent;
    else if (messageType == MessageType.DANGER)
      nTypeColor = R.color.bts_danger_color_light;
    else if (messageType == MessageType.SUCCESS)
      nTypeColor = R.color.bts_success_color_light;
    else if (messageType == MessageType.WARNING)
      nTypeColor = R.color.bts_warning_color_light;
    return nTypeColor;
  }

  private static int getActionColor(MessageType messageType) {

    if (messageType == MessageType.WARNING)
      return R.color.light_text;
    else
      return R.color.light_text;
  }

  private static Drawable getMessageTypeIcon(MessageType messageType) {

    Drawable nIcon = BaseApplication.getResourceDrawable(R.drawable.ic_info_outline_w_24);
    if (messageType == MessageType.PRIMARY)
      nIcon = BaseApplication.getResourceDrawable(R.drawable.ic_info_outline_w_24);
    else if (messageType == MessageType.ACCENT)
      nIcon = BaseApplication.getResourceDrawable(R.drawable.ic_info_outline_w_24);
    else if (messageType == MessageType.DANGER)
      nIcon = BaseApplication.getResourceDrawable(R.drawable.ic_close_w_24);
    else if (messageType == MessageType.SUCCESS)
      nIcon = BaseApplication.getResourceDrawable(R.drawable.ic_done_w_24);
    else if (messageType == MessageType.WARNING)
      nIcon = BaseApplication.getResourceDrawable(R.drawable.ic_warning_w_24);
    return nIcon;
  }

  //region Getter Setter

  public MessageCode getMessageCode() {
    return mMessageCode;
  }

  private void setMessageCode(MessageCode mMessageCode) {
    this.mMessageCode = mMessageCode;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String mTitle) {
    this.mTitle = mTitle;
  }

  public String getContent() {
    return mContent;
  }

  public void setContent(String mContent) {
    this.mContent = mContent;
  }

  public String getAction() {
    return mAction;
  }

  private void setAction(String mAction) {
    this.mAction = mAction;
  }

  public MessageType getMessageType() {
    return mMessageType;
  }

  public void setMessageType(MessageType mMessageType) {
    this.mMessageType = mMessageType;
  }

  public Drawable getMessageIcon() {
    return mMessageIcon;
  }

  private void setMessageIcon(Drawable mMessageIcon) {
    this.mMessageIcon = mMessageIcon;
  }

  public int getBackgroundColor() {
    return mBackgroundColor;
  }

  private void setBackgroundColor(int mBackgroundColor) {
    this.mBackgroundColor = mBackgroundColor;
  }

  public int getContentTextColor() {
    return mContentTextColor;
  }

  private void setContentTextColor(int mContentTextColor) {
    this.mContentTextColor = mContentTextColor;
  }

  public int getActionTextColor() {
    return mActionTextColor;
  }

  private void setActionTextColor(int mActionTextColor) {
    this.mActionTextColor = mActionTextColor;
  }

  public int getDuration() {
    return mDuration;
  }

  private void setDuration(int mDuration) {
    this.mDuration = mDuration;
  }

  //endregion Getter Setter

}
