package com.robertx22.mine_and_slash.database.items.currency.map;

import com.robertx22.mine_and_slash.database.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.database.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.item_types.MapReq;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddMapTier extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return "currency/add_map_tier";
    }

    private static final String name = Ref.MODID + ":currency/add_map_tier";

    public ItemAddMapTier() {
        super(name);
        this.itemTypesUsableOn = ItemType.MAP;
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        MapItemData map = Map.Load(stack);

        map.tier++;

        Map.Save(stack, map);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(MapReq.INSTANCE);
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Venture deeper.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Endless Road";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases map tier";
    }

    @Override
    public boolean addsInstability() {
        return false;
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }

}