package com.application.solodroid.attendancemonitor;


import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        finish();*/
        Intent mIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(mIntent);
    }
}

/*
public class SplashScreenActivity extends Activity {

    public final int SPLASH_DISPLAY_LENGTH = 1000;
    */
/*
    ** Called when the activity is first created
     *//*

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);

        */
/*
        **New Handler to start the Menu Activity
        * and close this Splash Screen after some seconds
         *//*

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                */
/*
                **create an Intent that will start the Menu Activity
                 *//*

                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
*/
