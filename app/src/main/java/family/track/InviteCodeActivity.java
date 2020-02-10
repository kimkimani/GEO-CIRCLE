package family.track;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InviteCodeActivity extends AppCompatActivity {
    String email,password,date,code,isSharing,name;
    Uri resultUri;
    TextView textcode;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    ProgressDialog progressDialog;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        textcode = (TextView)findViewById(R.id.invitecode);
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        auth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        Intent intent = getIntent();
        if (intent!=null){
                name=intent.getStringExtra("name");
                email= intent.getStringExtra("email");
                password= intent.getStringExtra("password");
                code= intent.getStringExtra("code");
                resultUri=intent.getParcelableExtra("imageUrl");
                isSharing= intent.getStringExtra("isSharing");

            }
        textcode.setText(code);
    }
    public  void  regesterUser(View v)
    {
        progressDialog.setMessage("Regestering .... please wait");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            CreateUser createUser= new CreateUser(
                               name,email,password,code,"false","na","na","na");
                            user=auth.getCurrentUser();
                            userid=user.getUid();


                            reference.child(userid).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(),
                                                        "user regestered succesful",Toast.LENGTH_LONG).show();

                                            }
                                            else {
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(),
                                                        "user not regesterd",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }

                    }
                });

    }
}
