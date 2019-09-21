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

public class thermalfoundation {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final List<String> BOW = ImmutableList.of("tool.bow_silver", "tool.bow_cconstantan",
			"tool.bow_copper", "tool.bow_nickel", "tool.bow_tin", "tool.bow_iron", "tool.bow_gold", "tool.bow_steel",
			"tool.bow_platinum", "tool.bow_aluminum", "tool.bow_bronze", "tool.bow_electrum", "tool.bow_lead",
			"tool.bow_diamond", "tool.bow_invar");

	private static final List<String> HAMMER = ImmutableList.of("tool.hammer_silver", "tool.hammer_cconstantan",
			"tool.hammer_copper", "tool.hammer_nickel", "tool.hammer_tin", "tool.hammer_iron", "tool.hammer_gold", "tool.hammer_steel",
			"tool.hammer_platinum", "tool.hammer_aluminum", "tool.hammer_bronze", "tool.hammer_electrum", "tool.hammer_lead",
			"tool.hammer_diamond", "tool.hammer_invar");

	private static final List<String> SWORD = ImmutableList.of("tool.sword_silver", "tool.sword_platinum",
			"tool.sword_invar", "tool.sword_steel", "tool.sword_bronze", "tool.sword_copper", "tool.sword_constantan",
			"tool.sword_lead", "tool.sword_tin", "tool.sword_electrum", "tool.sword_nickel", "tool.sword_aluminum");

	private static final List<String> Helmet = ImmutableList.of("armor.helmet_lead", "armor.helmet_tin",
			"armor.helmet_copper", "armor.helmet_constantan", "armor.helmet_aluminum", "armor.helmet_silver",
			"armor.helmet_nickel", "armor.helmet_invar", "armor.helmet_steel", "armor.helmet_platinum",
			"armor.helmet_bronze", "armor.helmet_electrum");

	private static final List<String> Chestplate = ImmutableList.of("armor.plate_lead", "armor.plate_tin",
			"armor.plate_copper", "armor.plate_constantan", "armor.plate_aluminum", "armor.plate_silver",
			"armor.plate_nickel", "armor.plate_invar", "armor.plate_steel", "armor.plate_platinum",
			"armor.plate_bronze", "armor.plate_electrum");

	private static final List<String> Leggings = ImmutableList.of("armor.legs_lead", "armor.legs_tin",
			"armor.legs_copper", "armor.legs_constantan", "armor.legs_aluminum", "armor.legs_silver",
			"armor.legs_nickel", "armor.legs_invar", "armor.legs_steel", "armor.legs_platinum", "armor.legs_bronze",
			"armor.legs_electrum");

	private static final List<String> Boots = ImmutableList.of("armor.boots_lead", "armor.boots_tin",
			"armor.boots_copper", "armor.boots_constantan", "armor.boots_aluminum", "armor.boots_silver",
			"armor.boots_nickel", "armor.boots_invar", "armor.boots_steel", "armor.boots_platinum",
			"armor.boots_bronze", "armor.boots_electrum");

	public thermalfoundation() {
		for (String s : BOW) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Staff()));
			LOGGER.debug("Registered Bows");
		}
		for (String s : HAMMER) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Hammer()));
			LOGGER.debug("Registered Hammers");
		}
		for (String s : SWORD) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Sword()));
			LOGGER.debug("Registered Swords");
		}
		for (String s : Helmet) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Helmet()));
			LOGGER.debug("Registered Helmet");
		}
		for (String s : Chestplate) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Chest()));
			LOGGER.debug("Registered Chestplate");
		}
		for (String s : Leggings) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Pants()));
			LOGGER.debug("Registered Leggings");
		}
		for (String s : Boots) {
			MineAndSlashAPI.addCompatibleItem("thermalfoundation:" + s, new ConfigItem().setType(new Boots()));
			LOGGER.debug("Registered Boots");
		}
	}
}
