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

	private static final List<String> SWORD = ImmutableList.of("bronzesword", "sapphiresword", "rubysword",
			"peridotsword");

	private static final List<String> Helmet = ImmutableList.of("peridothelmet", "rubyhelmet", "bronzehelmet");

	private static final List<String> Chestplate = ImmutableList.of("peridotchestplate", "rubychestplate",
			"bronzechestplate");

	private static final List<String> Leggings = ImmutableList.of("peridotleggings", "rubyleggings", "bronzeleggings");

	private static final List<String> Boots = ImmutableList.of("peridotboots", "rubyboots", "bronzeboots");

	public techreborn() {
		String modID = "techreborn:";

		MineAndSlashAPI.addCompatibleItem(modID + "nanosaber",
				new ConfigItem().setType(new Sword()).setSalvagable(false).setMinLevel(85));
		for (String s : SWORD) {
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Sword()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		}
		LOGGER.debug("Registered Swords");
		for (String h : Helmet) {
			MineAndSlashAPI.addCompatibleItem(modID + h, new ConfigItem().setType(new Helmet()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		}
		MineAndSlashAPI.addCompatibleItem(modID + "sapphirehelmet",
				new ConfigItem().setType(new Helmet()).setSalvagable(false).setMinLevel(55));
		LOGGER.debug("Registered Helmet");
		for (String c : Chestplate) {
			MineAndSlashAPI.addCompatibleItem(modID + c, new ConfigItem().setType(new Chest()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		}
		MineAndSlashAPI.addCompatibleItem(modID + "sapphirechestplate",
				new ConfigItem().setType(new Chest()).setSalvagable(false).setMinLevel(55));
		LOGGER.debug("Registered Chestplate");
		for (String l : Leggings) {
			MineAndSlashAPI.addCompatibleItem(modID + l, new ConfigItem().setType(new Pants()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		}
		MineAndSlashAPI.addCompatibleItem(modID + "sapphireleggings",
				new ConfigItem().setType(new Pants()).setSalvagable(false).setMinLevel(55));
		LOGGER.debug("Registered Leggings");
		for (String b : Boots) {
			MineAndSlashAPI.addCompatibleItem(modID + b, new ConfigItem().setType(new Boots()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		}
		MineAndSlashAPI.addCompatibleItem(modID + "sapphireboots",
				new ConfigItem().setType(new Boots()).setSalvagable(false).setMinLevel(55));
		LOGGER.debug("Registered Boots");
	}
}
