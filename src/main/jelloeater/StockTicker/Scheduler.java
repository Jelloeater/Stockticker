package jelloeater.StockTicker;
import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

	class Scheduler extends App{
	 
		private boolean killTask = false;  
		private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    
	
    public static void startTasks() {
    	Scheduler taskTimerThread = new Scheduler();
    	taskTimerThread.task();
    	Scheduler someTaskThread = new Scheduler();
    	someTaskThread.task2();
    	someTaskThread.shutdownScheduler();
		}
    
    public void task() {
        final Runnable taskToRun = new Runnable() {
        	public void run() { 
        		System.out.println("Party!"); //What code to run
        	}
        };
        
        ScheduledFuture<?> taskTimerHandle=scheduler.scheduleAtFixedRate(taskToRun, 0, 1, SECONDS);
        if (killTask==true) taskTimerHandle.cancel(true);
    }
    
    public void task2() {
        final Runnable taskToRun = new Runnable() {
        	public void run() { 
        		System.out.println("... all I wanna do is ..."); //What code to run
        	}
        };
        
        ScheduledFuture<?> taskTimerHandle=scheduler.scheduleAtFixedRate(taskToRun, 1, 2, SECONDS);
        if (killTask==true) taskTimerHandle.cancel(true);
    }
    public void shutdownScheduler(){
    	// Need to shutdown before quitting
    	final Runnable shutdownTaskScript = new Runnable() {
        	public void run() { 
        		scheduler.shutdown();
        		System.err.println("nightnight");
        	}
        };
        
        ScheduledFuture<?> taskTimerHandle=scheduler.scheduleAtFixedRate(shutdownTaskScript, 10, 1, SECONDS);
        if (killTask==true) taskTimerHandle.cancel(true);
    }
    
    public void setKillTask(boolean killTask) {
		this.killTask = killTask;
	}
    
    boolean isSetKillTaskEnabled() {
		return killTask;
	}
    
}