package com.sppcco.core.di.subcomponent;

import com.sppcco.core.data.remote.control.AccessRemoteControlRepository;
import com.sppcco.core.data.remote.repository.AccessRemoteDataRepository;
import com.sppcco.core.data.remote.service.ApiService;
import com.sppcco.core.di.component.CoreNetComponent;
import com.sppcco.core.di.scope.NetScope;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by m_pejam on 01/20/18.
 * CoreNetComponent
 */

@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {

  CoreNetComponent.Builder coreNetComponentBuilder();

  ApiService apiService();

}
