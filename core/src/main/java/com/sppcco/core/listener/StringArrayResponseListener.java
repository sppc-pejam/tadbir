package com.sppcco.core.listener;

/**
 * Created by m_pejam on 02/03/18.
 * StringArrayResponseListener
 */

public interface StringArrayResponseListener {

  void onSuccess(String... strings);
  void onFailure();

}
