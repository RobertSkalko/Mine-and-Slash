package com.robertx22.mine_and_slash.potion_effects.ember_mage;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BurnEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final BurnEffect INSTANCE = new BurnEffect();

    private BurnEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(20, ctx -> {
            int num = CALC.getCalculatedValue(ctx.casterData);

            DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                                                EffectData.EffectTypes.SPELL, WeaponTypes.None
            );
            dmg.element = Elements.Fire;
            dmg.removeKnockback();
            dmg.Activate();

            SoundUtils.playSound(ctx.entity, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, 0.5F, 1F);

            ParticleEnum.sendToClients(
                    ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.AOE).type(
                            ParticleTypes.FLAME).motion(new Vec3d(0, 0, 0)).amount(5));

            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.add(new StringTextComponent("Does damage:"));
            list.addAll(CALC.GetTooltipString(info));
            return list;
        }));

    }

    static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.25F, 2);

    @Override
    public int getDurationInSeconds() {
        return 15;
    }

    @Override
    public String GUID() {
        return "burn";
    }

    @Override
    public String locNameForLangFile() {
        return "Burn";
    }

    @Override
    public int getMaxStacks() {
        return 3;
    }

    public ExactStatData fire(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, ElementalResist.MAP.get(Elements.Fire)).scaleToLvl(
                extraData.casterLvl);
    }

    public ExactStatData water(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -2 * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, ElementalResist.MAP.get(Elements.Water)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(fire(data, extraData));
        list.add(water(data, extraData));

        return list;

    }

}

