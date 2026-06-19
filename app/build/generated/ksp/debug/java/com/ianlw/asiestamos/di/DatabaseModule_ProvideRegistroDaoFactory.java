package com.ianlw.asiestamos.di;

import com.ianlw.asiestamos.data.local.AppDatabase;
import com.ianlw.asiestamos.data.local.dao.RegistroDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideRegistroDaoFactory implements Factory<RegistroDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideRegistroDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RegistroDao get() {
    return provideRegistroDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRegistroDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRegistroDaoFactory(databaseProvider);
  }

  public static RegistroDao provideRegistroDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRegistroDao(database));
  }
}
