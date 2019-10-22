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

	public Vanilla() {

		MineAndSlashAPI.addCompatibleItem("minecraft:bow",
				new ConfigItem().setType(new Bow()).setSalvagable(false).setMinLevel(5));
		LOGGER.debug("Registered Bow");
		MineAndSlashAPI.addCompatibleItem("minecraft:wooden_sword", new ConfigItem().setType(new Sword())
				.setMaxRarity(0).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
		MineAndSlashAPI.addCompatibleItem("minecraft:stone_sword", new ConfigItem().setType(new Sword()).setMaxRarity(1)
				.setSalvagable(false).setAlwaysNormal().setMaxLevel(20));

		MineAndSlashAPI.addCompatibleItem("minecraft:iron_sword", new ConfigItem().setType(new Sword()).setMaxRarity(2)
				.setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:golden_sword", new ConfigItem().setType(new Sword())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:diamond_sword",
				new ConfigItem().setType(new Sword()).setSalvagable(false).setMinLevel(50));
		LOGGER.debug("Registered Swords");
		MineAndSlashAPI.addCompatibleItem("minecraft:leather_helmet", new ConfigItem().setType(new Helmet())
				.setMaxRarity(0).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
		MineAndSlashAPI.addCompatibleItem("minecraft:chainmail_helmet",
				new ConfigItem().setType(new Helmet()).setMinLevel(40));
		MineAndSlashAPI.addCompatibleItem("minecraft:iron_helmet", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:golden_helmet", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:diamond_helmet",
				new ConfigItem().setType(new Helmet()).setSalvagable(false).setMinLevel(50));
		LOGGER.debug("Registered Helmet");
		MineAndSlashAPI.addCompatibleItem("minecraft:leather_chestplate", new ConfigItem().setType(new Helmet())
				.setMaxRarity(0).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
		MineAndSlashAPI.addCompatibleItem("minecraft:chainmail_chestplate",
				new ConfigItem().setType(new Helmet()).setMinLevel(40));
		MineAndSlashAPI.addCompatibleItem("minecraft:iron_chestplate", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:golden_chestplate", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:diamond_chestplate",
				new ConfigItem().setType(new Helmet()).setSalvagable(false).setMinLevel(50));
		LOGGER.debug("Registered Chestplate");
		MineAndSlashAPI.addCompatibleItem("minecraft:leather_leggings", new ConfigItem().setType(new Helmet())
				.setMaxRarity(0).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
		MineAndSlashAPI.addCompatibleItem("minecraft:chainmail_leggings",
				new ConfigItem().setType(new Helmet()).setMinLevel(40));
		MineAndSlashAPI.addCompatibleItem("minecraft:iron_leggings", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:golden_leggings", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:diamond_leggings",
				new ConfigItem().setType(new Helmet()).setSalvagable(false).setMinLevel(50));
		LOGGER.debug("Registered Leggings");
		MineAndSlashAPI.addCompatibleItem("minecraft:leather_boots", new ConfigItem().setType(new Helmet())
				.setMaxRarity(0).setSalvagable(false).setAlwaysNormal().setMaxLevel(10));
		MineAndSlashAPI.addCompatibleItem("minecraft:chainmail_boots",
				new ConfigItem().setType(new Helmet()).setMinLevel(40));
		MineAndSlashAPI.addCompatibleItem("minecraft:iron_boots", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:golden_boots", new ConfigItem().setType(new Helmet())
				.setMaxRarity(2).setSalvagable(false).setAlwaysNormal().setMaxLevel(30));
		MineAndSlashAPI.addCompatibleItem("minecraft:diamond_boots",
				new ConfigItem().setType(new Helmet()).setSalvagable(false).setMinLevel(50));
		LOGGER.debug("Registered Boots");
	}
}
