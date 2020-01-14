package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class EntitySpellDataSaving {
    private static final String LOC = "entitySpellData";

    public static boolean has(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(LOC);
    }

    public static EntitySpellData Load(CompoundNBT nbt) {
        return LoadSave.Load(EntitySpellData.class, new EntitySpellData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, EntitySpellData data) {
        if (data != null) {
            LoadSave.Save(data, nbt, LOC);
        }
    }
}
