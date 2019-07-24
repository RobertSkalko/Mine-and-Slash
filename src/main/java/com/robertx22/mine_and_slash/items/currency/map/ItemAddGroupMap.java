package com.robertx22.mine_and_slash.items.currency.map;

import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddGroupMap extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return "add_group_map";
    }

    private static final String name = Ref.MODID + ":currency/add_group_map";

    public ItemAddGroupMap() {

        super(name);

        this.itemTypesUsableOn = ItemType.MAP;

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        MapItemData map = Map.Load(stack);

        map.groupPlay = true;
        map.maxPlayersInGroup = 5;

        Map.Save(stack, map);

        return stack;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {
        MapItemData map = Map.Load(stack);
        return map != null && map.groupPlay == false;
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public int rarity() {
        return 4;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("What you can't do alone..");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Stone of Solidarity";
    }

    @Override
    public String locDescForLangFile() {
        return "Turns a Map into a group map.";
    }
}

