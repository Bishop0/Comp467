package meatball.soundbyte.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import meatball.soundbyte.AudioRecorderApp;
import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationModule.class, AndroidInjectionModule.class, ActivityBuilderModule.class, ServiceBuilderModule.class})
public interface ApplicationComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance Builder application(Application application);

    ApplicationComponent build();
  }

  void inject(AudioRecorderApp audioRecorderApp);
}
