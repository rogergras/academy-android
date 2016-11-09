package com.sea.academy.list.base.treading;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BackgroundExecutor implements Executor {

    /**
     * The number of threads to keep in the pool, even if they are idle.
     * By default the available processors plus two.
     */
    private static final int DEFAULT_CORE_POOL_SIZE =
            Runtime.getRuntime().availableProcessors() * 2;

    /**
     * When the number of threads is greater than the core, this is the maximum time
     * that excess idle threads will wait for new tasks before terminating.
     */
    private static final int KEEP_ALIVE = 1;

    /**
     * Manage termination and tracking progress of one or more tasks.
     */
    private final Executor executor;

    /**
     * Builder.
     * Initializes the thread pool with the DEFAULT_CORE_POOL_SIZE.
     */
    public BackgroundExecutor() {

        // Create and setAnimationDelegate the subscribeExecutor with a fixed
        // thread pool and an unbounded blocking queue
        executor = new ThreadPoolExecutor(
                DEFAULT_CORE_POOL_SIZE,
                DEFAULT_CORE_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    /**
     * Executes a task.
     * @param task The task to execute. Do nothing if is null.
     */
    @Override
    public void execute(Runnable task) {

        if (task != null) {
            executor.execute(task);
        }

    }

}