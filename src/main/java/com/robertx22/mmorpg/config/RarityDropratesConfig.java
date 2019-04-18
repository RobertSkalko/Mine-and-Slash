package com.robertx22.mmorpg.config;

import net.minecraftforge.common.config.Config;

public class RarityDropratesConfig {
    public static class RarityWeights {

	@Config.Name("Item Weights")
	@Config.LangKey("mmorpg.word.item")
	@Config.Comment("")
	public RarityWeight ITEMS = new RarityWeight();

	@Config.Name("Runed Item Weights")
	@Config.LangKey("mmorpg.word.runed_item")
	@Config.Comment("")
	public RarityWeight RUNED_ITEMS = new RarityWeight();

	@Config.Name("Rune Weights")
	@Config.LangKey("mmorpg.word.rune")
	@Config.Comment("")
	public RarityWeight RUNES = RarityWeight.Runes();

	@Config.Name("Mob Weights")
	@Config.LangKey("mmorpg.word.mob")
	@Config.Comment("")
	public RarityWeight MOBS = new RarityWeight();

	@Config.Name("Map Weights")
	@Config.LangKey("mmorpg.word.map")
	@Config.Comment("")
	public RarityWeight MAPS = new RarityWeight();

	@Config.Name("Currency Weights")
	@Config.LangKey("mmorpg.word.currency")
	@Config.Comment("")
	public RarityWeight CURRENCY = new RarityWeight();

	@Config.Name("Spells Weights")
	@Config.LangKey("mmorpg.word.spell")
	@Config.Comment("")
	public RarityWeight SPELLS = new RarityWeight();

    }

    public static class RarityWeight {

	public static RarityWeight Runes() {
	    RarityWeight r = new RarityWeight();
	    r.COMMON_WEIGHT = 12000;
	    r.UNCOMMON_WEIGHT = 7000;
	    r.RARE_WEIGHT = 4000;
	    r.LEGENDARY_WEIGHT = 1500;
	    r.MYTHICAL_WEIGHT = 500;
	    return r;

	}

	@Config.Name("Common")
	@Config.LangKey("mmorpg.rarity.common")
	@Config.Comment("")
	public int COMMON_WEIGHT = 25000;

	@Config.Name("Uncommon")
	@Config.LangKey("mmorpg.rarity.uncommon")
	@Config.Comment("")
	public int UNCOMMON_WEIGHT = 20000;

	@Config.Name("Rare")
	@Config.LangKey("mmorpg.rarity.rare")
	@Config.Comment("")
	public int RARE_WEIGHT = 5000;

	@Config.Name("Epic")
	@Config.LangKey("mmorpg.rarity.epic")
	@Config.Comment("")
	public int EPIC_WEIGHT = 3000;

	@Config.Name("Legendary")
	@Config.LangKey("mmorpg.rarity.legendary")
	@Config.Comment("")
	public int LEGENDARY_WEIGHT = 1250;

	@Config.Name("Mythical")
	@Config.LangKey("mmorpg.rarity.mythical")
	@Config.Comment("")
	public int MYTHICAL_WEIGHT = 300;

    }
}
