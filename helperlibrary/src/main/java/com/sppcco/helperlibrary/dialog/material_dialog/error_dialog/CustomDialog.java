package com.sppcco.helperlibrary.dialog.material_dialog.error_dialog;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.sppcco.helperlibrary.R;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AlertDialog;

public class CustomDialog {

  public static void showErrorAlertDialog(Context context, String title, List<String> errorMessageList){

    StringBuilder stringBuilder = new StringBuilder();
    for (String s : errorMessageList) {
      stringBuilder.append("* ").append(s);
      stringBuilder.append("\n");
    }
    String message = stringBuilder.toString().trim();

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setTitle(title);
    builder.setIcon(R.drawable.vector_drawable_warning);

    AlertDialog dialog = builder.create();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      Objects.requireNonNull(dialog.getWindow()).getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
    dialog.show();
  }
}
