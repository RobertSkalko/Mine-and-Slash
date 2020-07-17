package com.robertx22.mine_and_slash.vanilla_mc.items.gearitems.bases.armor_materials;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.inventory.EquipmentSlotType;

public class ClothMat extends BaseMat {

    static int[] damageReductions = new int[]{
        2,
        4,
        6,
        3
    };

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 1000;
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
        return Ref.MODID + ":" + "cloth";
    }

    @Override
    public float getToughness() {
        return 2;
    }

}
