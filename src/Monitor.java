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
	private static boolean isEating = false;

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers

		int chopsticks = DiningPhilosophers.DEFAULT_NUMBER_OF_PHILOSOPHERS;
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
		if (!isEating) {
			isEating = true;
//			Philosopher.talk();
//			notifyAll();

		} else {
			System.out.println("A philosopher is already eating.");
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		// ...
		isEating = false;
		notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		if (!isTalking) {
			isTalking = true;
//			Philosopher.talk();
//			notifyAll();

		} else {
			System.out.println("A philosopher is already talking.");
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

//		isTalking = true;

	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// ...
		isTalking = false;
//		try {
//			wait(); //in try catch
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
		notifyAll();

	}
}

// EOF
