package com.udhaii.zuiver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {
    EditText et1, et2;
    Button b1;
    FirebaseAuth login;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1 = findViewById(R.id.Lemail);
        et2 = findViewById(R.id.Lpass);
        b1 = findViewById(R.id.Lbutton);
        t1 = findViewById(R.id.signup);
        login = FirebaseAuth.getInstance();
    }

    public void log(View view) {
        String text1, text2;
        text1 = et1.getText().toString();
        text2 = et2.getText().toString();
        final ProgressDialog Pl = ProgressDialog.show(this, "Login", "Connecting...", true);
        if (text1.isEmpty() && text2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Email/Password", Toast.LENGTH_SHORT).show();
            Pl.dismiss();
        } else {
            login.signInWithEmailAndPassword(text1, text2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Pl.dismiss();
                    if (task.isSuccessful()) {
                        Intent l = new Intent(getApplicationContext(), Menu.class);
                        startActivity(l);
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Pl.dismiss();
                    }
                }
            });
        }
    }

    public void signup(View view) {
        Intent signup = new Intent(getApplicationContext(), Signup.class);
        startActivity(signup);
    }
}
