package family.track;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class NameActivity extends AppCompatActivity {
String email,password;
EditText et_name;
ImageView cView;
Uri resultUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        et_name=(EditText)findViewById(R.id.editTextname);
        cView=(ImageView) findViewById(R.id.profile_image);
        Intent intent = getIntent();
        if (intent!=null){
            email =intent.getStringExtra("email");
            password =intent.getStringExtra("password");


        }
    }
    public void  generateCode(View v){
        Date mydate=new Date();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a",Locale.getDefault());
        String date= format.format(mydate);
        Random r = new Random();
        int n = 100000 + r.nextInt(900000);
        String  code =String.valueOf(n);
        if (resultUri!=null)
        {
            Intent intent = new Intent(NameActivity.this,InviteCodeActivity.class);
            intent.putExtra("name",et_name.getText().toString());
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            intent.putExtra("date",date);
            intent.putExtra("code",code);
            intent.putExtra("imageUrl",resultUri);
            intent.putExtra("isSharing","false");
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"choose image",Toast.LENGTH_LONG).show();

        }

    }
    public  void  selectimage (View v){
        Intent i =new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,12);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== 12 && resultCode== RESULT_OK && data!=null)
            CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1,1)
            .start(this);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                cView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


}
