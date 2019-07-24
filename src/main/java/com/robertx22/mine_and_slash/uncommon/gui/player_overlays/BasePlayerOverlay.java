package com.robertx22.mine_and_slash.uncommon.gui.player_overlays;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public abstract class BasePlayerOverlay {

    public int TEXTURE_WIDTH = 106;
    public int TEXTURE_HEIGHT = 11;

    public final ResourceLocation manatexturepath = new ResourceLocation("mmorpg", "textures/gui/mana_bar.png");
    public final ResourceLocation energytexturepath = new ResourceLocation("mmorpg", "textures/gui/energy_bar.png");
    public final ResourceLocation healthtexturepath = new ResourceLocation("mmorpg", "textures/gui/health_bar.png");
    public final ResourceLocation experiencetexturepath = new ResourceLocation("mmorpg", "textures/gui/experience_bar.png");

    public final ResourceLocation newhealthbar = new ResourceLocation("mmorpg", "textures/gui/hpbar.png");

    public int NEW_TEXTURE_WIDTH = 92;
    public int NEW_TEXTURE_HEIGHT = 5;

    public void drawHealthBar(Minecraft mc, AbstractGui gui, Unit unit,
                              ResourceLocation res, float current, float max,
                              boolean isExp, UnitData data, int x, int y) {

        GlStateManager.color4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(res);

        gui.blit(x, y, 0, 0, NEW_TEXTURE_WIDTH, NEW_TEXTURE_HEIGHT); // the bar
        int barwidth = (int) (((float) current / max * 100));
        if (barwidth == 99) { // float problems
            barwidth = 100;
        }
        if (barwidth > 100) {
            barwidth = 100;
        }
        gui.blit(x, y, 0, NEW_TEXTURE_HEIGHT, barwidth, NEW_TEXTURE_HEIGHT); // inner fill texture

        String now = DamageEffect.FormatNumber((int) current);
        String maximum = DamageEffect.FormatNumber((int) max);
        String str = "";

        if (!isExp) {
            str = now + "/" + maximum;
        } else {
            str = "Lvl:" + data.getLevel() + " " + now + "/" + maximum;
        }

        float text_x = x + NEW_TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2;
        float text_y = y - NEW_TEXTURE_HEIGHT - mc.fontRenderer.FONT_HEIGHT / 2;

        mc.fontRenderer.drawStringWithShadow(str, text_x, text_y, new Color(240, 66, 66).getRGB());

    }

    public abstract void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity,
                              RenderGameOverlayEvent event, Unit unit, UnitData level);

    public void DrawBar(Minecraft mc, AbstractGui gui, Unit unit, ResourceLocation res,
                        float current, float max, boolean isExp, UnitData data, int x,
                        int y) {

        GlStateManager.color4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(res);

        gui.blit(x, y, 0, 0, TEXTURE_WIDTH, this.TEXTURE_HEIGHT); // the bar
        int barwidth = (int) (((float) current / max * 100));
        if (barwidth > 100) {
            barwidth = 100;
        }
        gui.blit(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5); // inner fill texture

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

        mc.fontRenderer.drawString(str, text_x, text_y, Color.LIGHT_GRAY.getRGB());

    }

}
