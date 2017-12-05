package meatball.soundbyte.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import meatball.soundbyte.activities.MainActivity;
import meatball.soundbyte.di.qualifiers.ActivityContext;
import meatball.soundbyte.di.scopes.ActivityScope;

@Module
public class MainActivityModule {
  @Provides
  @ActivityContext
  @ActivityScope
  Context provideActivityContext(MainActivity activity) {
    return activity;
  }
}
