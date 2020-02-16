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
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
            new PosStats(new PhysicalDamageFlat()).weight(32000),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.VERY_LOW), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.VERY_LOW)),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.VERY_LOW), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.VERY_LOW)),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.VERY_LOW), new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.VERY_LOW)),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.VERY_LOW), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.VERY_LOW))
        );
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new LifestealFlat(), new LifeOnHitFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Weapon;
    }

}
