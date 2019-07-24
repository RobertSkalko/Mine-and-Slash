package com.robertx22.mine_and_slash.items.currency;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemChaosOrb extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "chaos_orb";
    }

    public static final String ID = Ref.MODID + ":currency/chaos_orb";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":chaos_orb");
    }

    public ItemChaosOrb() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear.chaosStats == null && gear.getGearEnum().canGetChaosStats(gear)) {
            return true;
        }

        return false;
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public int rarity() {
        return 1;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Do not gamble what you are not willing to lose.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Chaos Orb";
    }

    @Override
    public String locDescForLangFile() {
        return "Permanently adds a Chaos stat";
    }

}
