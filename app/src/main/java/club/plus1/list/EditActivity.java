package club.plus1.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final String POSITION = "POSITION";
    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";

    EditText edit_name, edit_phone, edit_email;
    Intent result;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_email = findViewById(R.id.edit_email);
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
        }
    }

    public void onSave(View view){
        result.putExtra(POSITION, position);
        result.putExtra(NAME, edit_name.getText().toString());
        result.putExtra(PHONE, edit_phone.getText().toString());
        result.putExtra(EMAIL, edit_email.getText().toString());
        setResult(RESULT_OK, result);
        finish();
    }
}