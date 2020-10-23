package club.plus1.list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    List<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        Person person = new Person();
        person.setName("Иван Иванов");
        person.setPhone("+7987654321");
        person.setEmail("test@test.com");
        list.add(person);
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
                Person person = new Person();
                person.setName(data.getStringExtra(EditActivity.NAME));
                person.setPhone(data.getStringExtra(EditActivity.PHONE));
                person.setEmail(data.getStringExtra(EditActivity.EMAIL));
                list.add(person);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == EditActivity.EDIT){
                int positon = data.getIntExtra(EditActivity.POSITION, -1);
                if (positon == -1) return;
                Person person = list.get(positon);
                person.setName(data.getStringExtra(EditActivity.NAME));
                person.setPhone(data.getStringExtra(EditActivity.PHONE));
                person.setEmail(data.getStringExtra(EditActivity.EMAIL));
                list.set(positon, person);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void onAdd(View view){
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent, EditActivity.ADD);
    }
}