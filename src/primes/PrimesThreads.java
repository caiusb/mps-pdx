package primes;

import util.UnimplementedExercise;

/*
 The goal is to parallelize the sequential implementation using threads.

 In a manner similar to the previous exercise, split the computation of 
 the primes between multiple threads.

 Steps:

 1. Create an inner class that extends Thread
 2. Transfer the algorithm to the inner class's run()
 3. Adapt the thread to only compute its proportional part of the workload
 	- hint: for simplicity, don't create a boolean array in each thread;
 	simply pass the shared outer one
 4. Remove the UnimplementedExercise interface
 5. Test using the PrimesTest JUnit test.
 */

public class PrimesThreads extends PrimesComputation implements
		UnimplementedExercise {
	@Override
	public Boolean[] computePrimes(int upto) {
		Boolean[] results = new Boolean[upto];
		for (int x = 0; x < results.length; x++)
			results[x] = isPrime(x);
		return results;
	}
}