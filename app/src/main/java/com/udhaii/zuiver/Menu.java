package com.udhaii.zuiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Document;

public class Menu extends Activity {
    SwipeButton sb1, sb2, sb3, sb4;
    FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sb1 = findViewById(R.id.sb1);
        sb2 = findViewById(R.id.sb2);
        sb3 = findViewById(R.id.sb3);
        sb4 = findViewById(R.id.sb4);
        user = FirebaseAuth.getInstance();
        sb1.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }

        });

        sb2.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent i1 = new Intent(getApplicationContext(), Image.class);
                startActivity(i1);
            }
        });

        sb3.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent i2 = new Intent(getApplicationContext(), Doc.class);
                startActivity(i2);
            }
        });

        sb4.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent i3 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i3);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logouts:
                user = FirebaseAuth.getInstance();
                Toast.makeText(this, user.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                //Log.e("User",user.getCurrentUser().getEmail());
                user.signOut();
                startActivity(new Intent(Menu.this, Login.class));
                break;
            case R.id.exit:
                user.signOut();
                finish();
                System.exit(0);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

}
