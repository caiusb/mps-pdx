package pi;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import util.StopWatch;
import util.UnimplementedExercise;

/**
 * Tests for PiApproximation implementations.
 * 
 * The tests run all implementations and report, for each of them, the speedup
 * and the approximation. The reported values are obtained after executing two
 * {@link #warmup(PiApproximation)} runs.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PiTest {
	private static final double PI = 3.141592653589793238462643383279D;
	private static final int ITERATIONS = 20000000;
	private static long sequentialRuntime = 0;

	@Test
	public void test1Sequential() throws Exception {
		test("Sequential version", new PiSequential());
	}

	@Test
	public void test2Threads() throws Exception {
		test("Threads version", new PiThreads());
	}

	@Test
	public void test3Live() throws Exception {
		test("Live version", new PiLive());
	}

	@Test
	public void test4LiveSync() throws Exception {
		test("Live with sync version", new PiLiveSync());
	}

	@Test
	public void test5LiveAtomic() throws Exception {
		test("Live with atomic version", new PiLiveAtomic());
	}

	protected void test(String version, final PiApproximation piApproximation)
			throws Exception {

		System.out.println(version);
		System.out.println("-----------------------------");

		if (piApproximation instanceof UnimplementedExercise) {
			System.out.println("UNIMPLEMENTED - when finished "
					+ "implementing this exercise, "
					+ "remove UnimplementedExercise from "
					+ "the list of implemented interfaces.\n");
			return;
		}

		// warm-up
		warmup(piApproximation);
		// double the warmup for the sequential version - just to make sure all
		// JIT is done
		if (sequentialRuntime == 0)
			warmup(piApproximation);

		StopWatch.start();
		Timer timer = new Timer("Print live value");
		if (piApproximation instanceof LiveValue) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("Live value: "
							+ ((LiveValue) piApproximation).liveValue());
				}
			}, 100, 100); //sequentialRuntime == 0 ? 500 : sequentialRuntime);
		}
		double pi = piApproximation.computePi(ITERATIONS);
		StopWatch.stop();

		StopWatch.log("Time: ");
		if (sequentialRuntime == 0)
			sequentialRuntime = StopWatch.getRuntime();
		else
			System.out.printf("Speed-up: %.2f\n", sequentialRuntime / 1.0
					/ StopWatch.getRuntime());

		System.out.println("     Real Pi: " + PI);
		System.out.println("Estimated Pi: " + pi);
		System.out.println("Delta (times 1,000,000): " + ((pi - PI) * 1000000));
		Assert.assertEquals(PI, pi, 0.001);
		System.out.println();
		timer.cancel();
	}

	private static void warmup(PiApproximation piApproximation)
			throws Exception {
		piApproximation.computePi(ITERATIONS);
		piApproximation.computePi(ITERATIONS);
	}
}