package com.robertx22.mine_and_slash.potion_effects.rogue;

import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.database.stats.types.class_based.RogueStealth;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.db_lists.initializers.Synergies;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class StealthEffect extends BasePotionEffect implements IApplyStatPotion {

    private StealthEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890",
            (double) 0.25F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    public static StealthEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "stealth";
    }

    @Override
    public String locNameForLangFile() {
        return "Stealth";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public int getDurationInSeconds() {
        return 20;
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(new ExactStatData(1, StatModTypes.Flat, RogueStealth.getInstance()));
        list.add(new ExactStatData(100, StatModTypes.Flat, CriticalDamage.getInstance()));
        list.add(new ExactStatData(100, StatModTypes.Flat, CriticalHit.getInstance()));

        return list;

    }

    @Override
    public void onPotionRemove(LivingEntity target) {
        target.removePotionEffect(Effects.INVISIBILITY);
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText("Makes you invisible."));
        list.add(new SText("Dealing Damage removes the effect."));

        return list;

    }

    @Override
    public void onPotionAdd(LivingEntity caster) {

        if (Synergies.STEALTH_DISAPPEAR.has(caster)) {
            Synergies.STEALTH_DISAPPEAR.tryActivate(new CasterContext(caster));
        }

        ParticleEnum.sendToClients(
            caster,
            new ParticlePacketData(caster.getPositionVector(), ParticleEnum.NOVA)
                .radius(1F)
                .amount(30)
                .type(ParticleTypes.CLOUD));

    }

    private static class SingletonHolder {
        private static final StealthEffect INSTANCE = new StealthEffect();
    }
}


