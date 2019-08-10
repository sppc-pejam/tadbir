package com.sppcco.core;

public interface ICoreViewModel<V, P> {

  void setView(V view);

  void setPresenter(P presenter);
}
