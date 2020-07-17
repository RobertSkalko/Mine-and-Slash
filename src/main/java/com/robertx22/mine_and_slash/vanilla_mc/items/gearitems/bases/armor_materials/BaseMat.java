package com.robertx22.mine_and_slash.vanilla_mc.items.gearitems.bases.armor_materials;

import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public abstract class BaseMat implements IArmorMaterial {

    public int getDurability() {
        return getDurability(null);
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(Items.STRUCTURE_BLOCK); // as in, nothing besides creative items should repair it
    }

}
