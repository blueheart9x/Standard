package com.elcom.background.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InterviewThreadPool implements ServletContextListener {

    private ExecutorService executorService;
    private static final int NUM_OF_THREAD = 1;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("InterviewThreadPool init....");
        executorService = Executors.newFixedThreadPool(NUM_OF_THREAD);
        //Runnable r1 = new WatchFileThread();
        //executorService.submit(r1);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
