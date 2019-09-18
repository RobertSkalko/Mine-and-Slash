package com.robertx22.compat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableList;
import com.robertx22.api.MineAndSlashAPI;
import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Boots;

public class Vanilla {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final List<String> Helmet = ImmutableList.of("leather_helmet", "chainmail_helmet", "iron_helmet",
			"diamond_helmet", "golden_helmet");

	private static final List<String> Chestplate = ImmutableList.of("leather_chestplate", "chainmail_chestplate",
			"iron_chestplate", "diamond_chestplate", "golden_chestplate");

	private static final List<String> Leggings = ImmutableList.of("leather_leggings", "chainmail_leggings",
			"iron_leggings", "diamond_leggings", "golden_leggings");

	private static final List<String> Boots = ImmutableList.of("leather_boots", "chainmail_boots", "iron_boots",
			"diamond_boots", "golden_boots");

	private static final List<String> SWORD = ImmutableList.of("iron_sword", "wooden_sword", "stone_sword",
			"diamond_sword", "golden_sword");

	private static final List<String> AXE = ImmutableList.of("iron_axe", "wooden_axe", "stone_axe", "diamond_axe",
			"golden_axe");

	public Vanilla() {

		for (String s : AXE) {
			MineAndSlashAPI.addCompatibleItem("minecraft:" + s, new ConfigItem().setType(new Axe()));
			LOGGER.debug("Registered Axes");
		}
		MineAndSlashAPI.addCompatibleItem("minecraft:bow", new ConfigItem().setType(new Bow()));
		LOGGER.debug("Registered Bow");
		for (String s : SWORD) {
			MineAndSlashAPI.addCompatibleItem("minecraft:" + s, new ConfigItem().setType(new Sword()));
			LOGGER.debug("Registered Swords");
		}
		for (String s : Helmet) {
			MineAndSlashAPI.addCompatibleItem("minecraft:" + s, new ConfigItem().setType(new Helmet()));
			LOGGER.debug("Registered Helmet");
		}
		for (String s : Chestplate) {
			MineAndSlashAPI.addCompatibleItem("minecraft:" + s, new ConfigItem().setType(new Chest()));
			LOGGER.debug("Registered Chestplate");
		}
		for (String s : Leggings) {
			MineAndSlashAPI.addCompatibleItem("minecraft:" + s, new ConfigItem().setType(new Pants()));
			LOGGER.debug("Registered Leggings");
		}
		for (String s : Boots) {
			MineAndSlashAPI.addCompatibleItem("minecraft:" + s, new ConfigItem().setType(new Boots()));
			LOGGER.debug("Registered Boots");
		}
	}
}
