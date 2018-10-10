package com.robertx22.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.Random;

import com.robertx22.constants.Gear;
import com.robertx22.constants.Tags;
import com.robertx22.utilityclasses.ItemUtils;

public class GearCreator {

    public static ItemStack createGear(int lvl, int rarity, String type) {

     return null;
    }


    ItemStack setName(ItemStack item, int rarityNum) {

        item = item.setStackDisplayName(
                Gear.rarityChatColors[rarityNum] + "" + TextFormatting.BOLD + item.getDisplayName());

        return item;
    }

   

}
