package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.new_content_test.blueprints.BlueprintItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class Blueprint {
    private static final String LOC = "BLUEPRINT_ITEM_DATA";

    public static boolean has(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(LOC);
    }

    public static BlueprintItemData Load(ItemStack stack) {

        if (stack == null) {
            return null;
        }
        if (!stack.hasTag()) {
            return null;
        }

        return LoadSave.Load(BlueprintItemData.class, new BlueprintItemData(), stack.getTag(), LOC);

    }

    public static void Save(ItemStack stack, BlueprintItemData gear) {

        if (stack == null) {
            return;
        }
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        if (gear != null) {
            LoadSave.Save(gear, stack.getTag(), LOC);
        }

    }

}
