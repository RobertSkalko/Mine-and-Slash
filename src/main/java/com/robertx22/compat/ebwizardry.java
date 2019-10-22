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

	private static final List<String> BASIC = ImmutableList.of("magic_wand", "novice_fire_wand", "novice_ice_wand",
			"novice_lightning_wand", "novice_necromancy_wand");

	private static final List<String> APPRENTICE = ImmutableList.of("apprentice_wand", 
			"apprentice_earth_wand", "apprentice_sorcery_wand", "apprentice_healing_wand", "apprentice_fire_wand",
			"apprentice_ice_wand", "apprentice_lightning_wand", "apprentice_necromancy_wand", "apprentice_earth_wand",
			"apprentice_sorcery_wand", "apprentice_healing_wand" );

	private static final List<String> ADVANCED = ImmutableList.of("advanced_wand", "advanced_earth_wand",
			"advanced_sorcery_wand", "advanced_healing_wand", "advanced_fire_wand", "advanced_ice_wand",
			"advanced_lightning_wand", "advanced_necromancy_wand", "advanced_earth_wand", "advanced_sorcery_wand",
			"advanced_healing_wand");

	private static final List<String> MASTER = ImmutableList.of("magic_wand", "apprentice_wand", "advanced_wand",
			"master_wand", "master_earth_wand", "master_sorcery_wand", "master_healing_wand", "master_fire_wand",
			"master_ice_wand", "master_lightning_wand", "master_necromancy_wand", "master_earth_wand",
			"master_sorcery_wand", "master_healing_wand");

	private static final List<String> Helmet = ImmutableList.of("wizard_hat_fire", "wizard_hat_ice",
			"wizard_hat_lightning", "wizard_hat_necromancy", "wizard_hat_earth", "wizard_hat_sorcery",
			"wizard_hat_healing");

	private static final List<String> Chestplate = ImmutableList.of("wizard_robe_fire", "wizard_robe_ice",
			"wizard_robe_lightning", "wizard_robe_necromancy", "wizard_robe_earth", "wizard_robe_sorcery",
			"wizard_robe_healing");

	private static final List<String> Leggings = ImmutableList.of("wizard_leggings_fire", "wizard_leggings_ice",
			"wizard_leggings_lightning", "wizard_leggings_necromancy", "wizard_leggings_earth",
			"wizard_leggings_sorcery", "wizard_leggings_healing");

	private static final List<String> Boots = ImmutableList.of("wizard_boots_fire", "wizard_boots_ice",
			"wizard_boots_lightning", "wizard_boots_necromancy", "wizard_boots_earth", "wizard_boots_sorcery",
			"wizard_boots_healing");

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
		List<String> b = BASIC;
		List<String> p = APPRENTICE;
		List<String> a = ADVANCED;
		List<String> m = MASTER;

		MineAndSlashAPI.addCompatibleItem(modID + b, new ConfigItem().setType(new Staff()).setMaxLevel(10));
		MineAndSlashAPI.addCompatibleItem(modID + p, new ConfigItem().setType(new Staff()).setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem(modID + a, new ConfigItem().setType(new Staff()).setMaxLevel(50));
		MineAndSlashAPI.addCompatibleItem(modID + m, new ConfigItem().setType(new Staff()).setMaxLevel(70));
		LOGGER.debug("Registered Staffs");
		for (String s : Helmet) {
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_hat", new ConfigItem().setType(new Helmet())
					.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Helmet()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
			LOGGER.debug("Registered Helmet");
		}
		for (String s : Chestplate) {
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_robe", new ConfigItem().setType(new Chest())
					.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Chest()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
			LOGGER.debug("Registered Chestplate");
		}
		for (String s : Leggings) {
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_leggings", new ConfigItem().setType(new Pants())
					.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Pants()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
			LOGGER.debug("Registered Leggings");
		}
		for (String s : Boots) {
			MineAndSlashAPI.addCompatibleItem(modID + "wizard_boots", new ConfigItem().setType(new Boots())
					.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
			MineAndSlashAPI.addCompatibleItem(modID + s, new ConfigItem().setType(new Boots()).setMaxRarity(2)
					.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
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
