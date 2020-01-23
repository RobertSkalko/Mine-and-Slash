package com.robertx22.mine_and_slash.gui.spell_cast_bar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpellCastBarOverlay extends AbstractGui {

    private static final ResourceLocation GUI_BARS_TEXTURES = new ResourceLocation("textures/gui/bars.png");

    static int WIDTH = 182;
    static int HEIGHT = 5;

    Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerOverlay(RenderGameOverlayEvent event) {

        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        PlayerSpellCap.ISpellsCap data = Load.spells(mc.player);

        if (data.getSpellData().isCasting()) {

            int x = mc.mainWindow.getScaledWidth() / 2 - WIDTH / 2;
            int y = (int) (mc.mainWindow.getScaledHeight() / 1.25F - HEIGHT / 2);

            float percent =
                    ((float) data.getSpellData().lastSpellCastTimeInTicks - (float) data.getSpellData().castingTicksLeft) / (float) data
                    .getSpellData().lastSpellCastTimeInTicks;

            render(x, y, BossInfo.Color.PURPLE, BossInfo.Overlay.NOTCHED_20, percent);
        }
    }

    private void render(int x, int y, BossInfo.Color color, BossInfo.Overlay overlay, float percent) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(GUI_BARS_TEXTURES);

        this.blit(x, y, 0, color.ordinal() * 5 * 2, WIDTH, HEIGHT);
        if (overlay != BossInfo.Overlay.PROGRESS) {
            this.blit(x, y, 0, 80 + (overlay.ordinal() - 1) * 5 * 2, WIDTH, HEIGHT);
        }

        int i = (int) (percent * 183.0F);
        if (i > 0) {
            this.blit(x, y, 0, color.ordinal() * 5 * 2 + 5, i, HEIGHT);
            if (overlay != BossInfo.Overlay.PROGRESS) {
                this.blit(x, y, 0, 80 + (overlay.ordinal() - 1) * 5 * 2 + 5, i, HEIGHT);
            }
        }

    }

}
