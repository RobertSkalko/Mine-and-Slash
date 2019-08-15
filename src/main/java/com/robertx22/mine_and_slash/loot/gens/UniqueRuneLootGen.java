package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueRuneBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.item.ItemStack;

public class UniqueRuneLootGen extends BaseLootGen {

    public UniqueRuneLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.UNIQUE_RUNE_DROPRATE.get().floatValue();
    }

    @Override
    public boolean condition() {
        return WorldUtils.dropsUniques(info.world);
    }

    @Override
    public LootType lootType() {
        return LootType.UniqueRune;
    }

    @Override
    public ItemStack generateOne() {

        UniqueRuneBlueprint runePrint = new UniqueRuneBlueprint(info.level, info.tier);

        return Create(runePrint);

    }

    public static ItemStack Create(UniqueRuneBlueprint blueprint) {

        BaseRuneItem item = blueprint.getRuneItem();

        ItemStack stack = ItemStack.EMPTY;

        if (item != null) {
            stack = new ItemStack(item);

            RuneItemData data = new RuneItemData();

            data.rarity = item.rarity;
            data.name = item.GUID();
            data.level = blueprint.level;
            data.tier = item.Tier();

            data.armor = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item
                    .armorStat()));

            data.weapon = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item
                    .weaponStat()));

            data.jewerly = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item
                    .jewerlyStat()));

            Rune.Save(stack, data);
        }

        return stack;

    }

}
