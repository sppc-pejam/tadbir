package com.sppcco.core.di.subcomponent;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sppcco.core.data.remote.control.AccessRemoteControlDataSource;
import com.sppcco.core.data.remote.control.AccessRemoteControlRepository;
import com.sppcco.core.data.remote.repository.AccessRemoteDataRepository;
import com.sppcco.core.data.remote.repository.AccessRemoteDataSource;
import com.sppcco.core.data.remote.service.ApiService;
import com.sppcco.core.di.component.CoreNetComponent;
import com.sppcco.core.di.scope.NetScope;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.network.error_handling.RxErrorHandling;
import com.sppcco.core.util.app.CoreConstants;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = {CoreNetComponent.class})
public class NetModule {


  private Application mApplication;

  public NetModule(Application application) {

    mApplication = application;
  }

  @Provides
  @Singleton
  ApiService provideApiService(Retrofit retrofit) {

    return retrofit.create(ApiService.class);
  }


  @Provides
  @Singleton
  Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

    return new Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create(gson))
      //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addCallAdapterFactory(RxErrorHandling.create())
      .baseUrl(BaseApplication.getBaseUrl())
      .client(okHttpClient)
      .build();
  }

  @Provides
  @Singleton
  Gson provideGson() {

    GsonBuilder gsonBuilder = new GsonBuilder();
    //gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    gsonBuilder.registerTypeAdapter(Date.class, new DateTypeDeserializer());
    gsonBuilder.registerTypeAdapter(Boolean.class, booleanAsIntAdapter);
    gsonBuilder.registerTypeAdapter(boolean.class, booleanAsIntAdapter);
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create();
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(Cache cache) {

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.cache(cache);
    //builder.addInterceptor(new RequestInterceptor(mApplication));

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    builder.interceptors().add(interceptor);

    builder.connectTimeout(60, TimeUnit.SECONDS);
    builder.readTimeout(60, TimeUnit.SECONDS);
    builder.writeTimeout(60, TimeUnit.SECONDS);
    return builder.build();
  }

  @Provides
  @Singleton
  Cache provideOkHttpCache() {

    int cacheSize = 1000 * 1024 * 1024; // 1000 MiB
    return new Cache(mApplication.getCacheDir(), cacheSize);
  }


  public class DateTypeDeserializer implements JsonDeserializer<Date> {

    private final String[] DATE_FORMATS = new String[]{
      "yyyy-MM-dd'T'HH:mm:ssZ",
      "yyyy-MM-dd'T'HH:mm:ss",
      "yyyy-MM-dd'T'HH:mm:ss.SSS",
      "yyyy-MM-dd",
      "EEE MMM dd HH:mm:ss z yyyy",
      "HH:mm:ss",
      "MM/dd/yyyy HH:mm:ss aaa",
      "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
      "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
      "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
      "MMM d',' yyyy H:mm:ss a"
    };

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      for (String format : DATE_FORMATS) {
        try {
          return new SimpleDateFormat(format, Locale.US).parse(json.getAsString());
        } catch (ParseException ignored) {
        }
      }
      throw new JsonParseException("Unparseable date: \"" + json.getAsString()
        + "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS));
    }
  }

  private static final TypeAdapter<Boolean> booleanAsIntAdapter = new TypeAdapter<Boolean>() {

    @Override public void write(JsonWriter out, Boolean value) throws IOException {
      if (value == null) {
        out.nullValue();
      } else {
        out.value(value);
      }
    }
    @Override public Boolean read(JsonReader in) throws IOException {
      JsonToken peek = in.peek();
      switch (peek) {
        case BOOLEAN:
          return in.nextBoolean();
        case NULL:
          in.nextNull();
          return null;
        case NUMBER:
          return in.nextInt() != 0;
        case STRING:
          return Boolean.parseBoolean(in.nextString());
        default:
          throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
      }
    }
  };



/*
  @Provides
  @Singleton
  LoginRemoteDataSource loginRemoteDataSource() {
    return new LoginRemoteDataSource();
  }

  @Provides
  @Singleton
  LoginRemoteControlRepository loginRemoteControlRepository() {
    return LoginRemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  AppVersionRemoteDataSource appVersionRemoteDataSource() {
    return new AppVersionRemoteDataSource();
  }

  @Provides
  @Singleton
  AppVersionRemoteControlRepository appVersionRemoteControlRepository() {
    return AppVersionRemoteControlDataSource.getInstance();
  }


  @Provides
  @Singleton
  CustomerRemoteDataSource customerRemoteDataSource() {
    return new CustomerRemoteDataSource();
  }

  @Provides
  @Singleton
  CustomerRemoteControlRepository customerRemoteControlRepository() {
    return CustomerRemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  ImageRemoteDataSource imageRemoteDataSync() {
    return new ImageRemoteDataSource();
  }

  @Provides
  @Singleton
  ImageRemoteControlRepository imageRemoteControlRepository() {
    return ImageRemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  MerchandiseRemoteDataSource merchandiseRemoteDataSource() {
    return new MerchandiseRemoteDataSource();
  }

  @Provides
  @Singleton
  MerchandiseRemoteControlRepository merchandiseRemoteControlRepository() {
    return MerchandiseRemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  SORemoteDataSource soRemoteDataSource() {
    return new SORemoteDataSource();
  }

  @Provides
  @Singleton
  SORemoteControlRepository soRemoteControlRepository() {
    return SORemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  SPFactorRemoteDataSource spFactorRemoteDataSource() {
    return new SPFactorRemoteDataSource();
  }

  @Provides
  @Singleton
  SPFactorRemoteControlRepository spFactorRemoteControlRepository() {
    return SPFactorRemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  SyncRemoteDataSource syncRemoteDataSource() {
    return new SyncRemoteDataSource();
  }

  @Provides
  SyncRemoteControlRepository syncRemoteControlRepository() {
    return SyncRemoteControlDataSource.getInstance();
  }

  @Provides
  @Singleton
  PrintPreviewRemoteDataSource printPreviewRemoteDataSource() {
    return new PrintPreviewRemoteDataSource();
  }

  @Provides
  @Singleton
  PrintPreviewRemoteControlRepository printPreviewRemoteControlRepository() {
    return PrintPreviewRemoteControlDataSource.getInstance();
  }*/
}
