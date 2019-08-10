package com.sppcco.core;

public interface ICoreView<T> {

  void setPresenter(T presenter);

  void initLayout();

  void initData();

  boolean updateModel();

  boolean updateView();

  boolean validData(boolean showMsg);
}
