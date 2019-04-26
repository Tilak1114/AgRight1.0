package com.example.agright;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {
    Button lgnbtn;
    EditText e1, e2;
    TextView bcktosnp;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lgnbtn = findViewById(R.id.btnlgn);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.pwd);
        bcktosnp = findViewById(R.id.bcksnp);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainScr.class));
        }
        lgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(e1.getText().toString().trim(), e2.getText().toString().trim());
            }
        });
        bcktosnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bsnp = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(bsnp);
            }
        });

    }
    private void validate(String user_email, String user_pwd){
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Authenticating. Please Wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(user_email, user_pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainScr.class));
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
