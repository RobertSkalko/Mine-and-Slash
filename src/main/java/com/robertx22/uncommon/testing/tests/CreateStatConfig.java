package com.robertx22.uncommon.testing.tests;

import com.robertx22.db_lists.Stats;
import com.robertx22.mmorpg.Ref;
import com.robertx22.stats.Stat;
import com.robertx22.stats.Trait;

public class CreateStatConfig {

    public void doit() {

	String text = "";

	String text2 = "";

	for (Stat stat : Stats.All.values()) {

	    if (!(stat instanceof Trait) && !stat.GUID().toLowerCase().contains("to")) {

		text += "@Config.Name(" + '"' + stat.GUID() + '"' + ')' + "\n";
		text += "@Config.LangKey(" + '"' + Ref.MODID + "." + "stat." + stat.unlocString() + '"' + ')' + "\n";
		text += "public float " + stat.unlocString() + "=0;" + "\n" + "\n";

		// perlevel
		text += "@Config.Name(" + '"' + stat.GUID() + " Per Level" + '"' + ')' + "\n";
		text += "@Config.LangKey(" + '"' + Ref.MODID + "." + "stat." + stat.unlocString() + "_per_level" + '"'
			+ ')' + "\n";
		text += "public float " + stat.unlocString() + "_per_level" + "=0;" + "\n\n";

		// other thing

		text2 += "unit.MyStats.get(" + '"' + stat.GUID() + '"' + ").Flat += (int) (ModConfig.BasePlayerStats."
			+ stat.unlocString() + " + " + "data.getLevel()";
		text2 += "- 1 * ModConfig.BasePlayerStats." + stat.unlocString() + "_per_level);\n\n";

	    }

	}

	System.out.println(text);
	System.out.println(text2);

    }

}
