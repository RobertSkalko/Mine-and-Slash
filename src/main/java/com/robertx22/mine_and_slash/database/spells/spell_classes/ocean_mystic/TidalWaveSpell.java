package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.entities.proj.TidalWaveEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TidalWaveSpell extends BaseSpell {

    private TidalWaveSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public SpellSchools school() {
                    return SpellSchools.OCEAN_MYSTIC;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE;
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            }.cooldownIfCanceled(true)
                .summonsEntity(w -> new TidalWaveEntity(w)));
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 10, 15);
        c.set(SC.BASE_VALUE, 3, 5);
        c.set(SC.ATTACK_SCALE_VALUE, 0.1F, 0.3F);
        c.set(SC.SHOOT_SPEED, 0.6F, 0.9F);
        c.set(SC.PROJECTILE_COUNT, 3, 5);
        c.set(SC.CAST_TIME_TICKS, 60, 50);
        c.set(SC.COOLDOWN_SECONDS, 20, 10);
        c.set(SC.TIMES_TO_CAST, 3, 4);
        c.set(SC.DURATION_TICKS, 60, 80);

        c.setMaxLevel(16);

        return c;
    }

    public static TidalWaveSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 1);
    }

    @Override
    public String GUID() {
        return "tidal_wave";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Throw waves in a cone, damaging enemies: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.TidalWave;
    }

    private static class SingletonHolder {
        private static final TidalWaveSpell INSTANCE = new TidalWaveSpell();
    }
}
