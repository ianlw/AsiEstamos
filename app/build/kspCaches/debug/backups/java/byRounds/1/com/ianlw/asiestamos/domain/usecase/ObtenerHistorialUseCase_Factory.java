package com.ianlw.asiestamos.domain.usecase;

import com.ianlw.asiestamos.domain.repository.GastoRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ObtenerHistorialUseCase_Factory implements Factory<ObtenerHistorialUseCase> {
  private final Provider<GastoRepository> repositoryProvider;

  public ObtenerHistorialUseCase_Factory(Provider<GastoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObtenerHistorialUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObtenerHistorialUseCase_Factory create(
      Provider<GastoRepository> repositoryProvider) {
    return new ObtenerHistorialUseCase_Factory(repositoryProvider);
  }

  public static ObtenerHistorialUseCase newInstance(GastoRepository repository) {
    return new ObtenerHistorialUseCase(repository);
  }
}
