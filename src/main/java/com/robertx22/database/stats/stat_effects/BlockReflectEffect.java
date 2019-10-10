package com.robertx22.database.stats.stat_effects;

import org.apache.logging.log4j.core.net.Priority;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.defense.BlockEffect;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;

public class BlockReflectEffect implements IStatEffect {

    public Elements element = Elements.Physical;

    public BlockReflectEffect(Elements element) {
        this.element = element;
    }

    @Override
    public int GetPriority() {
        return 30;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    int energyCost = 1;

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect && Effect.getEffectType()
                    .equals(EffectTypes.BASIC_ATTACK)) {

                DamageEffect dmgeffect = (DamageEffect) Effect;

                if (dmgeffect.isBlocked()) {

                    if (Effect.targetData.hasEnoughEnergy(energyCost)) {

                        Effect.targetData.consumeEnergy(energyCost);

                        float dmg = data.Value;

                        DamageEffect effect = new DamageEffect(null, Effect.Target, Effect.Source, (int) dmg, Effect.targetData, Effect.sourceData, EffectTypes.REFLECT, WeaponTypes.None);
                        effect.Element = stat.Element();

                        effect.Activate();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
