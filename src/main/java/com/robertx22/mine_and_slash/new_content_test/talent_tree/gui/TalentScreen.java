package com.robertx22.mine_and_slash.new_content_test.talent_tree.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.db_lists.initializers.TalentPoints;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils.PointF;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TalentScreen extends Screen {

    Minecraft mc;
    EntityCap.UnitData data;

    public float scrollX = 0;
    public float scrollY = 0;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/talent_frame.png");
    private static final ResourceLocation LINES = new ResourceLocation(Ref.MODID, "textures/gui/talents/lines.png");

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

        this.addButton(new TalentPointButton(TalentPoints.CRIT_HIT0, data));
        this.addButton(new TalentPointButton(TalentPoints.CRIT_HIT1, data));

        this.addButton(new TalentPointButton(TalentPoints.CRIT_DMG0, data));
        this.addButton(new TalentPointButton(TalentPoints.CRIT_DMG1, data));

    }

    @Override
    public boolean mouseDragged(double x, double y, int ticks, double dragX,
                                double dragY) {
        this.scrollX += dragX;
        this.scrollY += dragY;
        return true;

    }

    List<TalentPointButton> getTalentButtons() {
        List<TalentPointButton> list = new ArrayList<>();
        for (Widget w : this.buttons) {
            if (w instanceof TalentPointButton) {
                list.add((TalentPointButton) w);
            }
        }
        return list;

    }

    @Override
    public void render(int x, int y, float ticks) {

        super.render(x, y, ticks);

        drawBackGround();

        List<TalentPointButton> list = getTalentButtons();

        renderConnections(list);

        for (TalentPointButton but : list) {
            but.renderButton(x, y, ticks, (int) scrollX, (int) scrollY);
        }

        renderTooltips(list, x, y);
    }

    public void renderTooltips(List<TalentPointButton> list, int mouseX, int mouseY) {

        TooltipInfo info = new TooltipInfo();

        list.forEach(button -> {

            if (button.isInsideSlot(scrollX, scrollY, mouseX, mouseY)) {
                this.renderTooltip(TooltipUtils.compsToStrings(button.talentPoint.effect.GetTooltipString(info)), mouseX, mouseY, mc.fontRenderer);
            }
        });

    }

    @Override
    public boolean mouseClicked(double x, double y, int ticks) {
        getTalentButtons().forEach(t -> t.onClick(scrollX, scrollY, (int) x, (int) y));
        return super.mouseClicked(x, y, ticks);

    }

    private void renderConnections(List<TalentPointButton> list) {
        for (TalentPointButton but : list) {

            if (but.shouldRender((int) scrollX, (int) scrollY)) {

                for (TalentPointButton con : list.stream()
                        .filter(button -> button.talentPoint.isConnectedTo(but.talentPoint))
                        .collect(Collectors.toList())) {
                    if (con.shouldRender((int) scrollX, (int) scrollY)) {

                        int x1 = but.getMiddleX((int) scrollX);
                        int y1 = but.getMiddleY((int) scrollY);

                        int x2 = con.getMiddleX((int) scrollX);
                        int y2 = con.getMiddleY((int) scrollY);

                        int size = 5;

                        List<PointF> points = GuiUtils.generateCurve(new PointF(x1, y1), new PointF(x2, y2), 100f, size + 2, true);

                        for (PointF point : points) {
                            Minecraft.getInstance()
                                    .getTextureManager()
                                    .bindTexture(LINES);
                            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                            blit((int) (point.x - ((float) size / 2)), (int) (point.y - ((float) size / 2)), 0, 0.0F, 0.0F, size, size, 256, 256);
                        }

                    }
                }

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
