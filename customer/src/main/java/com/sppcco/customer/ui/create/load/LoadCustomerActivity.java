package com.sppcco.customer.ui.create.load;

import android.os.Bundle;

import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.util.app.ActivityUtils;
import com.sppcco.customer.R;


/**
 * Created by m_pejam on 01/21/18.
 * AddCustomerActivity
 */

public class LoadCustomerActivity extends BaseAppCompatActivity {

  private LoadCustomerContract.Presenter mPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new ActivityViewBuilder(this)
      .requestFeatures()
      .contentView(R.layout.activity_load_customer)
      .build();


    LoadCustomerFragment loadCustomerFragment =
      (LoadCustomerFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (loadCustomerFragment == null) {
      // Create the fragment
      loadCustomerFragment = LoadCustomerFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
        getSupportFragmentManager(), loadCustomerFragment, R.id.contentFrame);
    }

    // Create the presenter
    mPresenter = LoadCustomerPresenter.getPresenterInstance(loadCustomerFragment);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.destroy();
  }

}
