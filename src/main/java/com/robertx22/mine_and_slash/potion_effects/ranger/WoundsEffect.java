package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WoundsEffect extends BasePotionEffect implements IApplyStatPotion {

    private WoundsEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160895",
            (double) -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.tickActions.add(new OnTickAction(ctx -> {
            int num = getCalc(ctx.spellsCap).getCalculatedValue(ctx.casterData, ctx.spellsCap, this);

            DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                EffectData.EffectTypes.SPELL, WeaponTypes.None
            );
            dmg.element = Elements.Elemental;
            dmg.removeKnockback();
            dmg.Activate();

            ParticleEnum.sendToClients(
                ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.AOE).type(
                    ParticleTypes.ENCHANTED_HIT)
                    .motion(new Vec3d(0, 0, 0))
                    .amount(5));

            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.add(new StringTextComponent("Does damage:"));
            list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));
            return list;
        }));

    }

    public static WoundsEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "wounds";
    }

    @Override
    public String locNameForLangFile() {
        return "Wounds";
    }

    @Override
    public int getMaxStacks() {
        return 3;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(-25, HealPower.getInstance()));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 1, 3);
        p.set(SC.TICK_RATE, 30, 20);
        p.set(SC.DURATION_TICKS, 15 * 60, 25 * 60);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return null;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.RANGER;
    }

    private static class SingletonHolder {
        private static final WoundsEffect INSTANCE = new WoundsEffect();
    }
}

