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
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Boots;

public class fireice {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final List<String> STAFF = ImmutableList.of("dragon_stick", "myrmex_desert_staff",
			"myrmex_jungle_staff");

	private static final List<String> AXE = ImmutableList.of("dragonsteel_fire_axe", "dragonsteel_ice_axe",
			"troll_weapon.axe", "silver_axe", "dragonbone_axe", "myrmex_desert_axe", "myrmex_jungle_axe");

	private static final List<String> HAMMER = ImmutableList.of("troll_weapon.trunk_frost", "troll_weapon.trunk",
			"troll_weapon.hammer", "troll_weapon.column", "troll_weapon.column_frost", "troll_weapon.column_forest");

	private static final List<String> SWORD = ImmutableList.of("dragonbone_sword", "silver_sword", "hippogryph_sword",
			"stymphalian_bird_dagger", "dragonsteel_ice_sword", "dragonsteel_fire_sword", "myrmex_desert_sword",
			"myrmex_desert_sword_venom", "myrmex_jungle_sword", "myrmex_jungle_sword_venom");

	private static final List<String> Helmet = ImmutableList.of("armor_silver_metal_helmet", "sheep_helmet",
			"deathworm_yellow_helmet", "deathworm_white_helmet", "deathworm_red_helmet", "myrmex_desert_helmet",
			"myrmex_jungle_helmet", "armor_red_helmet", "armor_bronze_helmet", "armor_green_helmet",
			"armor_gray_helmet", "armor_blue_helmet", "armor_white_helmet", "armor_sapphire_helmet",
			"armor_silver_helmet", "tide_blue_helmet", "tide_bronze_helmet", "tide_deepblue_helmet",
			"tide_green_helmet", "tide_purple_helmet", "tide_red_helmet", "tide_teal_helmet",
			"forest_troll_leather_helmet", "frost_troll_leather_helmet", "mountain_troll_leather_helmet");

	private static final List<String> Chestplate = ImmutableList.of("armor_silver_metal_chestplate", "sheep_chestplate",
			"deathworm_yellow_chestplate", "deathworm_white_chestplate", "deathworm_red_chestplate", "myrmex_desert_chestplate",
			"myrmex_jungle_chestplate", "armor_red_chestplate", "armor_bronze_chestplate", "armor_green_chestplate",
			"armor_gray_chestplate", "armor_blue_chestplate", "armor_white_chestplate", "armor_sapphire_chestplate",
			"armor_silver_chestplate", "tide_blue_chestplate", "tide_bronze_chestplate", "tide_deepblue_chestplate",
			"tide_green_chestplate", "tide_purple_chestplate", "tide_red_chestplate", "tide_teal_chestplate",
			"forest_troll_leather_chestplate", "frost_troll_leather_chestplate", "mountain_troll_leather_chestplate");
	
	private static final List<String> Leggings = ImmutableList.of("armor_silver_metal_leggings", "sheep_leggings",
			"deathworm_yellow_leggings", "deathworm_white_leggings", "deathworm_red_leggings", "myrmex_desert_leggings",
			"myrmex_jungle_leggings", "armor_red_leggings", "armor_bronze_leggings", "armor_green_leggings",
			"armor_gray_leggings", "armor_blue_leggings", "armor_white_leggings", "armor_sapphire_leggings",
			"armor_silver_leggings", "tide_blue_leggings", "tide_bronze_leggings", "tide_deepblue_leggings",
			"tide_green_leggings", "tide_purple_leggings", "tide_red_leggings", "tide_teal_leggings",
			"forest_troll_leather_leggings", "frost_troll_leather_leggings", "mountain_troll_leather_leggings");

	private static final List<String> Boots = ImmutableList.of("armor_silver_metal_boots", "sheep_boots",
			"deathworm_yellow_boots", "deathworm_white_boots", "deathworm_red_boots", "myrmex_desert_boots",
			"myrmex_jungle_boots", "armor_red_boots", "armor_bronze_boots", "armor_green_boots",
			"armor_gray_boots", "armor_blue_boots", "armor_white_boots", "armor_sapphire_boots",
			"armor_silver_boots", "tide_blue_boots", "tide_bronze_boots", "tide_deepblue_boots",
			"tide_green_boots", "tide_purple_boots", "tide_red_boots", "tide_teal_boots",
			"forest_troll_leather_boots", "frost_troll_leather_boots", "mountain_troll_leather_boots");

	public fireice() {
		for (String s : STAFF) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Staff()));
			LOGGER.debug("Registered Staffs");
		}
		for (String s : AXE) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Axe()));
			LOGGER.debug("Registered Axes");
		}
		for (String s : HAMMER) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Hammer()));
			LOGGER.debug("Registered Hammers");
		}
		MineAndSlashAPI.addCompatibleItem("iceandfire:dragonbone_bow", new ConfigItem().setType(new Bow()));
		MineAndSlashAPI.addCompatibleItem("iceandfire:stymphalian_feather_bundle", new ConfigItem().setType(new Bow()));
		LOGGER.debug("Registered Bows");
		for (String s : SWORD) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Sword()));
			LOGGER.debug("Registered Swords");
		}
		for (String s : Helmet) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Helmet()));
			LOGGER.debug("Registered Helmet");
		}
		for (String s : Chestplate) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Chest()));
			LOGGER.debug("Registered Chestplate");
		}
		for (String s : Leggings) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Pants()));
			LOGGER.debug("Registered Leggings");
		}
		for (String s : Boots) {
			MineAndSlashAPI.addCompatibleItem("iceandfire:" + s, new ConfigItem().setType(new Boots()));
			LOGGER.debug("Registered Boots");
		}
	}

}
