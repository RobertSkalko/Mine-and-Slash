package com.robertx22.uncommon.testing;

import com.robertx22.uncommon.testing.tests.ItemGenRarityTest;
import com.robertx22.uncommon.testing.tests.MaximumPossibleStat;

public class TestManager {

	private static boolean RunTests = false;

	public static void RunAllTests() {

		if (RunTests) {

			new ItemGenRarityTest().GenManyItems();
			new MaximumPossibleStat().CalculateByMassRandomization();

			stringAppendLoop();
			stringAppendBuilderLoop();
		}

	}

	public static String stringAppendLoop() {

		String s = "";

		for (int i = 0; i < 10000; i++) {

			if (s.length() > 0)

				s += ", ";

			s += "bar";

		}

		return s;
	}

	public static String stringAppendBuilderLoop() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 10000; i++) {

			if (sb.length() > 0)

				sb.append(", ");

			sb.append("bar");

		}
		return sb.toString();
	}
}
