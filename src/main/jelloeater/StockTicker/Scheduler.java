package jelloeater.StockTicker;
import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

	/**
	 * Sets up threads and runs tasks
	 * @author Jesse
	 *
	 */
	class Scheduler extends App{

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        private static boolean killAllTasks = false;



        public void setKillTask(boolean killTask) {
		Scheduler.killAllTasks = killTask;
	}
    
    boolean isSetKillTaskEnabled() {
		return killAllTasks;
	}
    
    
    public void updateListTask() {
    	
        final Runnable taskToRun = new Runnable() {
        	public void run() {
        		TickerList.updateTickerList();
                // TODO Methods call for update goes here
        	}
        };
        
        scheduler.scheduleAtFixedRate(taskToRun, 0, 500, MILLISECONDS);
        // Good for only doing one set of actions per thread
    }


        public void task2() {
        final Runnable taskToRun = new Runnable() {
        	public void run() { 
        		System.out.println("... all I wanna do is ..."); //What code to run
        	}
        };
        
        ScheduledFuture<?> taskTimerHandle=scheduler.scheduleAtFixedRate(taskToRun, 1, 3, SECONDS);
        if (killAllTasks) taskTimerHandle.cancel(false);
        // Good for queuing multiple tasks in one thread that can be canceled on a task by task basis
    }
	
    
    public void shutdownThread(){
    	scheduler.shutdown();
    	if (debugMode) System.err.println("nightnight");
    }
        
    }

	
    
   
    
