package com.robertx22.uncommon.testing;

import com.robertx22.uncommon.testing.tests.ItemGenRarityTest;

public class TestManager {

    private static boolean RunTests = false;

    public static void RunAllTests() {

	// new CreateJsonsOfRunes().doit();

	if (RunTests) {
	    new ItemGenRarityTest().GenManyItems();

	}

    }

}
