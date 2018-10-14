package com.robertx22.testing;

import com.robertx22.testing.tests.ItemGenRarityTest;

public class TestManager {

	private static boolean RunTests = true;

	public static void RunAllTests() {

		if (RunTests) {

			new ItemGenRarityTest().GenManyItems();

		}

	}

}
