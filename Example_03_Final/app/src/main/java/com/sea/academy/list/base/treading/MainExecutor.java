package com.sea.academy.list.base.treading;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class MainExecutor implements Executor {

    private final Handler mainHandler;

    /**
     * Builder.
     */
    public MainExecutor() {
        mainHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable task) {
        synchronized (mainHandler) {
            mainHandler.post(task);
        }
    }

}
