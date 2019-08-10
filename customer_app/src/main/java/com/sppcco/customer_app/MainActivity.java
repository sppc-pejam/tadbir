package com.sppcco.customer_app;


import android.os.Bundle;
import android.util.Log;

import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.util.app.ActivityUtils;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.customer.ui.main.MainFragment;
import com.sppcco.customer.ui.main.MainPresenter;

public class MainActivity extends BaseAppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    new ActivityViewBuilder(this)
      .requestFeatures()
      .contentView(R.layout.activity_main)
      .build();


    MainFragment mainFragment =
      (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (mainFragment == null) {
      // Create the fragment
      mainFragment = MainFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
        getSupportFragmentManager(), mainFragment, R.id.contentFrame);
    }

    // Create the presenter
    MainPresenter.getPresenterInstance(mainFragment);

    Log.i(CoreConstants.APP_TAG, " CUSTOMER_App " + CoreConstants.getAppVersion());


  }
}
