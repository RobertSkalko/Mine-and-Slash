package com.rabbit.gui.render;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextRenderer {

	/**
	 * Returns main FontRenderer (commonly picked from Minecraft.class instance)
	 *
	 * @return {@link FontRenderer} instance
	 */
	public static FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}

	/**
	 * See {@link #renderString(int, int, String, int, boolean)}
	 */
	public static int renderString(int xPos, int yPos, String text) {
		return TextRenderer.renderString(xPos, yPos, text, Color.white, TextAlignment.LEFT);
	}

	public static int renderString(int xPos, int yPos, String text, Color color) {
		return TextRenderer.renderString(xPos, yPos, text, color, false, TextAlignment.LEFT);
	}

	/**
	 * Renders string at the givens x and y
	 *
	 * @param xPos
	 * @param yPos
	 * @param text
	 * @param color
	 * @param shadow
	 * @return X position of rendered string
	 */
	public static int renderString(int xPos, int yPos, String text, Color color, boolean shadow, TextAlignment align) {
		switch (align) {
		case LEFT:
			return TextRenderer.getFontRenderer().drawString(text, xPos, yPos, color.getRGB(), shadow);
		case CENTER:
			return TextRenderer.getFontRenderer().drawString(text,
					xPos - (TextRenderer.getFontRenderer().getStringWidth(text) / 2), yPos, color.getRGB(), shadow);
		case RIGHT:
			return TextRenderer.getFontRenderer().drawString(text,
					xPos - TextRenderer.getFontRenderer().getStringWidth(text), yPos, color.getRGB(), shadow);
		}
		return -1;
	}

	/**
	 * See {@link #renderString(int, int, String, int, boolean)}
	 */
	public static int renderString(int xPos, int yPos, String text, Color color, TextAlignment align) {
		return TextRenderer.renderString(xPos, yPos, text, color, false, align);
	}

	public static int renderString(int xPos, int yPos, String text, TextAlignment align) {
		return TextRenderer.renderString(xPos, yPos, text, Color.white, align);
	}

	/**
	 * See {@link #renderString(int, int, String, int, boolean)}
	 */
	public static int renderUnicodeString(int xPos, int yPos, String text, Color color, TextAlignment align) {
		TextRenderer.getFontRenderer().setUnicodeFlag(true);
		int retVal = TextRenderer.renderString(xPos, yPos, text, color, false, align);
		TextRenderer.getFontRenderer().setUnicodeFlag(false);
		return retVal;
	}

	public static int renderUnicodeString(int xPos, int yPos, String text, TextAlignment align) {
		TextRenderer.getFontRenderer().setUnicodeFlag(true);
		int retVal = TextRenderer.renderString(xPos, yPos, text, Color.white, align);
		TextRenderer.getFontRenderer().setUnicodeFlag(false);
		return retVal;
	}
}
