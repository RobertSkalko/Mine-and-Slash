package com.robertx22.mine_and_slash.gui.map_overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.QuestsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MapInfoOverlay extends AbstractGui {

    private static final ResourceLocation PROGRESS_TEX = new ResourceLocation(Ref.MODID,
        "textures/gui/map_info_overlay" +
            "/progress.png"
    );

    Minecraft mc = Minecraft.getInstance();

    QuestsCap.IQuestsData quests;
    PlayerMapCap.IPlayerMapData map;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerOverlay(RenderGameOverlayEvent event) {

        if (mc.player == null || mc.player.world == null || event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        try {
            if (WorldUtils.isMapWorldClass(mc.player.world)) {

                quests = Load.quests(mc.player);
                map = Load.playerMapData(mc.player);

                int x = 16;
                int y = (int) (mc.mainWindow.getScaledHeight() - 16);

                render(x, y);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render(int x, int y) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        float s = 0.5F;
        float as = 1F / s;

        int xr = 7;

        String progressText = quests.getMapQuestData()
            .getProgressPercent() + "%";

        x += 18;

        RenderSystem.scalef(s, s, s);
        mc.getTextureManager()
            .bindTexture(PROGRESS_TEX);
        this.blit((int) (x * as) + xr, (int) (y * as), 0, 0, 16, 16, 16, 16);
        RenderSystem.scalef(as, as, as);

        x += 16;

        mc.fontRenderer.drawString(progressText, x, y, TextFormatting.GREEN.getColor());

    }
}