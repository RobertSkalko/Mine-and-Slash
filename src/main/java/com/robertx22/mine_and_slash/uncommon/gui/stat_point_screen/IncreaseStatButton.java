package com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.SpendStatPointPacket;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class IncreaseStatButton extends ImageButton {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/button.png");
    static int sizeX = 13;
    static int sizeY = 13;

    FontRenderer font = Minecraft.getInstance().fontRenderer;
    PlayerStatsPointsCap.IPlayerStatPointsData data;
    String statmod;

    public IncreaseStatButton(PlayerStatsPointsCap.IPlayerStatPointsData data,
                              SingleStatPointData statData, int xPos, int yPos) {
        super(xPos, yPos, sizeX, sizeY, 0, 0, sizeY, TEXTURE, (button) -> {
        });

        this.data = data;
        this.statmod = statData.stat;

    }

    @Override
    public void onClick(double x, double y) {
        super.onClick(x, y);
        if (isInside((int) x, (int) y)) {
            MMORPG.sendToServer(new SpendStatPointPacket(this.statmod));
            MMORPG.sendToServer(new RequestSyncCapToClient(CapTypes.STAT_POINTS));
        }
    }

    public boolean isInside(int x, int y) {
        return isInRect(this.x, this.y, sizeX, sizeY, x, y);
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX,
                                   int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    @Override
    public void renderButton(int x, int y, float f) {
        super.renderButton(x, y, f);

        SingleStatPointData single = data.getStatData(statmod);

        Stat stat = single.getStat();

        String str = CLOC.translate(stat.locName()) + ": " + single.points;

        font.drawStringWithShadow(str, this.x - sizeX - 5 - font.getStringWidth(str), this.y - sizeY / 2 + font.FONT_HEIGHT, TextFormatting.YELLOW
                .getColor());

    }

}
