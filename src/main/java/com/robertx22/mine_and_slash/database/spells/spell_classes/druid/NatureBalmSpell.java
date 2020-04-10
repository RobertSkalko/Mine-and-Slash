package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class NatureBalmSpell extends BaseSpell {

    private NatureBalmSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public SpellSchools school() {
                    return SpellSchools.DRUID;
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
                    return Elements.Nature;
                }
            }.addsEffect(RegenerateEffect.INSTANCE));

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 25, 50);
        c.set(SC.RADIUS, 2, 4);
        c.set(SC.CAST_TIME_TICKS, 30, 20);
        c.set(SC.COOLDOWN_SECONDS, 45, 25);
        c.set(SC.DURATION_TICKS, 60 * 15, 60 * 25);
        c.set(SC.TICK_RATE, 30, 20);

        c.setMaxLevel(14);
        return c;
    }

    public static NatureBalmSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "regenerate";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();
        list.add(new StringTextComponent("Applies buff: "));
        list.addAll(RegenerateEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.NatureBalm;
    }

    @Override
    public void spawnParticles(SpellCastContext ctx) {
        if (ctx.caster.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, ctx.caster, 10);
        }
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 2);
    }

    private static class SingletonHolder {
        private static final NatureBalmSpell INSTANCE = new NatureBalmSpell();
    }
}
