package com.nehem.pabweek3.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DataModule_ProvideTodoRepositoryFactory implements Factory<TodoRepository> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideTodoRepositoryFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TodoRepository get() {
    return provideTodoRepository(contextProvider.get());
  }

  public static DataModule_ProvideTodoRepositoryFactory create(Provider<Context> contextProvider) {
    return new DataModule_ProvideTodoRepositoryFactory(contextProvider);
  }

  public static TodoRepository provideTodoRepository(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideTodoRepository(context));
  }
}
