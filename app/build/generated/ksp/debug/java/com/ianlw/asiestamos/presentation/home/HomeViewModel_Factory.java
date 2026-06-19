package com.ianlw.asiestamos.presentation.home;

import android.content.Context;
import com.ianlw.asiestamos.data.preferences.UserPreferences;
import com.ianlw.asiestamos.domain.repository.GastoRepository;
import com.ianlw.asiestamos.domain.usecase.ProcesarImagenConIAUseCase;
import com.ianlw.asiestamos.domain.usecase.ProcesarTextoConIAUseCase;
import com.ianlw.asiestamos.domain.usecase.RegistrarGastoUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<RegistrarGastoUseCase> registrarGastoUseCaseProvider;

  private final Provider<ProcesarTextoConIAUseCase> procesarTextoConIAProvider;

  private final Provider<ProcesarImagenConIAUseCase> procesarImagenConIAProvider;

  private final Provider<GastoRepository> gastoRepositoryProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  private final Provider<Context> contextProvider;

  public HomeViewModel_Factory(Provider<RegistrarGastoUseCase> registrarGastoUseCaseProvider,
      Provider<ProcesarTextoConIAUseCase> procesarTextoConIAProvider,
      Provider<ProcesarImagenConIAUseCase> procesarImagenConIAProvider,
      Provider<GastoRepository> gastoRepositoryProvider,
      Provider<UserPreferences> userPreferencesProvider, Provider<Context> contextProvider) {
    this.registrarGastoUseCaseProvider = registrarGastoUseCaseProvider;
    this.procesarTextoConIAProvider = procesarTextoConIAProvider;
    this.procesarImagenConIAProvider = procesarImagenConIAProvider;
    this.gastoRepositoryProvider = gastoRepositoryProvider;
    this.userPreferencesProvider = userPreferencesProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(registrarGastoUseCaseProvider.get(), procesarTextoConIAProvider.get(), procesarImagenConIAProvider.get(), gastoRepositoryProvider.get(), userPreferencesProvider.get(), contextProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<RegistrarGastoUseCase> registrarGastoUseCaseProvider,
      Provider<ProcesarTextoConIAUseCase> procesarTextoConIAProvider,
      Provider<ProcesarImagenConIAUseCase> procesarImagenConIAProvider,
      Provider<GastoRepository> gastoRepositoryProvider,
      Provider<UserPreferences> userPreferencesProvider, Provider<Context> contextProvider) {
    return new HomeViewModel_Factory(registrarGastoUseCaseProvider, procesarTextoConIAProvider, procesarImagenConIAProvider, gastoRepositoryProvider, userPreferencesProvider, contextProvider);
  }

  public static HomeViewModel newInstance(RegistrarGastoUseCase registrarGastoUseCase,
      ProcesarTextoConIAUseCase procesarTextoConIA, ProcesarImagenConIAUseCase procesarImagenConIA,
      GastoRepository gastoRepository, UserPreferences userPreferences, Context context) {
    return new HomeViewModel(registrarGastoUseCase, procesarTextoConIA, procesarImagenConIA, gastoRepository, userPreferences, context);
  }
}
