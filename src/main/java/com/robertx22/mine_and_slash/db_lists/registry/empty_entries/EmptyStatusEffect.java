package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EmptyStatusEffect extends BaseStatusEffect {
    @Override
    public Item ItemModel() {
        return Items.AIR;
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public List<StatModData> Stats() {
        return new ArrayList<>();
    }
}
