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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerName;
    private EditText registerPassword;
    private EditText registerEmail;
    private TextView registerToLogin;
    private Button registerButton;
    private ProgressDialog registerProgress;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerButton = (Button) findViewById(R.id.register_button);
        registerName = (EditText) findViewById(R.id.register_name);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerToLogin = (TextView) findViewById(R.id.register_to_login);
        registerProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        registerToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = registerName.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                String email = registerEmail.getText().toString().trim();

                if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(email)) {

                    registerProgress.setTitle("Saving");
                    registerProgress.setMessage("Your account is created, please wait");
                    registerProgress.setCanceledOnTouchOutside(false);
                    registerProgress.show();

                    register_user(name, password, email);
                }

            }
        });
    }

    private void register_user(final String name, final String password, final String email) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    String user_id = mAuth.getCurrentUser().getUid();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("email", email);
                    userMap.put("password", password);
                    userMap.put("aquarium", "off");
                    userMap.put("gas", "off");
                    userMap.put("water", "off");
                    userMap.put("gardenLight", "off");
                    userMap.put("greenHouse", "off");
                    userMap.put("electricity", "off");

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {

                            if(task.isSuccessful())
                            {
                                registerProgress.dismiss();
                                Intent mainIntent = new Intent(RegisterActivity.this, main_screen.class);
                                startActivity(mainIntent);
                            }
                        }
                    });

                }
                else {
                    registerProgress.dismiss();
                    Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

