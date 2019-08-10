package com.sppcco.helperlibrary.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterateSerializeObject {

  @SuppressWarnings("unchecked")
  public static <T> List<T> iterateReponse(Iterator iterator){
    List<T> list = new ArrayList<>();
    while(iterator.hasNext()) {
      Object object = iterator.next();
      T t = ((T) object);
      list.add(t);
    }
    return list;
  }
}
