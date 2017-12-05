package meatball.soundbyte.playlist;

import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import meatball.soundbyte.db.RecordItemDataSource;
import meatball.soundbyte.db.RecordingItem;
import meatball.soundbyte.mvpbase.BasePresenter;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class PlayListPresenterImpl<V extends PlayListMVPView> extends BasePresenter<V>
    implements PlayListPresenter<V> {
  private static final int INVALID_ITEM = -1;
  private static final int PROGRESS_OFFSET = 20;
  @Inject
  public RecordItemDataSource recordItemDataSource;

  private int currentPlayingItem;
  private boolean isAudioPlaying = false;
  private boolean isAudioPaused = false;
  private List<RecordingItem> recordingItems = new ArrayList<>();

  @Inject
  public PlayListPresenterImpl(CompositeDisposable compositeDisposable) {
    super(compositeDisposable);
  }

  @Override public void onViewInitialised() {
    fillAdapter();
    getAttachedView().startWatchingForFileChanges();
  }

  @Override public void renameFile(int adapterPosition, String value) {
    rename(recordingItems.get(adapterPosition), adapterPosition, value).subscribe(
        new SingleObserver<Integer>() {
          @Override public void onSubscribe(Disposable d) {

          }

          @Override public void onSuccess(Integer position) {
            getAttachedView().notifyListItemChange(position);
          }

          @Override public void onError(Throwable e) {
            getAttachedView().showError(e.getMessage());
          }
        });
  }

  private Single<Integer> rename(RecordingItem recordingItem, int adapterPosition, String name) {
    return Single.create((SingleOnSubscribe<Integer>) e -> {
      File newFile = new File(
          Environment.getExternalStorageDirectory().getAbsolutePath() + "/SoundRecorder/" + name);
      if (newFile.exists() && !newFile.isDirectory()) {
        e.onError(new Exception("File with same name already exists"));
      } else {
        File oldFilePath = new File(recordingItem.getFilePath());
        if (oldFilePath.renameTo(newFile)) {
          recordingItem.setName(name);
          recordingItem.setFilePath(newFile.getPath());
          recordItemDataSource.updateRecordItem(recordingItem);
          e.onSuccess(adapterPosition);
        } else {
          e.onError(new Throwable("Cannot Rename file. Please try again"));
        }
      }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }

  @Override public void deleteFile(int position) {
    removeFile(recordingItems.get(position), position).subscribe(new SingleObserver<Integer>() {
      @Override public void onSubscribe(Disposable d) {

      }

      @Override public void onSuccess(Integer position) {
        getAttachedView().notifyListItemRemove(position);
      }

      @Override public void onError(Throwable e) {
        getAttachedView().showError(e.getMessage());
      }
    });
  }

  @Override public RecordingItem getListItemAt(int position) {
    return recordingItems.get(position);
  }

  @Override public void mediaPlayerStopped() {
try {


}catch (Exception e){

}
  }



  @Override public void onListItemClick(Context v ,int position) {

    AlertDialog.Builder builder2 = new AlertDialog.Builder(v);
    builder2.setMessage("Choose play mode");
    builder2.setCancelable(true);

    builder2.setPositiveButton(
            "Normal",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                try {
                  if (isAudioPlaying) {
                    if (currentPlayingItem == position) {
                      if (isAudioPaused) {
                        isAudioPaused = false;
                        getAttachedView().resumeMediaPlayer(position);
                        recordingItems.get(position).isPlaying = true;

                      } else {
                        isAudioPaused = true;
                        getAttachedView().pauseMediaPlayer(position);
                        recordingItems.get(position).isPlaying = false;

                      }
                    } else {
                      getAttachedView().stopMediaPlayer(currentPlayingItem);

                      startPlayer(position);
                    }
                  } else {
                    startPlayer(position);

                  }
                  getAttachedView().notifyListItemChange(position);
                } catch (IOException e) {
                  getAttachedView().showError("Failed to start media Player");
                }
              }
            });

    builder2.setNegativeButton(
            "With FX",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {



                AlertDialog.Builder builder1 = new AlertDialog.Builder(v);
                builder1.setMessage("Choose FX !");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Low Pitch",
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                            float playbackSpeed=1.5f;
                            SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

                            int soundId = soundPool.load(recordingItems.get(position).getFilePath(), 1);
                            AudioManager mgr = (AudioManager) v.getSystemService(Context.AUDIO_SERVICE);
                            final float volume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

                            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
                            {
                              @Override
                              public void onLoadComplete(SoundPool arg0, int arg1, int arg2)
                              {
                                soundPool.play(soundId, volume, volume, 1, 0, playbackSpeed);
                              }
                            });

                          }
                        });

                builder1.setNegativeButton(
                        "High Pitch",
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {


//low pitch
                            float playbackSpeed=0.9f;
                            SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

                            int soundId = soundPool.load(recordingItems.get(position).getFilePath(), 1);
                            AudioManager mgr = (AudioManager) v.getSystemService(Context.AUDIO_SERVICE);
                            final float volume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

                            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
                            {
                              @Override
                              public void onLoadComplete(SoundPool arg0, int arg1, int arg2)
                              {
                                soundPool.play(soundId, volume, volume, 1, 0, playbackSpeed);
                              }
                            });


                          }
                        });


                builder1.setNeutralButton(
                        "Echo",
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {

//echo


                            try {
                              startPlayer(position);
                              Handler handler = new Handler();
                              handler.postDelayed(new Runnable() {
                                public void run() {
                                  try {
                                    startPlayer(position);
                                  } catch (IOException e) {
                                    e.printStackTrace();
                                  }
                                }
                              }, 500);


                            } catch (IOException e) {
                              e.printStackTrace();
                            }
                          }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();

              }
            });


    AlertDialog alert11 = builder2.create();
    alert11.show();



  }

  private long currentProgress = 0;
  private void startPlayer(int position) throws IOException {
    isAudioPlaying = true;
    currentProgress = 0;
    recordingItems.get(position).isPlaying = true;
    getAttachedView().startMediaPlayer(position, recordingItems.get(position));
    currentPlayingItem = position;

  }

  @Override public void onListItemLongClick(int position) {
    getAttachedView().showFileOptionDialog(position, recordingItems.get(position));
  }

  @Override public int getListItemCount() {
    return recordingItems.size();
  }

  @Override public void shareFileClicked(int position) {
    getAttachedView().shareFileDialog(recordingItems.get(position).getFilePath());
  }

  @Override public void renameFileClicked(int position) {
    getAttachedView().showRenameFileDialog(position);
  }

  @Override public void deleteFileClicked(int position) {
    getAttachedView().showDeleteFileDialog(position);
  }

  private Single<Integer> removeFile(RecordingItem recordingItem, int position) {
    return Single.create((SingleOnSubscribe<Integer>) e -> {
      File file = new File(recordingItem.getFilePath());
      if (file.delete()) {
        recordItemDataSource.deleteRecordItem(recordingItem);
        recordingItems.remove(position);
        e.onSuccess(position);
      } else {
        e.onError(new Exception("File deletion failed"));
      }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }

  @Override public void onDetach() {
    getAttachedView().stopWatchingForFileChanges();
    getAttachedView().stopMediaPlayer(currentPlayingItem);
    super.onDetach();
  }

  private void fillAdapter() {
    getCompositeDisposable().add(recordItemDataSource.getAllRecordings()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((recordingItems) -> {
          if (recordingItems.size() > 0) {
            this.recordingItems.addAll(recordingItems);
            getAttachedView().notifyListAdapter();
          } else {
            getAttachedView().setRecordingListInVisible();
            getAttachedView().setEmptyLabelVisible();
          }
        }));
  }
}
