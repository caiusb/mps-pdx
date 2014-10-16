package pi;

import util.UnimplementedExercise;

/**
 * Parallel implementation of {@link PiSequential} using {@link Thread}
 */

/*
	The goal is to split the approximation work between multiple threads, each
	computing only a portion of the total number of iterations.

	A straightforward approach is to create an inner class that extends Thread,
	move the computation to that thread, and then merge the results from multiple
	threads.

	Detailed steps:

 1. Transfer the algorithm from computePi() to the new run() method of the
	PiApproximationThread class already created below 
 2. Store the result in a field of the inner class and make a getter for that field 
 3. In computePi(), create an array of p PiApproximationThread threads, where p is 
 	equal to the number of processors
 4. Populate the array with PiApproximationThreads 
 5. Iterate the array and start() all threads 
 6. Iterate the array (different "for" loop) and join() all threads. 7. Adapt the 
 	algorithm in PiApproximationThread to only compute its proportional part of the workload
 8. Iterate the array, average the results, and return the final value. 
 9. Remove UnimplementedExercise from the list of interfaces 10. Test your implementation by 
 	running the PiTest JUnit test.
 */

public class PiThreads implements PiApproximation, UnimplementedExercise {

	@Override
	public double computePi(long iterations) {
		long inside = 0;
		for (int j = 0; j < iterations; j++) {
			double x = Math.random();
			double y = Math.random();
			double lenght = x * x + y * y;
			if (lenght < 1.0)
				inside++;
		}
		return ((double) inside) / iterations * 4;
	}

	class PiApproximationThread extends Thread {
		@Override
		public void run() {
		}
	}
}
