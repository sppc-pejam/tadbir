package com.sppcco.core.framework.widget;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.sppcco.core.framework.application.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class BaseTab {

  public static class Item {
    private Fragment fragment;
    private String title;
    private int icon;

    Item(Class<? extends Fragment> fragmentClass, String title, int icon) {
      try {
        this.fragment = fragmentClass.newInstance();
        this.title = title;
        this.icon = icon;
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    public Item(Fragment fragment, String title, int icon) {
      this.fragment = fragment;
      this.title = title;
      this.icon = icon;
    }

    public Fragment getFragment() {
      return fragment;
    }

    public String getTitle() {
      return title;
    }

    public int getIcon() {
      return icon;
    }

    public void setTitle(String value) {
      title = value;
    }

    public void setIcon(int value) {
      icon = value;
    }

  }

  public class Adapter extends FragmentStatePagerAdapter {
    private List<Item> items = new ArrayList<>();

    public Adapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
      return items.get(position).getFragment();
    }

    Item getRawItem(int position) {
      return items.get(position);
    }

    @Override
    public int getCount() {
      return items.size();
    }

    public void add(Item item) {
      items.add(item);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return items.get(position).getTitle();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      if (position < getCount()) {
        FragmentManager manager = ((Fragment) object).getFragmentManager();
        assert manager != null;
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) object);
        trans.commit();
      }
    }
  }

  private ViewPager viewPager;
  private TabLayout tabLayout;
  private Adapter adapter;


  /**
   * this constructor use when BaseTab in Activity class
   */
  public BaseTab() {
  }

  /**
   * this constructor use when BaseTab in Activity class
   */
  public BaseTab(AppCompatActivity activity, int viewPagerId, int tabLayoutId) {
    View view = activity.getWindow().getDecorView();
    viewPager = view.findViewById(viewPagerId);
    tabLayout = view.findViewById(tabLayoutId);
    adapter = new Adapter(activity.getSupportFragmentManager());
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }


  public BaseTab(FragmentManager fragmentManager, View view, int viewPagerId, int tabLayoutId) {
    viewPager =  view.findViewById(viewPagerId);
    tabLayout =  view.findViewById(tabLayoutId);
    adapter = new Adapter(fragmentManager);
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }


  private void refreshIcons() {
    for (int i = 0; i < adapter.getCount(); i++) {
      int itemIcon = adapter.getRawItem(i).getIcon();
      if (itemIcon != 0) {
        tabLayout.getTabAt(i).setIcon(adapter.getRawItem(i).getIcon());
      }
    }
  }

  public Fragment add(Class<? extends Fragment> fragmentClass, String title, int icon) {
    Item item = new Item(fragmentClass, title, icon);
    adapter.add(item);
    adapter.notifyDataSetChanged();

    refreshIcons();
    return item.getFragment();
  }

  public Fragment add(Class<? extends Fragment> fragmentClass, String title) {
    return add(fragmentClass, title, 0);
  }

  public Fragment add(Class<? extends Fragment> fragmentClass, int icon) {
    return add(fragmentClass, null, icon);
  }

  public void setIcon(int index, int icon) {
    adapter.getRawItem(index).setIcon(icon);
    refreshIcons();
  }

  public void setTitle(int index, String title) {
    adapter.getRawItem(index).setTitle(title);
    adapter.notifyDataSetChanged();
    refreshIcons();
  }

/*  private String getTag(int nFragmentPosition) {
    String strFragmentTag = "";
    if (nFragmentPosition > adapter.getCount())
      return "";
    strFragmentTag = "android:switcher:" + viewPager.getId() + ":"
      + adapter.getItemId(nFragmentPosition);
    return strFragmentTag;
  }*/

  /*public String getFragmentTag(int nFragmentPosition) {
    return getTag(nFragmentPosition);
  }*/

  public void setTabsFont() {

    ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
    int tabsCount = vg.getChildCount();
    for (int j = 0; j < tabsCount; j++) {
      ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
      int tabChildsCount = vgTab.getChildCount();
      for (int i = 0; i < tabChildsCount; i++) {
        View tabViewChild = vgTab.getChildAt(i);

        if (tabViewChild instanceof TextView)
          ((TextView) tabViewChild).setTypeface(BaseApplication.getFaNumTypeface(), Typeface.NORMAL);

      }
    }
  }

  public void setFirstTab(){
    //tabLayout.setScrollPosition(adapter.getCount(),0f,true);
    viewPager.setCurrentItem(adapter.getCount());
  }

  public int getCurrentItem(){
    return viewPager.getCurrentItem();
  }


}
