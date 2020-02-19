package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runes.base.RuneItem;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.bases.RunePart;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class RuneBlueprint extends ItemBlueprint {

    public RuneBlueprint(int level) {
        super(level);

    }

    public RunePart runePart = new RunePart(this);

    @Override
    public ItemStack generate() {

        BaseRune rune = runePart.get();
        rune.rarity = rarity.get()
            .Rank();
        RuneItem item = rune.byRarityItem(rarity.get()
            .Rank());

        if (rune != null && item != null) {

            ItemStack stack = new ItemStack(item);

            RuneItemData data = new RuneItemData();

            data.rarity = item.rarity;
            data.name = rune.GUID();
            data.level = level.get();

            data.armor = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(rune.armorStat()));

            data.weapon = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(rune.weaponStat()));

            data.jewerly = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(rune.jewerlyStat()));

            Rune.Save(stack, data);

            return stack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public BaseRaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Runes;
    }

}
