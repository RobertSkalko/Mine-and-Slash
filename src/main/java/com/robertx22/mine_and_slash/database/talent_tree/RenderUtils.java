package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RenderUtils {

    public static void renderIcon(ResourceLocation tex, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(tex);
        AbstractGui.blit(x, y, 0, 0, 16, 16, 16, 16);
    }

    public static void renderIcons(List<ExactStatData> list, int x, int y) {

        y -= 8;

        for (ExactStatData stat : list) {

            Minecraft.getInstance()
                    .getTextureManager()
                    .bindTexture(stat.getStat().getIconLocation());

            AbstractGui.blit(x, y, 0, 0, 16, 16, 16, 16);

            y += 16;

        }

    }

}
