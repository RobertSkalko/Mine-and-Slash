package com.robertx22.mine_and_slash.database.spells.spell_classes.fire;

import com.robertx22.mine_and_slash.database.spells.entities.proj.ThrowFlameEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThrowFlamesSpell extends BaseSpell {

    private ThrowFlamesSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.FIRE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_FIRE_EXTINGUISH;
                }

                @Override
                public Elements element() {
                    return Elements.Fire;
                }
            }.cooldownIfCanceled(true)
                .summonsEntity(w -> new ThrowFlameEntity(w))
                .setSwingArmOnCast());
    }

    public static ThrowFlamesSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 10, 15);
        c.set(SC.BASE_VALUE, 2, 8);
        c.set(SC.ATTACK_SCALE_VALUE, 0.3F, 0.5F);
        c.set(SC.SHOOT_SPEED, 0.45F, 0.75F);
        c.set(SC.PROJECTILE_COUNT, 3, 3);
        c.set(SC.CAST_TIME_TICKS, 60, 50);
        c.set(SC.COOLDOWN_SECONDS, 20, 10);
        c.set(SC.DURATION_TICKS, 100, 120);
        c.set(SC.TIMES_TO_CAST, 3, 3);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 1);
    }

    @Override
    public String GUID() {
        return "throw_flames";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Throw out slow flames: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThrowFlames;
    }

    private static class SingletonHolder {
        private static final ThrowFlamesSpell INSTANCE = new ThrowFlamesSpell();
    }
}

