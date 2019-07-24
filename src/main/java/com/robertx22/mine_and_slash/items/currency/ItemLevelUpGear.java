package com.robertx22.mine_and_slash.items.currency;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemLevelUpGear extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "item_levelup";
    }

    private static final String name = Ref.MODID + ":currency/item_levelup";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":item_levelup");
    }

    public ItemLevelUpGear() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.level++;
        gear.timesLeveledUp++;
        Gear.Save(stack, gear);

        return stack;
    }

    public static final int MAXIMUM_LEVEL_UPS = 10;

    @Override
    public int rarity() {
        return 2;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.timesLeveledUp < MAXIMUM_LEVEL_UPS && gear.level < ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                .get();

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Who said your sword can't level with you?");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Crystal Of Ascension";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases item level";
    }

}