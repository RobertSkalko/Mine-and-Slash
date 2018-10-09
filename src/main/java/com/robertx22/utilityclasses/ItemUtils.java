package com.robertx22.utilityclasses;

import com.robertx22.constants.Stat;
import com.robertx22.constants.Stats;
import com.robertx22.constants.Tag;
import com.robertx22.customitems.MyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class ItemUtils {

    public static boolean isGear(ItemStack item) {
        if (item.hasTagCompound()) {
            return item.getTagCompound().getBoolean(Tag.IS_GEAR);
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

    public static int randomizeStat(Stat stat) {

        return stat.minValue + (new Random().nextInt(stat.maxValue - stat.minValue));

    }

    public static int randomizeBaseStat(Stat stat, int rarity) {

        return (stat.maxValue / 5 * rarity) + stat.minValue + (new Random().nextInt(stat.maxValue - stat.minValue));

    }

    public static ItemStack upgradeItem(ItemStack item) {

        NBTTagCompound nbt = item.getTagCompound();

        int upgradeNumber = nbt.getInteger(Tag.UPGRADE_NUMBER) + 1;

        nbt.setInteger(Tag.UPGRADE_NUMBER, upgradeNumber);

        item.setTagCompound(nbt);

        incrementAllScalingStatsByPercent(item, 10);

        return item;
    }

    public static ItemStack incrementAllScalingStatsByPercent(ItemStack item, int percent) {

        NBTTagCompound NBT = item.getTagCompound();

        NBTTagCompound statsNBT = NBT.getCompoundTag(Tag.STATS);

        for (Stat stat : Stats.getAllStats()) {

            if (stat.isPercent) {
                continue;
            }

            int currentStat = statsNBT.getInteger(stat.name);

            if (currentStat > 0) {

                int incrementedStat = currentStat + currentStat * percent / 100;

                statsNBT.setInteger(stat.name, incrementedStat);

            }

        }

        NBT.setTag(Tag.STATS, statsNBT);

        item.setTagCompound(NBT);

        return item;
    }

}
