package family.track;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RegistreActivity extends AppCompatActivity {
    EditText e4;
    FirebaseAuth auth;
    String email;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        e4 =(EditText)findViewById(R.id.temail);
        auth= FirebaseAuth.getInstance();
    }
    public  void  goToPasswordActivity(View v){

        auth.fetchSignInMethodsForEmail(e4.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()){

                            boolean check =!task.getResult().getSignInMethods().isEmpty();
                            if (!check){
                                Intent intent = new Intent(RegistreActivity.this,PasswordActivity.class);
                                intent.putExtra("email",e4.getText().toString());
                                startActivity(intent);

                            }
                            else {

                                Toast.makeText(getApplicationContext(),"email alredy exst",Toast.LENGTH_LONG).show();

                            }
                            if (TextUtils.isEmpty("email")) {
                                Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                                return;
                            }

                        }

                    }
                });

        }

}
