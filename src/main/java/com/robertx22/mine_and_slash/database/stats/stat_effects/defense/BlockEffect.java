package com.robertx22.mine_and_slash.database.stats.stat_effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;

public class BlockEffect implements IStatEffect {

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
                DamageEffect dmgeffect = (DamageEffect) Effect;

                DamageSource dmgsource = DamageSource.causeMobDamage(Effect.source);

                if (canBlockDamageSource(Effect.target, dmgsource)) {

                    float blockval = data.Value;

                    float afterblock = Effect.number - blockval;

                    if (afterblock < 0) {
                        dmgeffect.isFullyBlocked = true;
                    } else {
                        dmgeffect.isPartiallyBlocked = true;
                    }

                    dmgeffect.number = afterblock;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

    private boolean canBlockDamageSource(LivingEntity target,
                                         DamageSource damageSourceIn) {
        if (!damageSourceIn.isUnblockable() && target.isActiveItemStackBlocking()) {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null) {
                Vec3d vec3d1 = target.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(target.posX, target.posY, target.posZ))
                        .normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D) {
                    return true;
                }
            }
        }

        return false;
    }
}
