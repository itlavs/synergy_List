package club.plus1.list;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    public static App context;
    public static DB db;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        db = Room.databaseBuilder(this, DB.class, "db").build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        context = null;
        db.close();
        db = null;
    }
}
