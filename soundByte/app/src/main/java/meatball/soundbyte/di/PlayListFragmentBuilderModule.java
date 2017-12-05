package meatball.soundbyte.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import meatball.soundbyte.di.scopes.FragmentScope;
import meatball.soundbyte.playlist.PlayListFragment;

/**
 * Created by arjun on 12/1/17.
 */

@Module
abstract class PlayListFragmentBuilderModule {
  @FragmentScope
  @ContributesAndroidInjector(modules = {PlayListFragmentModule.class})
  abstract PlayListFragment contributePlayListFragment();
}
