package meatball.soundbyte.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import meatball.soundbyte.audiorecording.RecordFragment;
import meatball.soundbyte.di.scopes.FragmentScope;

/**
 * Created by arjun on 12/1/17.
 */

@Module
abstract class RecordFragmentBuilderModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = {RecordFragmentModule.class})
  abstract RecordFragment contributeRecordFragment();
}
