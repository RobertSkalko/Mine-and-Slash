package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
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

    PreCalcSpellConfigs getPreCalcConfig();

    ResourceLocation getIconLoc();

    public Type getAbilityType();

    @Nullable
    public BaseSpell getSpell();

    public default void finishTooltip(List<ITextComponent> list, SpellCastContext ctx, TooltipInfo info) {
        try {
            TooltipUtils.addEmpty(list);

            list.add(new SText(getElement().format + "Element: " + getElement().name()));

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


