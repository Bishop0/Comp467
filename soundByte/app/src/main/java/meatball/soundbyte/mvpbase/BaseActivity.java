package meatball.soundbyte.mvpbase;

import android.os.Bundle;
import dagger.android.AndroidInjection;
import meatball.soundbyte.theme.ThemedActivity;

public abstract class BaseActivity extends ThemedActivity implements IMVPView {
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidInjection.inject(this);
  }
}
