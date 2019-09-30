package com.robertx22.mine_and_slash.new_content_test.talent_tree.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentPoint;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;

public class TalentPointButton extends ImageButton {

    TalentPoint talentPoint;
    EntityCap.UnitData data;

    public TalentPointButton(TalentPoint talentPoint, EntityCap.UnitData data) {
        super(0, 0, talentPoint.getPerkType().sizeX, talentPoint.getPerkType().sizeY, 0, 0, talentPoint
                .getPerkType().sizeY, talentPoint.getPerkType().TEXTURE, (button) -> {
        });

        this.talentPoint = talentPoint;
        this.data = data;
    }

    @Override
    public void renderButton(int x, int y, float ticks) {

    }

    public void renderButton(int x, int y, float ticks, int scrollX, int scrollY) {
        if (shouldRender(x, y, scrollX, scrollY)) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bindTexture(this.talentPoint.getPerkType().TEXTURE);
            GlStateManager.disableDepthTest();
            int yStart = 0;
            if (this.isHovered()) {
                yStart += talentPoint.getPerkType().sizeY;
            }
            float xstart = 0;

            int offsetX = mc.mainWindow.getScaledWidth() / 2 - TalentScreen.sizeX / 2;
            int offsetY = mc.mainWindow.getScaledHeight() / 2 - TalentScreen.sizeY / 2;

            int finalX = getX(scrollX) + offsetX;
            int finalY = getY(scrollY) + offsetY;

            blit(finalX, finalY, xstart, (float) yStart, this.width, this.height, 256, 256);
            GlStateManager.enableDepthTest();

            mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(talentPoint.renderStack, finalX, finalY);

        }
    }

    private int getX(int scrollX) {

        int pos = this.talentPoint.x * 20 + scrollX;

        return pos;
    }

    private int getY(int scrollY) {
        int pos = this.talentPoint.y * 20 + scrollY;

        return pos;
    }

    public boolean shouldRender(int x, int y, int scrollX, int scrollY) {

        int xpos = getX(scrollX);
        int ypos = getY(scrollY);

        if (xpos >= 0 && xpos < TalentScreen.sizeX - talentPoint.getPerkType().sizeX) {
            if (ypos >= 0 && ypos < TalentScreen.sizeY - talentPoint.getPerkType().sizeY) {
                return true;
            }
        }

        return false;

    }

}
