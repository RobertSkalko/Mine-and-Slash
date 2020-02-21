package com.robertx22.mine_and_slash.gui.talent_tree_gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.database.talent_tree.PerkScreenContext;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.TryAllocatePerkPacket;
import com.robertx22.mine_and_slash.packets.TryRemovePerkPacket;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;

import java.awt.*;

public class PerkButton extends ImageButton {

    public BasePerk perk;
    public EntityCap.UnitData data;
    public Minecraft mc;
    public PerkConnection.Allocation status;

    public PerkButton(PerkConnection.Allocation status, BasePerk perk, EntityCap.UnitData data) {
        super(0, 0, perk.getPerkType().sizeX, perk.getPerkType().sizeY, 0, 0, perk.getPerkType().sizeY,
              perk.getPerkType().TEXTURE, (button) -> {
                }
        );

        this.status = status;
        this.perk = perk;
        this.data = data;
        this.mc = Minecraft.getInstance();
    }

    @Override
    public void renderButton(int x, int y, float ticks) {

    }

    public void renderButton(int x, int y, PerkScreenContext ctx) {

        int finalX = getPosX(ctx);
        int finalY = getPosY(ctx);

        int checkX = finalX;
        int checkY = finalY;

        if (TalentPerkTreeScreen.shouldRender(checkX, checkY, ctx, perk.getPerkType())) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(this.perk.getPerkType().TEXTURE);
            RenderSystem.disableDepthTest();

            float xstart = perk.getPerkType().getOffsetX();
            int yStart = perk.getPerkType().getOffsetY(status);

            blit(finalX, finalY, xstart, (float) yStart, this.width, this.height, 256, 256);
            RenderSystem.enableDepthTest();

            int itemX = finalX - 8 + perk.getPerkType().sizeX / 2;
            int itemY = finalY - 8 + perk.getPerkType().sizeY / 2;

            this.perk.render(itemX, itemY);

        }
    }

    public static int getSpacing() {
        return 40;
    }

    public int getMiddleX(PerkScreenContext ctx) {
        return (int) (getPosX(ctx) + perk.getPerkType().sizeX / 2);
    }

    public int getMiddleY(PerkScreenContext ctx) {
        return (int) (getPosY(ctx) + perk.getPerkType().sizeY / 2);
    }

    public int getPosX(PerkScreenContext ctx) {
        int offsetX = mc.mainWindow.getScaledWidth() / 2;
        offsetX *= ctx.getZoomMulti();
        offsetX -= TalentPerkTreeScreen.sizeX() * ctx.zoom / 2;

        return getX(ctx) + offsetX;
    }

    public int getPosY(PerkScreenContext ctx) {
        int offsetY = mc.mainWindow.getScaledHeight() / 2;
        offsetY *= ctx.getZoomMulti();
        offsetY -= TalentPerkTreeScreen.sizeY() * ctx.zoom / 2;
        return getY(ctx) + offsetY;
    }

    public int getX(PerkScreenContext ctx) {

        int pos = (int) ((this.perk.x * getSpacing() - ctx.scrollX) * ctx.zoom) - perk.getPerkType().sizeX / 2;

        return pos;
    }

    public int getY(PerkScreenContext ctx) {
        int pos = (int) ((this.perk.y * getSpacing() - ctx.scrollY) * ctx.zoom) - perk.getPerkType().sizeY / 2;

        return pos;
    }

    public void onClick(PerkScreenContext ctx, int mouseX, int mouseY, int click) {
        if (isInsideSlot(ctx, mouseX, mouseY)) {
            if (click == 1) { // if right click
                MMORPG.sendToServer(new TryRemovePerkPacket(this.perk));
            } else {
                MMORPG.sendToServer(new TryAllocatePerkPacket(this.perk));

            }
        }
    }

    public boolean isInsideSlot(PerkScreenContext ctx, int mouseX, int mouseY) {

        Point guipos = new Point(getPosX(ctx), getPosY(ctx));
        Point mousePos = new Point((int) (mouseX * ctx.getZoomMulti()), (int) (mouseY * ctx.getZoomMulti()));

        int xEnd = (int) (perk.getPerkType().sizeX);
        int yEnd = (int) (perk.getPerkType().sizeY);

        Point size = new Point(xEnd, yEnd);

        return GuiUtils.isInRectPoints(guipos, size, mousePos);

    }

}
