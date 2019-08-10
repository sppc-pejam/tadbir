package com.sppcco.customer.ui.select;

import android.os.Bundle;

import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.util.app.ActivityUtils;
import com.sppcco.customer.R;


/**
 * Created by m_pejam on 01/21/18.
 *
 */

public class SelectCustomerActivity extends BaseAppCompatActivity {

  private SelectCustomerContract.Presenter mPresenter;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new ActivityViewBuilder(this)
      .requestFeatures()
      .setToolbar(R.id.toolbar)
      .contentView(R.layout.activity_select_customer)
      .setActivityTitle(R.string.cpt_search_by_customer)
      .build();


    SelectCustomerFragment selectCustomerFragment =
      (SelectCustomerFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (selectCustomerFragment == null) {
      // Create the fragment
      selectCustomerFragment = SelectCustomerFragment.newInstance();
      ActivityUtils.addFragmentToActivity(
        getSupportFragmentManager(), selectCustomerFragment, R.id.contentFrame);
    }

    // Create the presenter
    mPresenter = SelectCustomerPresenter.getSearchDialogPresenterInstance(selectCustomerFragment);
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.destroy();
  }

}
