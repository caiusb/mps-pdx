package primes.solutions;

import java.util.stream.IntStream;

import primes.PrimesComputation;

public class PrimesParallel extends PrimesComputation {

	@Override
	public Boolean[] computePrimes(int upto) {
		return IntStream.range(0, upto)
						.parallel()
						.mapToObj(x -> isPrime(x))
						.toArray(Boolean[]::new);
	}
}