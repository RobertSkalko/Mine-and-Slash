package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class Map {
    private static final String LOC = "MAP_ITEM_DATA";
    private static final String NBT_LOC = "mapObject";

    public static boolean has(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(LOC);
    }

    public static MapItemData Load(ItemStack stack) {

        if (stack == null) {
            return null;
        }
        if (!stack.hasTag()) {
            return null;
        }

        return LoadSave.Load(MapItemData.class, new MapItemData(), stack.getTag(), LOC);

    }

    public static void Save(ItemStack stack, MapItemData gear) {

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

    public static MapItemData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(MapItemData.class, new MapItemData(), nbt, NBT_LOC);

    }

    public static void Save(CompoundNBT nbt, MapItemData gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, NBT_LOC);
        }

    }

}
