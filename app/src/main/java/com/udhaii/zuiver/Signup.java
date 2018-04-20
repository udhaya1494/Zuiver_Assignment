package com.udhaii.zuiver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends Activity {
    EditText et1, et2, et3, et4;
    Button b1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et1 = findViewById(R.id.Uname);
        et2 = findViewById(R.id.Uemail);
        et3 = findViewById(R.id.Upass);
        et4 = findViewById(R.id.Ucpass);
        b1 = findViewById(R.id.Rbutton);
        auth = FirebaseAuth.getInstance();
    }

    public void reg(View view) {
        String text2, text3, text4;
        text2 = et2.getText().toString();
        text3 = et3.getText().toString();
        text4 = et4.getText().toString();
        final ProgressDialog PD = ProgressDialog.show(this, "Please Wait", "Loading", true);
        if (text2.length() <= 1) {
            Log.d("Validate", text2.toString());
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
            PD.dismiss();
        } else {
            if (text3.length() >= 6 && text4.length() >= 6) {
                (auth.createUserWithEmailAndPassword(text2, text4)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        PD.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(getApplicationContext(), Login.class);
                            startActivity(login);
                        } else {
                            Log.e("Firebase Exception", task.getException().toString());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                PD.dismiss();
                Toast.makeText(getApplicationContext(), "Passwords Mismatched/Less than 6 Letters", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

