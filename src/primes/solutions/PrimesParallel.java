package primes.solutions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import primes.PrimesComputation;

public class PrimesParallel extends PrimesComputation {

	@Override
	public Boolean[] computePrimes(int upto) {
		Boolean[] results = new Boolean[upto];
		
		List<Boolean> booleanList  = IntStream.range(0, upto)
										.parallel()
										.mapToObj(PrimesComputation::isPrime)
										.collect(Collectors.<Boolean>toList());
				
		return booleanList.toArray(results);

	}

}