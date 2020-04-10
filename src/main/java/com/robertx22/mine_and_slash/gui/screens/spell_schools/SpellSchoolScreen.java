package com.robertx22.mine_and_slash.gui.screens.spell_schools;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.allocation.abilities.TryAllocateAbilityPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.abilities.TryRemoveAbilityPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.schools.TryAddSchoolPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.schools.TryRemoveSchoolPointPacket;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpellSchoolScreen extends BaseScreen implements INamedScreen {

    static ResourceLocation BACKGROUND = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/background.png");
    static ResourceLocation FOREGROUND = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/fore.png");
    static ResourceLocation PICK_BACKGROUND = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/pick_background.png");
    static ResourceLocation PARTS = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/parts.png");
    static ResourceLocation ABILITY = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/ability.png");
    static ResourceLocation SCHOOL_BUTTON = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/school_button.png");
    static ResourceLocation BACK_BUTTON = new ResourceLocation(
        Ref.MODID, "textures/gui/spell_schools/back_button.png");

    static int X = 246;
    static int Y = 186;

    PlayerSpellCap.ISpellsCap spells;
    EntityCap.UnitData data;

    public SpellSchoolScreen() {
        super(X, Y);
    }

    public SpellSchoolScreen(SpellSchools school) {
        super(X, Y);
        this.school = school;
    }

    private SpellSchools school = null;

    public void setSpellSchool(SpellSchools school) {
        this.school = school;
        this.init();
    }

    @Override
    public void init() {

        this.spells = Load.spells(Minecraft.getInstance().player);
        this.data = Load.Unit(Minecraft.getInstance().player);

        this.buttons.clear();

        super.init();

        if (this.school != null) {
            addButton(new SchoolButton(school, guiLeft + 14, guiTop + 10));

            addButton(new BackButton(guiLeft + X - BackButton.xSize, guiTop - BackButton.ySize));

            int i = 0;
            for (BasePotionEffect e : SlashRegistry.PotionEffects()
                .getFilterWrapped(x -> x.getSchool()
                    .equals(school)).list) {

                if (e.getSpell() == null) {
                    int xpos = guiLeft + 50 + (i * (AbilityButton.xSize + 6));
                    int ypos = guiTop + 157;
                    addButton(new AbilityButton(e, xpos, ypos));
                    i++;
                }
            }

            List<IAbility> list = new ArrayList<>();

            list.addAll(
                SlashRegistry.Spells()
                    .getFilterWrapped(x -> x.getSchool()
                        .equals(school)).list);
            list.addAll(
                SlashRegistry.Synergies()
                    .getFilterWrapped(x -> x.getSchool()
                        .equals(school)).list);

            list.forEach(x -> {

                int xpos = guiLeft + 50 + x.getAbilityPlace().x * (AbilityButton.xSize + 6);
                int ypos = guiTop + 134 - x.getAbilityPlace().y * (AbilityButton.ySize + 3);

                this.addButton(new AbilityButton(x, xpos, ypos));

            });

        } else {

            int xpos = guiLeft + 25;
            int ypos = guiTop + 15;

            for (SpellSchools value : SpellSchools.values()) {

                addButton(new PickSchoolButton(this, value, xpos, ypos));

                xpos += PickSchoolButton.xSize + PickSchoolButton.xSize / 3;

            }

        }

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        this.buttons.forEach(b -> {
            if (b instanceof AbilityButton) {
                AbilityButton ab = (AbilityButton) b;
                ab.renderUnder();
            }
        });

        super.render(x, y, ticks);

        Minecraft mc = Minecraft.getInstance();

        if (school != null) {
            String str = "Skill Points: " + spells.getAbilitiesData()
                .getFreeAbilityPoints(data);
            int xp = (int) (guiLeft + 5);
            int yp = guiTop + Y + 5;
            mc.fontRenderer.drawStringWithShadow(str, xp, yp, TextFormatting.GREEN.getColor());

            str = "Reset Points: " + spells.getAbilitiesData().resetPoints;
            xp = (int) (guiLeft + X - mc.fontRenderer.getStringWidth(str));
            yp = guiTop + Y + 5;
            mc.fontRenderer.drawStringWithShadow(str, xp, yp, TextFormatting.LIGHT_PURPLE.getColor());
        }

        this.buttons.forEach(b -> b.renderToolTip(x, y));

    }

    protected void drawBackground(float partialTicks, int x, int y) {

        RenderSystem.enableBlend();

        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(PICK_BACKGROUND);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, X, Y, 256, 256);

        if (school != null) {

            Minecraft.getInstance()
                .getTextureManager()
                .bindTexture(FOREGROUND);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            blit(guiLeft + 7, guiTop + 7, this.getBlitOffset(), 0.0F, 0.0F, X, Y, 256, 256);

        }

        RenderSystem.disableBlend();

    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/spells.png");
    }

    @Override
    public Words screenName() {
        return Words.Spells;
    }

    static class AbilityButton extends ImageButton {
        public static int xSize = 18;
        public static int ySize = 18;

        IAbility ability;

        EntityCap.UnitData data;

        PlayerSpellCap.ISpellsCap spells;

        public AbilityButton(IAbility ability, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, new ResourceLocation(""), (button) -> {
            });

            this.ability = ability;
            this.data = Load.Unit(Minecraft.getInstance().player);
            this.spells = Load.spells(Minecraft.getInstance().player);

        }

        public void renderToolTip(int mouseX, int mouseY) {
            if (this.ability != null) {
                if (GuiUtils.isInRectPoints(new Point(x, y), new Point(xSize, ySize), new Point(mouseX, mouseY))) {
                    TooltipInfo info = new TooltipInfo(Minecraft.getInstance().player);
                    GuiUtils.renderTooltip(ability.GetTooltipString(info), mouseX, mouseY);
                }
            }
        }

        @Override
        public boolean mouseClicked(double x, double y, int click) {
            if (this.active && this.visible && this.isHovered()) {
                if (click == 1) { // if right click
                    MMORPG.sendToServer(new TryRemoveAbilityPointPacket(ability));
                } else {
                    MMORPG.sendToServer(new TryAllocateAbilityPointPacket(ability));
                }
            }
            return super.mouseClicked(x, y, click);
        }

        public void renderUnder() {
            // renders connectors to synergies and spells. Really simple hacky system that just works for now.

            if (ability.getAbilityType()
                .equals(IAbility.Type.SYNERGY)) {

                Minecraft mc = Minecraft.getInstance();

                mc.getTextureManager()
                    .bindTexture(ABILITY);

                blit(this.x + this.xSize / 2 - 3, this.y + this.ySize / 2, 136, 0, 6, 21);
            }
        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            Minecraft mc = Minecraft.getInstance();

            mc.getTextureManager()
                .bindTexture(ABILITY);

            int offx = 0;
            int offy = 0;

            if (ability.getAbilityType()
                .equals(IAbility.Type.SPELL)) {
                offx = 19;
            }

            if (spells.getAbilitiesData()
                .getFreeAbilityPoints(data) > 0 && spells.getLevelOf(ability) < 1 && !spells
                .getAbilitiesData()
                .canAddPointsOrHasPoints(ability, data)) {
                offy = 19;
            }

            RenderSystem.disableDepthTest();

            blit(this.x, this.y, offx, offy, this.width, this.height);

            RenderSystem.enableDepthTest();

            if (ability != null) {

                float s = 0.5F;
                float as = 1 / s;

                RenderSystem.scalef(s, s, s);
                RenderUtils.render32Icon(
                    ability.getIconLoc(),
                    (int) ((this.x + 5) * as),
                    (int) ((this.y + 5) * as));
                RenderSystem.scalef(as, as, as);

                String str = spells.getLevelOf(ability) + "/" + ability.getMaxSpellLevelNormal();
                int xp = (int) (this.x + xSize / 2);
                int yp = this.y + xSize - 2;
                GuiUtils.renderScaledText(xp, yp, 0.75F, str, TextFormatting.YELLOW);

            }

        }

    }

    static class PickSchoolButton extends ImageButton {
        public static int xSize = 32;
        public static int ySize = 32;

        SpellSchools school;

        EntityCap.UnitData data;

        SpellSchoolScreen screen;

        public PickSchoolButton(SpellSchoolScreen screen, SpellSchools school, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, new ResourceLocation(""), (button) -> {
            });

            this.screen = screen;
            this.school = school;
            this.data = Load.Unit(Minecraft.getInstance().player);

        }

        public void renderToolTip(int mouseX, int mouseY) {

        }

        @Override
        public boolean mouseClicked(double x, double y, int click) {
            if (this.active && this.visible && this.isHovered()) {
                //this.screen.setSpellSchool(school);
                Minecraft.getInstance()
                    .displayGuiScreen(new SpellSchoolScreen(school));
                return true;
            }
            return super.mouseClicked(x, y, click);
        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            Minecraft mc = Minecraft.getInstance();

            mc.getTextureManager()
                .bindTexture(school.getGuiIconLocation());

            int offx = 0;
            int offy = 0;

            RenderSystem.disableDepthTest();

            blit(this.x, this.y, offx, offy, this.width, this.height, xSize, ySize);

            RenderSystem.enableDepthTest();

        }

    }

    static class BackButton extends ImageButton {
        public static int xSize = 26;
        public static int ySize = 16;

        public BackButton(int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, BACK_BUTTON, (button) -> {
                Minecraft.getInstance()
                    .displayGuiScreen(new SpellSchoolScreen(null));

            });
        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);
        }

    }

    static class SchoolButton extends ImageButton {
        public static int xSize = 22;
        public static int ySize = 161;

        SpellSchools school;

        EntityCap.UnitData data;
        PlayerSpellCap.ISpellsCap spells;

        public SchoolButton(SpellSchools school, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, new ResourceLocation(""), (button) -> {
            });

            this.school = school;
            this.data = Load.Unit(Minecraft.getInstance().player);
            this.spells = Load.spells(Minecraft.getInstance().player);

        }

        public void renderToolTip(int mouseX, int mouseY) {

            if (GuiUtils.isInRectPoints(new Point(x, y), new Point(xSize, ySize), new Point(mouseX, mouseY))) {
                TooltipInfo info = new TooltipInfo(Minecraft.getInstance().player);

                List<ITextComponent> list = new ArrayList<>();

                school.getStatsFor(spells.getAbilitiesData()
                    .getSchoolPoints(school))
                    .forEach(x -> {
                        list.addAll(x.GetTooltipString(info));
                    });

                TooltipUtils.abilityLevel(list, spells.getAbilitiesData()
                        .getSchoolPoints(school)
                    , SpellSchools.MAXIMUM_POINTS);

                GuiUtils.renderTooltip(list, mouseX, mouseY);

            }
        }

        @Override
        public boolean mouseClicked(double x, double y, int click) {
            if (this.active && this.visible && this.isHovered()) {
                if (click == 1) { // if right click
                    MMORPG.sendToServer(new TryRemoveSchoolPointPacket(school));
                } else {
                    MMORPG.sendToServer(new TryAddSchoolPointPacket(school));
                }
            }
            return super.mouseClicked(x, y, click);
        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            float filled = (float) spells.getAbilitiesData()
                .getSchoolPoints(school) / (float) SpellSchools.MAXIMUM_POINTS;

            Minecraft mc = Minecraft.getInstance();

            mc.getTextureManager()
                .bindTexture(SCHOOL_BUTTON);

            int offx = 84; // todo each school needs different offset
            int offy = 0;

            RenderSystem.disableDepthTest();
            RenderSystem.enableBlend();

            blit(this.x, this.y, 0, 0, this.width, this.height); // background

            int fillY = (int) (this.y + ((1 - filled) * (ySize - 22)));

            blit(this.x + 1, fillY, offx, offy, this.width - 1, (int) ((this.height - 22) * filled)); // the fill bar

            blit(this.x, this.y, xSize + 1, 0, this.width, this.height); // front layer

            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();

        }

    }
}
