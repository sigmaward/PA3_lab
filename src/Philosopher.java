import common.BaseThread;

/**
 * Class Philosopher.
 * Outlines main subrutines of our virtual philosopher.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Philosopher extends BaseThread
{


	/**
	 * Max time an action can take (in milliseconds)
	 */
	public static final long TIME_TO_WASTE = 1000;

	/**
	 * The act of eating.
	 * - Print the fact that a given phil (their TID) has started eating.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are done eating.
	 */
	//yield method is called randomYield() in BaseThread
	public void eat()
	{
		try
		{
			// ...
			//print phil TID
			System.out.println("This philosopher has started eating: " + getTID());

			//yield
			randomYield();
			sleep((long)(Math.random() * TIME_TO_WASTE));
			// ...
			randomYield();
			System.out.println("The philosopher " + getTID() + " is done eating.");
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of thinking.
	 * - Print the fact that a given phil (their TID) has started thinking.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are done thinking.
	 */
	public void think()
	{
		// ...
		try
		{
			// ...
			//print phil TID
			System.out.println("This philosopher has started thinking: " + getTID());

			//yield
			randomYield();
			sleep((long)(Math.random() * TIME_TO_WASTE));
			// ...
			randomYield();
			System.out.println("The philosopher " + getTID() + " is done thinking.");
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.think():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}


	}

	/**
	 * The act of talking.
	 * - Print the fact that a given phil (their TID) has started talking.
	 * - yield
	 * - Say something brilliant at random
	 * - yield
	 * - The print that they are done talking.
	 */
	public void talk()
	{
		// ...

//		saySomething();

		// ...
		//print phil TID
		System.out.println("This philosopher has started talking: " + getTID());

		//yield
		randomYield();
		saySomething();//sleep((long)(Math.random() * TIME_TO_WASTE));
		// ...
		randomYield();
		System.out.println("The philosopher " + getTID() + " is done talking.");

	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run()
	{
		for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
		{
			DiningPhilosophers.soMonitor.pickUp(getTID());

			eat();

			DiningPhilosophers.soMonitor.putDown(getTID());

//			think();

			/*
			 * TODO:
			 * A decision is made at random whether this particular
			 * philosopher is about to say something terribly useful.
			 */
//			if(Math.random() > 0.5)
//			{
//				// Some monitor ops down here...
//				DiningPhilosophers.soMonitor.requestTalk();
//
//				talk();
//				// ...
//
//				DiningPhilosophers.soMonitor.endTalk();
//			}



			randomYield(); //yield(); //I guess you gotta edit it to Thread.yield(); no!!!!! randomYield();
		}
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 * Feel free to add your own phrases.
	 */
	public void saySomething()
	{
		String[] astrPhrases =
		{
			"Eh, it's not easy to be a philosopher: eat, think, talk, eat...",
			"You know, true is false and false is true if you think of it",
			"2 + 2 = 5 for extremely large values of 2...",
			"If thee cannot speak, thee must be silent",
			"My number is " + getTID() + ""
		};

		System.out.println
		(
			"Philosopher " + getTID() + " says: " +
			astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
}

// EOF
