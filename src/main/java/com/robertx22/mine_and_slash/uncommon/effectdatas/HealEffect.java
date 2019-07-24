package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.HealthUtils;

public class HealEffect extends EffectData {

    public HealData healData;

    public HealEffect(HealData data) {

        super(data.source, data.target, data.sourceData, data.targetData);

        this.number = data.number;
        this.healData = data;
    }

    @Override
    protected void activate() {

        if (this.canceled) {
            return;
        }

        this.target.heal(getNumber());

    }

    public float getNumber() {
        // increases heal amount by the amount its decreased after in an event
        return HealthUtils.DamageToMinecraftHealth(number / ModConfig.INSTANCE.Server.NON_MOD_HEAL_MULTI
                .get()
                .floatValue(), target);
    }
}
