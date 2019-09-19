package com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class StatPointScreen extends Screen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/stat_point_screen.png");
    int sizeY = 220;
    int sizeX = 215;

    protected StatPointScreen() {
        super(new StringTextComponent(""));
    }

    boolean addedButtons = false;

    PlayerStatsPointsCap.IPlayerStatPointsData data = Load.statPoints(Minecraft.getInstance().player);

    @Override
    public void render(int mouseX, int mouseY, float ticks) {

        if (!addedButtons) {
            for (SingleStatPointData single : data.getData().getAllStatDatas()) {

            }

            addedButtons = true;
        }

    }
}
