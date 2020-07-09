package com.robertx22.mine_and_slash.gui.screens.main_hub;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.IAlertScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.gui.overlays.bar_overlays.bases.BaseBarsOverlay;
import com.robertx22.mine_and_slash.gui.screens.spell_hotbar_setup.SpellHotbatSetupScreen;
import com.robertx22.mine_and_slash.gui.screens.stats_overview.StatOverviewScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class MainHubScreen extends BaseScreen implements INamedScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/main_hub/window.png");
    public static ResourceLocation EXLAMATION_MARK_TEX = new ResourceLocation(
        Ref.MODID, "textures/gui/main_hub/exclamation_mark.png");

    public Minecraft mc;

    static int x = 318;
    static int y = 232;

    public MainHubScreen() {
        super(x, y);
        this.mc = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        List<INamedScreen> screens = new ArrayList<>();

        screens.add(new SpellHotbatSetupScreen());
        screens.add(new StatOverviewScreen());

        int x = guiLeft + 10;
        int y = guiTop + 45;

        int count = 0;

        for (INamedScreen screen : screens) {

            if (count >= 3) {
                y += Button.ySize + 5;
                x = guiLeft + 9;
                count = 0;
            }
            if (count >= 1) {
                x += Button.xSize + 5;
            }
            count++;

            addButton(new Button(screen, x, y));

        }

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        super.render(x, y, ticks);

        renderTitle();

        int expx = this.guiLeft + sizeX / 2 - BaseBarsOverlay.BAR_WIDTH / 2;
        int expy = this.guiTop + sizeY - BaseBarsOverlay.BAR_HEIGHT - 5;

    }

    private void renderTitle() {
        double scale = 1.8F;
        String str = "Main Hub";
        int xp = (int) (guiLeft + (MainHubScreen.x / 2));
        int yp = (int) (guiTop + 20);
        GuiUtils.renderScaledText(xp, yp, scale, str, TextFormatting.YELLOW);
    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 512);

    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/map_info.png");
    }

    @Override
    public Words screenName() {
        return Words.MapInfo;
    }

    static class Button extends ImageButton {

        public static int xSize = 95;
        public static int ySize = 32;

        boolean shouldAlert = false;

        static ResourceLocation buttonLoc = new ResourceLocation(Ref.MODID, "textures/gui/main_hub/buttons.png");

        INamedScreen screen;

        public Button(INamedScreen screen, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, buttonLoc, (button) -> {
                Minecraft.getInstance()
                    .displayGuiScreen((Screen) screen);

            });

            this.screen = screen;

            if (screen instanceof IAlertScreen) {
                IAlertScreen alert = (IAlertScreen) screen;
                this.shouldAlert = alert.shouldAlert();
            }

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);

            RenderUtils.render16Icon(screen.iconLocation(), this.x + 9, this.y + 7);

            if (shouldAlert) {
                RenderUtils.render16Icon(EXLAMATION_MARK_TEX, this.x + 5, this.y + 7);
            }

            String str = screen.screenName()
                .translate();

            Minecraft.getInstance().fontRenderer.drawStringWithShadow(
                str, this.x + 30, this.y + 10, TextFormatting.GREEN.getColor());
        }

    }

}

