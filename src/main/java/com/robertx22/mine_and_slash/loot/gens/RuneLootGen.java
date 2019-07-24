package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.RuneBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class RuneLootGen extends BaseLootGen {

    public RuneLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNE_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.Rune;
    }

    @Override
    public ItemStack generateOne() {

        RuneBlueprint runePrint = new RuneBlueprint(info.level);

        return Create(runePrint);

    }

    public static ItemStack Create(RuneBlueprint blueprint) {

        int rarity = blueprint.getRarity();

        BaseRuneItem item = blueprint.getRuneItem().byRarity(rarity);

        ItemStack stack = new ItemStack(item);

        RuneItemData data = new RuneItemData();

        data.rarity = item.rarity;
        data.name = item.name();
        data.level = blueprint.level;

        data.armor = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item
                .armorStat()));

        data.weapon = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item
                .weaponStat()));

        data.jewerly = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item
                .jewerlyStat()));

        Rune.Save(stack, data);

        return stack;

    }

}
