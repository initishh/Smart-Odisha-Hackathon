package com.example.initish.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.initish.testapp.employer.EmployerLogin;
import com.example.initish.testapp.employer.emp_home;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1;
    TextView registeremp;
    GoogleApiClient mGoogleApiClient;
    private EditText emai,passwor;
    ProgressBar progressBarLogin;
    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    Button button,register;
    FirebaseAuth.AuthStateListener mAuthListener;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emai=(EditText) findViewById(R.id.email_login);
        passwor=(EditText) findViewById(R.id.password_login);
        registeremp=findViewById(R.id.registeremp);
        button=findViewById(R.id.button);
        register=findViewById(R.id.register);

        sharedPreferences=this.getSharedPreferences("com.example.initish.testapp",Context.MODE_PRIVATE);


        progressBarLogin=findViewById(R.id.progressBarLogin);
        SignInButton signInButton = findViewById(R.id.signin_button);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            if(sharedPreferences.getInt("activity",0)==1)
             startActivity(new Intent(getApplicationContext(), Discussion.class));
           else
               startActivity(new Intent(getApplicationContext(),emp_home.class));
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

        registeremp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EmployerLogin.class));
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

    }




    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_button:
                signIn();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                sharedPreferences.edit().putInt("activity",1).apply();
                startActivity(new Intent(getApplicationContext(), Discussion.class));
            } catch (ApiException e) {

                Log.w("Message", "Google sign in failed", e);
                Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d("info", "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("info", "signInWithCredential:success");
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), Discussion.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("info", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(getApplicationContext(),Discussion.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Message", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



   public void login(View view){

        String email=emai.getText().toString();
        String password=passwor.getText().toString();

        button.setVisibility(View.INVISIBLE);
        progressBarLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {

                           Log.d("Message", "signInWithEmail:success");
                           FirebaseUser user = mAuth.getCurrentUser();
                           Intent intent=new Intent(getApplicationContext(),Discussion.class);
                           startActivity(intent);
                       } else {
                           progressBarLogin.setVisibility(View.INVISIBLE);
                           button.setVisibility(View.VISIBLE);
                           Log.w("Message", "signInWithEmail:failure", task.getException());
                           Toast.makeText(MainActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                       }
                   }
               });
   }

}
