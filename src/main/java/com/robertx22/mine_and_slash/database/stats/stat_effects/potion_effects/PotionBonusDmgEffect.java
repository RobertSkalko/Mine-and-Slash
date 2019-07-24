package com.robertx22.mine_and_slash.database.stats.stat_effects.potion_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ElementalParticleUtils;

public class PotionBonusDmgEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    static float manaCost = 3F;

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK) {

                    if (dmg.sourceData.hasEnoughMana(manaCost)) {
                        dmg.sourceData.consumeMana(manaCost);

                    } else {
                        return Effect;
                    }

                    Elements element = stat.Element();
                    dmg.addBonusEleDmgDivideByMulti(element, data.Value);

                    ElementalParticleUtils.SpawnNovaParticle(element, dmg.target, 0.5F, 50);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
