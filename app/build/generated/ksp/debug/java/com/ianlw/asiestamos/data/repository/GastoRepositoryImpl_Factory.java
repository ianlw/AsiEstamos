package com.ianlw.asiestamos.data.repository;

import com.ianlw.asiestamos.data.local.dao.RegistroDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class GastoRepositoryImpl_Factory implements Factory<GastoRepositoryImpl> {
  private final Provider<RegistroDao> daoProvider;

  public GastoRepositoryImpl_Factory(Provider<RegistroDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GastoRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static GastoRepositoryImpl_Factory create(Provider<RegistroDao> daoProvider) {
    return new GastoRepositoryImpl_Factory(daoProvider);
  }

  public static GastoRepositoryImpl newInstance(RegistroDao dao) {
    return new GastoRepositoryImpl(dao);
  }
}
