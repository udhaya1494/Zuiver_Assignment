package com.udhaii.zuiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    Intent s = new Intent(Splash.this, Login.class);
                    startActivity(s);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();
    }
}
