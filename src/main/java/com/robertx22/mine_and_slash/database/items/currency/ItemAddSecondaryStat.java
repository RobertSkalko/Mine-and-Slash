package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddSecondaryStat extends CurrencyItem implements ICurrencyItemEffect, IRenamed {

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":add_secondary_stat");
    }

    @Override
    public String GUID() {
        return name;
    }

    private static final String name = Ref.MODID + ":currency/add_secondary_stat";

    public ItemAddSecondaryStat() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.secondaryStats.AddStat(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.SECONDARY_STATS, SimpleGearLocReq.SEC_STAT_NOT_ALREADY_ADDED);
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("More power is always good, right?");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Crystal Of Legend";
    }

    @Override
    public String locDescForLangFile() {
        return "Add another secondary stat";
    }

    @Override
    public int instabilityAddAmount() {
        return 10;
    }
}