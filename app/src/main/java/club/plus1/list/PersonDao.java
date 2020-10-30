package club.plus1.list;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {
    //CRUD
    //Create
    //Read
    //Update
    //Delete
    @Insert
    void create(Person person);

    @Query("SELECT * from person")
    List<Person> readAll();

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("DELETE FROM person")
    void clear();
}
