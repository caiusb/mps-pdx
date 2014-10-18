package primes;

import util.UnimplementedExercise;

/**
 * Java 8 Streams version
 */

/*
 * Steps:
 * 
 * 1. Remove the for loop. We shall replace it with java 8 streams.
 * 
 * 2. Create an IntStream with integers from 0 to upTo via the IntStream.range
 * method.
 * 
 * 4. Apply the isPrime method to all elements of the stream via the .mapToObj
 * method. You can chain all the method calls.
 * 
 * 3. Convert the resulting stream of booleans to a list of booleans via the
 * .collect method and assign it to a variable of type List<Boolean>.
 * 
 * 4. In order to preserve the .computePrimes method return type, convert the
 * list of booleans to an array of booleans and return it.
 * 
 * 5. The code should now work correctly in a sequential manner. To parallelize
 * it, apply the method .parallelize to the int stream before mapping it.
 * 
 * 4. Remove the UnimplementedExercise interface and test. The performance
 * should be worse than the thread pool and fork-join versions. This is because
 * parallel streams use much more fine grained tasks and, even with its very
 * efficient work distribution algorithm, it cannot beat a manual
 * implementation. The up side is very readable code and easy adaptation to new
 * hardware.
 */

public class PrimesParallel extends PrimesComputation implements
		UnimplementedExercise {

	@Override
	public Boolean[] computePrimes(int upto) {
		Boolean[] results = new Boolean[upto];

		for (int x = 0; x < results.length; x++)
			results[x] = isPrime(x);
		return results;
	}
}
