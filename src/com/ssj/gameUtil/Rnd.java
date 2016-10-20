package com.ssj.gameUtil;

import java.util.Random;

public class Rnd {
	
	static final Random rnd = new  Random();
	
	public static float get() // get random number from 0 to 1
	{
		return rnd.nextFloat();
	}

	/**
	 * Gets a random number from 0(inclusive) to n(exclusive)
	 * 
	 * @param n
	 *            The superior limit (exclusive)
	 * @return A number from 0 to n-1
	 */
	public static int get(int n)
	{
		return (int) Math.floor(rnd.nextDouble() * n);
	}

	/**
	 * @param min
	 * @param max
	 * @return value
	 */
	public static int get(int min, int max) // get random number from
	// min to max (not max-1 !)
	{
		return min + (int) Math.floor(rnd.nextDouble() * (max - min + 1));
	}

	
	public static long get(long min, long max) // get random number from
	// min to max (not max-1 !)
	{
		return min +  (long)Math.floor(rnd.nextDouble() * (max - min + 1));
	}
	
	/**
	 * @param n
	 * @return n
	 */
	public static int nextInt(int n)
	{
		return (int) Math.floor(rnd.nextDouble() * n);
	}

	/**
	 * @return int
	 */
	public static int nextInt()
	{
		return rnd.nextInt();
	}

	/**
	 * @return double
	 */
	public static double nextDouble()
	{
		return rnd.nextDouble();
	}

	/**
	 * @return double
	 */
	public static double nextGaussian()
	{
		return rnd.nextGaussian();
	}

	/**
	 * @return boolean
	 */
	public static boolean nextBoolean()
	{
		return rnd.nextBoolean();
	}
}
