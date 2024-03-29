package com.application.solodroid.attendancemonitor;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.full_name)
    EditText fullName;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_confirm)
    EditText passwordConfirm;
    @BindView(R.id.sign_up_button)
    Button signUpButton;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.reset_button)
    Button resetButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);//using butterknife fot finding widgets
        //click R.layout.activity_signup press alt + enter to generate

        //firebase authentication instance
        firebaseAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupActivity.this.startActivity(new Intent(SignupActivity.this, ResetActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupActivity.this.finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupActivity.this.registerUser();

                /*EditText editText = (EditText)findViewById(R.id.full_name);
                String text = editText.getText().toString();

                Intent myIntent = new Intent(view.getContext(), ReportActivity.class);
                myIntent.putExtra("", text);
                startActivity(myIntent);*/
            }
        });

    }


    private void registerUser() {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String userFullName = fullName.getText().toString().trim();
        String userPasswordConfirm = passwordConfirm.getText().toString().trim();

        if (TextUtils.isEmpty((userFullName))) {
            showToast("Please enter your full name!");
            return;
        }

        if (TextUtils.isEmpty(userEmail)) {
            showToast("Enter email address!");
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            showToast("Enter Password!");
            return;
        }

        if(userPassword.length() < 6){
            showToast("Password too short, enter minimum 6 characters");
            return;
        }

        if (TextUtils.isEmpty(userPasswordConfirm)){
            showToast("Please confirm your password before proceeding!");
        }

        if(!userPassword.equals(userPasswordConfirm)){
            showToast("Password not match, re-enter and confirm your password");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //register user
        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "New user registration: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            SignupActivity.this.showToast("Authentication failed. " + task.getException());
                        } else {
                            SignupActivity.this.startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            SignupActivity.this.finish();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

}
