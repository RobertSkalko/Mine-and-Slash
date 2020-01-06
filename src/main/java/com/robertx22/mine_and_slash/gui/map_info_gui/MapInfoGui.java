package com.robertx22.mine_and_slash.gui.map_info_gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.gui.BaseScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MapInfoGui extends BaseScreen {

    Minecraft mc;

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/map_info/window.png");

    static int x = 318;
    static int y = 232;

    public MapInfoGui() {
        super(x, y);

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);
        super.render(x, y, ticks);

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.blitOffset, 0.0F, 0.0F, this.x, this.y, 256, 512);

    }

}

