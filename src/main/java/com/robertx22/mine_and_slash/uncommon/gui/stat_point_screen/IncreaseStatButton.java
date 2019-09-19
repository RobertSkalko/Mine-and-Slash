package com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class IncreaseStatButton extends ImageButton {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/button.png");
    static int sizeX = 13;
    static int sizeY = 13;

    PlayerStatsPointsCap.IPlayerStatPointsData data;
    String statmod;

    public IncreaseStatButton(PlayerStatsPointsCap.IPlayerStatPointsData data,
                              SingleStatPointData statData, int xPos, int yPos) {
        super(xPos, yPos, sizeX, sizeY, 0, 0, sizeY, TEXTURE, (button) -> {

            System.out.println("button click");

        });

        this.data = data;
        this.statmod = statData.statmod;

    }

    @Override
    public void renderButton(int x, int y, float f) {
        super.renderButton(x, y, f);

        SingleStatPointData single = data.getStatData(statmod);

        Stat stat = single.getMod().GetBaseStat();

        String str = CLOC.translate(stat.locName()) + ": " + single.points;

        Minecraft.getInstance().fontRenderer.drawString(str, this.x - sizeX - 5 - Minecraft
                .getInstance().fontRenderer.getStringWidth(str), this.y - sizeY / 2, TextFormatting.GRAY
                .getColor());

    }

}
