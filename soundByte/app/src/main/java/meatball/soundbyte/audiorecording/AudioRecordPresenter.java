package meatball.soundbyte.audiorecording;

import meatball.soundbyte.mvpbase.IMVPPresenter;

public interface AudioRecordPresenter<V extends AudioRecordMVPView> extends IMVPPresenter<V> {
  void onToggleRecodingStatus();

  void onTogglePauseStatus();

  boolean isRecording();

  boolean isPaused();

  void onViewInitialised();

  void onServiceStatusAvailable(boolean isRecoding, boolean isRecordingPaused);

  void onServiceUpdateReceived(String actionExtra);
}
