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
public final class RegistrarGastoUseCase_Factory implements Factory<RegistrarGastoUseCase> {
  private final Provider<GastoRepository> repositoryProvider;

  public RegistrarGastoUseCase_Factory(Provider<GastoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RegistrarGastoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RegistrarGastoUseCase_Factory create(Provider<GastoRepository> repositoryProvider) {
    return new RegistrarGastoUseCase_Factory(repositoryProvider);
  }

  public static RegistrarGastoUseCase newInstance(GastoRepository repository) {
    return new RegistrarGastoUseCase(repository);
  }
}
