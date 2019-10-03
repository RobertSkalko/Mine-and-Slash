package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class RenderUtils {

    public static void renderPerkIcon(ResourceLocation tex, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(tex);
        AbstractGui.blit(x, y, 0, 0, 16, 16, 16, 16);
    }

}
