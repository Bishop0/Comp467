package meatball.soundbyte.di;

import dagger.Module;
import dagger.Provides;
import meatball.soundbyte.di.scopes.FragmentScope;
import meatball.soundbyte.playlist.PlayListMVPView;
import meatball.soundbyte.playlist.PlayListPresenter;
import meatball.soundbyte.playlist.PlayListPresenterImpl;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by arjun on 12/1/17.
 */

@Module
class PlayListFragmentModule {

  @Provides
  @FragmentScope
  PlayListPresenter<PlayListMVPView> providePlayListPresenter(PlayListPresenterImpl<PlayListMVPView> playListPresenter) {
    return playListPresenter;
  }

  @Provides
  @FragmentScope
  CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }
}
