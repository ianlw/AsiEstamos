package com.ianlw.asiestamos.data.repository;

import com.ianlw.asiestamos.gemini.GeminiApiService;
import com.ianlw.asiestamos.gemini.GeminiResponseParser;
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
public final class GeminiRepositoryImpl_Factory implements Factory<GeminiRepositoryImpl> {
  private final Provider<GeminiApiService> apiServiceProvider;

  private final Provider<GeminiResponseParser> parserProvider;

  public GeminiRepositoryImpl_Factory(Provider<GeminiApiService> apiServiceProvider,
      Provider<GeminiResponseParser> parserProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.parserProvider = parserProvider;
  }

  @Override
  public GeminiRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), parserProvider.get());
  }

  public static GeminiRepositoryImpl_Factory create(Provider<GeminiApiService> apiServiceProvider,
      Provider<GeminiResponseParser> parserProvider) {
    return new GeminiRepositoryImpl_Factory(apiServiceProvider, parserProvider);
  }

  public static GeminiRepositoryImpl newInstance(GeminiApiService apiService,
      GeminiResponseParser parser) {
    return new GeminiRepositoryImpl(apiService, parser);
  }
}
