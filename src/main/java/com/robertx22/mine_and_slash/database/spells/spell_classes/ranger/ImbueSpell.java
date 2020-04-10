package com.robertx22.mine_and_slash.database.spells.spell_classes.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.ranger.ImbueEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ImbueSpell extends BaseSpell {

    private ImbueSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public SpellSchools school() {
                    return SpellSchools.RANGER;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.GIVE_EFFECT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }.addsEffect(ImbueEffect.getInstance()));

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 15, 30);
        c.set(SC.BASE_VALUE, 2, 6);
        c.set(SC.ATTACK_SCALE_VALUE, 0.1F, 0.2F);
        c.set(SC.CAST_TIME_TICKS, 25, 10);
        c.set(SC.COOLDOWN_SECONDS, 45, 25);
        c.set(SC.DURATION_TICKS, 60 * 30, 60 * 45);

        c.setMaxLevel(14);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 2);
    }

    public static ImbueSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "imbue";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.buff());

        list.addAll(ImbueEffect.getInstance()
            .GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Imbue;
    }

    private static class SingletonHolder {
        private static final ImbueSpell INSTANCE = new ImbueSpell();
    }
}