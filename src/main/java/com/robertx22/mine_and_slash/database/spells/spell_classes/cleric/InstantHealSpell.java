package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SetupPreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class InstantHealSpell extends BaseSpell {

    public InstantHealSpell() {

        super(
            new ImmutableSpellConfigs() {
                @Override
                public SpellSchools school() {
                    return SpellSchools.CLERIC;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SELF_HEAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_GENERIC_DRINK;
                }

                @Override
                public int maxSpellLevel() {
                    return 16;
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }
            ,
            new SetupPreCalcSpellConfigs() {

                @Override
                public float manaCost() {
                    return 30;
                }

                @Override
                public LevelBased baseValue() {
                    return new LevelBased(5, 20);
                }

                @Override
                public LevelBased castTimeTicks() {
                    return new LevelBased(0, 0);
                }

                @Override
                public LevelBased cooldownTicks() {
                    return new LevelBased(45, 30);
                }
            }
        );

    }

    @Override
    public String GUID() {
        return "instant_heal";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Heals the user: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.InstantHeal;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        try {
            LivingEntity caster = ctx.caster;
            World world = caster.world;

            if (!world.isRemote()) {

                UnitData data = Load.Unit(caster);

                if (Synergies.INSTANT_HEAL_REMOVE_DEBUFF.has(caster)) {
                    Synergies.INSTANT_HEAL_REMOVE_DEBUFF.tryActivate(new CasterTargetContext(caster, caster));
                }

                if (Synergies.INSTANT_HEAL_MAGIC_SHIELD.has(caster)) {
                    Synergies.INSTANT_HEAL_MAGIC_SHIELD.tryActivate(new CasterTargetContext(caster, caster));
                }

                ParticleUtils.spawnHealParticles(caster, 10);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
