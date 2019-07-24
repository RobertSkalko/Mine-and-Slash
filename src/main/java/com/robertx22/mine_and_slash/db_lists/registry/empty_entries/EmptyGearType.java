package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmptyGearType extends GearItemSlot {
    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return new ArrayList<>();
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return new ArrayList<>();
    }

    @Override
    public Item DefaultItem() {
        return Items.AIR;
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return new HashMap<>();
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return "";
    }
}
