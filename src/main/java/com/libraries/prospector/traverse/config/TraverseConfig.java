package com.libraries.prospector.traverse.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class TraverseConfig {
	public static final String CATEGORY_BIOMES = "Biomes";
	public static File configDir;
	public static File mainConfig;
	public static Configuration config;
	public static TraverseConfig traverseConfiguration;

	public static boolean useVanillaWood = false;
	public static boolean registerBiomesRegardless = false;
	public static boolean disableCustomSkies = false;
	public static boolean vanillaCanyonBlocks = false;
	public static int findBiomeCommandTimeout = 30_000;
	public static boolean disallowVillages = false;
	public static boolean disallowBoulders = false;

	public static boolean disableAutumnalWoods = false;
	public static boolean disableWoodlands = false;
	public static boolean disableMiniJungle = false;
	public static boolean disableMeadow = false;
	public static boolean disableLushSwamp = false;
	public static boolean disableRedDesert = false;
	public static boolean disableTemperateRainforest = false;
	public static boolean disableBadlands = false;
	public static boolean disableMountainousDesert = false;
	public static boolean disableRockyPlateau = false;
	public static boolean disableForestedHills = false;
	public static boolean disableBirchForestedHills = false;
	public static boolean disableAutumnalWoodedHills = false;
	public static boolean disableCliffs = false;
	public static boolean disableGlacier = false;
	public static boolean disableGlacierSpikes = false;
	public static boolean disableSnowyConiferousForest = false;
	public static boolean disableLushHills = false;
	public static boolean disableCanyon = true;
	public static boolean disableCragCliffs = true;
	public static boolean disableDesertShrubland = false;
	public static boolean disableThicket = false;
	public static boolean disableAridHighland = false;
	public static boolean disableRockyPlains = false;

	public static boolean enableNewTextures = false;

	private static TraverseConfig instance = null;

	private TraverseConfig() {
		config = new Configuration(mainConfig);
		config.load();

		useVanillaWood = config.get(Configuration.CATEGORY_GENERAL, "useVanillaWood", useVanillaWood,
				"Use vanilla logs for Traverse trees (might not look as nice)").getBoolean();
		registerBiomesRegardless = config.get(Configuration.CATEGORY_GENERAL, "registerBiomesRegardless",
				registerBiomesRegardless,
				"All biomes will always be registered, ignoring the instance traverse_world_data (WARNING: This will cause ugly world generation borders at the edge of what has previously been generated and what is new!!)")
				.getBoolean();
		disableCustomSkies = config.get(Configuration.CATEGORY_GENERAL, "disableCustomSkies", disableCustomSkies,
				"When true, Traverse will only use the default sky colour.").getBoolean();
		vanillaCanyonBlocks = config.get(Configuration.CATEGORY_GENERAL, "vanillaCanyonBlocks", vanillaCanyonBlocks,
				"Use vanilla Red Sandstone instead of Red Rock in the Canyon biome.").getBoolean();
		findBiomeCommandTimeout = config.get(Configuration.CATEGORY_GENERAL, "findBiomeCommandTimeout",
				findBiomeCommandTimeout,
				"How long, in ms, the /findbiome command will search for biomes before timing out, normally 20000ms")
				.getInt();
		disallowVillages = config.get(Configuration.CATEGORY_GENERAL, "disallowVillages", disallowVillages,
				"With this enabled, villages will not spawn in Traverse biomes").getBoolean();
		disallowBoulders = config
				.get(Configuration.CATEGORY_GENERAL, "disallowBoulders", disallowBoulders,
						"With this enabled, cobblestone boulder formations will not spawn in Traverse biomes")
				.getBoolean();

		disableAutumnalWoods = config.get(CATEGORY_BIOMES, "disableAutumnalWoods", disableAutumnalWoods,
				"Force disable the Autumnal Woods biome").getBoolean();
		disableWoodlands = config
				.get(CATEGORY_BIOMES, "disableWoodlands", disableWoodlands, "Force disable the Woodlands biome")
				.getBoolean();
		disableMiniJungle = config
				.get(CATEGORY_BIOMES, "disableMiniJungle", disableMiniJungle, "Force disable the Mini Jungle biome")
				.getBoolean();
		disableMeadow = config.get(CATEGORY_BIOMES, "disableMeadow", disableMeadow, "Force disable the Meadow biome")
				.getBoolean();
		disableLushSwamp = config
				.get(CATEGORY_BIOMES, "disableLushSwamp", disableLushSwamp, "Force disable the Lush Swamp biome")
				.getBoolean();
		disableRedDesert = config
				.get(CATEGORY_BIOMES, "disableRedDesert", disableRedDesert, "Force disable the Red Desert biome")
				.getBoolean();
		disableTemperateRainforest = config.get(CATEGORY_BIOMES, "disableTemperateRainforest",
				disableTemperateRainforest, "Force disable the Temperate Rainforest biome").getBoolean();
		disableBadlands = config
				.get(CATEGORY_BIOMES, "disableBadlands", disableBadlands, "Force disable the Badlands biome")
				.getBoolean();
		disableMountainousDesert = config.get(CATEGORY_BIOMES, "disableMountainousDesert", disableMountainousDesert,
				"Force disable the Mountainous Desert biome").getBoolean();
		disableRockyPlateau = config.get(CATEGORY_BIOMES, "disableRockyPlateau", disableRockyPlateau,
				"Force disable the Rocky Plateau biome").getBoolean();
		disableForestedHills = config.get(CATEGORY_BIOMES, "disableForestedHills", disableForestedHills,
				"Force disable the Forested Hills biome").getBoolean();
		disableBirchForestedHills = config.get(CATEGORY_BIOMES, "disableBirchForestedHills", disableBirchForestedHills,
				"Force disable the Birch Forested Hills biome").getBoolean();
		disableAutumnalWoodedHills = config.get(CATEGORY_BIOMES, "disableAutumnalWoodedHills",
				disableAutumnalWoodedHills, "Force disable the Autumnal Wooded Hills biome").getBoolean();
		disableCliffs = config.get(CATEGORY_BIOMES, "disableCliffs", disableCliffs, "Force disable the Cliffs biome")
				.getBoolean();
		disableGlacier = config
				.get(CATEGORY_BIOMES, "disableGlacier", disableGlacier, "Force disable the Glacier biome").getBoolean();
		disableGlacierSpikes = config.get(CATEGORY_BIOMES, "disableGlacierSpikes", disableGlacierSpikes,
				"Force disable the Glacier Spikes biome").getBoolean();
		disableSnowyConiferousForest = config.get(CATEGORY_BIOMES, "disableSnowyConiferousForest",
				disableSnowyConiferousForest, "Force disable the Snowy Coniferous Forest biome").getBoolean();
		disableLushHills = config
				.get(CATEGORY_BIOMES, "disableLushHills", disableLushHills, "Force disable the Lush Hills biome")
				.getBoolean();
		disableCanyon = config.get(CATEGORY_BIOMES, "disableCanyon", disableCanyon, "Force disable the Canyon biome")
				.getBoolean();
		disableCragCliffs = config
				.get(CATEGORY_BIOMES, "disableCragCliffs", disableCragCliffs, "Force disable the Crag Cliffs biome")
				.getBoolean();
		disableDesertShrubland = config.get(CATEGORY_BIOMES, "disableDesertShrubland", disableDesertShrubland,
				"Force disable the Desert Shrubland biome").getBoolean();
		disableThicket = config
				.get(CATEGORY_BIOMES, "disableThicket", disableThicket, "Force disable the Thicket biome").getBoolean();
		disableAridHighland = config.get(CATEGORY_BIOMES, "disableAridHighland", disableAridHighland,
				"Force disable the Arid Highland biome").getBoolean();
		disableRockyPlains = config
				.get(CATEGORY_BIOMES, "disableRockyPlains", disableRockyPlains, "Force disable the Rocky Plains biome")
				.getBoolean();

		enableNewTextures = config.get(Configuration.CATEGORY_CLIENT, "enableNewTextures", enableNewTextures,
				"Enable the new Traverse textures in the newer minecraft style").getBoolean();

		config.save();
	}

	public static TraverseConfig initialize() {
		if (instance == null)
			instance = new TraverseConfig();
		else
			throw new IllegalStateException("Cannot initialize config twice");

		return instance;
	}

	public static TraverseConfig getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Instance of config requested before initialization");
		}
		return instance;
	}

}