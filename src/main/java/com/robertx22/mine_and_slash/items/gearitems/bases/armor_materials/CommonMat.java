package com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.inventory.EquipmentSlotType;

public class CommonMat extends BaseMat {

    static int[] damageReductions = new int[]{1, 2, 3, 1};

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 700;
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
        return Ref.MODID + ":common";
    }

    @Override
    public float getToughness() {
        return 2;
    }

}
