package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.util.math.MathHelper;

public class MagicShieldEffect extends BaseDamageEffect {

    public static final MagicShieldEffect INSTANCE = new MagicShieldEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect modifyEffect(DamageEffect effect, StatData data, Stat stat) {
        float dmgReduced = MathHelper.clamp(effect.number, 0, effect.targetData.getResources().getMagicShield());

        if (dmgReduced > 0) {

            effect.number -= dmgReduced;

            ResourcesData.Context ctx = new ResourcesData.Context(effect.targetData, effect.target,
                                                                  ResourcesData.Type.MAGIC_SHIELD, dmgReduced,
                                                                  ResourcesData.Use.SPEND
            );

            effect.targetData.getResources().modify(ctx);

        }
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.targetData.getResources().getMagicShield() > 0;
    }

}

