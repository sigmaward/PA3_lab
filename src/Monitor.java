/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	//adding variable if philosopher is talking
	private static boolean isTalking = false;
	public  static int[] chopsticks;


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		chopsticks = new int[piNumberOfPhilosophers];

		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = 1;
//			System.out.println(chopsticks[i]);
		}

	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{

		//check if chopsticks are both available (hint: use modulo)
		//if available turn the element of the array 0
		//else wait();

		int leftChopstick = piTID-1;
		int rightChopstick = (piTID) % chopsticks.length;

		//try pick up both chopsticks
		while (true) {
			//check if both chopsticks are available
			if (chopsticks[leftChopstick] == 1 && chopsticks[rightChopstick] == 1) {
				//pick up chopsticks (turning 1 into 0)
				chopsticks[leftChopstick] = 0;
				chopsticks[rightChopstick] = 0;
				break; //exit the loop once chopsticks are picked up
			} else {
				try {
					wait(); //if chopsticks are not available
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Philosopher " + piTID + " was interrupted.");
				}
			}
		}
	}


	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		//put down the chopsticks by turning the 0 back to 1

		int leftChopstick = piTID-1;
		int rightChopstick = (piTID) % chopsticks.length;
		chopsticks[leftChopstick] = 1;
		chopsticks[rightChopstick] = 1;
		notifyAll();

	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		while (isTalking) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}

		isTalking = true;

	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// ...
		isTalking = false;

		notifyAll();

	}
}

// EOF
