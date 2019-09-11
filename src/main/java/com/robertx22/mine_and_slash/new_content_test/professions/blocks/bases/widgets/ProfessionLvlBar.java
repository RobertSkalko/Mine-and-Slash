package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.widgets;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.uncommon.capability.ProfessionsCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

public class ProfessionLvlBar extends ImageButton {
    public static ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/profession/level_bar.png");
    public static int xSize = 102;
    public static int ySize = 5;
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

        int max = data.getExpToReachNextLevel(prof);
        int current = data.getCurrentExp(prof);

        int percent = (int) ((float) data.getCurrentExp(prof) / (float) data.getExpToReachNextLevel(prof) * (float) xSize);

        blit(this.x, this.y, 0, 0, this.width, this.height, 256, 256);
        blit(this.x, this.y, 0, 0 + ySize, this.width, this.height, 256, 256);
        blit(this.x, this.y, 0, ySize * 2, percent, this.height, 256, 256);

        GlStateManager.enableDepthTest();
    }

}
