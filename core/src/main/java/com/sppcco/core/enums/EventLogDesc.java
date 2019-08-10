package com.sppcco.core.enums;


import com.sppcco.core.R;
import com.sppcco.core.framework.application.BaseApplication;

public enum EventLogDesc {

  INSERT_SP_PURCHASE(BaseApplication.getResourceString(R.string.insert_sp_purchase_desc)),
  DELETE_SP_PURCHASE(BaseApplication.getResourceString(R.string.delete_sp_purchase_desc)),

  INSERT_SP_SALES(BaseApplication.getResourceString(R.string.insert_sp_sales_desc)),
  DELETE_SP_SALES(BaseApplication.getResourceString(R.string.delete_sp_sales_desc)),

  INSERT_SP_P_RETURN(BaseApplication.getResourceString(R.string.insert_sp_p_return_desc)),
  DELETE_SP_P_RETURN(BaseApplication.getResourceString(R.string.delete_sp_p_return_desc)),

  INSERT_SP_S_RETURN(BaseApplication.getResourceString(R.string.insert_sp_s_return_desc)),
  DELETE_SP_S_RETURN(BaseApplication.getResourceString(R.string.delete_sp_s_return_desc)),

  INSERT_SP_PRESALES(BaseApplication.getResourceString(R.string.insert_sp_presales_desc)),
  DELETE_SP_PRESALES(BaseApplication.getResourceString(R.string.delete_sp_presales_desc)),

  INSERT_SPF_SALESORDER(BaseApplication.getResourceString(R.string.insert_spf_sales_order_desc)),
  DELETE_SPF_SALESORDER(BaseApplication.getResourceString(R.string.delete_spf_sales_order_desc)),

  INSERT_SP_CUSTOMER(BaseApplication.getResourceString(R.string.insert_sp_customer_desc));

  private String Value;

  EventLogDesc(String nVal) {
    Value = nVal;
  }

  public String getValue() {
    return Value;
  }
}
