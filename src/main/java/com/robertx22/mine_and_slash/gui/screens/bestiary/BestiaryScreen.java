package com.robertx22.mine_and_slash.gui.screens.bestiary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.gui.BaseScrollbar;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.BestiaryGroup;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.UniqueGearBestiary;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BestiaryScreen extends BaseScreen implements INamedScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/window.png");
    ResourceLocation FRAME_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/frame.png");
    ResourceLocation BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/buttons.png");
    ResourceLocation SPLITTER_BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/split.png");
    ResourceLocation GROUP_BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/bestiary_group_buttons.png");

    public Minecraft mc;

    public static int entryButtonX = 223;
    public static int entryButtonY = 32;

    public static int groupButtonX = 20;
    public static int groupButtonY = 20;

    Scrollbar scrollbar;

    static int x = 248;
    static int y = 232;

    public BestiaryGroup currentBestiaryGroup = new UniqueGearBestiary();

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

        initEntries();

        setupEntryButtons();
        setupGroupButtons();
        setupScrollbar();

    }

    public void setupScrollbar() {

        int sliderXSize = 10;
        int sliderYSize = 30;

        int sliderX = guiLeft + BestiaryScreen.x - sliderXSize;
        int sliderY = guiTop + 50;

        scrollbar = addButton(new Scrollbar(sliderX, sliderY, 180));

        // AbstractSlider
    }

    public void initEntries() {
        this.entries = currentBestiaryGroup.getDefaultSplitter()
            .split(level);
        this.elementsAmount = entries.size();
    }

    public void setupGroupButtons() {

        int gx = guiLeft + BestiaryScreen.x / 2 - (BestiaryGroup.getAll()
            .size() * groupButtonX) / 2;
        int gy = guiTop + 5;

        for (BestiaryGroup bestiaryGroup : BestiaryGroup.getAll()) {
            addButton(new GroupButton(this, bestiaryGroup, gx, gy));
            gx += groupButtonX;
        }

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

                if (entry instanceof BestiaryEntry.Splitter) {
                    addButton(new SplitterButton((BestiaryEntry.Splitter) entry, x, y));
                    y += entryButtonY + 2;
                } else if (entry instanceof BestiaryEntry.Item) {
                    addButton(new EntryButton((BestiaryEntry.Item) entry, x, y));
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

        this.setCurrentElement((int) (currentElement - num3));

        scrollbar.setValueFromElement(currentElement, elementsAmount);

        return super.mouseScrolled(num1, num2, num3);

    }

    public void setCurrentElement(int element) {

        this.currentElement = MathHelper.clamp(element, 0, elementsAmount);

        setupEntryButtons();

    }

    @Override
    public boolean mouseReleased(double m1, double m2, int m3) {
        this.setDragging(false);

        buttons.stream()
            .filter(x -> x.isMouseOver(m1, m2))
            .findFirst()
            .ifPresent(x -> x.onClick(m1, m2));

        return super.mouseReleased(m1, m2, m3);

    }

    class SplitterButton extends ImageButton {
        BestiaryEntry.Splitter splitter;

        public SplitterButton(BestiaryEntry.Splitter splitter, int xPos, int yPos) {
            super(xPos, yPos, entryButtonX, entryButtonY, 0, 0, entryButtonY, SPLITTER_BUTTON_TEXTURE, (button) -> {
            });

            this.splitter = splitter;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);

            int xp = (int) (this.x + 10);
            int yp = (int) (this.y + entryButtonY / 2) - mc.fontRenderer.FONT_HEIGHT / 2;

            mc.fontRenderer.drawStringWithShadow(splitter.splitReason, xp, yp, TextFormatting.YELLOW.getColor());

        }

    }

    public void setGroup(BestiaryGroup group) {
        this.currentBestiaryGroup = group;

        initEntries();

        this.currentElement = 0;

        this.setupEntryButtons();

    }

    class Scrollbar extends BaseScrollbar {

        protected Scrollbar(int xpos, int ypos, int scrollbarTotalHeight) {
            super(xpos, ypos, scrollbarTotalHeight);
        }

        @Override
        protected void applyValue() {
            BestiaryScreen.this.setCurrentElement((int) (this.value * BestiaryScreen.this.elementsAmount));
        }
    }

    class GroupButton extends ImageButton {
        BestiaryGroup group;
        BestiaryScreen screen;

        public GroupButton(BestiaryScreen screen, BestiaryGroup group, int xPos, int yPos) {
            super(xPos, yPos, groupButtonX, groupButtonY, 0, 0, groupButtonY, GROUP_BUTTON_TEXTURE, (button) -> {
            });

            this.screen = screen;
            this.group = group;

        }

        @Override
        public void onPress() {
            super.onPress();

            screen.setGroup(group);

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (this.isHovered) {
                BestiaryScreen.this.renderTooltip(
                    Arrays.asList(TextFormatting.BLUE + "" + TextFormatting.BOLD +
                        CLOC.translate(group.getName())), x, y, Minecraft.getInstance().fontRenderer);
            }
        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);
            RenderUtils.render16Icon(group.getTextureLoc(), this.x + 2, this.y + 2);
        }

    }

    class EntryButton extends ImageButton {
        BestiaryEntry.Item item;

        public EntryButton(BestiaryEntry.Item item, int xPos, int yPos) {
            super(xPos, yPos, entryButtonX, entryButtonY, 0, 0, entryButtonY, BUTTON_TEXTURE, (button) -> {
            });

            this.item = item;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            super.renderButton(x, y, ticks);

            if (item != null) {
                mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(item.stack, this.x + 12, this.y + 8);

                int xp = (int) (this.x + 35);
                int yp = (int) (this.y + entryButtonY / 2) - mc.fontRenderer.FONT_HEIGHT / 2;

                mc.fontRenderer.drawStringWithShadow(item.getName(), xp, yp, TextFormatting.GREEN.getColor());

            }

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (GuiUtils.isInRect(this.x, this.y, entryButtonX, entryButtonY, x, y)) {
                BestiaryScreen.this.renderTooltip(item.stack, x, y);

            }
        }
    }

}

