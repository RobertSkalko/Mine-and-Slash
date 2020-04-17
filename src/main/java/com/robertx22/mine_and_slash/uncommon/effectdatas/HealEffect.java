package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnHealedSynergy;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.HealthUtils;

public class HealEffect extends EffectData {

    public ResourcesData.Context healData;

    public HealEffect(ResourcesData.Context data) {
        super(data.source, data.target, data.sourceData, data.targetData);
        this.number = data.amount;
        this.healData = data;
    }

    @Override
    protected void activate() {

        if (this.canceled) {
            return;
        }

        if (target.isAlive()) {
            this.calculateEffects();

            //this.targetData.getResources().modify(this.healData);

            if (this.activateSynergies) {
                activateSynergies();
            }

            this.target.heal(getNumber());
        }
    }

    private void activateSynergies() {

        if (this.activateSynergies) {
            if (this instanceof SpellHealEffect) {
                SpellHealEffect e = (SpellHealEffect) this;

                e.spell.getAllocatedSynergies(Load.spells(e.source))
                    .forEach(x -> {
                        if (x instanceof OnHealedSynergy) {
                            OnHealedSynergy s = (OnHealedSynergy) x;
                            s.tryActivate(e);
                        }
                    });

            }
        }
    }

    public float getNumber() {
        // increases heal amount by the amount its decreased after in an event
        return HealthUtils.DamageToMinecraftHealth(
            number / ModConfig.INSTANCE.Server.NON_MOD_HEAL_MULTI.get()
                .floatValue(), target, targetData);
    }
}
