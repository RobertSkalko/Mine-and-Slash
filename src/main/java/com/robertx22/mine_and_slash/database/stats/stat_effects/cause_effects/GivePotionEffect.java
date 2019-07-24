package com.robertx22.mine_and_slash.database.stats.stat_effects.cause_effects;

import com.robertx22.mine_and_slash.database.stats.stat_effects.OnCauseDoEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class GivePotionEffect extends BaseCauseEffect {

    private Effect potion;
    private int durationInSeconds;
    private int amplifier = 1;

    public GivePotionEffect(Effect potion, int durationInSeconds) {
        this.potion = potion;
        this.durationInSeconds = durationInSeconds;

    }

    public GivePotionEffect setAmplifier(int amp) {
        this.amplifier = amp;
        return this;
    }

    @Override
    public void activate(OnCauseDoEffect oncause, EffectData effect) {

        LivingEntity entity;

        if (oncause.whoGetsEffect.equals(IStatEffect.EffectSides.Source)) {
            entity = effect.source;

        } else {
            entity = effect.target;
        }

        entity.addPotionEffect(new EffectInstance(potion, durationInSeconds * 20, amplifier));

    }
}
