package com.udhaii.zuiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends Activity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    public EditText name, age;
    public RadioGroup gender;
    public RadioButton Sgender;
    public Button ok, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.Uname);
        age = findViewById(R.id.Age);
        gender = findViewById(R.id.radioGender);
        ok = findViewById(R.id.OkButton);
        back = findViewById(R.id.BackButton);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getInstance().getReference("users");
    }


    public void add(View view) {
        String Fname = name.getText().toString();
        String Fage = age.getText().toString();
        int RadioID = gender.getCheckedRadioButtonId();
        Sgender = findViewById(RadioID);
        String Fgender = Sgender.getText().toString();
        if (TextUtils.isEmpty(Fname) || TextUtils.isEmpty(Fage)) {
            Toast.makeText(getApplicationContext(), "Enter all Fields", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                Intent firebaseUserIntent = new Intent(Profile.this, Login.class);
                startActivity(firebaseUserIntent);
                finish();
            } else {
                String userId = user.getProviderId();
                String id = user.getUid();
                String profileEmail = user.getEmail();
                FirebaseUserEntity userEntity = new FirebaseUserEntity(id, profileEmail, Fname, Fage, Fgender);
                myRef.child(id).setValue(userEntity);
                Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                name.setText("");
                age.setText("");
            }

        }
    }
}
