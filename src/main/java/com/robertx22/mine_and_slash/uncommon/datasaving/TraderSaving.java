package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.gui.trader.TraderData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class TraderSaving {

    private static final String LOC = "mmorpg:trader";

    public static boolean has(ItemStack stack) {
        return stack.hasTag() && stack.getTag()
            .contains(LOC);
    }

    public static TraderData Load(CompoundNBT nbt) {
        try {
            return LoadSave.Load(TraderData.class, new TraderData(), nbt, LOC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void Save(CompoundNBT nbt, TraderData gear) {
        try {
            LoadSave.Save(gear, nbt, LOC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
