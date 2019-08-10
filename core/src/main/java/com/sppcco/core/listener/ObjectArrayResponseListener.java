package com.sppcco.core.listener;

/**
 * Created by m_pejam on 02/03/18.
 * ObjectResponseListener
 */

public interface ObjectArrayResponseListener {

  void onSuccess(Object... object);
  void onFailure();

}
