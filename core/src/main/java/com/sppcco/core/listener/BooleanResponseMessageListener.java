package com.sppcco.core.listener;

import java.util.List;

/**
 * Created by b_nematzadeh on 03/26/19.
 * BooleanResponseListener
 */

public interface BooleanResponseMessageListener {
  void onResponse(boolean b, List<String> message);
}
