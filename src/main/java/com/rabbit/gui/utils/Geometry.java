package com.rabbit.gui.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Geometry {

	/**
	 * Returns current Minecraft scale factor based on width and height
	 *
	 * @return Minecraft scale factor
	 */
	public static int computeScaleFactor() {
		Minecraft mc = Minecraft.getMinecraft();
		return new ScaledResolution(mc).getScaleFactor();
	}

	public static boolean isDotInArea(int areaX, int areaY, int areaWidth, int areaHeight, int dotX, int dotY) {
		return (dotX >= areaX) && (dotX <= (areaX + areaWidth)) && (dotY >= areaY) && (dotY <= (areaY + areaHeight));
	}

}
