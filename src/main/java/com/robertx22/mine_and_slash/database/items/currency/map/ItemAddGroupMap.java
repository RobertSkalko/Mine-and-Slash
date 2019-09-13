package com.robertx22.mine_and_slash.database.items.currency.map;

import com.robertx22.mine_and_slash.database.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.database.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleMapLocReq;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddGroupMap extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return "currency/add_group_map";
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
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(SimpleMapLocReq.NO_GROUP_MAP_AFFIX);
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
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

    @Override
    public boolean addsInstability() {
        return false;
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }
}

