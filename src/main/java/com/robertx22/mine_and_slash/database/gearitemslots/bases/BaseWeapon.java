package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public abstract class BaseWeapon extends GearItemSlot implements IWeapon {

    @Override
    public List<PosStats> PrimaryStats() {
        return Arrays.asList(
                new PosStats(new PhysicalDamageFlat()).weight(32000),
                new PosStats(new PhysicalDamageFlat(), new ElementalAttackDamageFlat(Elements.Nature)),
                new PosStats(new PhysicalDamageFlat(), new ElementalAttackDamageFlat(Elements.Fire)),
                new PosStats(new PhysicalDamageFlat(), new ElementalAttackDamageFlat(Elements.Water)),
                new PosStats(new PhysicalDamageFlat(), new ElementalAttackDamageFlat(Elements.Thunder))
        );
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
