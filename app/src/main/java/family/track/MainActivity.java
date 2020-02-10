package family.track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToLogin(View v){
        Intent intent = new Intent(MainActivity.this,loginActivity.class
        );
        startActivity(intent);
    }
    public void goToRegester(View v){
        Intent intent = new Intent(MainActivity.this,RegistreActivity.class
        );
        startActivity(intent);
    }

}

