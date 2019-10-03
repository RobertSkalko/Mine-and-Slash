package com.robertx22.mine_and_slash.new_content_test.talent_tree.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.AllocateTalentPacket;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.ScreenContext;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentPoint;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;

import java.awt.*;

public class TalentPointButton extends ImageButton {

    TalentPoint talentPoint;
    EntityCap.UnitData data;
    Minecraft mc;

    public TalentPointButton(TalentPoint talentPoint, EntityCap.UnitData data) {
        super(0, 0, talentPoint.getPerkType().sizeX, talentPoint.getPerkType().sizeY, 0, 0, talentPoint
                .getPerkType().sizeY, talentPoint.getPerkType().TEXTURE, (button) -> {
        });

        this.talentPoint = talentPoint;
        this.data = data;
        this.mc = Minecraft.getInstance();
    }

    @Override
    public void renderButton(int x, int y, float ticks) {

    }

    public void renderButton(int x, int y, ScreenContext ctx) {

        int finalX = getPosX(ctx);
        int finalY = getPosY(ctx);

        if (TalentScreen.shouldRender(finalX, finalY, ctx)) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(this.talentPoint.getPerkType().TEXTURE);
            GlStateManager.disableDepthTest();
            int yStart = 0;
            if (this.isHovered()) {
                yStart += talentPoint.getPerkType().sizeY;
            }
            float xstart = 0;

            blit(finalX, finalY, xstart, (float) yStart, this.width, this.height, 256, 256);
            GlStateManager.enableDepthTest();

            int itemX = finalX - 8 + talentPoint.getPerkType().sizeX / 2;
            int itemY = finalY - 8 + talentPoint.getPerkType().sizeY / 2;

            mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(talentPoint.renderStack, itemX, itemY);

        }
    }

    public int getSpacing() {
        return 40;
    }

    public int getMiddleX(ScreenContext ctx) {
        return (int) (getPosX(ctx) + talentPoint.getPerkType().sizeX / 2);
    }

    public int getMiddleY(ScreenContext ctx) {
        return (int) (getPosY(ctx) + talentPoint.getPerkType().sizeY / 2);
    }

    public int getPosX(ScreenContext ctx) {
        int offsetX = mc.mainWindow.getScaledWidth() / 2;
        offsetX *= ctx.getZoomMulti();
        offsetX -= TalentScreen.sizeX * ctx.zoom / 2;
        return getX(ctx) + offsetX;
    }

    public int getPosY(ScreenContext ctx) {
        int offsetY = mc.mainWindow.getScaledHeight() / 2;
        offsetY *= ctx.getZoomMulti();
        offsetY -= TalentScreen.sizeY * ctx.zoom / 2;
        return getY(ctx) + offsetY;
    }

    public int getX(ScreenContext ctx) {

        int pos = (int) (this.talentPoint.x * getSpacing() + ctx.scrollX * ctx.zoom);

        return pos;
    }

    public int getY(ScreenContext ctx) {
        int pos = (int) (this.talentPoint.y * getSpacing() + ctx.scrollY * ctx.zoom);

        return pos;
    }

    public void onClick(ScreenContext ctx, int mouseX, int mouseY) {

        if (isInsideSlot(ctx, mouseX, mouseY)) {
            MMORPG.sendToServer(new AllocateTalentPacket(this.talentPoint));

            System.out.println(this.talentPoint.GUID());
        }

    }

    public boolean isInsideSlot(ScreenContext ctx, int mouseX, int mouseY) {

        Point guipos = new Point(getPosX(ctx), getPosY(ctx));
        Point mousePos = new Point((int) (mouseX * ctx.getZoomMulti()), (int) (mouseY * ctx
                .getZoomMulti()));

        int xEnd = (int) (talentPoint.getPerkType().sizeX * ctx.zoom);
        int yEnd = (int) (talentPoint.getPerkType().sizeY * ctx.zoom);

        Point size = new Point(xEnd, yEnd);

        return GuiUtils.isInRectPoints(guipos, size, mousePos);

    }

}
