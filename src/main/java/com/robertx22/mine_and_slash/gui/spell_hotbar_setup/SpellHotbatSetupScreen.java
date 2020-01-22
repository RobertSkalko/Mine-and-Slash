package com.robertx22.mine_and_slash.gui.spell_hotbar_setup;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class SpellHotbatSetupScreen extends BaseScreen implements INamedScreen {

    static ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
            Ref.MODID, "textures/gui/hotbar_setup/window.png");
    public Minecraft mc;

    static int x = 215;
    static int y = 220;

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

        for (int i = 0; i < 10; i++) {
            spells.addAll(this.spells.getAvailableSpells());
        }

        int x = guiLeft + 10;
        int y = guiTop + 10;

        int count = 0;

        for (BaseSpell spell : spells) {
            if (count >= 10) {
                y += AvailableSpellButton.ySize + 2;
                x = guiLeft + 10;
                count = 0;
            }
            if (count >= 1) {
                x += AvailableSpellButton.xSize + 2;
            }
            count++;
            addButton(new AvailableSpellButton(spell, x, y));
        }

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        super.render(x, y, ticks);

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 512);

    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/spellbar.png");
    }

    @Override
    public Words screenName() {
        return Words.Spellbar;
    }

    static class HotbarButton extends ImageButton {

        public static int xSize = 16;
        public static int ySize = 16;

        static ResourceLocation buttonLoc = new ResourceLocation(
                Ref.MODID, "textures/gui/hotbar_setup/hotbar_button.png");

        @Nullable
        BaseSpell spell;

        public HotbarButton(BaseSpell spell, int xPos, int yPos) {

            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, buttonLoc, (button) -> {

            });

            this.spell = spell;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);

            if (spell != null) {
                RenderUtils.renderIcon(spell.getIcon(), this.x, this.y);
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

            });

            this.spell = spell;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);

            RenderUtils.renderIcon(spell.getIcon(), this.x, this.y);

        }

    }

}

