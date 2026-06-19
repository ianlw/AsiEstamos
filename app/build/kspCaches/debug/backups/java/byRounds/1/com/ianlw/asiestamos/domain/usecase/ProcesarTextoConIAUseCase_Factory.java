package com.ianlw.asiestamos.domain.usecase;

import com.ianlw.asiestamos.domain.repository.GeminiRepository;
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
public final class ProcesarTextoConIAUseCase_Factory implements Factory<ProcesarTextoConIAUseCase> {
  private final Provider<GeminiRepository> repositoryProvider;

  public ProcesarTextoConIAUseCase_Factory(Provider<GeminiRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ProcesarTextoConIAUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ProcesarTextoConIAUseCase_Factory create(
      Provider<GeminiRepository> repositoryProvider) {
    return new ProcesarTextoConIAUseCase_Factory(repositoryProvider);
  }

  public static ProcesarTextoConIAUseCase newInstance(GeminiRepository repository) {
    return new ProcesarTextoConIAUseCase(repository);
  }
}
