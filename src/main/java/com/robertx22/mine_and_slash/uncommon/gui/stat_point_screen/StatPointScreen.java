package com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class StatPointScreen extends Screen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/stat_point_screen.png");
    int sizeY = 220;
    int sizeX = 215;

    public StatPointScreen() {
        super(new StringTextComponent(""));
    }

    boolean addedButtons = false;

    PlayerStatsPointsCap.IPlayerStatPointsData data = Load.statPoints(Minecraft.getInstance().player);

    int guiLeft = 0;
    int guiTop = 0;

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - sizeX) / 2;
        this.guiTop = (this.height - sizeY) / 2;
        MMORPG.sendToServer(new RequestSyncCapToClient(CapTypes.STAT_POINTS));
    }

    int ticks = 0;

    @Override
    public void render(int mouseX, int mouseY, float ticks) {

        drawGuiBackgroundLayer(ticks, mouseX, mouseY);
        super.render(mouseX, mouseY, ticks);

        if (!addedButtons) {

            int y = 0;

            for (SingleStatPointData single : data.getData().getAllStatDatas()) {
                this.buttons.add(new IncreaseStatButton(data, single, guiLeft + sizeX / 2 + 25, guiTop + 40 + y));
                y += IncreaseStatButton.sizeY + 3;
            }

            addedButtons = true;
        }

        String str = "Stat Points Remaining: " + data.getAvailablePoints(Load.Unit(minecraft.player));

        Minecraft.getInstance().fontRenderer.drawStringWithShadow(str, guiLeft + sizeX / 2 + 50 - Minecraft
                .getInstance().fontRenderer.getStringWidth(str), guiTop + 15, TextFormatting.GREEN
                .getColor());

    }

    @Override
    public boolean mouseClicked(double x, double y, int ticks) {

        for (Widget button : this.buttons) {
            button.onClick(x, y);
        }

        return true;

    }

    protected void drawGuiBackgroundLayer(float partialTicks, int x, int y) {

        minecraft.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(minecraft.mainWindow.getScaledWidth() / 2 - this.sizeX / 2, minecraft.mainWindow
                .getScaledHeight() / 2 - this.sizeY / 2, 0, 0, sizeX, sizeY);

    }
}
