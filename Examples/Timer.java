import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

	class Timer {
	 
		public static boolean killTasks = false;  
		private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
		
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
            
        final Runnable shutdownTask = new Runnable() {
			public void run() {
				scheduler.shutdown();
			}
		};
        
        ScheduledFuture<?> taskTimerHandle=scheduler.scheduleAtFixedRate(taskToRun, 0, 2, SECONDS);
        if (killTasks==true) taskTimerHandle.cancel(true);
        
        scheduler.scheduleAtFixedRate(shutdownTask, 10, 1, SECONDS);  
    }
    
    public void shutdownScheduler(){
    	// Need to shutdown before quitting
    	scheduler.shutdown();
    }
}