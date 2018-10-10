package com.robertx22.utilityclasses;

import com.robertx22.constants.Tags;
import com.robertx22.customitems.MyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class ItemUtils {

    public static boolean isGear(ItemStack item) {
        if (item.hasTagCompound()) {
            return item.getTagCompound().getBoolean(Tags.IS_GEAR);
        }
        else {
            return false;
        }
    }

    public static boolean isPowder(Item item) {

        if (item.equals(MyItems.magic_powder)
                || item.equals(MyItems.rare_powder)
                || item.equals(MyItems.epic_powder)
                || item.equals(MyItems.legendary_powder)
                || item.equals(MyItems.mythical_powder)) {
            return true;
        }
        return false;

    }

    public static boolean isOre(Item item) {

        if (item.equals(MyItems.magic_ore)
                || item.equals(MyItems.rare_ore)
                || item.equals(MyItems.epic_ore)
                || item.equals(MyItems.legendary_ore)
                || item.equals(MyItems.mythical_ore)) {
            return true;
        }
        return false;

    }

    public static boolean isSocket(Item item) {

        if (item.equals(MyItems.magic_socket)
                || item.equals(MyItems.rare_socket)
                || item.equals(MyItems.epic_socket)
                || item.equals(MyItems.legendary_socket)
                || item.equals(MyItems.mythical_socket)) {
            return true;
        }
        return false;

    }

  

    public static ItemStack upgradeItem(ItemStack item) {

        NBTTagCompound nbt = item.getTagCompound();

        int upgradeNumber = nbt.getInteger(Tags.UPGRADE_NUMBER) + 1;

        nbt.setInteger(Tags.UPGRADE_NUMBER, upgradeNumber);

        item.setTagCompound(nbt);

        incrementAllScalingStatsByPercent(item, 10);

        return item;
    }

    public static ItemStack incrementAllScalingStatsByPercent(ItemStack item, int percent) {
return item;
    }

}
