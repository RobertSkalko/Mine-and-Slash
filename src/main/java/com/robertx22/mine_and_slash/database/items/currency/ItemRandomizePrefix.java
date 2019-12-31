package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemRandomizePrefix extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "currency/randomize_prefix";
    }

    private static final String name = Ref.MODID + ":currency/randomize_prefix";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":randomize_prefix");
    }

    public ItemRandomizePrefix() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.prefix.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.AFFIXES, SimpleGearLocReq.HAS_PREFIX);
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Common;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("There is always a better choice");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Ever-Changing Prefix";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls prefix";
    }

    @Override
    public int instabilityAddAmount() {
        return 5;
    }
}