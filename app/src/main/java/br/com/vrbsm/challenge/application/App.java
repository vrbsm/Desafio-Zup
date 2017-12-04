package br.com.vrbsm.challenge.application;

import android.app.Application;

/**
 * Created by vmascare on 30/11/17.
 */

public class App extends Application {

    private static App sInstance;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
