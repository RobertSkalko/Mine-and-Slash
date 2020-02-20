package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.bases.UniqueRunePart;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.Item;
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
        BaseRune rune = uniqueRunePart.get();

        Item item = rune.getItemFromRegistry(IRarity.Unique);

        ItemStack stack = ItemStack.EMPTY;

        if (rune != null && item != null) {
            stack = new ItemStack(item);

            RuneItemData data = new RuneItemData();

            data.rarity = rarity.get()
                .Rank();
            data.name = rune.GUID();
            data.level = level.get();
            data.tier = rune.Tier();

            data.armor = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(rune.armorStat()));

            data.weapon = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(rune.weaponStat()));

            data.jewerly = StatModData.NewRandom(data.getRarity(), RandomUtils.weightedRandom(rune.jewerlyStat()));

            Rune.Save(stack, data);
        }

        return stack;

    }

    @Override
    public BaseRaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Runes;
    }

}

