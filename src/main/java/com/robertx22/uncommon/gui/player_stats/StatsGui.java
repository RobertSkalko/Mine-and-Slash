package com.robertx22.uncommon.gui.player_stats;

import java.awt.Color;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class StatsGui extends GuiScreen {
	
	private int guiWidth, guiHeight;
	
	int xPos = 2;
	int yPos = 4;
	
	public int TEXTURE_WIDTH2 = 120;
	public int TEXTURE_HEIGHT2 = 90;
	
	public final ResourceLocation backgroundtexturepath = new ResourceLocation("mmorpg", "textures/gui/background2.png");

	public void DrawUI(Minecraft mc, Gui gui, Unit unit, ResourceLocation res, UnitData data, int x, int y) {

		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(res);
		gui.drawTexturedModalRect(x, y, 0, 0, this.TEXTURE_WIDTH2, this.TEXTURE_HEIGHT2);

	}

	public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit,
			UnitData data) {

		xPos = 3;
		yPos = 1;
		DrawUI(mc, gui, unit, backgroundtexturepath, data, xPos, yPos);
	}
	
	@Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
