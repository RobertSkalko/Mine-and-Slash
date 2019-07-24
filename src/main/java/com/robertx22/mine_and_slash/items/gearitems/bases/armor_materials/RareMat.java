package com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.inventory.EquipmentSlotType;

public class RareMat extends BaseMat {

    static int[] damageReductions = new int[]{2, 4, 5, 2};

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 900;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReductions[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public String getName() {
        return Ref.MODID + ":" + "rare";
    }

    @Override
    public float getToughness() {
        return 2;
    }

}
