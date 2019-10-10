package com.robertx22.database.stats.stat_effects.defense;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.Stat;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class BlockEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return 20;
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

                DamageSource dmgsource = DamageSource.causeMobDamage(Effect.Source);

                if (canBlockDamageSource(Effect.Target, dmgsource)) {

                    float blockval = data.Value;

                    float afterblock = Effect.Number - blockval;

                    if (afterblock < 0) {
                        dmgeffect.isFullyBlocked = true;
                    } else {
                        dmgeffect.isPartiallyBlocked = true;
                    }

                    dmgeffect.Number = afterblock;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

    private boolean canBlockDamageSource(EntityLivingBase target,
                                         DamageSource damageSourceIn) {
        if (target.isActiveItemStackBlocking()) {
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
