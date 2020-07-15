package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class BaseCurio extends BaseGearType {

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return null;
    }

    public final int maximumRareAffixes(GearRarity rarity) {
        if (rarity.maxAffixes() > 2) {
            return rarity.maxAffixes() - 2;
        } else {
            return rarity.maxAffixes();
        }
    }

    @Override
    public int Weight() {
        return 750;
    }

}
