package meatball.soundbyte.audiorecording;

import meatball.soundbyte.mvpbase.IMVPView;
import meatball.soundbyte.recordingservice.AudioRecorder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface AudioRecordMVPView extends IMVPView {
  void updateChronometer(String text);

  void togglePauseStatus();

  void toggleRecordButton();

  void linkGLViewToHandler();

  void setPauseButtonVisible();

  void setPauseButtonInVisible();

  void setScreenOnFlag();

  void clearScreenOnFlag();

  void startServiceAndBind();

  void stopServiceAndUnBind();

  void bindToService();

  void unbindFromService();

  void pauseRecord();

  void resumeRecord();

  Disposable subscribeForTimer(Consumer<AudioRecorder.RecordTime> recordTimeConsumer);
}
