package com.sppcco.core.util.app;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;

/**
 * Executor that runs a task on a new background thread.
 */
public class DiskIOThreadExecutor implements Executor {

    private final Executor mDiskIO;

    DiskIOThreadExecutor() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
