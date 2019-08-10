package com.sppcco.core.listener;

import com.sppcco.core.enums.ResponseType;

public interface SyncResponseListener  {
  void onSuccess();
  void onProgress();
  void onFailure(ResponseType responseType);
}
