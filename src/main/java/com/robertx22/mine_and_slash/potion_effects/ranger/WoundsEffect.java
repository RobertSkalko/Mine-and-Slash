package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class WoundsEffect extends BasePotionEffect implements IApplyStatPotion {

    private WoundsEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160895",
            (double) -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.tickActions.add(new OnTickAction(20, ctx -> {
            int num = CALC.getCalculatedValue(ctx.casterData);

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
            list.addAll(CALC.GetTooltipString(info));
            return list;
        }));

    }

    static SpellCalcData CALC = SpellCalcData.allAttackAndSpellDamages(0.02F, 0.03F, 1);

    public static WoundsEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int getDurationInSeconds() {
        return 10;
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

    public ExactStatData lessHealing(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -25 * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, HealPower.getInstance()).scaleToLvl(
            extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(lessHealing(data, extraData));

        return list;

    }

    private static class SingletonHolder {
        private static final WoundsEffect INSTANCE = new WoundsEffect();
    }
}

