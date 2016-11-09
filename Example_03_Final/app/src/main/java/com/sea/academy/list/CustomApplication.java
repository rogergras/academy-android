package com.sea.academy.list;

import android.app.Application;

public class CustomApplication extends Application {

    private Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = new Injector(this);
    }

}
