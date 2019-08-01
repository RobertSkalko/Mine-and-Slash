package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RerollPrefixNumbers extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "reroll_prefix_numbers";
    }

    private static final String name = Ref.MODID + ":currency/reroll_prefix_numbers";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":reroll_prefix_numbers");
    }

    public RerollPrefixNumbers() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.prefix.RerollNumbers(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.AFFIXES, SimpleGearLocReq.HAS_PREFIX);
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("I command you to change!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Prefix Blessing";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls numbers of a prefix";
    }
}