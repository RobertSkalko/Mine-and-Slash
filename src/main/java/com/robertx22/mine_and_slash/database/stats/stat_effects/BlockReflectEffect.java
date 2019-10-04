package com.robertx22.mine_and_slash.database.stats.stat_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
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

                    ResourcesData.Context ctx = new ResourcesData.Context(Effect.targetData, Effect.target, ResourcesData.Type.ENERGY, energyCost, ResourcesData.Use.SPEND);

                    if (Effect.targetData.getResources().hasEnough(ctx)) {

                        Effect.targetData.getResources().modify(ctx);

                        float dmg = data.Value;

                        DamageEffect effect = new DamageEffect(null, Effect.target, Effect.source, (int) dmg, Effect.targetData, Effect.sourceData, EffectTypes.REFLECT, WeaponTypes.None);
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
