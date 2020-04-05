package com.robertx22.mine_and_slash.gui.bases;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.database.talent_tree.PerkScreenContext;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.gui.screens.talent_tree_gui.PerkButton;
import com.robertx22.mine_and_slash.gui.screens.talent_tree_gui.PerkConnectionRender;
import com.robertx22.mine_and_slash.mmorpg.CapSyncCheck;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.talents.BasePerksData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.IPerkCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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

import static com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils.PointF;
import static com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils.generateCurve;

public abstract class BasePerkTreeScreen<T extends BasePerk, D extends BasePerksData<T>, C extends IPerkCap<T, D>> extends Screen implements INamedScreen {

    public Minecraft mc;
    public EntityCap.UnitData unitData;

    public Set<PerkConnection> connections;
    public Set<PerkConnectionRender> buttonConnections;

    public C capData;

    public float scrollX = 0;
    public float scrollY = 0;
    public float zoom = 1;

    public static int CENTER_X = 500;
    public static int CENTER_Y = 500;

    public abstract ResourceLocation getBorderTexture();

    public abstract ResourceLocation getSpaceTexture();

    public abstract ResourceLocation getLineTexture();

    public static int sizeX() {
        return Minecraft.getInstance().mainWindow.getWidth();
    }

    public static int sizeY() {
        return Minecraft.getInstance().mainWindow.getHeight();
    }

    public abstract PlayerCaps getCapType();

    public abstract void reloadData();

    public BasePerkTreeScreen() {
        super(new StringTextComponent(""));
        this.mc = Minecraft.getInstance();
        this.unitData = Load.Unit(mc.player);

        reloadData();

        requestSync();

    }

    public void requestSync() {
        MMORPG.sendToServer(new RequestSyncCapToClient(getCapType()));
    }

    public void refreshConnections() {
        connections = this.capData.getConnections();
        this.buttonConnections = getButtonConnections();
    }

    @Override
    public void init(Minecraft mc, int x, int y) {
        super.init(mc, x, y);

    }

    public void refresh() {
        MMORPG.sendToServer(new RequestSyncCapToClient(getCapType()));

        reloadData();

        refreshConnections();
        refreshAllocatedStatus();
    }

    public void refreshAllocatedStatus() {

        this.buttons.stream()
            .filter(x -> x instanceof PerkButton)
            .forEach(y -> ((PerkButton) y).status = ((PerkButton) y).perk.getStatus(capData));

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean bool = super.keyPressed(keyCode, scanCode, modifiers);

        if (keyCode == 32) { // SPACE BAR
            returnToCenter();
        }

        return bool;

    }

    public void returnToCenter() {
        this.scrollX = PerkButton.getSpacing() * CENTER_X;
        this.scrollY = PerkButton.getSpacing() * CENTER_Y;

        this.scrollX -= sizeX() / 2;
        this.scrollY -= sizeY() / 2;

        this.zoom = 0.6F;

    }

    @Override
    public boolean mouseDragged(double x, double y, int ticks, double dragX, double dragY) {
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

        this.ticks++;

        if (CapSyncCheck.get(getCapType())) {
            refresh();
        }

        if (mc.player.ticksExisted % 100 == 0) {
            requestSync();
            refresh();
        }

        super.render(x, y, ticks);

        drawSpace();

        List<PerkButton> list = getTalentButtons();

        renderZoomables(x, y, ticks, list);

        drawPointsLeftNumber();

        renderTooltips(list, x, y);
    }

    public void renderZoomables(int x, int y, float ticks, List<PerkButton> list) {

        PerkScreenContext ctx = new PerkScreenContext(this);

        GL11.glScalef(zoom, zoom, zoom);

        renderConnections(list);

        for (PerkButton but : list) {
            but.renderButton(x, y, ctx);
        }

        float reset = 1 / zoom;
        GL11.glScalef(reset, reset, reset);

    }

    public float maxZoomOut = 0.23F;

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {

        if (scroll < 0) {
            this.zoom -= 0.1F;
        }
        if (scroll > 0) {
            this.zoom += 0.1F;
        }

        this.zoom = MathHelper.clamp(zoom, maxZoomOut, 1);

        return true;

    }

    int ticks = 0;

    public void renderTooltips(List<PerkButton> list, int mouseX, int mouseY) {

        TooltipInfo info = new TooltipInfo(mc.player);

        PerkScreenContext ctx = new PerkScreenContext(this);

        list.forEach(button -> {

            if (button.isInsideSlot(ctx, mouseX, mouseY)) {
                this.renderTooltip(TooltipUtils.compsToStrings(button.perk.effect.GetTooltipString(info)), mouseX,
                    mouseY, mc.fontRenderer
                );
            }
        });

    }

    @Override
    public boolean mouseReleased(double x, double y, int button) {

        if (this.ticks < 100) {
            return false; // dont work straight from entering gui, can cause undesired talent allocation
        }

        PerkScreenContext ctx = new PerkScreenContext(this);

        getTalentButtons().forEach(t -> t.onClick(ctx, (int) x, (int) y, button));

        boolean bool = super.mouseReleased(x, y, button);

        refresh();

        return bool;

    }

    public Set<PerkConnectionRender> getButtonConnections() {

        Set<PerkConnectionRender> conns = new ObjectArraySet<>();

        List<PerkButton> list = getTalentButtons();

        for (PerkConnection connection : connections) {

            Optional<PerkButton> but1 = list.stream()
                .filter(button -> button.perk.GUID()
                    .equals(connection.one.GUID()))
                .findAny();

            Optional<PerkButton> but2 = list.stream()
                .filter(button -> button.perk.GUID()
                    .equals(connection.two.GUID()))
                .findAny();

            if (but1.isPresent() && but2.isPresent()) {
                conns.add(new PerkConnectionRender(but1.get(), but2.get(), connection));
            }
        }
        return conns;
    }

    private void renderConnections(List<PerkButton> list) {

        PerkScreenContext ctx = new PerkScreenContext(this);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(getLineTexture());

        for (PerkConnectionRender c : this.buttonConnections) {
            renderConnection(c.perk1, c.perk2, c.connection, ctx);
        }

    }

    public static boolean shouldRender(int x, int y, PerkScreenContext ctx, PerkType type) {

        int perkX = type == null ? 5 : type.sizeX / 2;
        int perkY = type == null ? 5 : type.sizeY / 2;

        if (x >= ctx.offsetX + 10 && x < ctx.offsetX + (sizeX() - perkX) * ctx.getZoomMulti() - 10) {
            if (y >= ctx.offsetY + 10 && y < ctx.offsetY + (sizeY() - perkY) * ctx.getZoomMulti() - 10) {
                return true;
            }
        }

        return false;

    }

    private void renderConnection(PerkButton one, PerkButton two, PerkConnection connection, PerkScreenContext ctx) {

        int x1 = one.getMiddleX(ctx);
        int y1 = one.getMiddleY(ctx);

        int x2 = two.getMiddleX(ctx);
        int y2 = two.getMiddleY(ctx);

        int size = 6;

        float spacing = size + size / 2;

        List<PointF> points = generateCurve(new PointF(x1, y1), new PointF(x2, y2), 360f, spacing, true);

        for (PointF point : points) {

            int x = (int) (point.x - ((float) size / 2));
            int y = (int) (point.y - ((float) size / 2));

            if (shouldRender(x, y, ctx, null)) {
                blit(x, y, 0, connection.allocationStatus.spriteOffsetX, 0.0F, size, size, 256, 256);
            }
        }

    }

    protected void drawSpace() {

        int maxX = 5000;
        int maxY = 5000;

        float ticks = 0;

        if (sizeX() > maxX || sizeY() > maxY) {
            this.renderDirtBackground(0); // if screen is larger than my space picture
        } else {
            float x = sizeX();
            float y = sizeY();

            float scale = x / maxX;

            float antiScale = 1F / scale;

            RenderSystem.scaled(scale, scale, scale);

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            int offsetX = mc.mainWindow.getScaledWidth() / 2 - sizeX() / 2;
            int offsetY = mc.mainWindow.getScaledHeight() / 2 - sizeY() / 2;
            Minecraft.getInstance()
                .getTextureManager()
                .bindTexture(getSpaceTexture());
            blit(0, 0, 0, 0.0F, 0.0F, 5000, 5000, 5000, 5000);

            RenderSystem.scaled(antiScale, antiScale, antiScale);
        }
    }

    protected void drawPointsLeftNumber() {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int offsetX = 5;
        int offsetY = 5;

        String str2 = "Reset Points (RMB): " + this.capData.getAbilitiesData().resetPoints;

        mc.fontRenderer.drawStringWithShadow(str2, offsetX, offsetY, TextFormatting.GREEN.getColor());

        String str = "Points (LMB): " + this.capData.getFreePoints(unitData);

        mc.fontRenderer.drawStringWithShadow(
            str, offsetX, offsetY + mc.fontRenderer.FONT_HEIGHT + 5, TextFormatting.GREEN.getColor());

    }
}
