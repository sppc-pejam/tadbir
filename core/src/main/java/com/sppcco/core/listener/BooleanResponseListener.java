package com.sppcco.core.listener;

/**
 * Created by m_pejam on 02/03/18.
 * BooleanResponseListener
 */

public interface BooleanResponseListener {

  boolean onSuccess(boolean b);
  boolean onFailure();
}
