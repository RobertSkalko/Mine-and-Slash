package com.libraries.rabbit.gui.component.display.tabs;

import java.awt.Color;

import com.libraries.rabbit.gui.component.display.DisplayItem;
import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ItemTab extends Tab {

	private String title;
	private DisplayItem disItem;

	public ItemTab(int x, int y, int width, int height) {
		this(x, y, width, height, Blocks.BARRIER);
	}

	public ItemTab(int x, int y, int width, int height, Block block) {
		this(x, y, width, height, 0, block);
	}

	public ItemTab(int x, int y, int width, int height, int angle, Block block) {
		this(x, y, width, height, "", angle, block);
	}

	public ItemTab(int x, int y, int width, int height, int angle, Item item) {
		this(x, y, width, height, "", angle, item);
	}

	public ItemTab(int x, int y, int width, int height, Item item) {
		this(x, y, width, height, 0, item);
	}

	public ItemTab(int x, int y, int width, int height, String title, int angle, Block block) {
		this(x, y, width, height, title, angle, Item.getItemFromBlock(block));
	}

	public ItemTab(int x, int y, int width, int height, String title, int angle, Item item) {
		super(x, y, width, height, angle);
		this.title = title;
		if ((angle % 180) == 0) {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				disItem = new DisplayItem(0, 0, (int) (size), (int) (size), item);
			} else {
				double size = Math.min(width * .75, height * .75);
				disItem = new DisplayItem(0, 0, (int) (size), (int) (size), item);
			}
		} else {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				disItem = new DisplayItem(0, 0, (int) (size), (int) (size), item);
			} else {
				double size = Math.min(width * .75, height * .75);
				disItem = new DisplayItem(0, 0, (int) (size), (int) (size), item);
			}
		}
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		beginDrawingTab(mouseX, mouseY, partialTicks);
		if (!isHidden) {
			if (angle == 180) {
				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(width, 0, 1);
					TextRenderer.renderString((int) (width / 2.0) - 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(-disItem.getWidth() / 4,
							(height / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(width - (disItem.getWidth() / 4), height / 9, 0);
				}
				disItem.onDraw(mouseX, mouseY, partialTicks);
			} else if (angle == 90) {
				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(-height, 0, 1);
					TextRenderer.renderString((int) (height / 2.0) + 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(disItem.getWidth() / 4,
							(width / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(-height + (disItem.getWidth() / 4), width / 9, 0);
				}
				disItem.onDraw(mouseX, mouseY, partialTicks);
			} else if (angle == 270) {
				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(height, 0, 0);
					TextRenderer.renderString((int) (height / 2.0) - 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(-disItem.getWidth() / 4,
							(width / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(height - (disItem.getWidth() / 4), width / 9, 0);
				}
				disItem.onDraw(mouseX, mouseY, partialTicks);
			} else {

				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(-width, 0, 0);
					TextRenderer.renderString((int) (width / 2.0) - 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(disItem.getWidth() / 4,
							(height / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(-width + (disItem.getWidth() / 4), height / 9, 0);
				}
				disItem.onDraw(mouseX, mouseY, partialTicks);
			}
		}
		finishDrawingTab(mouseX, mouseY, partialTicks);
		drawHoverText(mouseX, mouseY, partialTicks);
	}
}
