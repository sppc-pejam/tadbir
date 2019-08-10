package com.sppcco.customer.ui.main;

import com.sppcco.core.framework.presenter.BasePresenter;

import androidx.annotation.NonNull;

public class MainPresenter extends BasePresenter  implements MainContract.Presenter {


  private final MainContract.View mView;

  private MainPresenter(@NonNull MainContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }

  public static MainContract.Presenter getPresenterInstance(@NonNull MainContract.View view) {

    return new MainPresenter(view);
  }

  @Override
  public void start() {

  }

  @Override
  public void destroy() {

  }

}
