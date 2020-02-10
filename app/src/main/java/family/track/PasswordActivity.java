package family.track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
String email;
EditText e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        e3 =(EditText)findViewById(R.id.editTextp);

        Intent intent =getIntent();
        if (intent!=null){
            email =intent.getStringExtra("email");
        }
     }
     public void gotoNameActivity(View v){
        if (e3.getText().toString().length()>6){
            Intent intent = new Intent(PasswordActivity.this,NameActivity.class);
            intent.putExtra("emmail",email);
            intent.putExtra("passwors",e3.getText().toString());
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"password must be more that 6",Toast.LENGTH_LONG).show();
        }
     }
}
