package com.ianlw.asiestamos.presentation.historial;

import com.ianlw.asiestamos.domain.usecase.EliminarRegistroUseCase;
import com.ianlw.asiestamos.domain.usecase.ObtenerHistorialUseCase;
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
public final class HistorialViewModel_Factory implements Factory<HistorialViewModel> {
  private final Provider<ObtenerHistorialUseCase> obtenerHistorialProvider;

  private final Provider<EliminarRegistroUseCase> eliminarRegistroProvider;

  public HistorialViewModel_Factory(Provider<ObtenerHistorialUseCase> obtenerHistorialProvider,
      Provider<EliminarRegistroUseCase> eliminarRegistroProvider) {
    this.obtenerHistorialProvider = obtenerHistorialProvider;
    this.eliminarRegistroProvider = eliminarRegistroProvider;
  }

  @Override
  public HistorialViewModel get() {
    return newInstance(obtenerHistorialProvider.get(), eliminarRegistroProvider.get());
  }

  public static HistorialViewModel_Factory create(
      Provider<ObtenerHistorialUseCase> obtenerHistorialProvider,
      Provider<EliminarRegistroUseCase> eliminarRegistroProvider) {
    return new HistorialViewModel_Factory(obtenerHistorialProvider, eliminarRegistroProvider);
  }

  public static HistorialViewModel newInstance(ObtenerHistorialUseCase obtenerHistorial,
      EliminarRegistroUseCase eliminarRegistro) {
    return new HistorialViewModel(obtenerHistorial, eliminarRegistro);
  }
}
