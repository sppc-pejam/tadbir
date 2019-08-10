package com.sppcco.core.framework.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.sppcco.core.R;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.util.message.Message;

import java.util.Objects;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

public class BaseFragment extends CoreFragment {

  @Override
  public void onResume() {
    super.onResume();

    BaseApplication.setCurrentFragment(this);
  }

  protected void initToolbar(AppCompatActivity appCompatActivity, View view, @IdRes int toolbarId , boolean isHomeEnabled) {
    Toolbar toolbar = view.findViewById(toolbarId);
    appCompatActivity.setSupportActionBar(toolbar);
    Objects.requireNonNull(appCompatActivity.getSupportActionBar()).setDisplayHomeAsUpEnabled(isHomeEnabled);
  }

  // region SnackBar

  @SuppressLint("InflateParams")
  protected void snackBarActionCard(View parentView, Message message, DoneResponseListener doneResponseListener) {

    final Snackbar snackbar = Snackbar.make(parentView, "", message.getDuration());
    //inflate view
    View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_card_image, null);
    CardView crdMessage = custom_view.findViewById(R.id.crd_message);
    AppCompatTextView tvMessage = custom_view.findViewById(R.id.tv_message);
    AppCompatTextView tvAction = custom_view.findViewById(R.id.tv_action);
    AppCompatImageView imgMessage = custom_view.findViewById(R.id.img_message);
    View separator = custom_view.findViewById(R.id.separator);


    snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
    Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
    snackBarView.setPadding(0, 0, 0, 0);


    // Position snackbar at top
    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBarView.getLayoutParams();
    params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
    params.width = CoordinatorLayout.LayoutParams.MATCH_PARENT;
    snackBarView.setLayoutParams(params);


    tvMessage.setText(message.getContent());
    if (message.getAction() != null) {
      tvAction.setText(message.getAction());
    } else {
      tvAction.setVisibility(View.GONE);
      separator.setVisibility(View.GONE);
    }

    crdMessage.setCardBackgroundColor(ContextCompat.getColor(BaseApplication.getContext(), message.getBackgroundColor()));
    imgMessage.setImageDrawable(message.getMessageIcon());
    tvAction.setTextColor(ContextCompat.getColor(BaseApplication.getContext(), message.getActionTextColor()));
    tvMessage.setTextColor(ContextCompat.getColor(BaseApplication.getContext(), message.getContentTextColor()));

    /*imgMessage.setColorFilter(ContextCompat.getColor(UApp.getAppContext(), getMessageTypeColor(messageType)));*/
    //tvAction.setBackgroundDrawable(UApp.getResourceDrawable(R.drawable.border_button_unselect_error));

    if (doneResponseListener != null)
      (custom_view.findViewById(R.id.tv_action)).setOnClickListener(v -> {
        snackbar.dismiss();
        doneResponseListener.onDone();
      });


    snackBarView.addView(custom_view, 0);
    snackbar.show();
  }

  // endregion SnackBar


}
