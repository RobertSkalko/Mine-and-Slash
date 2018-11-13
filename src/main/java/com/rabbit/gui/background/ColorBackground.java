package com.rabbit.gui.background;

import java.awt.Color;

import com.rabbit.gui.component.IBackground;
import com.rabbit.gui.render.Renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorBackground implements IBackground {

	private final Color color;

	public ColorBackground(Color color) {
		this.color = color;
	}

	@Override
	public void onDraw(int width, int height, int mouseX, int mouseY, float partialTicks) {
		GlStateManager.resetColor();
		Renderer.drawRect(0, 0, width, height, color.getRGB());
	}

}
