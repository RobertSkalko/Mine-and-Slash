package com.robertx22.compat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableList;
import com.robertx22.api.MineAndSlashAPI;
import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.Necklace;

public class ebwizardry {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final List<String> TYPE = ImmutableList.of("fire", "ice", "lightning", "necromancy", "earth",
			"sorcery", "healing");

	private static final List<String> Ring = ImmutableList.of("ring_arcane_frost", "ring_battlemage",
			"ring_blockwrangler", "ring_combustion", "ring_condensing", "ring_conjurer", "ring_defender",
			"ring_disintegration", "ring_earth_biome", "ring_earth_melee", "ring_extraction", "ring_fire_biome",
			"ring_fire_melee", "ring_full_moon", "ring_hammer", "ring_ice_biome", "ring_ice_melee", "ring_interdiction",
			"ring_leeching", "ring_lightning_melee", "ring_mana_return", "ring_mind_control", "ring_necromancy_melee",
			"ring_paladin", "ring_poison", "ring_seeking", "ring_shattering", "ring_siphoning", "ring_soulbinding",
			"ring_storm");

	private static final List<String> Amulet = ImmutableList.of("amulet_anchoring", "amulet_arcance_defence",
			"amulet_auto_shield", "amulet_banishing", "amulet_channeling", "amulet_fire_cloaking",
			"amulet_fire_protection", "amulet_glide", "amulet_ice_immunity", "amulet_ice_protection", "amulet_lich",
			"amulet_potential", "amulet_recovery", "amulet_resurrection", "amulet_transience", "amulet_warding",
			"amulet_wisdom", "amulet_wither_immunity");

	private static final List<String> Charm = ImmutableList.of("charm_haggler", "charm_abseiling",
			"charm_experience_tome", "charm_feeding", "charm_flight", "charm_growth", "charm_lava_walking",
			"charm_light", "charm_minion_health", "charm_minion_variants", "charm_silk_touch", "charm_stop_time",
			"charm_storm");

	public ebwizardry() {

		String modID = "ebwizardry:";

		for (String w : TYPE) {
			MineAndSlashAPI.addCompatibleItem(modID + "novice_" + w + "_wand",
					new ConfigItem().setType(new Staff()).setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + "magic_wand",
					new ConfigItem().setType(new Staff()).setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + "apprentice_" + w + "_wand",
					new ConfigItem().setType(new Staff()).setMinLevel(30));
			MineAndSlashAPI.addCompatibleItem(modID + "apprentice_wand",
					new ConfigItem().setType(new Staff()).setMinLevel(30));
			MineAndSlashAPI.addCompatibleItem(modID + "advanced_" + w + "_wand",
					new ConfigItem().setType(new Staff()).setMinLevel(50));
			MineAndSlashAPI.addCompatibleItem(modID + "advanced_wand",
					new ConfigItem().setType(new Staff()).setMinLevel(50));
			MineAndSlashAPI.addCompatibleItem(modID + "master_" + w + "_wand",
					new ConfigItem().setType(new Staff()).setMinLevel(70));
			MineAndSlashAPI.addCompatibleItem(modID + "master_wand",
					new ConfigItem().setType(new Staff()).setMinLevel(70));
		}
		LOGGER.debug("Registered Staffs");
		for (String s : TYPE) {
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_hat",
					new ConfigItem().setType(new Helmet()).setMaxRarity(2).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_hat_" + s,
					new ConfigItem().setType(new Helmet()).setMaxRarity(2).setAlwaysNormal().setMinLevel(30));
			LOGGER.debug("Registered Helmet");
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_robe",
					new ConfigItem().setType(new Chest()).setMaxRarity(2).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_robe_" + s,
					new ConfigItem().setType(new Chest()).setMaxRarity(2).setAlwaysNormal().setMinLevel(30));
			LOGGER.debug("Registered Chestplate");
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_leggings",
					new ConfigItem().setType(new Pants()).setMaxRarity(2).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_leggings_" + s,
					new ConfigItem().setType(new Pants()).setMaxRarity(2).setAlwaysNormal().setMinLevel(30));
			LOGGER.debug("Registered Leggings");
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_boots",
					new ConfigItem().setType(new Boots()).setMaxRarity(2).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_boots" + s,
					new ConfigItem().setType(new Boots()).setMaxRarity(2).setAlwaysNormal().setMinLevel(30));
			LOGGER.debug("Registered Boots");
		}
		for (String s : Ring) {
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Ring()));
			LOGGER.debug("Registered Rings");
		}
		for (String s : Amulet) {
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Necklace()));
			LOGGER.debug("Registered Amulets");
		}
		for (String s : Charm) {
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Charm()));
			LOGGER.debug("Registered Charms");
		}
	}

}
