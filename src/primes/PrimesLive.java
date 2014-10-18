package primes;

import util.UnimplementedExercise;

/*
 In this exercise, we make the Primes example slightly more complex. 
 Let's say we want to gather the results of the computation in an array, 
 each element of the array storing one prime number. Also, we want this 
 array to be updated live, as the computation progresses.

 1.	We start from the thread pool version of this exercise. Copy either your
 	or the given thread pool solution to the class below. Merge as necessary.

 2.	Create an int array field in PrimesLive. This array will keep the live results.
 	Also, create an int field to keep the current number of found primes.

 3. PrimesLive implements LiveResults. Implement the two methods in the interface.

 4. Make the run() method of the computation task also add elements to the live 
 	array as they are computed.

 5. Remove the UnimplementedExercise interface and test. So far, you've seen that 
 	the test code also displays the number of prime numbers found. This is a simple yet
 	effective way of checking the different versions of the algorithm run correctly.
 	In this last case, we also display the number of elements in the live array at the 
 	end of the computation. Does it have the expected value? Why?
 */

public class PrimesLive extends PrimesComputation implements
		LiveResults<Integer[]>, UnimplementedExercise {

	@Override
	public Boolean[] computePrimes(int upto) {
		// TODO: implement this method
		return null;
	}

	@Override
	public Integer[] getResults() {
		// TODO implement this method
		return null;
	}

	@Override
	public int resultsCount() {
		// TODO implement this method
		return 0;
	}

}
