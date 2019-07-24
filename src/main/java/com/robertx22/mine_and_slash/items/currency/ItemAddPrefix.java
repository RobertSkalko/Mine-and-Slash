package com.robertx22.mine_and_slash.items.currency;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrefixData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddPrefix extends CurrencyItem implements ICurrencyItemEffect, IRenamed {

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":add_prefix");
    }

    @Override
    public String GUID() {
        return "add_prefix";
    }

    public static final String ID = Ref.MODID + ":currency/add_prefix";

    public ItemAddPrefix() {
        super(ID);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.prefix = new PrefixData();
        gear.prefix.RerollFully(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.prefix == null && gear.getGearEnum()
                .canGetAffixes(gear);
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public int rarity() {
        return 4;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Unchart your potential.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Unearth Prefix";
    }

    @Override
    public String locDescForLangFile() {
        return "Add a prefix";
    }
}