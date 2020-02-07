package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class RunedGearLootGen extends BaseLootGen<RunedGearBlueprint> {

    public RunedGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.RunedItem;
    }

    @Override
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.RUNES_AND_RUNED_GEAR_DROP_AFTER_LEVEL.get();
    }
    
    @Override
    public ItemStack generateOne() {
        RunedGearBlueprint blueprint = new RunedGearBlueprint(info.level);
        return blueprint.generate();

    }

}
