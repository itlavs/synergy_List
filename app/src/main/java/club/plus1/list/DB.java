package club.plus1.list;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Person.class, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract PersonDao personDao();
}
