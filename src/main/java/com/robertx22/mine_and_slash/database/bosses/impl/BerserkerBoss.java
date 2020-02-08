package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.base.BossData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.cleric.RighteousFuryEffect;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;

public class BerserkerBoss extends Boss {

    private BerserkerBoss() {
    }

    public static BerserkerBoss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IParticleData getParticle() {
        return ParticleTypes.WITCH;
    }

    @Override
    public ITextComponent getName() {
        return new SText("Berserker");
    }

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold t) {
        if (t == BossData.HealthTreshhold.T_75) {
            en.addPotionEffect(new EffectInstance(Effects.SPEED, 5000, 1));
        } else if (t == BossData.HealthTreshhold.T_50) {
            PotionEffectUtils.applyToSelf(RighteousFuryEffect.INSTANCE, en);
        } else if (t == BossData.HealthTreshhold.T_25) {
            PotionEffectUtils.applyToSelf(RighteousFuryEffect.INSTANCE, en);
            en.addPotionEffect(new EffectInstance(Effects.SPEED, 4000, 2));
        } else if (t == BossData.HealthTreshhold.T_10) {
            PotionEffectUtils.applyToSelf(RighteousFuryEffect.INSTANCE, en);
            en.addPotionEffect(new EffectInstance(Effects.SPEED, 3000, 3));

        }
    }

    @Override
    public String GUID() {
        return "berserker";
    }

    private static class SingletonHolder {
        private static final BerserkerBoss INSTANCE = new BerserkerBoss();
    }
}
