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
public final class EditarRegistroUseCase_Factory implements Factory<EditarRegistroUseCase> {
  private final Provider<GastoRepository> repositoryProvider;

  public EditarRegistroUseCase_Factory(Provider<GastoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public EditarRegistroUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static EditarRegistroUseCase_Factory create(Provider<GastoRepository> repositoryProvider) {
    return new EditarRegistroUseCase_Factory(repositoryProvider);
  }

  public static EditarRegistroUseCase newInstance(GastoRepository repository) {
    return new EditarRegistroUseCase(repository);
  }
}
