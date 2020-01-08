package com.robertx22.mine_and_slash.gui.talent_tree_gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.database.talent_tree.ScreenContext;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap.IPlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils.PointF;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PerkTreeScreen extends Screen implements INamedScreen {

    Minecraft mc;
    EntityCap.UnitData data;

    IPlayerTalentsData talents;

    public float scrollX = 0;
    public float scrollY = 0;
    public float zoom = 1;

    public static int CENTER_X = 500;
    public static int CENTER_Y = 500;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/talent_frame.png");
    private static final ResourceLocation SPACE = new ResourceLocation(Ref.MODID, "textures/gui/talents/space.png");
    private static final ResourceLocation LINES = new ResourceLocation(Ref.MODID, "textures/gui/talents/lines.png");

    public static int sizeX = 318;
    public static int sizeY = 233;

    Set<PerkConnection> connections;

    Set<PerkConnectionRender> buttonConnections;

    public PerkTreeScreen() {
        super(new StringTextComponent(""));
        this.mc = Minecraft.getInstance();
        this.data = Load.Unit(mc.player);

        MMORPG.sendToServer(new RequestSyncCapToClient(CapTypes.TALENTS));
        this.talents = Load.talents(mc.player);

    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/talents.png");
    }

    @Override
    public Words screenName() {
        return Words.Talents;
    }

    public void refreshConnections() {
        connections = this.talents.getConnections();
        this.buttonConnections = getButtonConnections();
    }

    @Override
    public void init(Minecraft mc, int x, int y) {
        super.init(mc, x, y);

        for (Perk talent : SlashRegistry.Perks().getList()) {
            this.addButton(new PerkButton(talents, talent, data));
        }

        returnToCenter();

        refreshConnections();

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean bool = super.keyPressed(keyCode, scanCode, modifiers);

        if (keyCode == 32) { // SPACE BAR
            returnToCenter();
        }

        return bool;

    }

    private void returnToCenter() {
        this.scrollX = PerkButton.getSpacing() * CENTER_X;
        this.scrollY = PerkButton.getSpacing() * CENTER_Y;

        this.scrollX -= sizeX / 2;
        this.scrollY -= sizeY / 2;

        this.zoom = 0.6F;

    }

    @Override
    public boolean mouseDragged(double x, double y, int ticks, double dragX,
                                double dragY) {
        this.scrollX -= dragX * 1 / zoom;
        this.scrollY -= dragY * 1 / zoom;
        return true;

    }

    List<PerkButton> getTalentButtons() {
        List<PerkButton> list = new ArrayList<>();
        for (Widget w : this.buttons) {
            if (w instanceof PerkButton) {
                list.add((PerkButton) w);
            }
        }
        return list;

    }

    @Override
    public void render(int x, int y, float ticks) {

        if (mc.player.ticksExisted % 100 == 0) {
            MMORPG.sendToServer(new RequestSyncCapToClient(CapTypes.TALENTS));
            this.talents = Load.talents(mc.player);
            refreshConnections();
        }

        super.render(x, y, ticks);

        drawSpace();

        List<PerkButton> list = getTalentButtons();

        renderZoomables(x, y, ticks, list);

        drawBorders();

        drawPointsLeftNumber();

        renderTooltips(list, x, y);
    }

    public void renderZoomables(int x, int y, float ticks, List<PerkButton> list) {

        ScreenContext ctx = new ScreenContext(this);

        GL11.glScalef(zoom, zoom, zoom);

        renderConnections(list);

        for (PerkButton but : list) {
            but.renderButton(x, y, ctx);
        }

        float reset = 1 / zoom;
        GL11.glScalef(reset, reset, reset);

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {

        if (scroll < 0) {
            this.zoom -= 0.1F;
        }
        if (scroll > 0) {
            this.zoom += 0.1F;
        }

        this.zoom = MathHelper.clamp(zoom, 0.23F, 1);

        return true;

    }

    public void renderTooltips(List<PerkButton> list, int mouseX, int mouseY) {

        TooltipInfo info = new TooltipInfo();

        ScreenContext ctx = new ScreenContext(this);

        list.forEach(button -> {

            if (button.isInsideSlot(ctx, mouseX, mouseY)) {
                this.renderTooltip(TooltipUtils.compsToStrings(button.perk.effect.GetTooltipString(info)), mouseX, mouseY, mc.fontRenderer);
            }
        });

    }

    @Override
    public boolean mouseReleased(double x, double y, int button) {
        ScreenContext ctx = new ScreenContext(this);

        getTalentButtons().forEach(t -> t.onClick(ctx, (int) x, (int) y, button));

        refreshConnections();

        return super.mouseReleased(x, y, button);

    }

    private Set<PerkConnectionRender> getButtonConnections() {

        Set<PerkConnectionRender> conns = new ObjectArraySet<>();

        List<PerkButton> list = getTalentButtons();

        for (PerkConnection connection : connections) {

            Optional<PerkButton> but1 = list.stream()
                    .filter(button -> button.perk.GUID().equals(connection.one.GUID()))
                    .findAny();

            Optional<PerkButton> but2 = list.stream()
                    .filter(button -> button.perk.GUID().equals(connection.two.GUID()))
                    .findAny();

            if (but1.isPresent() && but2.isPresent()) {
                conns.add(new PerkConnectionRender(but1.get(), but2.get(), connection));
            }
        }
        return conns;
    }

    private void renderConnections(List<PerkButton> list) {

        ScreenContext ctx = new ScreenContext(this);

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().getTextureManager().bindTexture(LINES);

        for (PerkConnectionRender c : this.buttonConnections) {
            renderConnection(c.perk1, c.perk2, c.connection, ctx);
        }

    }

    public static boolean shouldRender(int x, int y, ScreenContext ctx, PerkType type) {

        int perkX = type == null ? 5 : type.sizeX / 2;
        int perkY = type == null ? 5 : type.sizeY / 2;

        if (x >= ctx.offsetX + 10 && x < ctx.offsetX + (sizeX - perkX) * ctx.getZoomMulti() - 10) {
            if (y >= ctx.offsetY + 10 && y < ctx.offsetY + (sizeY - perkY) * ctx.getZoomMulti() - 10) {
                return true;
            }
        }

        return false;

    }

    private void renderConnection(PerkButton one, PerkButton two,
                                  PerkConnection connection, ScreenContext ctx) {

        int x1 = one.getMiddleX(ctx);
        int y1 = one.getMiddleY(ctx);

        int x2 = two.getMiddleX(ctx);
        int y2 = two.getMiddleY(ctx);

        int size = 6;

        float spacing = size + size / 2;

        List<PointF> points = GuiUtils.generateCurve(new PointF(x1, y1), new PointF(x2, y2), 360f, spacing, true);

        for (PointF point : points) {

            int x = (int) (point.x - ((float) size / 2));
            int y = (int) (point.y - ((float) size / 2));

            if (shouldRender(x, y, ctx, null)) {
                blit(x, y, 0, connection.allocationStatus.spriteOffsetX, 0.0F, size, size, 256, 256);
            }
        }

    }

    protected void drawPointsLeftNumber() {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int offsetX = mc.mainWindow.getScaledWidth() / 2 - sizeX / 2;
        int offsetY = mc.mainWindow.getScaledHeight() / 2 - sizeY / 2 + 10;

        String str2 = "Reset Points (RMB): " + this.talents.getData().resetPoints;

        mc.fontRenderer.drawStringWithShadow(str2, offsetX + 10, offsetY, TextFormatting.GREEN
                .getColor());

        String str = "Points (LMB): " + this.talents.getFreePoints(data);

        mc.fontRenderer.drawStringWithShadow(str, offsetX + 10, offsetY + mc.fontRenderer.FONT_HEIGHT + 5, TextFormatting.GREEN
                .getColor());

    }

    protected void drawSpace() {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int offsetX = mc.mainWindow.getScaledWidth() / 2 - sizeX / 2;
        int offsetY = mc.mainWindow.getScaledHeight() / 2 - sizeY / 2;
        Minecraft.getInstance().getTextureManager().bindTexture(SPACE);
        blit(offsetX + 3, offsetY + 3, this.blitOffset, 0.0F, 0.0F, sizeX - 6, sizeY - 6, 2048, 2048);
    }

    protected void drawBorders() {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int offsetX = mc.mainWindow.getScaledWidth() / 2 - sizeX / 2;
        int offsetY = mc.mainWindow.getScaledHeight() / 2 - sizeY / 2;
        Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
        blit(offsetX, offsetY, this.blitOffset, 0.0F, 0.0F, sizeX, sizeY, 256, 512);

    }

}
