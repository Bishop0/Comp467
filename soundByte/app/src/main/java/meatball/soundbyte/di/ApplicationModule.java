package meatball.soundbyte.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import meatball.soundbyte.db.AppDataBase;
import meatball.soundbyte.db.RecordItemDataSource;
import meatball.soundbyte.di.qualifiers.ApplicationContext;
import javax.inject.Singleton;

@Module
public class ApplicationModule {

  @Provides
  @ApplicationContext
  @Singleton
  Context provideApplicationContext(Application application) {
    return application.getApplicationContext();
  }

  @Provides
  @Singleton
  RecordItemDataSource provideRecordItemDataSource(@ApplicationContext Context context) {
    return AppDataBase.getInstance(context).getRecordItemDataSource();
  }
}
