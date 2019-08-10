package com.sppcco.helperlibrary.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by m_pejam on 02/01/18.
 *
 */

public class EncryptionManager {

  public static String MD5Encrypt(String strPassword) {
    String strGeneratedPassword = "";
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(strPassword.getBytes());
      byte[] bytes = md.digest();
      StringBuilder sb = new StringBuilder();
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xFF) + 0x100, 16).substring(1));
      }
      strGeneratedPassword = sb.toString();
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return strGeneratedPassword.toUpperCase();
  }

}
