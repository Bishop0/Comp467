package meatball.soundbyte.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import meatball.soundbyte.activities.MainActivity;
import meatball.soundbyte.activities.PlayListActivity;
import meatball.soundbyte.activities.SettingsActivity;
import meatball.soundbyte.di.scopes.ActivityScope;

/**
 * Created by arjun on 12/1/17.
 */

@Module
abstract class ActivityBuilderModule {
  @ActivityScope
  @ContributesAndroidInjector(modules = {MainActivityModule.class, RecordFragmentBuilderModule.class})
  abstract MainActivity contributeMainActivity();

  @ActivityScope
  @ContributesAndroidInjector(modules = {PlayListActivityModule.class, PlayListFragmentBuilderModule.class})
  abstract PlayListActivity contributePlayListActivity();

  @ContributesAndroidInjector()
  abstract SettingsActivity contributeSettingsActivity();
}
