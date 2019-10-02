package com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.SpendStatPointPacket;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
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
    LvlPointStat stat;
    EntityCap.UnitData unitdata;

    public IncreaseStatButton(EntityCap.UnitData unitdata,
                              PlayerStatsPointsCap.IPlayerStatPointsData data,
                              SingleStatPointData statData, int xPos, int yPos) {
        super(xPos, yPos, sizeX, sizeY, 0, 0, sizeY, TEXTURE, (button) -> {
        });

        this.data = data;
        this.stat = statData.stat;
        this.unitdata = unitdata;

    }

    @Override
    public void onClick(double x, double y) {
        super.onClick(x, y);
        if (isInside((int) x, (int) y)) {
            MMORPG.sendToServer(new SpendStatPointPacket(this.stat));
            MMORPG.sendToServer(new RequestSyncCapToClient(CapTypes.STAT_POINTS));
        }
    }

    public boolean isInside(int x, int y) {
        return GuiUtils.isInRect(this.x, this.y, sizeX, sizeY, x, y);
    }

    @Override
    public void renderButton(int x, int y, float f) {
        super.renderButton(x, y, f);

        TextFormatting format = TextFormatting.YELLOW;

        SingleStatPointData single = data.getStatData(stat);

        Stat stat = single.getStat();

        String str = single.stat.formatting + single.stat.shortName + format + ": " + TextFormatting.GREEN + single.points + format;
        str += ", Current(" + TextFormatting.GREEN + (int) this.unitdata.getUnit()
                .getStat(stat).Value + format + ")";

        font.drawStringWithShadow(str, this.x - sizeX - 5 - font.getStringWidth(str), this.y - sizeY / 2 + font.FONT_HEIGHT, format
                .getColor());

    }

}
