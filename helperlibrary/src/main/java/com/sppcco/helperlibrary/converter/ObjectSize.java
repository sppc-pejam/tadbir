package com.sppcco.helperlibrary.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Behzad on 6/8/2018.
 * 
 */

public class ObjectSize {

  public static long getObjectSize(List list, VolumeType volumeType){
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(baos);
      out.writeObject(list);
      out.close();
      if(volumeType == VolumeType.BYTE)
        return baos.toByteArray().length;
      else if(volumeType == VolumeType.KB)
        return baos.toByteArray().length / 1000;
      else if(volumeType == VolumeType.MB)
        return baos.toByteArray().length / 1000000;
      else if(volumeType == VolumeType.GB)
        return baos.toByteArray().length / 1000000000 ;
      else
        return baos.toByteArray().length;
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }
  
  public enum VolumeType{
    BYTE,
    KB,
    MB,
    GB
  }
  
}
