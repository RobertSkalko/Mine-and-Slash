package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;

import java.util.Arrays;
import java.util.List;

public abstract class BaseWeapon extends GearItemSlot implements IWeapon {

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new PhysicalDamageFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new LifestealFlat(), new LifeOnHitFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Weapon;
    }

}
