package com.sppcco.core.listener;

import java.util.List;

/**
 * Created by m_pejam on 02/03/18.
 * StringResponseListener
 */

public interface StringListResponseListener {

  void onSuccess(List<String> s);
  void onFailure();

}
