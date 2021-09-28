package com.example.myapplicationnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView createnewAccount;

    EditText inputEmail, inputPassword;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        createnewAccount = findViewById (R.id.createNewAccount);

        inputEmail = findViewById (R.id.inputEmail);
        inputPassword = findViewById (R.id.inputPassword);
        btnLogin = findViewById (R.id.btnLogin);
        progressDialog = new ProgressDialog (this);
        mAuth = FirebaseAuth.getInstance ();
        mUser = mAuth.getCurrentUser ();


        createnewAccount.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (MainActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                performLogin ();
            }
        });
    }

    private void performLogin() {
        String email = inputEmail.getText ().toString ();
        String password = inputPassword.getText ().toString ();


        if (!email.matches (emailPattern)) {
            inputEmail.setError ("Enter Correct Email");
            inputEmail.requestFocus ();
        } else if (password.isEmpty () || password.length () < 7) {
            inputPassword.setError ("Enter Proper Password");
        } else {
            progressDialog.setMessage ("Please Wait While Login...");
            progressDialog.setTitle ("Registration");
            progressDialog.setCanceledOnTouchOutside (false);
            progressDialog.show ();
            mAuth.signInWithEmailAndPassword (email,password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful ())
                    {
                        progressDialog.dismiss ();
                        sendUserToNextActivity();
                        Toast.makeText (MainActivity.this,"Login Successful",Toast.LENGTH_LONG);
                    }
                    else
                    {
                        progressDialog.dismiss ();
                        Toast.makeText (MainActivity.this,""+task.getException (),Toast.LENGTH_LONG);
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent (MainActivity.this, NewActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity (intent);
    }
}
