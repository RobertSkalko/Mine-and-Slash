package com.robertx22.mine_and_slash.database.status_effects;

import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class MobElementResistSE extends BaseStatusEffect {

    @Override
    public Item ItemModel() {
        return Items.GOLDEN_CHESTPLATE;
    }

    @Override
    public String GUID() {
        return "MobElementResistSE";
    }

    int percent = 300;

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new ElementalResistFlat(Elements.Fire), percent), StatModData
                .Load(new ElementalResistFlat(Elements.Water), percent), StatModData.Load(new ElementalResistFlat(Elements.Thunder), percent), StatModData
                .Load(new ElementalResistFlat(Elements.Nature), percent));
    }

}
