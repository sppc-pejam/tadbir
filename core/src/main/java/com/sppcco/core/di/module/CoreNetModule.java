package com.sppcco.core.di.module;

import com.sppcco.core.data.remote.control.AccessRemoteControlDataSource;
import com.sppcco.core.data.remote.control.AccessRemoteControlRepository;
import com.sppcco.core.data.remote.control.CustomerRemoteControlDataSource;
import com.sppcco.core.data.remote.control.CustomerRemoteControlRepository;
import com.sppcco.core.data.remote.control.ImageRemoteControlDataSource;
import com.sppcco.core.data.remote.control.ImageRemoteControlRepository;
import com.sppcco.core.data.remote.control.MerchandiseRemoteControlDataSource;
import com.sppcco.core.data.remote.control.MerchandiseRemoteControlRepository;
import com.sppcco.core.data.remote.repository.AccessRemoteDataRepository;
import com.sppcco.core.data.remote.repository.AccessRemoteDataSource;
import com.sppcco.core.data.remote.repository.CustomerRemoteDataSource;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.remote.repository.ImageRemoteDataSource;
import com.sppcco.core.data.remote.repository.ImageRemoteRepository;
import com.sppcco.core.data.remote.repository.MerchandiseRemoteDataSource;
import com.sppcco.core.data.remote.repository.MerchandiseRemoteRepository;
import com.sppcco.core.di.scope.NetScope;

import dagger.Module;
import dagger.Provides;

@Module()
public class CoreNetModule {


  @Provides
  @NetScope
  AccessRemoteDataRepository accessRemoteDataRepository() {

    return new AccessRemoteDataSource();
  }

  @Provides
  @NetScope
  AccessRemoteControlRepository accessRemoteControlRepository() {

    return new AccessRemoteControlDataSource();
  }

  @Provides
  @NetScope
  CustomerRemoteRepository customerRemoteDataSource() {
    return new CustomerRemoteDataSource();
  }

  @Provides
  @NetScope
  CustomerRemoteControlRepository customerRemoteControlRepository() {
    return new CustomerRemoteControlDataSource();
  }

  @Provides
  @NetScope
  ImageRemoteRepository imageRemoteDataSource() {
    return new ImageRemoteDataSource();
  }

  @Provides
  @NetScope
  ImageRemoteControlRepository imageRemoteControlRepository() {
    return new ImageRemoteControlDataSource();
  }
  @Provides
  @NetScope
  MerchandiseRemoteRepository merchandiseRemoteDataSource() {
    return new MerchandiseRemoteDataSource();
  }

  @Provides
  @NetScope
  MerchandiseRemoteControlRepository merchandiseRemoteControlRepository() {
    return new MerchandiseRemoteControlDataSource();
  }

}
