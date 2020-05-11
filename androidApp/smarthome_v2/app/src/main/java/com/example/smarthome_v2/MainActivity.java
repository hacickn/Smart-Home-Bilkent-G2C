package com.example.smarthome_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText loginPassword;
    private EditText loginEmail;
    private Button loginButton;
    private TextView loginToregister;
    private ProgressDialog loginProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPassword = (EditText) findViewById(R.id.password);
        loginEmail = (EditText) findViewById(R.id.login_email);
        loginButton = (Button) findViewById(R.id.login);
        loginToregister = (TextView) findViewById(R.id.login_need_account);
        loginProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        loginToregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                    loginProgress.setTitle("Logging In");
                    loginProgress.setMessage("Login to your account, please wait");
                    loginProgress.setCanceledOnTouchOutside(false);
                    loginProgress.show();
                    loginUser(email, password);

                }
            }
        });
    }


    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    loginProgress.dismiss();
                    Toast.makeText(getApplicationContext(), "Login is succesfull", Toast.LENGTH_SHORT);
                    Intent mainIntent = new Intent(MainActivity.this, main_screen.class);
                    startActivity(mainIntent);

                } else {
                    loginProgress.dismiss();
                    Toast.makeText(getApplicationContext(), "Login is unsuccesfull" + task.getException().getMessage(), Toast.LENGTH_SHORT);
                }
            }
        });
    }
}

