package com.sppcco.helperlibrary.dialog.material_dialog.manager;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sppcco.helperlibrary.R;
import com.sppcco.helperlibrary.dialog.material_dialog.DialogType;
import com.sppcco.helperlibrary.dialog.material_dialog.listener.SelectItemResponseListener;
import com.sppcco.helperlibrary.dialog.material_dialog.listener.YNResponseListener;
import com.sppcco.helperlibrary.dialog.material_dialog.listener.WarningResponseListener;

import java.util.List;

/**
 * Created by m_pejam on 02/03/18.
 *
 */

public class MDialogManager {

  public static void basicDialog(Context context, Activity activity, DialogType dialogType,
                                 String content, YNResponseListener ynResponseListener){

    String strTitle = null;
    String strPositiveText = null;
    String strNegativeText = null;

    Resources resources = context.getResources();
    if(dialogType == DialogType.ERROR_YES_NO){
       strTitle = resources.getString(R.string.error);
       strPositiveText = resources.getString(R.string.yes);
       strNegativeText = resources.getString(R.string.no);
    }else if(dialogType == DialogType.ERROR_AGREE_DISAGREE){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.agree);
      strNegativeText = resources.getString(R.string.disagree);
    }else if(dialogType == DialogType.ERROR_AGREE_DISMISS){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.agree);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.ERROR_APPROVE_DISAGREE){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.approve);
      strNegativeText = resources.getString(R.string.disagree);
    }else if(dialogType == DialogType.ERROR_APPROVE_DISMISS){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.approve);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.WARNING_YES_NO){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.yes);
      strNegativeText = resources.getString(R.string.no);
    }else if(dialogType == DialogType.WARNING_AGREE_DISAGREE) {
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.agree);
      strNegativeText = resources.getString(R.string.disagree);
    }else if(dialogType == DialogType.WARNING_AGREE_DISMISS){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.agree);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.WARNING_APPROVE_DISAGREE) {
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.approve);
      strNegativeText = resources.getString(R.string.disagree);
    }else if(dialogType == DialogType.WARNING_APPROVE_DISMISS){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.approve);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.WARNING_APPROVE){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.approve);
      strNegativeText = null;
    }else if(dialogType == DialogType.ERROR_SAVE_DELETE){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.save);
      strNegativeText = resources.getString(R.string.delete);
    }else if(dialogType == DialogType.ERROR_SAVE_DISMISS){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.save);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.ERROR_DELETE_DISMISS){
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.delete);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.WARNING_SAVE_DELETE){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.save);
      strNegativeText = resources.getString(R.string.delete);
    }else if(dialogType == DialogType.WARNING_SAVE_DISMISS){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.save);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.WARNING_DELETE_DISMISS){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.delete);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.WARNING_EDIT_DISMISS){
      strTitle = resources.getString(R.string.warning);
      strPositiveText = resources.getString(R.string.edit);
      strNegativeText = resources.getString(R.string.dismiss);
    }else if(dialogType == DialogType.ERROR_EDIT_DISMISS) {
      strTitle = resources.getString(R.string.error);
      strPositiveText = resources.getString(R.string.edit);
      strNegativeText = resources.getString(R.string.dismiss);
    }


    new MaterialDialog.Builder(activity)
      .title(strTitle)
      .content(content)
      .positiveText(strPositiveText)
      .negativeText(strNegativeText)
      .titleGravity(GravityEnum.END)
      .itemsGravity(GravityEnum.END)
      .btnStackedGravity(GravityEnum.END)
      .buttonsGravity(GravityEnum.END)
      .contentGravity(GravityEnum.END)
      .backgroundColor(resources.getColor(R.color.backgroundColor))
      .titleColor(resources.getColor(R.color.surfaceDarkerTextColor))
      .contentColor(resources.getColor(R.color.surfaceDarkTextColor))
      .negativeColor(resources.getColor(R.color.bts_danger_color))
      .positiveColor(resources.getColor(R.color.bts_info_color_dark))
      .typeface("iranian-sans-mobile-fa-num.ttf","iranian-sans-mobile-fa-num.ttf")
      .canceledOnTouchOutside(false)
      .onAny((dialog, which) -> {
        if (which.name().equals("POSITIVE")) {
          ynResponseListener.onAgree();
        }
        else {
          ynResponseListener.onDisAgree();
        }
      })
      .show();
  }

  public static void warningDialog(Context context, Activity activity, DialogType dialogType,
                                 String content, WarningResponseListener listener){

    String strTitle = null;
    String strPositiveText = null;
    String strNegativeText = null;

    Resources resources = context.getResources();
    if(dialogType == DialogType.WARNING){
       strTitle = resources.getString(R.string.warning);
       strPositiveText = resources.getString(R.string.exit);
    }


    new MaterialDialog.Builder(activity)
      .title(strTitle)
      .content(content)
      .positiveText(strPositiveText)
      .titleGravity(GravityEnum.END)
      .itemsGravity(GravityEnum.END)
      .btnStackedGravity(GravityEnum.END)
      .buttonsGravity(GravityEnum.END)
      .contentGravity(GravityEnum.END)
      .typeface("iranian-sans-mobile-fa-num.ttf","iranian-sans-mobile-fa-num.ttf")
      .canceledOnTouchOutside(true)
      .backgroundColor(resources.getColor(R.color.backgroundColor))
      .titleColor(resources.getColor(R.color.surfaceDarkerTextColor))
      .contentColor(resources.getColor(R.color.surfaceDarkTextColor))
      .negativeColor(resources.getColor(R.color.bts_danger_color))
      .positiveColor(resources.getColor(R.color.bts_info_color_dark))
      .onAny((dialog, which) -> {
        if (which.name().equals("POSITIVE")) {
          listener.onAgree();
        }
      })
      .show();
  }

  public static void basicListDialog(Context context, Activity activity, List<String> listContent,
                                     String content, int selectedIndex, SelectItemResponseListener selectItemResponseListener,
                                     Integer... disabledIndices) {

    Resources resources = context.getResources();
    String strPositiveText = resources.getString(R.string.choose);

    new MaterialDialog.Builder(activity)
      .title(content)
      .items(listContent)
      .itemsDisabledIndices(disabledIndices)
      .positiveText(strPositiveText)
      .titleGravity(GravityEnum.END)
      .itemsGravity(GravityEnum.END)
      .btnStackedGravity(GravityEnum.END)
      .buttonsGravity(GravityEnum.END)
      .contentGravity(GravityEnum.END)
      .backgroundColor(Color.WHITE)
      .backgroundColor(resources.getColor(R.color.backgroundColor))
      .titleColor(resources.getColor(R.color.surfaceDarkerTextColor))
      .contentColor(resources.getColor(R.color.surfaceDarkTextColor))
      .negativeColor(resources.getColor(R.color.bts_danger_color))
      .positiveColor(resources.getColor(R.color.bts_info_color_dark))
      .typeface("iranian-sans-mobile-fa-num.ttf","iranian-sans-mobile-fa-num.ttf")
      .itemsCallbackSingleChoice(
        selectedIndex,
        (dialog, view, position, text) -> {
          selectItemResponseListener.onItemSelected(text.toString(), position);
          return true; // allow selection
        })
      .cancelListener(dialogInterface -> {
        selectItemResponseListener.onDisAgree();
      })
      .positiveText(strPositiveText)
      .show();
  }
}
