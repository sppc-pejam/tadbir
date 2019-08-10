package com.sppcco.merchandise_app;

import android.os.Bundle;
import android.util.Log;

import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.util.app.ActivityUtils;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.merchandise.ui.main.MainFragment;
import com.sppcco.merchandise.ui.main.MainPresenter;

public class MainActivity extends BaseAppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    new BaseAppCompatActivity.ActivityViewBuilder(this)
      .requestFeatures()
      .contentView(R.layout.activity_main)
      .build();


    MainFragment mainFragment =
      (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    if (mainFragment == null) {
      // Create the fragment
      mainFragment = MainFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
        getSupportFragmentManager(), mainFragment, R.id.content_frame);
    }

    // Create the presenter
    MainPresenter.getPresenterInstance(mainFragment);

    Log.i(CoreConstants.APP_TAG, " CUSTOMER_App " + CoreConstants.getAppVersion());


  }
}
