package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public interface IAbility extends IGUID, ITooltipList {
    public enum Type {
        SPELL, SYNERGY, EFFECT
    }

    public static List<IAbility> getAll() {

        List<IAbility> list = new ArrayList<>();

        list.addAll(SlashRegistry.Synergies()
            .getList());
        list.addAll(SlashRegistry.Spells()
            .getList());
        list.addAll(SlashRegistry.PotionEffects()
            .getList());

        return list;

    }

    public Elements getElement();

    public static IAbility fromId(String id) {
        IAbility ability = null;

        if (SlashRegistry.Synergies()
            .isRegistered(id)) {
            ability = SlashRegistry.Synergies()
                .get(id);
        } else if (SlashRegistry.Spells()
            .isRegistered(id)) {
            ability = SlashRegistry.Spells()
                .get(id);
        } else if (SlashRegistry.PotionEffects()
            .isRegistered(id)) {
            ability = SlashRegistry.PotionEffects()
                .get(id);
        }

        return ability;
    }

    ITextComponent getLocName();

    public default int getEffectiveAbilityLevel(PlayerSpellCap.ISpellsCap spells) {

        int spellLvl = spells.getLevelOf(this);

        if (spellLvl == 0) {
            return 0;
        }
        if (spellLvl == 1) {
            return 1;
        }

        return (int) (((float) spellLvl / (float) getMaxSpellLevelBuffed()) * ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get());
    }

    PreCalcSpellConfigs getPreCalcConfig();

    ResourceLocation getIconLoc();

    public Type getAbilityType();

    public default float getLevelPowerMulti(PlayerSpellCap.ISpellsCap cap) {
        return (float) cap.getLevelOf(this) / (float) getMaxSpellLevelBuffed();
    }

    public int getMaxSpellLevelNormal();

    public int getMaxSpellLevelBuffed();

    public AbilityPlace getAbilityPlace();

    @Nullable
    public BaseSpell getSpell();

    @Nullable
    public IAbility getRequiredAbility();

    public Masteries getMastery();

    public default int getSchoolPointsNeeded() {

        AbilityPlace place = getAbilityPlace();

        if (place == null) {
            if (this.getAbilityType()
                .equals(Type.EFFECT)) {
                return 10; //
            } else {
                throw new RuntimeException("Only effects are allowed to have null ability place, not: " + this.GUID());
            }
        }

        if (place.y == 0) {
            return 1;
        }
        if (place.y == 1) {
            return 5;
        }
        if (place.y == 2) {
            return 10;
        }
        if (place.y == 3) {
            return 20;
        }
        if (place.y == 4) {
            return 30;
        }
        if (place.y == 5) {
            return 40;
        }
        if (place.y == 6) {
            return Masteries.MAXIMUM_POINTS;
        }

        return Integer.MAX_VALUE;
    }

    public default void finishTooltip(List<ITextComponent> list, SpellCastContext ctx, TooltipInfo info) {
        try {
            TooltipUtils.addEmpty(list);

            TooltipUtils.abilityLevel(list, ctx.spellsCap.getLevelOf(this), getMaxSpellLevelNormal());

            list.add(new SText(TextFormatting.YELLOW + "Effective Ability level: " + getEffectiveAbilityLevel(ctx.spellsCap)));

            list.add(new SText(getElement().format + "Element: " + getElement().name()));

            if (ctx.spellsCap.getAbilitiesData()
                .getSchoolPoints(this.getMastery()) < getSchoolPointsNeeded()) {
                list.add(new SText(TextFormatting.RED + "Needs ").appendSibling(getMastery().getFullName()
                    .appendText(" of Level: " + getSchoolPointsNeeded())));
            }

            list.add(new SText(""));

            if (!Screen.hasShiftDown()) {
                list.add(new SText(TextFormatting.BLUE + "").appendSibling(Words.Press_Shift_For_More_Info.locName()));
            } else {
                list.add(new SText(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.BOLD).appendText("Ability Stats:"));

                list.addAll(ctx.getConfigFor(this)
                    .GetTooltipString(info, ctx));

                if (this instanceof Synergy) {
                    Synergy s = (Synergy) this;

                    list.add(new SText(""));
                    list.add(new SText(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.BOLD + "Affects Spell: "));

                    list.addAll(s.getConfigsAffectingSpell()
                        .GetTooltipString(info, ctx));
                }

            }
            TooltipUtils.removeDoubleBlankLines(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


