package com.sppcco.helperlibrary.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ImageConverter extends AsyncTask<Integer, Void, String> {

  private final WeakReference<ImageView> imageView;
  private final String strImage;
  private final int resImageDefault;

  public ImageConverter(ImageView imageView, String strImage, int resImageDefault) {
    this.imageView = new WeakReference<>(imageView);
    this.strImage = strImage;
    this.resImageDefault = resImageDefault;
  }

  @Override
  protected String doInBackground(Integer... integers) {
    if (strImage == null)
      return null;
    return strImage.split(",")[0];
  }

  @Override
  protected void onPostExecute(String base64Image) {
    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    if (base64Image == null)
      bitmap = null;

    if (imageView != null && bitmap != null) {
      imageView.get().setImageBitmap(bitmap);
    } else {
      imageView.get().setImageResource(resImageDefault);
    }
  }
}
