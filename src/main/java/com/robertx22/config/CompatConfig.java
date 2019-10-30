package com.robertx22.config;

import net.minecraftforge.common.config.Config;

public class CompatConfig {
	
	@Config.Name("Auto Enable Vanilla Items Stats")
	@Config.LangKey("mmorpg.config.")
	@Config.Comment("This enables auto adding stats to vanilla items")
	public boolean AUTOCOMPATIBILITY_VANILLAITEMS = true;

	@Config.Name("Auto Enable EB Wizadry Item Stats")
	@Config.LangKey("mmorpg.config.")
	@Config.Comment("This enables auto adding stats to items from EB Wizardry")
	public boolean AUTOCOMPATIBILITY_EBWIZARDRYITEMS = true;

	@Config.Name("Auto Enable Ice and Fire Item Stats")
	@Config.LangKey("mmorpg.config.")
	@Config.Comment("This enables auto adding stats to items from Ice and Fire")
	public boolean AUTOCOMPATIBILITY_ICEFIREITEMS = true;

	@Config.Name("Auto Enable Tech Reborn Item Stats")
	@Config.LangKey("mmorpg.config.")
	@Config.Comment("This enables auto adding stats to items from Tech Reborn")
	public boolean AUTOCOMPATIBILITY_TECHREBORNITEMS = true;

	@Config.Name("Auto Enable Thermal Foundation Item Stats")
	@Config.LangKey("mmorpg.config.")
	@Config.Comment("This enables auto adding stats to items from Thermal Foundation")
	public boolean AUTOCOMPATIBILITY_THERMALITEMS = true;
}
