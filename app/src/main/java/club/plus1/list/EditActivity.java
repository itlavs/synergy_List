package club.plus1.list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class EditActivity extends AppCompatActivity {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final int SELECT = 3;
    public static final String POSITION = "POSITION";
    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String PHOTO = "PHOTO";

    ImageView edit_photo;
    EditText edit_name, edit_phone, edit_email;
    Intent result;
    int position;
    Uri photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_email = findViewById(R.id.edit_email);
        edit_photo = findViewById(R.id.edit_photo);
        result = new Intent();
        setResult(RESULT_CANCELED, result);
        Intent intent = getIntent();
        if (intent.getExtras() == null){
            position = -1;
        } else {
            position = intent.getIntExtra(POSITION, -1);
            edit_name.setText(intent.getStringExtra(NAME));
            edit_phone.setText(intent.getStringExtra(PHONE));
            edit_email.setText(intent.getStringExtra(EMAIL));
            if (intent.getStringExtra(PHOTO) != null){
                Glide.with(this).load(Uri.parse(intent.getStringExtra(PHOTO))).into(edit_photo);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == SELECT) {
                photo = data.getData();
                Glide.with(this).load(photo).into(edit_photo);
            }
        }
    }

    public void onSave(View view){
        result.putExtra(POSITION, position);
        result.putExtra(NAME, edit_name.getText().toString());
        result.putExtra(PHONE, edit_phone.getText().toString());
        result.putExtra(EMAIL, edit_email.getText().toString());
        result.putExtra(PHOTO, photo.toString());
        setResult(RESULT_OK, result);
        finish();
    }

    public void onSelect(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT);
    }
}