package com.sppcco.customer.framework.widget;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.helperlibrary.dialog.material_dialog.DialogType;
import com.sppcco.helperlibrary.dialog.material_dialog.listener.YNResponseListener;
import com.sppcco.helperlibrary.dialog.material_dialog.manager.MDialogManager;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseVerticalStepper extends BaseFragment  {

  private final String TAG = BaseVerticalStepper.class.getSimpleName();

  private View mParentView;

  @BindView(R2.id.tv_title_no_1)
  AppCompatTextView tvTitleNo1;
  @BindView(R2.id.exp_no_1)
  ExpandableLayout expNo1;
  @BindView(R2.id.tv_title_no_2)
  AppCompatTextView tvTitleNo2;
  @BindView(R2.id.exp_no_2)
  ExpandableLayout expNo2;
  @BindView(R2.id.tv_title_no_3)
  AppCompatTextView tvTitleNo3;
  @BindView(R2.id.exp_no_3)
  ExpandableLayout expNo3;
  @BindView(R2.id.tv_title_no_4)
  AppCompatTextView tvTitleNo4;
  @BindView(R2.id.exp_no_4)
  ExpandableLayout expNo4;
  @BindView(R2.id.tv_title_no_5)
  AppCompatTextView tvTitleNo5;
  @BindView(R2.id.exp_no_5)
  ExpandableLayout expNo5;
  @BindView(R2.id.step_title_1)
  RelativeLayout stepTitle1;
  @BindView(R2.id.step_title_2)
  RelativeLayout stepTitle2;
  @BindView(R2.id.step_title_3)
  RelativeLayout stepTitle3;
  @BindView(R2.id.step_title_4)
  RelativeLayout stepTitle4;
  @BindView(R2.id.step_title_5)
  RelativeLayout stepTitle5;

  //private int resource;
  private int stepsCount;
  private boolean forcedPassageSteps;
  //----------------------------------
  private boolean mModified = false;
  //----------------------------------
  private List<String> lstTitles;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected View onParentCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState,
                                    int resource, List<String> titles, int stepsCount, boolean forcedPassageSteps
  ) {
    Log.i(TAG, "onParentCreateView");
    mParentView = inflater.inflate(resource, container, false);
    ButterKnife.bind(this, mParentView);

    //setResource(resource);
    lstTitles = titles;
    setStepsCount(stepsCount);
    setForcedPassageSteps(forcedPassageSteps);

    initToolbar((AppCompatActivity) getActivity(), mParentView,R.id.toolbar ,true);
    initTitles();

    return mParentView;
  }

  private void initTitles() {

    tvTitleNo1.setText(lstTitles.get(0));
    tvTitleNo2.setText(lstTitles.get(1));
    tvTitleNo3.setText(lstTitles.get(2));
    tvTitleNo4.setText(lstTitles.get(3));
    tvTitleNo5.setText(lstTitles.get(4));
  }


  //region OptionsMenu

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finishView();
    }
    return false;
  }

  //endregion OptionsMenu



  protected void finishView() {

    if (isModified()) {
      MDialogManager.basicDialog(BaseApplication.getContext(), getActivity(), DialogType.WARNING_YES_NO,
        BaseApplication.getResourceString(R.string.msg_save_changes), new YNResponseListener() {
          @Override
          public void onAgree() {

            create();

          }

          @Override
          public void onDisAgree() {

            Objects.requireNonNull(getActivity()).finish();
          }
        });
    } else {
      Objects.requireNonNull(getActivity()).finish();
    }

  }


  @OnClick({
    R2.id.tv_title_no_1, R2.id.tv_title_no_2, R2.id.tv_title_no_3, R2.id.tv_title_no_4, R2.id.tv_title_no_5})
  public void onViewClicked(View view) {
    int i = view.getId();

    if (i == R.id.tv_title_no_1) {
      if ((isForcedPassageSteps() && (getSuccessStep() >= 0 && getCurrentStep() != 1)) ||
        (!isForcedPassageSteps() && getCurrentStep() != 1)) {
        setCurrentStep(1);
        collapseAll();
        expNo1.expand();
      }
    } else if (i == R.id.tv_title_no_2) {
      if ((isForcedPassageSteps() && (getSuccessStep() >= 1 && getCurrentStep() != 2)) ||
        (!isForcedPassageSteps() && getCurrentStep() != 2)) {
        setCurrentStep(2);
        collapseAll();
        expNo2.expand();
      }
    } else if (i == R.id.tv_title_no_3) {
      if ((isForcedPassageSteps() && (getSuccessStep() >= 2 && getCurrentStep() != 3)) ||
        (!isForcedPassageSteps() && getCurrentStep() != 3)) {
        setCurrentStep(3);
        collapseAll();
        expNo3.expand();
      }
    } else if (i == R.id.tv_title_no_4) {
      if ((isForcedPassageSteps() && (getSuccessStep() >= 3 && getCurrentStep() != 4)) ||
        (!isForcedPassageSteps() && getCurrentStep() != 4)) {
        setCurrentStep(4);
        collapseAll();
        expNo4.expand();
      }
    } else if (i == R.id.tv_title_no_5) {
      if ((isForcedPassageSteps() && (getSuccessStep() >= 4 && getCurrentStep() != 5)) ||
        (!isForcedPassageSteps() && getCurrentStep() != 5)) {
        setCurrentStep(5);
        collapseAll();
        expNo5.expand();
      }
    }
  }

  protected void collapseAndContinue(int index) {

    collapseByIndex(index);
    setCheckedStep(index);
    index++;
    setCurrentStep(index);
    setSuccessStep(index > getSuccessStep() ? index : getSuccessStep());
    expandByIndex(index);

  }

  private void setCheckedStep(int index) {

    RelativeLayout relative = stepTitle1;
    if (index == 2) relative = stepTitle2;
    if (index == 3) relative = stepTitle3;
    if (index == 4) relative = stepTitle4;
    if (index == 5) relative = stepTitle5;
    relative.removeAllViews();
    ImageButton img = new ImageButton(getActivity());
    img.setImageResource(R.drawable.ic_done_w_24);
    img.setBackgroundColor(Color.TRANSPARENT);
    img.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    relative.addView(img);

  }

  private void collapseAll() {

    if (getStepsCount() >= 1)
      expNo1.collapse();
    if (getStepsCount() >= 2)
      expNo2.collapse();
    if (getStepsCount() >= 3)
      expNo3.collapse();
    if (getStepsCount() >= 4)
      expNo4.collapse();
    if (getStepsCount() == 5)
      expNo5.collapse();

  }

  private void collapseByIndex(int index) {

    if (index == 1) expNo1.collapse();
    else if (index == 2) expNo2.collapse();
    else if (index == 3) expNo3.collapse();
    else if (index == 4) expNo4.collapse();
    else if (index == 5) expNo5.collapse();

  }

  private void expandByIndex(int index) {

    if (index == 1) expNo1.expand();
    else if (index == 2) expNo2.expand();
    else if (index == 3) expNo3.expand();
    else if (index == 4) expNo4.expand();
    else if (index == 5) expNo5.expand();

  }


  //region Abstract

  public abstract int getCurrentStep();

  public abstract int getSuccessStep();

  public abstract void setCurrentStep(int currentStep);

  public abstract void setSuccessStep(int successStep);

  public abstract boolean create();

  //endregion Abstract


  //region Getter Setter

  /*public int getResource() {
    return resource;
  }

  private void setResource(int resource) {
    this.resource = resource;
  }*/

  private boolean isModified() {
    return mModified;
  }

  protected void setModified(boolean modified) {
    mModified = modified;
  }

  private int getStepsCount() {
    return stepsCount;
  }

  private void setStepsCount(int stepsCount) {
    this.stepsCount = stepsCount;
  }

  private boolean isForcedPassageSteps() {
    return forcedPassageSteps;
  }

  private void setForcedPassageSteps(boolean forcedPassageSteps) {
    this.forcedPassageSteps = forcedPassageSteps;
  }

  //endregion Getter Setter

}
