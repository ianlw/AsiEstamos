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
public final class EliminarRegistroUseCase_Factory implements Factory<EliminarRegistroUseCase> {
  private final Provider<GastoRepository> repositoryProvider;

  public EliminarRegistroUseCase_Factory(Provider<GastoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public EliminarRegistroUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static EliminarRegistroUseCase_Factory create(
      Provider<GastoRepository> repositoryProvider) {
    return new EliminarRegistroUseCase_Factory(repositoryProvider);
  }

  public static EliminarRegistroUseCase newInstance(GastoRepository repository) {
    return new EliminarRegistroUseCase(repository);
  }
}
