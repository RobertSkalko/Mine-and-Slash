package com.robertx22.mine_and_slash.database.status_effects;

import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class MobArmorSE extends BaseStatusEffect {

    @Override
    public Item ItemModel() {
        return Items.IRON_CHESTPLATE;
    }

    @Override
    public String GUID() {
        return "mob_armor_se";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new ArmorFlat(), 300));
    }

}
