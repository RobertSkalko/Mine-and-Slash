package com.robertx22.compat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableList;
import com.robertx22.api.MineAndSlashAPI;
import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.gearitemslots.Sword;

public class techreborn {
	
	private static final Logger LOGGER = LogManager.getLogger();

	private static final List<String> SWORD = ImmutableList.of("bronzesword", "sapphiresword", "rubysword", "peridotsword", "nanosaber");

	private static final List<String> Helmet = ImmutableList.of("peridothelmet", "rubyhelmet", "sapphirehelmet", "bronzehelmet");

	private static final List<String> Chestplate = ImmutableList.of("peridotchestplate", "rubychestplate", "sapphirechestplate", "bronzechestplate");
	
	private static final List<String> Leggings = ImmutableList.of("peridotleggings", "rubyleggings", "sapphireleggings", "bronzeleggings");

	private static final List<String> Boots = ImmutableList.of("peridotboots", "rubyboots", "sapphireboots", "bronzeboots");

	public techreborn() {
		for (String s : SWORD) {
			MineAndSlashAPI.addCompatibleItem("techreborn:" + s, new ConfigItem().setType(new Sword()));
			LOGGER.debug("Registered Swords");
		}
		for (String s : Helmet) {
			MineAndSlashAPI.addCompatibleItem("techreborn:" + s, new ConfigItem().setType(new Helmet()));
			LOGGER.debug("Registered Helmet");
		}
		for (String s : Chestplate) {
			MineAndSlashAPI.addCompatibleItem("techreborn:" + s, new ConfigItem().setType(new Chest()));
			LOGGER.debug("Registered Chestplate");
		}
		for (String s : Leggings) {
			MineAndSlashAPI.addCompatibleItem("techreborn:" + s, new ConfigItem().setType(new Pants()));
			LOGGER.debug("Registered Leggings");
		}
		for (String s : Boots) {
			MineAndSlashAPI.addCompatibleItem("techreborn:" + s, new ConfigItem().setType(new Boots()));
			LOGGER.debug("Registered Boots");
		}
	}
}
