package com.robertx22.mine_and_slash.new_content_test.talent_tree.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.AllocateTalentPacket;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.ScreenContext;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;

import java.awt.*;

public class PerkButton extends ImageButton {

    Perk perk;
    EntityCap.UnitData data;
    Minecraft mc;
    PlayerTalentsCap.IPlayerTalentsData talents;

    public PerkButton(PlayerTalentsCap.IPlayerTalentsData talents, Perk perk,
                      EntityCap.UnitData data) {
        super(0, 0, perk.getPerkType().sizeX, perk.getPerkType().sizeY, 0, 0, perk.getPerkType().sizeY, perk
                .getPerkType().TEXTURE, (button) -> {
        });

        this.perk = perk;
        this.data = data;
        this.mc = Minecraft.getInstance();
        this.talents = talents;
    }

    @Override
    public void renderButton(int x, int y, float ticks) {

    }

    public void renderButton(int x, int y, ScreenContext ctx) {

        int finalX = getPosX(ctx);
        int finalY = getPosY(ctx);

        if (PerkTreeScreen.shouldRender(finalX, finalY, ctx)) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(this.perk.getPerkType().TEXTURE);
            GlStateManager.disableDepthTest();

            PerkConnection.Allocation status = perk.getStatus(talents);

            float xstart = perk.getPerkType().getOffsetX();
            int yStart = perk.getPerkType().getOffsetY(status);

            blit(finalX, finalY, xstart, (float) yStart, this.width, this.height, 256, 256);
            GlStateManager.enableDepthTest();

            int itemX = finalX - 8 + perk.getPerkType().sizeX / 2;
            int itemY = finalY - 8 + perk.getPerkType().sizeY / 2;

            mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(perk.renderStack, itemX, itemY);

        }
    }

    public int getSpacing() {
        return 40;
    }

    public int getMiddleX(ScreenContext ctx) {
        return (int) (getPosX(ctx) + perk.getPerkType().sizeX / 2);
    }

    public int getMiddleY(ScreenContext ctx) {
        return (int) (getPosY(ctx) + perk.getPerkType().sizeY / 2);
    }

    public int getPosX(ScreenContext ctx) {
        int offsetX = mc.mainWindow.getScaledWidth() / 2;
        offsetX *= ctx.getZoomMulti();
        offsetX -= PerkTreeScreen.sizeX * ctx.zoom / 2;
        return getX(ctx) + offsetX;
    }

    public int getPosY(ScreenContext ctx) {
        int offsetY = mc.mainWindow.getScaledHeight() / 2;
        offsetY *= ctx.getZoomMulti();
        offsetY -= PerkTreeScreen.sizeY * ctx.zoom / 2;
        return getY(ctx) + offsetY;
    }

    public int getX(ScreenContext ctx) {

        int pos = (int) (this.perk.x * getSpacing() + ctx.scrollX * ctx.zoom);

        return pos;
    }

    public int getY(ScreenContext ctx) {
        int pos = (int) (this.perk.y * getSpacing() + ctx.scrollY * ctx.zoom);

        return pos;
    }

    public void onClick(ScreenContext ctx, int mouseX, int mouseY) {

        if (isInsideSlot(ctx, mouseX, mouseY)) {
            MMORPG.sendToServer(new AllocateTalentPacket(this.perk));

            System.out.println(this.perk.GUID());
        }

    }

    public boolean isInsideSlot(ScreenContext ctx, int mouseX, int mouseY) {

        Point guipos = new Point(getPosX(ctx), getPosY(ctx));
        Point mousePos = new Point((int) (mouseX * ctx.getZoomMulti()), (int) (mouseY * ctx
                .getZoomMulti()));

        int xEnd = (int) (perk.getPerkType().sizeX);
        int yEnd = (int) (perk.getPerkType().sizeY);

        Point size = new Point(xEnd, yEnd);

        return GuiUtils.isInRectPoints(guipos, size, mousePos);

    }

}
