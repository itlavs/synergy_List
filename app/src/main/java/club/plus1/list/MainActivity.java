package club.plus1.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    List<Person> list;

    static Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        handler = new Handler() {
            public void handleMessage(@NonNull Message message) {
                adapter.list = list;
                adapter.notifyDataSetChanged();
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                list.clear();
                list = App.db.personDao().readAll();
                handler.sendEmptyMessage(0);
            }
        });

        adapter = new ListAdapter(this, list);
        recyclerView = findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // ImagePhoto - паскалевская нотация
        // imagePhoto - верблюжья нотация
        // image_photo - змеиная нотация
        // image-photo - кебаб нотация
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getExtras() != null){
            if (requestCode == EditActivity.ADD){
                final Person person = new Person();
                person.setName(data.getStringExtra(EditActivity.NAME));
                person.setPhone(data.getStringExtra(EditActivity.PHONE));
                person.setEmail(data.getStringExtra(EditActivity.EMAIL));
                if (data.getStringExtra(EditActivity.PHOTO) != null) {
                    person.setPhoto(Uri.parse(data.getStringExtra(EditActivity.PHOTO)));
                }
                list.add(person);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        App.db.personDao().create(person);
                        App.db.close();
                        handler.sendEmptyMessage(0);
                    }
                });
                adapter.notifyDataSetChanged();
            }
            if (requestCode == EditActivity.EDIT){
                int positon = data.getIntExtra(EditActivity.POSITION, -1);
                if (positon == -1) return;
                final Person person = list.get(positon);
                person.setName(data.getStringExtra(EditActivity.NAME));
                person.setPhone(data.getStringExtra(EditActivity.PHONE));
                person.setEmail(data.getStringExtra(EditActivity.EMAIL));
                if (data.getStringExtra(EditActivity.PHOTO) != null) {
                    person.setPhoto(Uri.parse(data.getStringExtra(EditActivity.PHOTO)));
                }
                list.set(positon, person);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        App.db.personDao().update(person);
                        App.db.close();
                        handler.sendEmptyMessage(0);
                    }
                });
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void onAdd(View view){
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent, EditActivity.ADD);
    }
}