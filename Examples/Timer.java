import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
 class Timer {
   
	 private final ScheduledExecutorService scheduler =
       Executors.newScheduledThreadPool(1);
    
    public static void main(String[] args) {
    	Timer taskTimer = new Timer();
    	taskTimer.task();
    	
	}
    public void task() {
        final Runnable taskToRun = new Runnable() {
                public void run() { 
                	System.out.println("Code to Run"); //What code to run
                	}
            };
        final ScheduledFuture<?> timerHandle =
            scheduler.scheduleAtFixedRate(taskToRun, 0, 2, SECONDS); // Initial delay, interval
        scheduler.schedule(new Runnable() {
                public void run() { timerHandle.cancel(true); }
            }, 60 * 60, SECONDS); // How long to run for
    }
 }