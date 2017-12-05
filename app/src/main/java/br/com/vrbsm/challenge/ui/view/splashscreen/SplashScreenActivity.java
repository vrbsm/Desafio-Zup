package br.com.vrbsm.challenge.ui.view.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.home.HomeActivity;

/**
 * Created by vmascare on 29/11/17.
 */

public class SplashScreenActivity extends AbstractActivity {

    private final int SPLASH_TIME_LENGTH = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_LENGTH);
    }
}
