package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.util.math.MathHelper;

public class MagicShieldEffect implements IStatEffect {

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
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                float dmgReduced = MathHelper.clamp(Effect.number, 0, Effect.targetData.getResources()
                        .getMagicShield());

                if (dmgReduced > 0) {

                    Effect.number -= dmgReduced;

                    ResourcesData.Context ctx = new ResourcesData.Context(Effect.targetData, Effect.target, ResourcesData.Type.MAGIC_SHIELD, dmgReduced, ResourcesData.Use.SPEND);

                    Effect.targetData.getResources().modify(ctx);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

