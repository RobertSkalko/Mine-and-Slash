package com.robertx22.mine_and_slash.database.stats.stat_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class BlockReflectEffect implements IStatEffect {

    public Elements element = Elements.Physical;

    public BlockReflectEffect(Elements element) {
        this.element = element;
    }

    @Override
    public int GetPriority() {
        return Priority.afterThis(new BlockEffect().GetPriority());
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

                        DamageEffect effect = new DamageEffect(Effect.target, Effect.source, (int) dmg, Effect.targetData, Effect.sourceData, EffectTypes.REFLECT, WeaponTypes.None);
                        effect.element = stat.Element();

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
