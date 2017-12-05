package meatball.soundbyte.mvpbase;

import android.content.Context;
import dagger.android.support.AndroidSupportInjection;
import meatball.soundbyte.theme.ThemedFragment;

public abstract class BaseFragment extends ThemedFragment implements IMVPView {

  @Override public void onAttach(Context context) {
    super.onAttach(context);
try {
  AndroidSupportInjection.inject(this);
}catch (Exception E){

}
  }
}
