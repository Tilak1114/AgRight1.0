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

public class SignupActivity extends AppCompatActivity {
    private EditText se1, se2, se3, se4;
    private Button signupbtn;
    private TextView backtologin;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        se1 = findViewById(R.id.etName);
        se2 = findViewById(R.id.etEmail);
        se3 = findViewById(R.id.etPass);
        se4 = findViewById(R.id.etCnfrmpwd);
        backtologin = findViewById(R.id.algn);
        signupbtn = findViewById(R.id.registerBtn);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Registration in progress");
                progressDialog.show();
                if(validate()){
                    String email = se2.getText().toString().trim();
                    String pwd = se3.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent redir = new Intent(getApplicationContext(), MainScr.class);
                                startActivity(redir);
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bcklgn = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(bcklgn);
            }
        });
    }
    private boolean validate(){
        Boolean res = false;

        /*String name = se1.getText().toString();
        String email = se2.getText().toString();
        String pwd = se3.getText().toString();
        String cnfrmpwd = se4.getText().toString();*/

        if(se1.getText().toString().isEmpty()||se2.getText().toString().isEmpty()||se3.getText().toString().isEmpty()||se4.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Incomplete fields!", Toast.LENGTH_SHORT).show();
        }
        /*else if(se3.getText().toString() != se4.getText().toString()){
            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
        }*/
        else{
            res = true;
        }
        return res;
    }
}
