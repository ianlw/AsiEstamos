package com.ianlw.asiestamos.gemini;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class GeminiResponseParser_Factory implements Factory<GeminiResponseParser> {
  @Override
  public GeminiResponseParser get() {
    return newInstance();
  }

  public static GeminiResponseParser_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GeminiResponseParser newInstance() {
    return new GeminiResponseParser();
  }

  private static final class InstanceHolder {
    private static final GeminiResponseParser_Factory INSTANCE = new GeminiResponseParser_Factory();
  }
}
