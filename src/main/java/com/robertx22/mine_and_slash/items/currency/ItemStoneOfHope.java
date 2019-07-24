package com.robertx22.mine_and_slash.items.currency;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemStoneOfHope extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "stone_of_hope";
    }

    public static final String ID = Ref.MODID + ":currency/stone_of_hope";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":stone_of_hope");
    }

    public ItemStoneOfHope() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.SetSpecificType(gear.gearTypeName);
        gearPrint.minRarity = gear.Rarity + 1;
        gearPrint.LevelRange = false;

        GearItemData newgear = GearLootGen.CreateData(gearPrint);
        gear.WriteOverDataThatShouldStay(newgear);

        ItemStack result = ItemStack.EMPTY;

        if (gear.changesItemStack()) {
            result = GearLootGen.CreateStack(newgear);
        } else {
            result = stack;
            Gear.Save(result, newgear);
        }

        return result;

    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null && gear.Rarity < Rarities.MAXIMUM_ITEM_RARITY) {
            return true;
        }

        return false;
    }

    @Override
    public int rarity() {
        return 4;
    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("When there is hope, there is a way.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Stone Of Hope";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform any rarity gear into higher rarity";
    }
}