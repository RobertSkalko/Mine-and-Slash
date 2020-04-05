package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SetupPreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateSpell extends BaseSpell {

    private RegenerateSpell() {
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
                public int maxSpellLevel() {
                    return 14;
                }

                @Override
                public BasePotionEffect potionEffect() {
                    return RegenerateEffect.INSTANCE;
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }
            },
            new SetupPreCalcSpellConfigs() {

                @Override
                public LevelBased radius() {
                    return new LevelBased(2, 4);
                }

                @Override
                public LevelBased manaCost() {
                    return new LevelBased(25, 50);
                }

                @Override
                public LevelBased baseValue() {
                    return new LevelBased(2, 6);
                }

                @Override
                public LevelBased castTimeTicks() {
                    return new LevelBased(30, 20);
                }

                @Override
                public LevelBased cooldownTicks() {
                    return new LevelBased(45, 25);
                }
            });

    }

    public static RegenerateSpell getInstance() {
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
        list.addAll(RegenerateEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        LivingEntity caster = ctx.caster;

        boolean bool = super.cast(ctx);

        if (bool) {
            if (Synergies.REGEN_AOE.has(caster)) {
                Synergies.REGEN_AOE.tryActivate(new CasterContext(caster));
            }
            if (Synergies.REGEN_THORNS.has(caster)) {
                Synergies.REGEN_THORNS.tryActivate(new CasterContext(caster));
            }
        }

    }

    @Override
    public Words getName() {
        return Words.Regeneration;
    }

    @Override
    public void spawnParticles(SpellCastContext ctx) {
        if (ctx.caster.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, ctx.caster, 10);
        }
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 2);
    }

    private static class SingletonHolder {
        private static final RegenerateSpell INSTANCE = new RegenerateSpell();
    }
}
