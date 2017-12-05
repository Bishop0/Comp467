package meatball.soundbyte.di;

import dagger.Module;
import dagger.Provides;
import meatball.soundbyte.audiorecording.AudioRecordMVPView;
import meatball.soundbyte.audiorecording.AudioRecordPresenter;
import meatball.soundbyte.audiorecording.AudioRecordPresenterImpl;
import meatball.soundbyte.di.scopes.FragmentScope;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by arjun on 12/1/17.
 */

@Module
class RecordFragmentModule {

  @Provides
  @FragmentScope
  AudioRecordPresenter<AudioRecordMVPView> provideAudioRecordPresenter(
      AudioRecordPresenterImpl<AudioRecordMVPView> audioRecordPresenter) {
    return audioRecordPresenter;
  }

  @Provides
  @FragmentScope
  CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }
}
