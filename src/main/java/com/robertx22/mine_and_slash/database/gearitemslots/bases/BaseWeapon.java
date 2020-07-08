package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.Arrays;
import java.util.List;

public abstract class BaseWeapon extends GearItemSlot {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.MAINHAND;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
            new PosStats(new PhysicalDamageFlat()).weight(32000),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.HALF)),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.HALF)),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.HALF)),
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.HALF))
        );
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Weapon;
    }

}
