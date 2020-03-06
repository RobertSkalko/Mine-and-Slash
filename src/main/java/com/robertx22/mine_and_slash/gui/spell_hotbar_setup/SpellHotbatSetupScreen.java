package com.robertx22.mine_and_slash.gui.spell_hotbar_setup;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.spells.HotbarSetupPacket;
import com.robertx22.mine_and_slash.packets.spells.WeaponRightClickSpellPacket;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellHotbatSetupScreen extends BaseScreen implements INamedScreen {

    static ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
        Ref.MODID, "textures/gui/hotbar_setup/window.png");
    public Minecraft mc;

    static int x = 212;
    static int y = 222;

    PlayerSpellCap.ISpellsCap spells;

    public SpellHotbatSetupScreen() {
        super(x, y);
        this.mc = Minecraft.getInstance();
        this.spells = Load.spells(mc.player);
    }

    @Override
    protected void init() {
        super.init();

        List<BaseSpell> spells = this.spells.getAvailableSpells();

        int x = guiLeft + 7;
        int y = guiTop + 40;

        int count = 0;

        for (BaseSpell spell : spells) {

            if (spell == null) {
                continue;
            }

            if (count >= 11) {
                y += AvailableSpellButton.ySize + 2;
                x = guiLeft + 7;
                count = 0;
            }
            if (count >= 1) {
                x += AvailableSpellButton.xSize + 2;
            }
            count++;
            addButton(new AvailableSpellButton(spell, x, y));
        }

        count = 0;

        x = guiLeft + 55;
        y = guiTop + 90;

        HotbarButton right = new HotbarButton(0, PlayerSpellsData.Hotbar.FIRST, guiLeft + SpellHotbatSetupScreen.x / 2 - HotbarButton.xSize / 2, y);
        right.isForRightClickWeaponSpell = true;
        this.addButton(right);

        for (PlayerSpellsData.Hotbar bar : Arrays.asList(
            PlayerSpellsData.Hotbar.FIRST, PlayerSpellsData.Hotbar.SECOND)) {

            y += 50;
            x = guiLeft + 55;

            for (int i = 0; i < 5; i++) {

                HotbarButton but = new HotbarButton(i, bar, x, y);

                this.addButton(but);

                x += HotbarButton.xSize;

            }

        }

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        drawText();

        super.render(x, y, ticks);

        this.buttons.forEach(b -> b.renderToolTip(x, y));

    }

    private void drawText() {

        double scale = 1.25;

        String str = "Weapon Right Click Spell";
        int xp = (int) (guiLeft + (SpellHotbatSetupScreen.x / 2));
        int yp = 80 + guiTop;
        GuiUtils.renderScaledText(xp, yp, scale, str, TextFormatting.LIGHT_PURPLE);

        str = "First Hotbar";
        xp = (int) (guiLeft + (SpellHotbatSetupScreen.x / 2));
        yp = 130 + guiTop;
        GuiUtils.renderScaledText(xp, yp, scale, str, TextFormatting.GREEN);

        str = "Second Hotbar";
        xp = (int) (guiLeft + (SpellHotbatSetupScreen.x / 2));
        yp = 180 + guiTop;
        GuiUtils.renderScaledText(xp, yp, scale, str, TextFormatting.GREEN);

        str = "Available Spells";
        xp = (int) (guiLeft + (SpellHotbatSetupScreen.x / 2));
        yp = 12 + guiTop;
        GuiUtils.renderScaledText(xp, yp, scale, str, TextFormatting.YELLOW);

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 256);

    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/spellbar.png");
    }

    @Override
    public Words screenName() {
        return Words.Spellbar;
    }

    public static HotbarButton barBeingPicked = null;

    static class HotbarButton extends ImageButton {

        public static int xSize = 20;
        public static int ySize = 20;

        static ResourceLocation NORMAL_TEX = new ResourceLocation(
            Ref.MODID, "textures/gui/hotbar_setup/hotbar_button.png");
        static ResourceLocation PICKED_TEX = new ResourceLocation(
            Ref.MODID, "textures/gui/hotbar_setup/picked_bar.png");

        int number;
        PlayerSpellsData.Hotbar hotbar;

        public boolean isForRightClickWeaponSpell = false;

        public HotbarButton(int number, PlayerSpellsData.Hotbar hotbar, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, new ResourceLocation(""), (button) -> {
            });

            this.hotbar = hotbar;
            this.number = number;

        }

        @Override
        public void renderToolTip(int mouseX, int mouseY) {
            if (this.getSpell() != null) {
                if (GuiUtils.isInRectPoints(new Point(x, y), new Point(xSize, ySize), new Point(mouseX, mouseY))) {
                    TooltipInfo info = new TooltipInfo(Minecraft.getInstance().player);
                    GuiUtils.renderTooltip(getSpell().GetTooltipString(info), mouseX, mouseY);
                }
            } else {
                if (this.isForRightClickWeaponSpell) {
                    if (GuiUtils.isInRectPoints(new Point(x, y), new Point(xSize, ySize), new Point(mouseX, mouseY))) {
                        TooltipInfo info = new TooltipInfo(Minecraft.getInstance().player);
                        List<ITextComponent> tip = new ArrayList<>();
                        tip.add(new SText(TextFormatting.BLUE + "Some spells can be attached to a weapon as a right click spell."));
                        GuiUtils.renderTooltip(tip, mouseX, mouseY);
                    }
                }
            }
        }

        @Override
        public void onPress() {
            super.onPress();

            if (!isForRightClickWeaponSpell) {
                if (this.getSpell() != null) {
                    MMORPG.sendToServer(new HotbarSetupPacket(null, number, hotbar));
                } else {
                    SpellHotbatSetupScreen.barBeingPicked = this;
                }
            } else {
                if (this.getSpell() != null) {
                    MMORPG.sendToServer(new WeaponRightClickSpellPacket(null));
                    WeaponRightClickSpellPacket.activate(Minecraft.getInstance().player, ""); // for client sync
                } else {
                    SpellHotbatSetupScreen.barBeingPicked = this;
                }
            }
        }

        @Nullable
        public BaseSpell getSpell() {
            if (isForRightClickWeaponSpell) {
                GearItemData gear = Gear.Load(Minecraft.getInstance().player.getHeldItemMainhand());
                return gear != null ? gear.getRightClickSpell() : null;
            } else {
                return Load.spells(Minecraft.getInstance().player)
                    .getSpellData()
                    .getSpellByKeybind(number, hotbar);
            }
        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            Minecraft mc = Minecraft.getInstance();

            if (SpellHotbatSetupScreen.barBeingPicked == this) {
                mc.getTextureManager()
                    .bindTexture(PICKED_TEX);
            } else {
                mc.getTextureManager()
                    .bindTexture(NORMAL_TEX);
            }

            RenderSystem.disableDepthTest();

            blit(this.x, this.y, 0, 0, this.width, this.height);

            RenderSystem.enableDepthTest();

            BaseSpell spell = getSpell();

            if (spell != null) {
                RenderUtils.render16Icon(spell.getIcon(), this.x + 2, this.y + 2);
            }
        }

    }

    static class AvailableSpellButton extends ImageButton {

        public static int xSize = 16;
        public static int ySize = 16;

        static ResourceLocation buttonLoc = new ResourceLocation(Ref.MODID, "textures/gui/main_hub/buttons.png");

        BaseSpell spell;

        public AvailableSpellButton(BaseSpell spell, int xPos, int yPos) {

            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, new ResourceLocation(""), (button) -> {

                if (SpellHotbatSetupScreen.barBeingPicked != null) {

                    HotbarButton bar = SpellHotbatSetupScreen.barBeingPicked;

                    if (bar.isForRightClickWeaponSpell) {
                        MMORPG.sendToServer(new WeaponRightClickSpellPacket(spell));
                        WeaponRightClickSpellPacket.activate(Minecraft.getInstance().player, spell.GUID()); // for client sync
                    } else {
                        if (bar.hotbar != null) {
                            MMORPG.sendToServer(new HotbarSetupPacket(spell, bar.number, bar.hotbar));
                        }
                    }
                }

            });

            this.spell = spell;

        }

        @Override
        public void renderToolTip(int mouseX, int mouseY) {
            if (spell != null) {
                if (GuiUtils.isInRectPoints(new Point(x, y), new Point(xSize, ySize), new Point(mouseX, mouseY))) {
                    TooltipInfo info = new TooltipInfo(Minecraft.getInstance().player);
                    GuiUtils.renderTooltip(spell.GetTooltipString(info), mouseX, mouseY);
                }
            }
        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            //super.renderButton(x, y, ticks);

            if (spell != null && spell.getIcon() != null) {
                RenderUtils.render16Icon(spell.getIcon(), this.x, this.y);
            }
        }

    }

}

