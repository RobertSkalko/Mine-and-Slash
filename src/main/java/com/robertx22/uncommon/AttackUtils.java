package com.robertx22.uncommon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class AttackUtils {

    public static void NoEnergyMessage(EntityLivingBase entity) {
	entity.sendMessage(new TextComponentString(TextFormatting.RED + "Not Enough Energy."));
    }
}
