package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeHealContext;
import com.robertx22.mine_and_slash.db_lists.initializers.Synergies;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HeartOfIceSpell extends BaseSpell {

    private HeartOfIceSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public SpellSchools school() {
                    return SpellSchools.OCEAN_MYSTIC;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SELF_HEAL;
                }

                @Override
                public SoundEvent sound() {
                    return ModSounds.FREEZE.get();
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            });
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 15, 45);
        c.set(SC.BASE_VALUE, 5, 20);
        c.set(SC.SHOOT_SPEED, 0.6F, 0.9F);
        c.set(SC.CAST_TIME_TICKS, 30, 15);
        c.set(SC.COOLDOWN_SECONDS, 60, 30);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 1);
    }

    public static HeartOfIceSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "heart_of_ice";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Restores health to caster:"));

        list.addAll(getCalculation(ctx).GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.HeartOfIce;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        try {
            World world = caster.world;

            if (!world.isRemote) {

                SoundUtils.playSound(caster, SoundEvents.MUSIC_UNDER_WATER, 1, 1);

                UnitData data = Load.Unit(caster);

                if (Synergies.HEART_OF_ICE_FROST.has(caster)) {
                    Synergies.HEART_OF_ICE_FROST.tryActivate(new BeforeHealContext(caster, caster, heal));
                }

                if (Synergies.HEART_OF_ICE_MAGIC_SHIELD.has(caster)) {
                    Synergies.HEART_OF_ICE_MAGIC_SHIELD.tryActivate(new BeforeHealContext(caster, caster, heal));
                }

                heal.Activate();

                SoundUtils.playSound(caster, ModSounds.FREEZE.get(), 1, 1);

                ParticleUtils.spawnParticles(ParticleRegister.BUBBLE, caster, 25);

            } else {
                SoundUtils.playSound(caster, ModSounds.FREEZE.get(), 1, 1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class SingletonHolder {
        private static final HeartOfIceSpell INSTANCE = new HeartOfIceSpell();
    }
}
