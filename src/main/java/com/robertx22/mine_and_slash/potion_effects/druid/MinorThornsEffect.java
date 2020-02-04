package com.robertx22.mine_and_slash.potion_effects.druid;

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
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class MinorThornsEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final MinorThornsEffect INSTANCE = new MinorThornsEffect();

    private MinorThornsEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(20, ctx -> {
            int num = CALC.getCalculatedValue(ctx.casterData);

            DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                                                EffectData.EffectTypes.SPELL, WeaponTypes.None
            );
            dmg.element = Elements.Nature;
            dmg.removeKnockback();
            dmg.Activate();

            ParticleEnum.sendToClients(
                    ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.THORNS).amount(10));

            SoundUtils.playSound(ctx.entity, SoundEvents.BLOCK_GRASS_BREAK, 1, 1);
            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.add(new StringTextComponent("Does damage:"));
            list.addAll(CALC.GetTooltipString(info));

            return list;
        }));
    }

    static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Nature), 0.1F, 2);

    @Override
    public int getDurationInSeconds() {
        return 15;
    }

    @Override
    public String GUID() {
        return "minor_thorns";
    }

    @Override
    public String locNameForLangFile() {
        return "Minor Thorns";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    public ExactStatData nature(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -2 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Nature)).scaleToLvl(
                extraData.casterLvl);
    }

    public ExactStatData thunder(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -3 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Thunder)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(thunder(data, extraData));
        list.add(nature(data, extraData));

        return list;

    }

}

