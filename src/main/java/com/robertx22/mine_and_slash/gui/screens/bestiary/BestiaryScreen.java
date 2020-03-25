package com.robertx22.mine_and_slash.gui.screens.bestiary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class BestiaryScreen extends BaseScreen implements INamedScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/window.png");
    ResourceLocation FRAME_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/frame.png");
    ResourceLocation BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/buttons.png");
    ResourceLocation SPLITTER_BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/split.png");

    public Minecraft mc;

    public static int entryButtonX = 200;
    public static int entryButtonY = 32;

    static int x = 318;
    static int y = 232;

    public BestiaryGroup currentBestiaryGroup = BestiaryGroup.UNIQUE_GEAR;

    int currentElement = 0;

    public List<BestiaryEntry> entries = new ArrayList<>();

    int level = 1;
    int elementsAmount = 1;

    public BestiaryScreen() {
        super(x, y);
        this.mc = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        this.level = Load.Unit(mc.player)
            .getLevel();
        this.entries = currentBestiaryGroup.getAll(level);
        this.elementsAmount = currentBestiaryGroup
            .getSize();

        setupEntryButtons();
    }

    public void setupEntryButtons() {

        this.buttons.removeIf(x -> x instanceof EntryButton || x instanceof SplitterButton);

        int x = this.guiLeft + BestiaryScreen.x / 2 - entryButtonX / 2;
        int y = this.guiTop + 50;

        for (int i = currentElement; i < currentElement + 5; i++) {
            if (i >= elementsAmount) {
                continue;
            } else {

                BestiaryEntry entry = entries.get(i);

                if (entry.isSplitter()) {
                    this.buttons.add(new SplitterButton((BestiaryEntry.Splitter) entry, x, y));
                    y += entryButtonY + 2;
                } else if (entry.isItem()) {
                    this.buttons.add(new EntryButton(entry.stack, x, y));
                    y += entryButtonY + 2;
                }

            }

        }

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        super.render(x, y, ticks);

        this.buttons.forEach(b -> b.renderToolTip(x, y));

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 512);
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(FRAME_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 512);
    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/bestiary.png");
    }

    @Override
    public Words screenName() {
        return Words.Bestiary;
    }

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {
        this.currentElement -= num3;
        this.currentElement = MathHelper.clamp(currentElement, 0, elementsAmount);

        setupEntryButtons();

        return false;
    }

    class SplitterButton extends ImageButton {
        BestiaryEntry.Splitter splitter;

        public SplitterButton(BestiaryEntry.Splitter splitter, int xPos, int yPos) {
            super(xPos, yPos, entryButtonX, entryButtonY, 0, 0, entryButtonY + 1, SPLITTER_BUTTON_TEXTURE, (button) -> {
            });

            this.splitter = splitter;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);

            double scale = 1.5F;
            int xp = (int) (this.x + 35);
            int yp = (int) (this.y + entryButtonY / 2);
            GuiUtils.renderScaledText(xp, yp, scale, splitter.splitReason, TextFormatting.YELLOW);
        }

    }

    class EntryButton extends ImageButton {
        ItemStack stack;

        public EntryButton(ItemStack stack, int xPos, int yPos) {
            super(xPos, yPos, entryButtonX, entryButtonY, 0, 0, entryButtonY + 1, BUTTON_TEXTURE, (button) -> {
            });

            this.stack = stack;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            super.renderButton(x, y, ticks);

            if (stack != null) {
                mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(stack, this.x + 12, this.y + 8);
            }
        }

        @Override
        public void renderToolTip(int x, int y) {
            if (GuiUtils.isInRect(this.x, this.y, entryButtonX, entryButtonY, x, y)) {

                BestiaryScreen.this.renderTooltip(stack, x, y);

            }
        }
    }

}

