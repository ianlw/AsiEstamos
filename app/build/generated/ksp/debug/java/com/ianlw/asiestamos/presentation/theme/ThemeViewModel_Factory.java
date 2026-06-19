package com.ianlw.asiestamos.presentation.theme;

import com.ianlw.asiestamos.data.preferences.UserPreferences;
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
public final class ThemeViewModel_Factory implements Factory<ThemeViewModel> {
  private final Provider<UserPreferences> userPreferencesProvider;

  public ThemeViewModel_Factory(Provider<UserPreferences> userPreferencesProvider) {
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public ThemeViewModel get() {
    return newInstance(userPreferencesProvider.get());
  }

  public static ThemeViewModel_Factory create(Provider<UserPreferences> userPreferencesProvider) {
    return new ThemeViewModel_Factory(userPreferencesProvider);
  }

  public static ThemeViewModel newInstance(UserPreferences userPreferences) {
    return new ThemeViewModel(userPreferences);
  }
}
