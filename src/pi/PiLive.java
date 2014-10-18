package pi;

import util.UnimplementedExercise;

/**
 * Parallel implementation of {@link PiApproximation} that also reports the
 * approximation so far using the {@link #liveValue()} method.
 * 
 * The liveValue() method can be invoked at any time *during* the computation to
 * get the latest approximation.
 * 
 */

/*
   	The goal of this exercise is to show how easy threads can communicate but
	also how easy it is to introduce concurrency bugs.

	Steps:

 1. We start from the threads version of this exercise. Copy either your or
	the given threads solution to the class below. Merge as necessary.

 2. Gather approximate results from the threads in a shared data structure.
 	Make sure to keep the live value of the number of inside points and the number of
 	outside points. Make it possible for other concurrent threads to inspect the most 
 	up to date value by calling the liveValue() method.

 3. Print out the live of Pi 100ms second after starting the threads, and
	before the join()s. It will be printed three times due to the warm-up.
*/

public class PiLive implements PiApproximation, LiveValue, UnimplementedExercise {

	@Override
	public double computePi(long iterations) {
		// TODO: implement
		return 100.0;
	}

	public double liveValue() {
		// TODO: implement
		return 100.0;
	}
}
