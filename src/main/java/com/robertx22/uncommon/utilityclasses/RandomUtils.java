package com.robertx22.uncommon.utilityclasses;

import java.util.Random;

public class RandomUtils {

	private static Random ran = new Random();

	public static int RandomRange(int min, int max) {

		int result = ran.nextInt(max - min);

		return result + min;

	}

	public static boolean roll(int chance) {

		Random ran = new Random();

		double ranNum = ran.nextDouble() * 100;

		if (chance > ranNum) {
			return true;
		}

		return false;
	}

	public static boolean roll(float chance) {

		Random ran = new Random();

		double ranNum = ran.nextDouble() * 100;

		if (chance > ranNum) {
			return true;
		}

		return false;
	}

	public static boolean roll(double chance) {

		Random ran = new Random();

		double ranNum = ran.nextDouble() * 100;

		if (chance > ranNum) {
			return true;
		}

		return false;
	}

	public static int rollWhile(int chance, int min, int max) {

		// 0 = Magic, 1 = rare, ... epic, legendary, mythical

		boolean rolling = true;

		while (rolling) {

			if (min >= max) {
				rolling = false;
				break;
			}

			if (roll(chance)) {
				min++;
			} else {
				rolling = false;
				break;
			}

		}

		return min;
	}

}
