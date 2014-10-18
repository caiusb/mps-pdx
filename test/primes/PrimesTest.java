package primes;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.StopWatch;
import util.UnimplementedExercise;

/**
 * A driver for testing classes that extend PrimeComputation.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrimesTest {
	final static int UP_TO = 7000000; // change together with NO_PRIMES_UP_TO
	final static int NO_PRIMES_UP_TO = 476648;
	
	static long referenceRuntime = 0;
	
	@Test
	public void test1Sequential() throws Exception {
		test("Sequential version", new PrimesSequential());
	}
	
	@Test
	public void test2Threads() throws Exception {
		test("Threads version", new PrimesThreads());
	}
	
	@Test
	public void test3Pool() throws Exception {
		test("Pool version", new PrimesPool());
	}
	
	@Test
	public void test4ForkJoin() throws Exception {
		test("ForkJoin version", new PrimesForkJoin());
	}
	
	@Test
	public void test5Parallel() throws Exception {
		test("Stream version", new PrimesParallel());
	}
	
	@Test
	public void test6Live() throws Exception {
		test("Live version", new PrimesLive());
	}

	private void test(String version, PrimesComputation p)
			throws Exception {
		test(version, p, UP_TO, false);
	}

	private void test(String version, PrimesComputation p, int upto,
			boolean resetReference) throws Exception {
		System.out.println(version);
		System.out.println("-----------------------------");

		if (p instanceof UnimplementedExercise) {
			System.out.println("UNIMPLEMENTED - when finished "
					+ "implementing this exercise, "
					+ "remove UnimplementedExercise from "
					+ "the list of implemented interfaces.\n");
			return;
		}

		// warm-up
		p.computePrimes(upto);
		p.computePrimes(upto);

		StopWatch.start();
		Boolean[] results = p.computePrimes(upto);
		StopWatch.stop();
		long numberOfPrimes = 0;
		for (boolean isP : results)
			if (isP)
				numberOfPrimes++;
		System.out.println("#      primes: " + numberOfPrimes);
		Assert.assertEquals(NO_PRIMES_UP_TO, numberOfPrimes);
		if (p instanceof LiveResults)
			System.out.println("# live primes: "
					+ ((LiveResults<?>) p).resultsCount());
		StopWatch.log("Time: ");
		if (resetReference || referenceRuntime == 0)
			referenceRuntime = StopWatch.getRuntime();
		else
			System.out.printf("Speed-up: %.2f\n", referenceRuntime / 1.0
					/ StopWatch.getRuntime());
		System.out.println();
	}
}