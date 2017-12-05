package meatball.soundbyte.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import meatball.soundbyte.activities.PlayListActivity;
import meatball.soundbyte.di.qualifiers.ActivityContext;
import meatball.soundbyte.di.scopes.ActivityScope;

/**
 * Created by arjun on 12/1/17.
 */

@Module
class PlayListActivityModule {
  @Provides
  @ActivityContext
  @ActivityScope
  Context provideActivityContext(PlayListActivity activity) {
    return activity;
  }
}
