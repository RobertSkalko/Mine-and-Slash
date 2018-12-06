package com.robertx22.database.status.effects;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MobLifestealSE extends BaseStatusEffect {

    @Override
    public Item ItemModel() {
	return Items.SPECKLED_MELON;
    }

    @Override
    public String GUID() {
	return "MobLifestealSE";
    }

    @Override
    public List<StatModData> Stats() {
	return Arrays.asList(StatModData.NewStatusEffect(1200, new LifestealFlat()));
    }

}
