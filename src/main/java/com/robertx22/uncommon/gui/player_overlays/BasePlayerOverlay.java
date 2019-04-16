package com.robertx22.uncommon.gui.player_overlays;

import java.awt.Color;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class BasePlayerOverlay {

    public int TEXTURE_WIDTH = 106;
    public int TEXTURE_HEIGHT = 11;

    public final ResourceLocation manatexturepath = new ResourceLocation("mmorpg", "textures/gui/mana_bar.png");
    public final ResourceLocation energytexturepath = new ResourceLocation("mmorpg", "textures/gui/energy_bar.png");
    public final ResourceLocation healthtexturepath = new ResourceLocation("mmorpg", "textures/gui/health_bar.png");
    public final ResourceLocation experiencetexturepath = new ResourceLocation("mmorpg",
	    "textures/gui/experience_bar.png");

    public abstract void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit,
	    UnitData level);

    public void DrawBar(Minecraft mc, Gui gui, Unit unit, ResourceLocation res, float current, float max, boolean isExp,
	    UnitData data, int x, int y) {

	GlStateManager.color(1F, 1F, 1F, 1F);
	mc.getTextureManager().bindTexture(res);
	gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, this.TEXTURE_HEIGHT); // the bar
	int barwidth = (int) (((float) current / max * 100));
	if (barwidth > 100) {
	    barwidth = 100;
	}
	gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5); // inner fill texture

	String now = DamageEffect.FormatNumber((int) current);
	String maximum = DamageEffect.FormatNumber((int) max);
	String str = "";

	if (!isExp) {
	    str = now + "/" + maximum;
	} else {
	    str = "Lvl:" + data.getLevel() + " " + now + "/" + maximum;
	}

	float text_x = x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2;
	float text_y = y + TEXTURE_HEIGHT / 2 - mc.fontRenderer.FONT_HEIGHT / 2 + 0.5F;

	mc.fontRenderer.drawString(str, text_x, text_y, Color.LIGHT_GRAY.getRGB(), true);

    }

}
