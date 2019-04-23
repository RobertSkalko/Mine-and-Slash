package com.robertx22.mmorpg.config;

import net.minecraftforge.common.config.Config;

public class EntityConfigs {

    @Config.Name("NPC Config")
    @Config.LangKey("mmorpg.word.npc")
    @Config.Comment("")
    public EntityConfig NPC_CONFIG = new EntityConfig(0.3F, 0.3F);

    @Config.Name("Mob Config")
    @Config.LangKey("mmorpg.word.mob")
    @Config.Comment("")
    public EntityConfig MOB_CONFIG = new EntityConfig(1, 1);

    @Config.Name("Animal Config")
    @Config.LangKey("mmorpg.word.animal")
    @Config.Comment("")
    public EntityConfig ANIMAL_CONFIG = new EntityConfig(0.01F, 0.05F);

    @Config.Name("Other Entities Config")
    @Config.LangKey("mmorpg.word.other_entities")
    @Config.Comment("")
    public EntityConfig OTHER_CONFIG = new EntityConfig(0, 0);

    public static class EntityConfig {

	public EntityConfig(float loot, float exp) {
	    this.LOOT_MULTI = loot;
	    this.EXP_MULTI = exp;
	}

	@Config.Name("Loot Multi")
	@Config.LangKey("mmorpg.config.loot_multi")
	@Config.Comment("")
	public float LOOT_MULTI = 0;

	@Config.Name("Exp Multi")
	@Config.LangKey("mmorpg.config.exp_multi")
	@Config.Comment("")
	public float EXP_MULTI = 0;

    }
}
