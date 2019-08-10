package com.sppcco.customer.ui.create.add;

import android.os.Bundle;

import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.util.app.ActivityUtils;
import com.sppcco.customer.R;


/**
 * Created by m_pejam on 01/21/18.
 * AddCustomerActivity
 */

public class AddCustomerActivity extends BaseAppCompatActivity {

  private AddCustomerContract.Presenter mPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new ActivityViewBuilder(this)
      .requestFeatures()
      .contentView(R.layout.activity_add_customer)
      .build();


    AddCustomerFragment addCustomerFragment =
      (AddCustomerFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (addCustomerFragment == null) {
      // Create the fragment
      addCustomerFragment = AddCustomerFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
        getSupportFragmentManager(), addCustomerFragment, R.id.contentFrame);
    }

    // Create the presenter
    mPresenter = AddCustomerPresenter.getPresenterInstance(addCustomerFragment);
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.destroy();
  }

}
