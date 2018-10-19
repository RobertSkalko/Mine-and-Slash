package com.robertx22.uncommon.testing;

import com.robertx22.uncommon.testing.tests.ItemGenRarityTest;

public class TestManager {

	private static boolean RunTests = true;

	public static void RunAllTests() {

		if (RunTests) {

			new ItemGenRarityTest().GenManyItems();

		}

	}

}
