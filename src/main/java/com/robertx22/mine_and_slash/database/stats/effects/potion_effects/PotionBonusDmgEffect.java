package com.robertx22.mine_and_slash.database.stats.effects.potion_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

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
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK) {

                    ResourcesData.Context ctx = new ResourcesData.Context(Effect.sourceData, Effect.source,
                                                                          ResourcesData.Type.MANA, manaCost,
                                                                          ResourcesData.Use.SPEND
                    );

                    if (dmg.sourceData.getResources().hasEnough(ctx)) {
                        dmg.sourceData.getResources().modify(ctx);

                    } else {
                        return Effect;
                    }

                    Elements element = stat.getElement();
                    dmg.addBonusEleDmgDivideByMulti(element, data.val);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
