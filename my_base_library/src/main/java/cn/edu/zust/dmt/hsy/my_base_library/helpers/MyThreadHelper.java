package cn.edu.zust.dmt.hsy.my_base_library.helpers;

import androidx.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/24/2020 15:19
 **/
public enum MyThreadHelper {
    /**
     * @description instance holder for singleton
     */
    INSTANCE;

    private final ThreadPoolExecutor mThreadPoolExecutor = initializeMyThreadPool();

    /**
     * @return initializer of {@link #mThreadPoolExecutor}
     */
    private ThreadPoolExecutor initializeMyThreadPool() {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        final int myThreadNumberLimit = Runtime.getRuntime().availableProcessors() + 1;
        return new ThreadPoolExecutor(1, myThreadNumberLimit, 30L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), threadFactory);
    }

    /**
     * @param task a new runnable task which supposed to run by individual thread
     */
    public void runMyTask(@NonNull final Runnable task) {
        mThreadPoolExecutor.execute(task);
    }
}
