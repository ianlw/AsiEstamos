package com.ianlw.asiestamos.presentation.estadisticas;

import com.ianlw.asiestamos.data.preferences.UserPreferences;
import com.ianlw.asiestamos.domain.usecase.ObtenerEstadisticasUseCase;
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
public final class EstadisticasViewModel_Factory implements Factory<EstadisticasViewModel> {
  private final Provider<ObtenerEstadisticasUseCase> obtenerEstadisticasProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public EstadisticasViewModel_Factory(
      Provider<ObtenerEstadisticasUseCase> obtenerEstadisticasProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.obtenerEstadisticasProvider = obtenerEstadisticasProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public EstadisticasViewModel get() {
    return newInstance(obtenerEstadisticasProvider.get(), userPreferencesProvider.get());
  }

  public static EstadisticasViewModel_Factory create(
      Provider<ObtenerEstadisticasUseCase> obtenerEstadisticasProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new EstadisticasViewModel_Factory(obtenerEstadisticasProvider, userPreferencesProvider);
  }

  public static EstadisticasViewModel newInstance(ObtenerEstadisticasUseCase obtenerEstadisticas,
      UserPreferences userPreferences) {
    return new EstadisticasViewModel(obtenerEstadisticas, userPreferences);
  }
}
