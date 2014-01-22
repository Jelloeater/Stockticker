package jelloeater.StockTicker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Sets up threads and runs tasks
 * @author Jesse
 *
 */
class Scheduler extends TickerWindow {

	private final ScheduledExecutorService schedulerController =
					Executors.newScheduledThreadPool(1);
		//Anything assigned to schedulerController gets run when the class is initialized
	private static boolean updateListCancel = false;


	public void setKillTask(boolean killTask) {
		Scheduler.updateListCancel = killTask;
	}

	boolean isSetKillTaskEnabled() {
			return updateListCancel;
	}


	public void updateListTask() {
		final Runnable taskToRun = new Runnable() {
			public void run() {
				//TickerList.updateTickerList(); // Call update
				//TickerList.updateIndexInfo(); // What it says on the tin
				// FIXME update index along with arrayList


				TickerWindow.updateGuiWindowText();

				if (debugMode >= 1) System.err.print("party!");
			}
		};

//		schedulerController.scheduleAtFixedRate(taskToRun, 0,
//				settingsProperties.getRefreshIntervalSeconds(), SECONDS);
		schedulerController.scheduleAtFixedRate(taskToRun, 0,3, SECONDS);

		// Good for only doing one set of actions per thread
	}


	public void task2() {
	final Runnable taskToRun = new Runnable() {
		public void run() {
			System.out.println("... all I wanna do is ..."); //What code to run
		}
	};

	ScheduledFuture<?> taskTimerHandle= schedulerController.scheduleAtFixedRate(taskToRun, 1, 3, SECONDS);
		if (updateListCancel) taskTimerHandle.cancel(false);
		// Good for queuing multiple tasks in one thread that can be canceled on a task by task basis
	}


    public void shutdownThread(){
		try {
			Thread.sleep(10000); // Wait 10 seconds
			//TODO Change me to 2 seconds when done testing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		schedulerController.shutdown();
	    if (debugMode >= 1) System.err.println("shutdownThread");
    }

    }

	
    
   
    
