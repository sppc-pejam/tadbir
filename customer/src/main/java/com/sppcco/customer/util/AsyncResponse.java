package com.sppcco.customer.util;

import java.util.List;

public interface AsyncResponse<T> {
  void processFinish(List<T> response);
}
