package com.robertx22.mine_and_slash.database.status_effects;

import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseMobEleDMG;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class MobWaterDMGSE extends BaseMobEleDMG {

    @Override
    public Item ItemModel() {
        return Items.WATER_BUCKET;
    }

    @Override
    public String GUID() {
        return "mob_water_damage_se";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new ElementalSpellToAttackDMGFlat(Elements.Water), percent));
    }

}
