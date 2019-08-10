package com.sppcco.core.di.component;

import com.sppcco.core.data.remote.control.AccessRemoteControlRepository;
import com.sppcco.core.data.remote.control.CustomerRemoteControlRepository;
import com.sppcco.core.data.remote.control.ImageRemoteControlRepository;
import com.sppcco.core.data.remote.control.MerchandiseRemoteControlRepository;
import com.sppcco.core.data.remote.repository.AccessRemoteDataRepository;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.remote.repository.ImageRemoteRepository;
import com.sppcco.core.data.remote.repository.MerchandiseRemoteRepository;
import com.sppcco.core.data.remote.service.ApiService;
import com.sppcco.core.di.module.CoreNetModule;
import com.sppcco.core.di.scope.NetScope;
import com.sppcco.core.di.subcomponent.NetComponent;
import com.sppcco.core.di.subcomponent.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by m_pejam on 01/20/18.
 * CoreNetComponent
 */

@NetScope
@Subcomponent(modules = CoreNetModule.class)
public interface CoreNetComponent {

  @Subcomponent.Builder
  interface Builder {
    CoreNetComponent.Builder coreNetModule(CoreNetModule module);

    CoreNetComponent build();
  }

  ApiService apiService();

  AccessRemoteDataRepository accessRemoteDataRepository();

  AccessRemoteControlRepository accessRemoteControlRepository();

  CustomerRemoteRepository customerRemoteRepository();

  CustomerRemoteControlRepository customerRemoteControlRepository();

  ImageRemoteRepository imageRemoteRepository();

  ImageRemoteControlRepository imageRemoteControlRepository();

  MerchandiseRemoteRepository merchandiseRemoteRepository();

  MerchandiseRemoteControlRepository merchandiseRemoteControlRepository();

}
