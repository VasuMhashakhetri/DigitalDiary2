package com.example.vasua.digital_diary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail,editTextPassword,editTextFname,editTextLname,editTextPhone;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextFname=(EditText)findViewById(R.id.editTextFname);
        editTextLname=(EditText)findViewById(R.id.editTextLname);
        editTextPhone=(EditText)findViewById(R.id.editTextPhone);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.buttonLogIn).setOnClickListener(this);


    }

    private void registerUser(){

        final String password = editTextPassword.getText().toString().trim();
        final String fname=editTextFname.getText().toString().trim();
        final String lname=editTextLname.getText().toString().trim();
        final String email=editTextEmail.getText().toString().trim();
        final String phone=editTextPhone.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Enter a valid e-mail");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Email is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            editTextPhone.setError("Email is required");
            editTextPhone.requestFocus();
            return;
        }

        if(phone.length()<10 || phone.length()>10){
            editTextPhone.setError("Invalid Phone Number");
            editTextPhone.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();

                  String user_id=mAuth.getCurrentUser().getUid();
                  DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("userInfo").child(user_id);

                  Map newPost =new HashMap();

                  newPost.put("f_name",fname);
                  newPost.put("l_name",lname);
                  newPost.put("phone",phone);
                  newPost.put("email",email);
                  newPost.put("password",password);

                  current_user_db.setValue(newPost);

              }
              else{
                  if(task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(),"E-mail already in use.",Toast.LENGTH_SHORT).show();
                  }
                  else
                      Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
              }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonSignUp:
                registerUser();

                break;

            case R.id.buttonLogIn:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
