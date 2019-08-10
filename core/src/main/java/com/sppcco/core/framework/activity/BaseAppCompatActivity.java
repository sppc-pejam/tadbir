package com.sppcco.core.framework.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.view.FullDrawerLayout;

import java.util.Objects;

import androidx.annotation.AnimRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NavigationRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.fragment.NavHostFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseAppCompatActivity extends CoreAppCompatActivity {

  public FullDrawerLayout mDrawerLayout;
  public NavigationView mNavigationView;

  public View mNavigationDrawer;
  public ConstraintLayout mHeaderNavigationDrawer;
  public ConstraintLayout mItemsNavigationDrawer;
  public ConstraintLayout mFooterNavigationDrawer;




  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }


  @Override
  protected void onResume() {
    super.onResume();

    BaseApplication.setCurrentActivity(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  public class ActivityViewBuilder {
    private final Activity activity;
    private int[] features = {0};
    private boolean noTitlebar;
    private boolean noActionbar;
    private boolean fullscreen;
    private int layoutId;
    private Object ui;
    private boolean bSetToolbar;
    private int layoutToolbarId;
    private boolean bSetActionbar;
    private int layoutActionbarId;
    private int titleID;
    private int gravity;
    private boolean bSetTitle;
    private int layoutActionbarLogoId;
    private boolean bSetActionbarLogo;
    private boolean bSetNavigationDrawer;
    private int layoutDrawerId;
    private int drawerColorId;
    private int navigationResId;
    private boolean bSetNav;
    private int layoutNavHostFragmentId;
    private int layoutNavGraphId;
    private int layoutNavDestinationId;
    private boolean bSetBundelNav;
    private Bundle bundle;
    private boolean bSetPendingTransition;
    private int animEnterId;
    private int animExitId;


    public ActivityViewBuilder(Activity activity) {
      this.activity = activity;
    }

    public ActivityViewBuilder requestFeatures(int... features) {
      this.features = features;
      return this;
    }

    public ActivityViewBuilder setToolbar(@IdRes int layoutResID) {
      this.bSetToolbar = true;
      this.layoutToolbarId = layoutResID;
      return this;
    }

    public ActivityViewBuilder setActionbar(@DrawableRes int layoutResID) {
      this.bSetActionbar = true;
      this.layoutActionbarId = layoutResID;
      return this;
    }

    public ActivityViewBuilder setActionbarLogo(@DrawableRes int layoutResID) {
      this.bSetActionbarLogo = true;
      this.layoutActionbarLogoId = layoutResID;
      return this;
    }

    public ActivityViewBuilder noTitlebar() {
      this.noTitlebar = true;
      return this;
    }

    public ActivityViewBuilder noActionbar() {
      this.noActionbar = true;
      return this;
    }

    public ActivityViewBuilder fullscreen() {
      this.fullscreen = true;
      return this;
    }

    public ActivityViewBuilder contentView(@LayoutRes int layoutResID) {
      this.layoutId = layoutResID;
      return this;
    }

    public ActivityViewBuilder setNavigationDrawer(@IdRes int layoutResID, int nColor, @IdRes int navigationResID) {
      this.bSetNavigationDrawer = true;
      this.layoutDrawerId = layoutResID;
      this.drawerColorId = nColor;
      this.navigationResId = navigationResID;
      return this;
    }

    public ActivityViewBuilder extractUi(Object ui) {
      this.ui = ui;
      return this;
    }

    public ActivityViewBuilder setActivityTitle(@StringRes int titleID) {
      bSetTitle = true;
      this.titleID = titleID;
      return this;
    }

    public ActivityViewBuilder setActivityTitle(@StringRes int titleID, int gravity) {
      bSetTitle = true;
      this.titleID = titleID;
      this.gravity = gravity;
      return this;
    }

    public ActivityViewBuilder setNav(@IdRes int layoutNavHostFragmentId,
                                      @NavigationRes int layoutNavGraphId,
                                      @IdRes int layoutNavDestinationId,
                                      Bundle bundle) {
      bSetNav = true;
      this.layoutNavHostFragmentId = layoutNavHostFragmentId;
      this.layoutNavGraphId = layoutNavGraphId;
      this.layoutNavDestinationId = layoutNavDestinationId;
      if (bundle != null) {
        bSetBundelNav = true;
        this.bundle = bundle;
      }
      return this;
    }

    public ActivityViewBuilder setPendingTransition(@AnimRes int animEnterId, @AnimRes int animExitId) {
      bSetPendingTransition = true;
      this.animEnterId = animEnterId;
      this.animExitId = animExitId;
      return this;
    }


    //--------build()------//

    public ActivityViewBuilder build() {
      for (int feature : this.features) {
        activity.getWindow().requestFeature(feature);
      }

      if (noTitlebar) {
        activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      }

      if (fullscreen) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      }

      if (noActionbar) {
        activity.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        {
          android.app.ActionBar actionBar = activity.getActionBar();
          if (actionBar != null) {
            actionBar.hide();
          }
        }

        if (activity instanceof AppCompatActivity) {
          AppCompatActivity castedActivity = (AppCompatActivity) activity;
          ActionBar actionBar = castedActivity.getSupportActionBar();
          if (actionBar != null) {
            actionBar.hide();
          }
        }
      }

      activity.setContentView(layoutId);

      /*if (bSetTitle)
        activity.setTitle(titleID);
      else
        activity.setTitle(null);*/


      // Set up the toolbar.
      if (bSetToolbar) {
        if (activity instanceof AppCompatActivity) {
          AppCompatActivity castedActivity = (AppCompatActivity) activity;
          Toolbar toolbar = activity.findViewById(layoutToolbarId);
          castedActivity.setSupportActionBar(toolbar);

          Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

          if (bSetTitle) {
            //TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            //mTitle.setText(titleID);
            //mTitle.setGravity(gravity);
          }

          BaseApplication.setToolbar(toolbar);
        }
      }


      if (bSetActionbar) {
        AppCompatActivity castedActivity = (AppCompatActivity) activity;
        ActionBar ab = castedActivity.getSupportActionBar();
        if (ab != null) {
          ab.setHomeAsUpIndicator(layoutActionbarId);
          ab.setDisplayHomeAsUpEnabled(true);
        }
      }


      if (bSetActionbarLogo) {
        AppCompatActivity castedActivity = (AppCompatActivity) activity;
        ActionBar ab = castedActivity.getSupportActionBar();
        if (ab != null) {
          ab.setLogo(layoutActionbarLogoId);
        }
      }

      if (bSetNav) {

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(layoutNavHostFragmentId);
        assert navHostFragment != null;
        NavInflater inflater = navHostFragment.getNavController().getNavInflater();
        NavGraph graph = inflater.inflate(layoutNavGraphId);
        //if (!bSetBundelNav)
        //graph.setDefaultArguments(bundle);
        graph.setStartDestination(layoutNavDestinationId);
        navHostFragment.getNavController().setGraph(graph);
      }

      if (bSetPendingTransition) {

        overridePendingTransition(animEnterId, animExitId);
      }


      if (bSetNavigationDrawer) {

        /*mDrawerLayout = activity.findViewById(layoutDrawerId);
        mDrawerLayout.setStatusBarBackground(drawerColorId);

        UApp.setDrawerLayout(mDrawerLayout);
        mNavigationView = activity.findViewById(navigationResId);

        mNavigationDrawer = mNavigationView.findViewById(R.id.inc_nav_layout);

        mHeaderNavigationDrawer = mNavigationDrawer.findViewById(R.id.inc_nav_header);
        mItemsNavigationDrawer = mNavigationDrawer.findViewById(R.id.scroll_nav_items).findViewById(R.id.inc_nav_items);
        mFooterNavigationDrawer = mNavigationDrawer.findViewById(R.id.inc_nav_footer);


        if (mNavigationView != null) {
          initNavigationDrawer();
          setupNavigationDrawer(mHeaderNavigationDrawer, mItemsNavigationDrawer, mFooterNavigationDrawer);
        }*/
      }

      return this;
    }
  }


  private void initNavigationDrawer() {
/*    View headerNavigationDrawer = mHeaderNavigationDrawer.findViewById(R.id.inc_nav_header);
    View itemsNavigationDrawer = mItemsNavigationDrawer.findViewById(R.id.inc_nav_items);
    View footerNavigationDrawer = mFooterNavigationDrawer.findViewById(R.id.inc_nav_footer);

    // header
    TextView tvUserName = headerNavigationDrawer.findViewById(R.id.tv_user_name);
    tvUserName.setText(BaseApplication.getCorePrefComponent().getPrefImplementation().getCurrentUserName());

    TextView tvCompanyName = headerNavigationDrawer.findViewById(R.id.tv_company);
    tvCompanyName.setText(BaseApplication.getCorePrefComponent().getPrefImplementation().getCompanyName());

    TextView tvFPIdName = headerNavigationDrawer.findViewById(R.id.tv_fpid_name);
    tvFPIdName.setText(BaseApplication.getCorePrefComponent().getPrefImplementation().getFPName());

    TextView tvSDate = headerNavigationDrawer.findViewById(R.id.tv_fpid_sdate);
    tvSDate.setText(BaseApplication.getCorePrefComponent().getPrefImplementation().getSDate());

    TextView tvEDate = headerNavigationDrawer.findViewById(R.id.tv_fpid_edate);
    tvEDate.setText(BaseApplication.getCorePrefComponent().getPrefImplementation().getEDate());

    // item
    SwitchCompat swShowImages = itemsNavigationDrawer.findViewById(R.id.cl_show_image).findViewById(R.id.sw_show_image);
    swShowImages.setChecked(BaseApplication.getCorePrefComponent().getPrefImplementation().isShowImages());

    // footer
    TextView tvAppVersion = footerNavigationDrawer.findViewById(R.id.tv_version);
    tvAppVersion.setText(AppConstants.getAppVersion());*/
  }

  public void setupNavigationDrawer(ConstraintLayout headerNavigationDrawer,
                                    ConstraintLayout itemsNavigationDrawer,
                                    ConstraintLayout footerNavigationDrawer) {

/*    headerNavigationDrawer.findViewById(R.id.inc_nav_header).findViewById(R.id.img_back).setOnClickListener(view -> closeDrawer());

    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_sync).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_sync_so).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_sync_pre_factor).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_customers).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_update_app).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_show_image).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_about_us).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_contact_us).setOnClickListener(itemsNavigationDrawerListener);
    itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.cl_log_out).setOnClickListener(itemsNavigationDrawerListener);
    //itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.sw_show_image).setOnClickListener(itemsNavigationDrawerListener);
    ((SwitchCompat) itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.sw_show_image)).setOnCheckedChangeListener(checkedChangeListener);

    //itemsNavigationDrawer.findViewById(R.id.inc_nav_items).findViewById(R.id.btn_acc_vector).setOnClickListener(itemsNavigationDrawerListener);
    footerNavigationDrawer.findViewById(R.id.inc_nav_footer).setOnClickListener(footerNavigationDrawerListener);*/
  }

}
