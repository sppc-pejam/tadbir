package com.sppcco.merchandise.ui.select;


import android.os.Bundle;


import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.merchandise.R;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;


public class SelectMerchandiseActivity extends BaseAppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    @IdRes
    int startDestId = R.id.merchandiseFragment;

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      Object object = extras.getSerializable(IntentKey.KEY_OBJECT.getKey());
      if (object instanceof SPFactor) {

        if (extras.getSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey()) != null)
          startDestId = R.id.merchandiseSPArticleEditFragment;
      } else if (object instanceof SalesOrder) {

        if (extras.getSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey()) != null)
          startDestId = R.id.merchandiseSOArticleEditFragment;
      }
    }


    new ActivityViewBuilder(this)
      .requestFeatures()
      .setToolbar(R.id.toolbar)
      .contentView(R.layout.activity_select_merchandise)
      .setActivityTitle(R.string.cpt_search_merchandise)
      .setNav(R.id.nav_host, R.navigation.nav_merchandise, startDestId, null)
      .build();

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public FragmentManager getSupportFragmentManager() {
    return super.getSupportFragmentManager();
  }
}
