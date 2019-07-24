package com.robertx22.mine_and_slash.items.currency;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemClearRunes extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return name;
    }

    private static final String name = Ref.MODID + ":currency/clear_runes";

    public ItemClearRunes() {
        super(name);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        gear.runes.clearRunes();
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        if (gear.isRuned()) {
            return true;
        }

        return false;
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
        return Arrays.asList("If only we could re-write the past.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb of Forgetfulness";
    }

    @Override
    public String locDescForLangFile() {
        return "Clears runes and runewords from an item.";
    }
}