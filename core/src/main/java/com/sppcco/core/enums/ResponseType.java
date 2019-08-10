package com.sppcco.core.enums;

import com.sppcco.core.R;
import com.sppcco.core.framework.application.CoreApplication;

import androidx.annotation.DrawableRes;

public enum ResponseType {


  // 1xx common message
  ERR_MISMATCH_DATA(0, CoreApplication.getResourceString(R.string.e_fa_mismatch_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_DATE_OUT_OF_FPID(1, CoreApplication.getResourceString(R.string.err_msg_invalid_current_date_on_fp), MessageType.DANGER, R.drawable.ic_circle_error_w_24),

  ERR_SYNC_TABLE(2, CoreApplication.getResourceString(R.string.err_msg_sync_table_operation), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  SUCCESS_SYNC_TABLE(3, CoreApplication.getResourceString(R.string.succ_msg_sync_table_operation), MessageType.SUCCESS, R.drawable.ic_circle_check_w_24),
  ERR_INVALID_CONFIG(4, CoreApplication.getResourceString(R.string.e_fa_invalid_config), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_NO_IMAGE(5, CoreApplication.getResourceString(R.string.e_fa_no_image), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_INVALID_KEY(6, CoreApplication.getResourceString(R.string.err_invalid_key), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_INVALID_USER_NAME(7, CoreApplication.getResourceString(R.string.err_invalid_user_name), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_INVALID_PASSWORD(8, CoreApplication.getResourceString(R.string.err_invalid_password), MessageType.DANGER, R.drawable.ic_circle_error_w_24),

  // 2xx local message
  ERR_VERSION_ACCESS(200, CoreApplication.getResourceString(R.string.err_msg_version_access), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SUBSYSTEM_ACCESS(201, CoreApplication.getResourceString(R.string.err_msg_subsystem_access), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_USER_ACCESS(202, CoreApplication.getResourceString(R.string.err_msg_no_access_user), MessageType.DANGER, R.drawable.ic_circle_error_w_24),


  ERR_PRINT_DIRECTORY(203, CoreApplication.getResourceString(R.string.err_print_directory), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_PRINT_EXECUTE(204, CoreApplication.getResourceString(R.string.err_print_execute), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_PRINT_INVALID_DATA(205, CoreApplication.getResourceString(R.string.err_print_invalid_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_PRINT(206, CoreApplication.getResourceString(R.string.err_print), MessageType.DANGER, R.drawable.ic_circle_error_w_24),


  // 3xx client message
  ERR_CLIENT_NO_CONNECTION(300, CoreApplication.getResourceString(R.string.e_fa_no_connection), MessageType.DANGER, R.drawable.ic_cloud_off_w_24),
  ERR_CLIENT_NO_NETWORK_ACCESS(301, CoreApplication.getResourceString(R.string.e_fa_no_network_access), MessageType.DANGER, R.drawable.ic_cloud_off_w_24),
  ERR_CLIENT_INVALID_DATA(302, CoreApplication.getResourceString(R.string.e_fa_client_invalid_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_CLIENT_DATE_FPID(303, CoreApplication.getResourceString(R.string.e_fa_mismatch_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SERVICE_ACCESS(304, CoreApplication.getResourceString(R.string.err_msg_subsystem_access), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_CHECK_SUM(305, CoreApplication.getResourceString(R.string.e_fa_mismatch_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_INCOMPATIBLE_VERSION(306, CoreApplication.getResourceString(R.string.e_fa_incompatible_version), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  WARN_COMPATIBLE_VERSION(307, CoreApplication.getResourceString(R.string.e_fa_compatible_version), MessageType.WARNING, R.drawable.ic_circle_error_w_24),
  ERR_TIME_OUT(307, CoreApplication.getResourceString(R.string.e_fa_time_out), MessageType.DANGER, R.drawable.ic_circle_error_w_24),


  // 4xx server message
  ERR_SERVER_BAD_REQUEST(400, CoreApplication.getResourceString(R.string.e_fa_mismatch_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SERVER_NOT_AVAILABLE_DATA(401, CoreApplication.getResourceString(R.string.e_fa_mismatch_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SERVER_INVALID_DATA(402, CoreApplication.getResourceString(R.string.e_fa_mismatch_data), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SERVER_FORBIDDEN(403, CoreApplication.getResourceString(R.string.e_fa_forbidden), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SERVER_NOT_FOUND(404, CoreApplication.getResourceString(R.string.e_fa_server_not_found), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_REQUEST_NOT_FOUND(500, CoreApplication.getResourceString(R.string.e_fa_request_not_found), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_FILE_NOT_FOUND(501, CoreApplication.getResourceString(R.string.e_fa_file_not_found), MessageType.DANGER, R.drawable.ic_circle_error_w_24),
  ERR_SERVER_ERROR(502, CoreApplication.getResourceString(R.string.err_msg_server_error), MessageType.DANGER, R.drawable.ic_circle_error_w_24);

  private final int nResponseType;
  private final String strContent;
  private final MessageType snackbarType;
  private final @DrawableRes
  int drawable;


  ResponseType(int responseType, String content, MessageType type, @DrawableRes int icon) {
    nResponseType = responseType;
    strContent = content;
    snackbarType = type;
    drawable = icon;
  }

  public int getValue() {
    return nResponseType;
  }
  public String getContent() { return strContent; }
  public MessageType getMessageType() { return snackbarType; }
  public @DrawableRes int getIcon() { return drawable; }

}
