package com.robertx22.mine_and_slash.gui.professions.widgets;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.uncommon.capability.ProfessionsCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class ProfessionLvlBar extends ImageButton {
    public static ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/profession/level_bar.png");
    public static int xSize = 102;
    public static int ySize = 5;

    static int lvlOrbStartY = 16;
    static int lvlOrbSize = 11;

    ProfessionsCap.IProfessionsData data;
    Professions prof;

    public ProfessionLvlBar(Professions prof, ProfessionsCap.IProfessionsData data,
                            int xPos, int yPos) {
        super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
        });
        this.data = data;
        this.prof = prof;

    }

    @Override
    public void renderButton(int x, int y, float f) {

        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(img);
        GlStateManager.disableDepthTest();

        float max = data.getExpToReachNextLevel(prof);
        float current = data.getCurrentExp(prof);

        int percent = (int) (current / max * xSize);

        blit(this.x, this.y, 0, 0, this.width, this.height, 256, 256);
        blit(this.x, this.y, 0, ySize, this.width, this.height, 256, 256);
        blit(this.x, this.y, 0, ySize * 2, percent, this.height, 256, 256);

        int lvl = data.getLevel(prof);

        int orbX = this.x + xSize / 2 - lvlOrbSize / 2;
        int orbY = this.y - lvlOrbSize - ySize;

        blit(orbX, orbY, this.y - lvlOrbSize + 2, 0, lvlOrbStartY, lvlOrbSize, lvlOrbSize, 256, 256);

        String text = lvl + "";

        mc.fontRenderer.drawString(text, orbX + lvlOrbSize + 3, orbY + mc.fontRenderer.FONT_HEIGHT / 2 - 2, TextFormatting.YELLOW
                .getColor());

        GlStateManager.enableDepthTest();
    }

}
