package com.robertx22.item;

import com.robertx22.constants.Stat;
import com.robertx22.constants.Stats;
import com.robertx22.constants.Tag;
import com.robertx22.customitems.MyItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;
import java.util.Random;

public class SocketCreator {

    static public ItemStack createSocket(int lvl, int rarity, String gearType) {

        ItemStack item = new ItemStack(MyItems.magic_socket);

        if (rarity == 1) {
            item = new ItemStack(MyItems.rare_socket);
        }
        if (rarity == 2) {
            item = new ItemStack(MyItems.epic_socket);
        }
        if (rarity == 3) {
            item = new ItemStack(MyItems.legendary_socket);
        }
        if (rarity == 4) {
            item = new ItemStack(MyItems.mythical_socket);
        }

        List<Stat> randomStats = Stats.getAllRandomStats();

        NBTTagCompound sockets = new NBTTagCompound();

        Random ran = new Random();

        int statAmount = ran.nextInt(2) + 1;

        sockets.setString(Tag.GEAR_TYPE, gearType);
        sockets.setInteger(Tag.RARITY_NUMBER, rarity);

        while (statAmount > 0) {

            int statNum = ran.nextInt(randomStats.size());
            Stat ranStat = randomStats.get(statNum);

            if (sockets.hasKey(ranStat.name)) {
                if (sockets.getInteger(ranStat.name) != 0) {
                    continue;
                }
            }

            if (ranStat.type != gearType) {
                continue;
            }

            int num = ran.nextInt(ranStat.maxValue / 2) + (int) ((float) ranStat.maxValue / 5 * (rarity + 1));

            // if the stat scales / not a perecent, multiply with lvl
            if (!randomStats.get(statNum).isPercent) {
                num *= lvl;
            }

            sockets.setInteger(ranStat.name, num);

            statAmount--;

        }

        item.setTagCompound(sockets);

        return item;
    }
}

