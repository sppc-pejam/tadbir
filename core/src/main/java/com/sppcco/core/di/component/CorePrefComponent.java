package com.sppcco.core.di.component;


import com.sppcco.core.data.local.pref.IPrefContract;
import com.sppcco.core.di.module.CorePrefModule;

import javax.inject.Singleton;

import dagger.Component;

//@PrefScope
@Singleton
@Component(modules = CorePrefModule.class)
public interface CorePrefComponent {

  IPrefContract getPrefImplementation();
}
