package com.robertx22.mine_and_slash.new_content_test.talent_tree.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class TalentScreen extends Screen {

    Minecraft mc;
    EntityCap.UnitData data;

    float scrollX = 0;
    float scrollY = 0;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/talent_frame.png");

    public static int sizeX = 318;
    public static int sizeY = 233;

    public TalentScreen() {
        super(new StringTextComponent(""));
        this.mc = Minecraft.getInstance();
        this.data = Load.Unit(mc.player);
    }

    @Override
    public void init(Minecraft mc, int x, int y) {
        super.init(mc, x, y);

        /*
        this.addButton(new TalentPointButton(new TalentPoint(0, 0, Items.COAL), data));
        this.addButton(new TalentPointButton(new TalentPoint(5, 0, Items.EMERALD), data));
        this.addButton(new TalentPointButton(new TalentPoint(0, 5, Items.SLIME_BALL), data));
        this.addButton(new TalentPointButton(new TalentPoint(10, 0, Items.GLOWSTONE), data));
        this.addButton(new TalentPointButton(new TalentPoint(0, 30, Items.BEETROOT), data));


         */
    }

    @Override
    public boolean mouseDragged(double x, double y, int ticks, double dragX,
                                double dragY) {
        this.scrollX += dragX;
        this.scrollY += dragY;
        return true;

    }

    @Override
    public void render(int x, int y, float ticks) {
        super.render(x, y, ticks);

        drawBackGround();

        for (Widget widget : this.buttons) {
            if (widget instanceof TalentPointButton) {
                TalentPointButton but = (TalentPointButton) widget;
                but.renderButton(x, y, ticks, (int) scrollX, (int) scrollY);
            }
        }

    }

    protected void drawBackGround() {

        Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int offsetX = mc.mainWindow.getScaledWidth() / 2 - sizeX / 2;
        int offsetY = mc.mainWindow.getScaledHeight() / 2 - sizeY / 2;

        blit(offsetX, offsetY, this.blitOffset, 0.0F, 0.0F, sizeX, sizeY, 256, 512);

    }
}
