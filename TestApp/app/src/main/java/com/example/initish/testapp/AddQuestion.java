package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddQuestion extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String DESC = "description";
    public static final String TAG = "Title";
    EditText title,description;
    ProgressBar loading;
    Context context;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference mDocref=db.collection("posts").document();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_added);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        title=findViewById(R.id.jobtitle);
        description=findViewById(R.id.description);
        loading=findViewById(R.id.loading);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {

            loading.setVisibility(View.VISIBLE);

            String titleText=title.getText().toString();
            String descText=description.getText().toString();


            if(titleText.isEmpty()){
                return false;
            }

            String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
            String photoUrl=FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();

            String UserName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            Item item1=new Item();
            item1.setQuery(titleText);
            if(photoUrl!=null)
             item1.setPhoto_url(photoUrl);
            else
                item1.setPhoto_url("https://banner2.kisspng.com/20180828/sxw/kisspng-clip-art-computer-icons-user-download-chamber-of-d-talonpaw-svg-png-icon-free-download-175238-on-5b84c95a116717.2809616615354289540713.jpg");

            item1.setuID(userId);
            item1.setDesc(descText);
            item1.setTitle_id(mDocref.getId());
            item1.setUsername(UserName);

            mDocref.set(item1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    DocumentReference documentReference=db.collection("posts").document(mDocref.getId())
                            .collection("comments").document();

                    documentReference.set(new Comment());
                    if(task.isSuccessful()){

                         loading.setVisibility(View.INVISIBLE);

                         Intent intent=new Intent(AddQuestion.this,Main2Activity.class);
                         intent.putExtra("selected_index",2);
                         startActivity(intent);
                         Toast.makeText(getApplicationContext(),"Question has been posted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Problem here!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
