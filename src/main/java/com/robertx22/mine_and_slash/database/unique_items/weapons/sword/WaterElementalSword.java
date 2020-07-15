package com.robertx22.mine_and_slash.database.unique_items.weapons.sword;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseGearType;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.melee.GemstoneSword;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaOnHit;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

import java.util.Arrays;
import java.util.List;

public class WaterElementalSword implements IUnique {
    @Override
    public List<StatModifier> uniqueStats() {
        return Arrays.asList(
            new StatModifier(1, 3, 4, 13, new WeaponDamage(Elements.Water), ModType.FLAT),
            new StatModifier(10, 50, new WeaponDamage(Elements.Water), ModType.LOCAL_INCREASE),
            new StatModifier(1, 2, ManaOnHit.getInstance(), ModType.FLAT)
        );
    }

    @Override
    public String locDescForLangFile() {
        return "Essence lies in water.";
    }

    @Override
    public String locNameForLangFile() {
        return "Water Elemental";
    }

    @Override
    public String GUID() {
        return "water_ele_sword";
    }

    @Override
    public BaseGearType getBaseGearType() {
        return GemstoneSword.INSTANCE;
    }
}
