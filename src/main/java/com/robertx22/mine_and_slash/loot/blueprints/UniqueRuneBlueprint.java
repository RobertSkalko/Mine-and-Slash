package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.bases.UniqueRunePart;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class UniqueRuneBlueprint extends ItemBlueprint {

    public UniqueRuneBlueprint(int level, int tier) {
        super(level);
        this.tier.number = tier;

        this.rarity.setSpecificRarity(IRarity.Unique);
    }

    public UniqueRunePart uniqueRunePart = new UniqueRunePart(this);

    @Override
    ItemStack generate() {
        BaseRuneItem item = uniqueRunePart.get();

        ItemStack stack = ItemStack.EMPTY;

        if (item != null) {
            stack = new ItemStack(item);

            RuneItemData data = new RuneItemData();

            data.rarity = item.rarity;
            data.name = item.GUID();
            data.level = level.get();
            data.tier = item.Tier();

            data.armor = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item.armorStat()));

            data.weapon = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item.weaponStat()));

            data.jewerly = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(item.jewerlyStat()));

            Rune.Save(stack, data);
        }

        return stack;

    }

    @Override
    public BaseRaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Runes;
    }

}

